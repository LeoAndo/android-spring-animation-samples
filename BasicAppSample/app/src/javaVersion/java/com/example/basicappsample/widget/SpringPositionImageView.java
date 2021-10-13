package com.example.basicappsample.widget;

import static com.example.basicappsample.helpers.AnimationHelper.createSpringAnimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class SpringPositionImageView extends AppCompatImageView {
    private SpringAnimation xAnim;
    private SpringAnimation yAnim;
    private static final float STIFFNESS = SpringForce.STIFFNESS_LOW;
    private static final float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;

    public SpringPositionImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public SpringPositionImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final AppCompatImageView img = this;
        // ビューの初期位置が確定したら、X軸およびY軸アニメーションを作成する
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xAnim = createSpringAnimation(
                        img, SpringAnimation.X, img.getX(), STIFFNESS, DAMPING_RATIO);
                yAnim = createSpringAnimation(
                        img, SpringAnimation.Y, img.getY(), STIFFNESS, DAMPING_RATIO);
                img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        final float[] dX = new float[1];
        final float[] dY = new float[1];
        this.setOnTouchListener((view, event) -> {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    // ビューの左上隅とタッチポイントの差をキャプチャする
                    dX[0] = view.getX() - event.getRawX();
                    dY[0] = view.getY() - event.getRawY();
                    // アニメーションをキャンセルして、前のアニメーション中にビューを取得できるようにする
                    xAnim.cancel();
                    yAnim.cancel();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //  ViewのLayoutParamsを変更する.
                    img.animate()
                            .x(event.getRawX() + dX[0])
                            .y(event.getRawY() + dY[0])
                            .setDuration(0)
                            .start();
                    break;
                case MotionEvent.ACTION_UP:
                    xAnim.start();
                    yAnim.start();
                    break;
            }
            return true;
        });
    }
}
