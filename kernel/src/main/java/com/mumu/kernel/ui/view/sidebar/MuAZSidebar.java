package com.mumu.kernel.ui.view.sidebar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mumu.kernel.R;

import java.util.Arrays;
import java.util.List;

/**
 * to be a better man.
 *
 * @author nullWolf
 * @date 2019/11/8
 * <p>
 * 从A到Z的侧边栏
 */
public class MuAZSidebar extends View {
    private static final String TAG = "WaveSideBarView";

    // 触摸接口listener
    private OnTouchLetterChangeListener listener;

    // 字母表合集
    private List<String> mLetters;

    // 当前选中的位置
    private int mChoose = -1;

    // 字母列表画笔
    private Paint textPaint = new Paint();
    // 提示字母画笔
    private Paint chooseTextPaint = new Paint();
    // 圆形画笔
    private Paint ciclePaint = new Paint();
    //默认字体大小
    private float mTextSize;
    //选中显示字体的大小
    private float chooseShowTextSize;
    //默认字体颜色
    private int mTextColor;
    //选中字体显示大字的颜色
    private int chooseShowTextColor;
    //选中字体的小字颜色
    private int chooseTextColor;
    //圆形的背景颜色
    private int circleBgColor;

    private int mWidth;
    private int mHeight;
    private int mItemHeight;
    private int mPadding;

    // 波浪路径
    private Path mWavePath = new Path();

    // 圆形路径
    private Path mBallPath = new Path();

    // 手指滑动的Y点作为中心点
    private float mCenterY; //中心点Y

    // 贝塞尔曲线的分布半径
    private int mRadius;

    // 圆形半径
    private int mBallRadius;
    // 用于过渡效果计算
    ValueAnimator mRatioAnimator;

    // 用于绘制贝塞尔曲线的比率
    private float mRatio;

    // 选中字体的坐标
    private float mPosX, mPosY;

    // 圆形中心点X
    private float mBallCentreX;

    public MuAZSidebar(Context context) {
        this(context, null);
    }

    public MuAZSidebar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MuAZSidebar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //字母合集
        mLetters = Arrays.asList(context.getResources().getStringArray(R.array.MuAZSideBarLetters));

        //默认字体颜色
        mTextColor = Color.parseColor("#969696");
        //选中字体小字颜色
        chooseTextColor = context.getResources().getColor(R.color.colorAccent);
        //选中字体大字颜色
        chooseShowTextColor = context.getResources().getColor(R.color.white);
        //圆形背景颜色
        circleBgColor = context.getResources().getColor(R.color.blue);

        //默认字体小字大小
        mTextSize = context.getResources().getDimensionPixelSize(R.dimen.mu_textSize_text_sidebar);
        //选中字体大字大小
        chooseShowTextSize = context.getResources().getDimensionPixelSize(R.dimen.mu_textSize_text_choose_show_sidebar);

        //边距
        mPadding = context.getResources().getDimensionPixelSize(R.dimen.mu_textSize_sidebar_padding);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MuAZSideBar);
            mTextColor = a.getColor(R.styleable.MuAZSideBar_sidebarTextColor, mTextColor);//默认字体颜色
            chooseTextColor = a.getColor(R.styleable.MuAZSideBar_sidebarChooseTextColor, chooseTextColor);//选中字体小字颜色
            chooseShowTextColor = a.getColor(R.styleable.MuAZSideBar_sidebarChooseShowTextColor, chooseShowTextColor); //选中字体大字颜色
            circleBgColor = a.getColor(R.styleable.MuAZSideBar_sidebarCircleBgColor, circleBgColor); //圆形背景颜色

            mTextSize = a.getFloat(R.styleable.MuAZSideBar_sidebarTextSize, mTextSize);//默认字体小字大小
            chooseShowTextSize = a.getFloat(R.styleable.MuAZSideBar_sidebarChooseShowTextSize, chooseShowTextSize);//选中字体大字大小

            mRadius = a.getInt(R.styleable.MuAZSideBar_sidebarRadius, context.getResources().getDimensionPixelSize(R.dimen.mu_radius_sidebar));
            mBallRadius = a.getInt(R.styleable.MuAZSideBar_sidebarBallRadius, context.getResources().getDimensionPixelSize(R.dimen.mu_ball_radius_sidebar));
            a.recycle();
        }

        //画圆的画笔
        ciclePaint = new Paint();
        ciclePaint.setAntiAlias(true);
        ciclePaint.setColor(circleBgColor);
        ciclePaint.setStyle(Paint.Style.FILL);

        //选中的字的画笔
        chooseTextPaint.setAntiAlias(true);
        chooseTextPaint.setColor(chooseShowTextColor);
        chooseTextPaint.setStyle(Paint.Style.FILL);
        chooseTextPaint.setTextSize(chooseShowTextSize);
        chooseTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final float y = event.getY();
        final float x = event.getX();

        final int oldChoose = mChoose;
        final int newChoose = (int) (y / mHeight * mLetters.size());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                if (x < mWidth - 2 * mRadius) {
                    return false;
                }
                startAnimator(mRatio, 1.0f);
                break;
            case MotionEvent.ACTION_MOVE://滑动
                mCenterY = y;
                //只有在特定情况下才会进行刷新invalidate，避免滑出屏幕
                if (oldChoose != newChoose) {
                    if (newChoose >= 0 && newChoose < mLetters.size()) {
                        mChoose = newChoose;
                        if (listener != null) {
                            listener.onLetterChange(mLetters.get(newChoose));
                        }
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL://取消和抬起
            case MotionEvent.ACTION_UP:
                startAnimator(mRatio, 0f);
                mChoose = -1;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mItemHeight = (mHeight - mPadding) / mLetters.size();
        mPosX = mWidth - 1.6f * mTextSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制字母列表
        drawLetters(canvas);

        //绘制圆
        drawBallPath(canvas);

        //绘制选中的字体
        drawChooseText(canvas);

    }

    //绘制小的字母列表
    private void drawLetters(Canvas canvas) {
        for (int i = 0; i < mLetters.size(); i++) {
            textPaint.reset();
            textPaint.setColor(mTextColor);
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(mTextSize);
            textPaint.setTextAlign(Paint.Align.CENTER);

            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float baseline = Math.abs(-fontMetrics.bottom - fontMetrics.top);

            float posY = mItemHeight * i + baseline / 2 + mPadding;

            if (i == mChoose) {
                mPosY = posY;
            } else {
                canvas.drawText(mLetters.get(i), mPosX, posY, textPaint);
            }
        }
    }

    //绘制选中时候的字体
    private void drawChooseText(Canvas canvas) {
        if (mChoose != -1) {
            // 绘制右侧选中字符
            textPaint.reset();
            textPaint.setColor(chooseTextColor);
            textPaint.setTextSize(mTextSize);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(mLetters.get(mChoose), mPosX, mPosY, textPaint);

            // 绘制提示字符
            if (mRatio >= 0.9f) {
                String target = mLetters.get(mChoose);
                Paint.FontMetrics fontMetrics = chooseTextPaint.getFontMetrics();
                float baseline = Math.abs(-fontMetrics.bottom - fontMetrics.top);
                float x = mBallCentreX;
                float y = mCenterY + baseline / 2;
                canvas.drawText(target, x, y, chooseTextPaint);
            }
        }
    }

    //绘制圆
    private void drawBallPath(Canvas canvas) {
        //x轴的移动路径
        mBallCentreX = (mWidth + mBallRadius) - (2.0f * mRadius + 2.0f * mBallRadius) * mRatio;
        mBallPath.reset();
        mBallPath.addCircle(mBallCentreX, mCenterY, mBallRadius, Path.Direction.CW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mBallPath.op(mWavePath, Path.Op.DIFFERENCE);
        }
        mBallPath.close();
        canvas.drawPath(mBallPath, ciclePaint);
    }


    //开启动画
    private void startAnimator(float... value) {
        if (mRatioAnimator == null) {
            mRatioAnimator = new ValueAnimator();
        }
        mRatioAnimator.cancel();
        mRatioAnimator.setFloatValues(value);
        mRatioAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator value) {
                mRatio = (float) value.getAnimatedValue();
                invalidate();
            }
        });
        mRatioAnimator.start();
    }


    /**
     * 设置字母合集
     *
     * @param letters
     */
    public void setLetters(List<String> letters) {
        this.mLetters = letters;
        invalidate();
    }

    /**
     * 触摸监听 外部调用的
     * @param listener
     */
    public void setOnTouchLetterChangeListener(OnTouchLetterChangeListener listener) {
        this.listener = listener;
    }

    public interface OnTouchLetterChangeListener {
        void onLetterChange(String letter);
    }
}
