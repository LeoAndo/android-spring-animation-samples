package com.example.basicappsample.widget;

import static com.example.basicappsample.helpers.AnimationHelper.createSpringAnimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import java.util.concurrent.atomic.AtomicReference;

public class SpringRotationView extends AppCompatImageView {
    private final SpringAnimation rotationAnim;
    private static final float STIFFNESS = SpringForce.STIFFNESS_LOW;
    private static final float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;
    private OnSpringAnimationEndListener listener;


    public SpringRotationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public SpringRotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        rotationAnim = createSpringAnimation(
                this, SpringAnimation.ROTATION, 0f, STIFFNESS, DAMPING_RATIO);

        // Registering the update listener
        rotationAnim.addEndListener((animation, canceled, value, velocity) -> {
            Log.d("xAnim", "canceled: " + canceled + " value: " + value + " velocity: " + velocity);
            if (listener != null) {
                listener.onAnimationEnd(canceled);
            }
        });

        AtomicReference<Float> currentRotation = new AtomicReference<>(0.0f);
        this.setOnTouchListener((view, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    rotationAnim.cancel();
                    currentRotation.set(rotationAmount(view, event.getX(), event.getY()));
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float previousRotation = currentRotation.get();
                    currentRotation.set(rotationAmount(view, event.getX(), event.getY()));
                    view.setRotation(currentRotation.get() - previousRotation);
                    break;
                case MotionEvent.ACTION_UP:
                    rotationAnim.start();
                    break;
            }
            return true;
        });
    }

    public void addEndListener(OnSpringAnimationEndListener listener) {
        this.listener = listener;
    }

    public interface OnSpringAnimationEndListener {
        void onAnimationEnd(boolean canceled);
    }

    private float rotationAmount(View view, float dx, float dy) {
        return (float) (view.getRotation() + Math.toDegrees(Math.atan2(dx - view.getWidth() / 2.0, view.getHeight() / 2.0 - dy)));
    }
}
