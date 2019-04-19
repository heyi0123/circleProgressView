package com.zkht.studydemo.ScrollZoomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

import com.zkht.studydemo.R;


/**
 * Created by Android1 on 2017/7/28.
 */

public class SlideZoomListView extends ListView {

    private int mImageViewHeight;//图片初始高度
    private ImageView mImageView;//缩放的图片

    public SlideZoomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mImageViewHeight = context.getResources().getDimensionPixelSize(R.dimen.size_default_height);
    }

    public void setZoomImageView(ImageView iv) {
        mImageView = iv;//设置图片
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //下拉过度的回调 dy
        /**
         * 两种情况:拉动的距离
         * deltaY： -  往下拉过度
         * deltaY： + 往上拉过度
         **/
        if (deltaY < 0) {//下拉过度
            //mImageView进行放大的效果--修改mImageView的高度  scaleType==centerCrop
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        } else {
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //上推图片缩小
        View header = (View) mImageView.getParent();
        //ListView会划出去的高度(负数)
        int deltaY = header.getTop();
        //只有当mImageView被放大过，这里才会执行缩小
        if (mImageView.getHeight() > mImageViewHeight) {
            mImageView.getLayoutParams().height = mImageView.getHeight() + deltaY;
            header.layout(header.getLeft(), 0, header.getRight(), header.getHeight());
            mImageView.requestLayout();
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    //松手弹回原来位置
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP) {
            //恢复到原来的高度-渐变动画
            ResetAnimation resetAnimation = new ResetAnimation();
            resetAnimation.setDuration(1000);
            mImageView.startAnimation(resetAnimation);
        }
        return super.onTouchEvent(ev);
    }

    //自定义渐变动画
    public class ResetAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            //动画执行百分比，interpolatedTime：0f~1f
            /**
             * 比例关系
             * 0 ~ 1
             * 当前高度 ~ 初始高度
             * 总的deltaY  (就是变化的高度差)*interpolatedTime
             * */
            mImageView.getLayoutParams().height = (int) (mImageView.getHeight() - (mImageView.getHeight() - mImageViewHeight) * interpolatedTime);
            mImageView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }
}
