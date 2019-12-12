package com.meiji.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Interpolator;

import com.meiji.yangshijie.login.User_selectionActivity;

public class press_anim_util {
    /**
     *  描述：点击动画
     *  时间：2018/8/15 15:30
     **/
    public static void onClickanimation(View view){


//        ObjectAnimator animatorX = ObjectAnimator.ofFloat(gameImL1,"scaleX",1.0f,1.8f);
//        ObjectAnimator animatorY = ObjectAnimator.ofFloat(gameImL1,"scaleY",1.0f,1.8f);
//        AnimatorSet set =new AnimatorSet();
//        set.setDuration(1000);
//        set.playTogether(animatorX,animatorY);
//        set.start();


        //ObjectAnimator animator0 = ObjectAnimator.ofFloat(view,"scaleX",0.9f,1.0f);
        //ObjectAnimator animator1 = ObjectAnimator.ofFloat(view,"scaleY",0.9f,1.0f);
        //AnimatorSet set =new AnimatorSet();
        // set.setDuration(10000);
        //set.setInterpolator(new SpringScaleInterpolator(0.6f));
        // set.playTogether(animator0,animator1);
        // set.start();


        //创建两个对象动画的实例和将这个实例组合起来的组合对象实例
        ObjectAnimator objectAnimator0 = null;
        ObjectAnimator objectAnimator1 = null;
        AnimatorSet animatorSet = new AnimatorSet();

//指定修改view的哪个属性及属性的起始值和结束值
        objectAnimator0 = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1.0f);
        objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1.0f);

//指定两个对象动画的执行顺序
        animatorSet.playTogether(objectAnimator0, objectAnimator1);
//指定两个动画组合之后的执行时间
        animatorSet.setDuration(2500);
//指定插值器
        animatorSet.setInterpolator(new SpringScaleInterpolator(0.6f));
//启动动画
        animatorSet.start();





    }

    /**
     *  描述：弹性动画
     *  时间：2018/8/15 15:34
     **/
   static class SpringScaleInterpolator implements Interpolator {
        //弹性因数
        private float factor;

        public SpringScaleInterpolator(float factor) {
            this.factor = factor;
        }

        @Override
        public float getInterpolation(float input) {

            return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);//弹性算法
        }


    }
}
