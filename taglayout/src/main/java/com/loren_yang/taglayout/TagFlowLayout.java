package com.loren_yang.taglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Loren Yang on 2017/7/14.
 */

public class TagFlowLayout extends ViewGroup {

    private static final String TAG2 = "onLayout";
    private static final String TAG1 = "onMesure";
    private String tGravity="1";//默认是居中
    private int tagDivider_horizontal=5;//默认是5dp
    private int tagDivider_vertical=5;
    private int tagPadding=5;

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TagFlowLayout, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            if(attr==R.styleable.TagFlowLayout_tagGravity){
                tGravity=a.getString(attr);
            }else if(attr==R.styleable.TagFlowLayout_tagDivider_horizontal){
                tagDivider_horizontal = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            }else if(attr==R.styleable.TagFlowLayout_tagDivider_vertical){
                tagDivider_vertical = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            }else if(attr==R.styleable.TagFlowLayout_tagPadding){
                tagPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            }
        }
        a.recycle();
    }

    /**
     * 测量当前ViewGroup的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        //获取父容器中给它设置的大小和模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //计算当前容器实际可用的宽度(去除padding值)
        int existWidth=sizeWidth - getPaddingLeft() - getPaddingRight()-2*tagPadding;
        //当前容器的宽高
        int width = 0;
        int height = 0;
        //每行的宽高
        int lineWidth = 0;
        int lineHeight = 0;
        int cCount = getChildCount();//获取所有子元素的个数

        //遍历所有子View
        for(int i=0;i<cCount;i++){
            View child = getChildAt(i);
            //测量当前child的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            //获取当前clild实际占用的空间
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;

            /**
             * 如果加入当前child的宽度后，超过了容器的可用宽度，换行重新计算宽度
             */
            Log.i(TAG1,(lineWidth+tagDivider_horizontal + childWidth)+"::::::::"+existWidth+"::"+childWidth+":::"+lineWidth+"::"+tagDivider_horizontal);
            if (lineWidth+tagDivider_horizontal + childWidth > existWidth)
            {
                //当前viewgroup的宽度赋值为最大那个
                width = Math.max(width, lineWidth);
                //换行后，当前行的宽度为新添的child宽度
                lineWidth = childWidth;
                //叠加高度
                height += lineHeight+tagDivider_vertical;
                lineHeight = childHeight;
            } else
            {
                if(lineWidth==0) {
                    lineWidth += childWidth;
                }else{
                    lineWidth += childWidth+tagDivider_horizontal;
                }
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if(i==0){
                width = Math.max(lineWidth, width);
                //第一次的高度并未加上，所以在这补充
                height += lineHeight;
            }
        }
        //设置测量宽高,如果是MeasureSpec.EXACTLY，则直接使用父ViewGroup传入的宽高，否则为自己计算的宽高
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight()+2*tagPadding,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()+2*tagPadding
        );
    }

    /**
     * 确定viewGroup在父容器中的位置
     */
    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        int cCount = getChildCount();
        //如果没有childview，直接返回
        if(cCount<=0) return;
        int existWidth=getWidth() - getPaddingLeft() - getPaddingRight()-2*tagPadding;
        int curTop=getPaddingTop()+tagPadding;
        int curLeft=getPaddingLeft()+tagPadding;
        int maxHeight=0;
        int lineViewCount=0;
        int surplusSpace=0;
        for(int i=0;i<cCount;i++){
            View child=getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            if(maxHeight==0){
                maxHeight=child.getMeasuredHeight()+ lp.topMargin
                        + lp.bottomMargin;
            }else{
                maxHeight=Math.max(maxHeight,child.getMeasuredHeight()+ lp.topMargin
                        + lp.bottomMargin);
            }
            int width=child.getMeasuredWidth()+ lp.leftMargin
                    + lp.rightMargin;
            int height=child.getMeasuredHeight()+ lp.topMargin
                    + lp.bottomMargin;
            Log.i(TAG2,(width+curLeft+tagDivider_horizontal-getPaddingLeft()-tagPadding)+"::::::::"+existWidth+"::"+width+"::"+curLeft+"::"+getPaddingLeft()+"::"+tagPadding);
            if(width+curLeft-getPaddingLeft()-tagPadding>existWidth){
                //每换一行，就将换行前的布局居中下
                surplusSpace=existWidth-curLeft+tagPadding+getPaddingLeft();//
                if(tGravity.equals("1")) adjustLine(lineViewCount, i,curTop,surplusSpace);

                lineViewCount=0;
                curLeft=getPaddingLeft()+tagPadding;
                curTop+=maxHeight+tagDivider_vertical;
                maxHeight=child.getMeasuredHeight()+ lp.topMargin
                        + lp.bottomMargin;;
            }
            child.layout(curLeft, curTop, curLeft + width, curTop + height);
            curLeft += width + tagDivider_horizontal;
            lineViewCount++;
            surplusSpace=existWidth-curLeft+tagPadding+getPaddingLeft();
        }

        //处理最后一行居中问题
        if(tGravity.equals("1")) adjustLine(lineViewCount, cCount,curTop,surplusSpace);
    }

    /* 调整一行，让这一行的子布局水平居中 */
    private void adjustLine(int lineViewCount, int i,int curTop,int surplusSpace) {
        int lineLeft=getPaddingLeft()+tagPadding;
        for (int lineViewNumber = lineViewCount; lineViewNumber > 0; lineViewNumber--) {
            View lineViewChild = getChildAt(i - lineViewNumber);
            if(lineViewNumber == lineViewCount) lineLeft=lineLeft+surplusSpace/2;
            lineViewChild.layout(lineLeft, curTop , lineLeft+lineViewChild.getMeasuredWidth(), curTop+lineViewChild.getMeasuredHeight());
            lineLeft += lineViewChild.getMeasuredWidth()+tagDivider_horizontal;
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
