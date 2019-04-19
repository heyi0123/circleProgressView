package com.zkht.studydemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zkht.studydemo.R;

import java.math.BigDecimal;

/**
 * @author 学无止境 ~ 2019/4/17 ~ 13:40
 */
public class CircleView extends View {

    private Paint circlePaint;//中心圆画笔
    private Paint annularPaint;//圆环画笔
    private int circleColor;//中心圆颜色
    private int annularColor;//环形颜色
    private float circleRadius;//中心圆半径
    private float annularRadius;//环形半径
    private float annularWidth;//环形宽度
    private float startAngle;//起始角度
    private float endAngle;//结束角度
    private float x;
    private float y;
    private Paint arcPaint;//圆弧画笔
    private int arcColor;//圆弧颜色
    private Paint textPaint;//文字画笔
    private float percentText;//文字内容
    private float textSize;//文字大小

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint(context, attrs, defStyleAttr);
    }

    private void initPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.myCircleView);
        circleColor = mTypeArray.getColor(R.styleable.myCircleView_circleColor, 0);
        circleRadius = mTypeArray.getDimension(R.styleable.myCircleView_circleRadius, 0);
        annularColor = mTypeArray.getColor(R.styleable.myCircleView_annularColor, 0);
        annularRadius = mTypeArray.getDimension(R.styleable.myCircleView_annularRadius, 0);
        annularWidth = mTypeArray.getDimension(R.styleable.myCircleView_annularWidth, 0);
        startAngle = mTypeArray.getInteger(R.styleable.myCircleView_startAngle, 0);
        endAngle = mTypeArray.getInteger(R.styleable.myCircleView_endAngle, 0);
        arcColor = mTypeArray.getColor(R.styleable.myCircleView_arcColor, 0);
        percentText = mTypeArray.getInteger(R.styleable.myCircleView_percent, 0);
        textSize = mTypeArray.getDimension(R.styleable.myCircleView_textSize, 0);
        mTypeArray.recycle();

        //中心圆
        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.FILL);

        //外环
        annularPaint = new Paint();
        annularPaint.setColor(annularColor);
        annularPaint.setStyle(Paint.Style.STROKE);
        annularPaint.setStrokeWidth(annularWidth);

        //圆弧
        arcPaint = new Paint();
        arcPaint.setColor(arcColor);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(annularWidth);

        //文字
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //文字位置及转换
        float textBaseY = y + (Math.abs(textPaint.ascent()) - textPaint.descent()) / 2;
        String text = String.format("%1.2f", percentText)+"%";

        endAngle = 360 * (percentText / 100);

        canvas.drawCircle(x, y, circleRadius, circlePaint);
        canvas.drawCircle(x, y, annularRadius, annularPaint);
        RectF oval = new RectF(x - annularRadius, y - annularRadius, x + annularRadius, y + annularRadius);
        canvas.drawArc(oval, startAngle, endAngle, false, arcPaint);
        canvas.drawText(text, x, textBaseY, textPaint);
    }

    public void setText(float text) {
        percentText = text;
        //重绘
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("WHY", "onMeasure");
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = 400;
        int mHeight = 400;

        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("WHY", "onLayout");
        x = getWidth() / 2;
        y = getHeight() / 2;
        Log.e("WHY", "x=" + x + "~~~y=" + y);
    }

}
