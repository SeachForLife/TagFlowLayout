package com.loren_yang.taglayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Loren Yang on 2017/7/14.
 */
public class RandomTextView extends View {

    private int mColor= Color.RED;
    private Rect mBound;
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private String mText="RecycleView";
    private int mSize=60;
    private int padding=5;//让文字在视图区 padding值为5的范围内随机移动

    private int width;
    private int height;

    private int[] colorVa={Color.RED,Color.BLACK,Color.BLUE,Color.GREEN,Color.GRAY,Color.YELLOW,Color.MAGENTA};

    public RandomTextView(Context context) {
        this(context,null);
    }

    public RandomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RandomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,mText,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int maxWidth=getWidth()-mBound.width()-padding;
        int maxHeight=getHeight()-padding;
        int minWidth=padding;
        int minHeight=mBound.height()+padding;
        /**
         * 随机将文本绘画在视图区内
         */
        Random rd=new Random();
        int cWidth = rd.nextInt(maxWidth)%(maxWidth-minWidth) + minWidth;
        int cHeight=rd.nextInt(maxHeight)%(maxHeight-minHeight) + minHeight;
        /**
         * 随机获取一个颜色数组内的颜色作为字体颜色
         */
        int cColor=rd.nextInt(colorVa.length);
        mPaint.setColor(colorVa[cColor]);
        canvas.drawText(mText, cWidth, cHeight, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        mBound=new Rect();
        mPaint.setColor(mColor);
        mPaint.setTextSize(mSize);
        mPaint.getTextBounds(mText,0,mText.length(),mBound);

        final int minimumWidth = getSuggestedMinimumWidth();
        final int minimumHeight = getSuggestedMinimumHeight();
        width = measureWidth(minimumWidth, widthMeasureSpec);
        height = measureHeight(minimumHeight, heightMeasureSpec);
        setMeasuredDimension(width+height, height*2);
    }

    private int measureWidth(int defaultWidth, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                defaultWidth = (int) mPaint.measureText(mText) + getPaddingLeft() + getPaddingRight();
                break;
            case MeasureSpec.EXACTLY:
                defaultWidth = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                defaultWidth = Math.max(defaultWidth, specSize);
                }
        return defaultWidth;
        }

    private int measureHeight(int defaultHeight, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //1.基准点是baseline
        //2.ascent：是baseline之上至字符最高处的距离
        //3.descent：是baseline之下至字符最低处的距离
        //4.leading：是上一行字符的descent到下一行的ascent之间的距离,也就是相邻行间的空白距离
        //5.top：是指的是最高字符到baseline的值,即ascent的最大值
        //6.bottom：是指最低字符到baseline的值,即descent的最大值
        switch (specMode) {
            case MeasureSpec.AT_MOST:
                defaultHeight = (int) (-mPaint.ascent() + mPaint.descent()) + getPaddingTop() + getPaddingBottom();
                break;
            case MeasureSpec.EXACTLY:
                defaultHeight = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                defaultHeight = Math.max(defaultHeight, specSize);
                break;
            }
        return defaultHeight;

        }

    public void setmText(String text){
        this.mText=text;
    }

    public void setmSize(int size){
        this.mSize=size;
    }

    public void setRandomColor(int[] colorIn){
        this.colorVa=colorIn;
    }
}
