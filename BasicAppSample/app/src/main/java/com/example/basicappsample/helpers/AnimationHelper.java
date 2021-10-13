package com.example.basicappsample.helpers;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.transition.TransitionManager;

import com.google.android.material.transition.MaterialFade;

public final class AnimationHelper {
    private AnimationHelper() {
    }

    /**
     * 点滅アニメーションを行う.
     *
     * @param view アニメーションを適用させるView
     */
    public static void startAlphaAnimation(View view) {
        final float fromAlpha = 0.0f, toAlpha = 1.0f;
        final long durationMillis = 800, startOffset = 1000;
        Animation animation = new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(durationMillis);
        animation.setStartOffset(startOffset);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }

    /**
     * Fade In / Fade outアニメーションを行う
     *
     * @param isVisible true: 表示 false: 非表示
     * @param vg ConstraintLayoutやLinearLayoutなどのレイアウトクラスのインスタンス
     * @param targetView アニメーションを適用させるView
     */
    public static void startFadeAnimation(boolean isVisible, ViewGroup vg, View targetView) {
        MaterialFade fade = new MaterialFade();
        fade.setDuration(500L);
        TransitionManager.beginDelayedTransition(vg, fade);
        int visibility = (isVisible) ? View.VISIBLE : View.INVISIBLE;
        targetView.setVisibility(visibility);
    }

    /**
     * Springアニメーション(バネ)の生成
     *
     * @param view          アニメーションされるView
     * @param property      {@link DynamicAnimation.ViewProperty} を指定する
     * @param finalPosition スプリングの最終位置. これは、アニメーションの開始前に設定する必要がある。
     * @param stiffness     ばねの非負の剛性定数
     * @param dampingRatio  ばねの減衰比、非負でなければいけない(0.0以上を指定すること)
     * @return {@link SpringAnimation}インスタンス
     */
    public static SpringAnimation createSpringAnimation(
            View view,
            DynamicAnimation.ViewProperty property,
            Float finalPosition,
            Float stiffness,
            Float dampingRatio) {
        final SpringAnimation animation = new SpringAnimation(view, property);
        final SpringForce spring = new SpringForce(finalPosition);
        spring.setStiffness(stiffness);
        spring.setDampingRatio(dampingRatio);
        animation.setSpring(spring);
        return animation;
    }
}
