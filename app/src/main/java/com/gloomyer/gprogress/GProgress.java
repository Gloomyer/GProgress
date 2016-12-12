package com.gloomyer.gprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Gloomy on 2016/12/12.
 */

public class GProgress extends View {
    private int bgImageSize;
    private int mItemCount;
    private int thickness;//厚度
    private Paint bgPaint;
    private Paint selectPaint;
    private Bitmap mBgImage;
    private Paint unSelectPaint;
    private int progress;

    public GProgress(Context context) {
        this(context, null);
    }

    public GProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bgImageSize = -1;
        mItemCount = 10;
        thickness = 20;
        int selectColor = Color.RED;
        int unSelectColor = Color.GREEN;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GProgress);
            for (int i = 0; i < a.getIndexCount(); i++) {
                int index = a.getIndex(i);
                switch (index) {
                    case R.styleable.GProgress_bgImg:
                        int resId = a.getResourceId(index, -1);
                        if (resId != -1)
                            mBgImage = BitmapFactory.decodeResource(context.getResources(), resId);
                        break;
                    case R.styleable.GProgress_imgSize:
                        bgImageSize = a.getDimensionPixelSize(index, -1);
                        break;

                    case R.styleable.GProgress_itemCount:
                        mItemCount = a.getInteger(index, 10);
                        break;

                    case R.styleable.GProgress_thickness:
                        thickness = a.getDimensionPixelOffset(index, 20);
                        break;
                    case R.styleable.GProgress_unSelectedColor:
                        unSelectColor = a.getColor(index, unSelectColor);
                        break;
                    case R.styleable.GProgress_selectedColor:
                        selectColor = a.getColor(index, selectColor);
                        break;
                }
            }
            a.recycle();
        }

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);

        unSelectPaint = new Paint();
        unSelectPaint.setAntiAlias(true);
        unSelectPaint.setStyle(Paint.Style.STROKE);
        unSelectPaint.setStrokeCap(Paint.Cap.ROUND);
        unSelectPaint.setColor(unSelectColor);
        unSelectPaint.setStrokeWidth(thickness);


        selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setStyle(Paint.Style.STROKE);
        selectPaint.setStrokeCap(Paint.Cap.ROUND);
        selectPaint.setColor(selectColor);
        selectPaint.setStrokeWidth(thickness);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * 绘制中间的图片
         */
        if (mBgImage != null) {
            Matrix matrix = new Matrix();

            if (bgImageSize == -1) {
                bgImageSize = (int) (canvas.getWidth() * 0.6f);
                Log.e("TAG", "size:" + bgImageSize);
            }

            float scaleX = bgImageSize * 1.0f / mBgImage.getWidth();
            float scaleY = bgImageSize * 1.0f / mBgImage.getHeight();
            Log.e("TAG", scaleX + "," + scaleY);
            matrix.setScale(scaleX, scaleY);
            matrix.postTranslate(canvas.getWidth() / 2 - bgImageSize / 2
                    , canvas.getHeight() / 2 - bgImageSize / 2);
            canvas.drawBitmap(mBgImage, matrix, bgPaint);
        }

        drawOverl(canvas);
    }

    /**
     * 画进度
     *
     * @param canvas
     */
    private void drawOverl(Canvas canvas) {

        int size = (int) (270 * 0.5f / mItemCount + 0.5f);

        int span = (int) (270 * 0.5f / (mItemCount - 1) + 0.5f);

        //开始绘制射线
        RectF rectF = new RectF(thickness, thickness,
                canvas.getWidth() - thickness, canvas.getHeight() - thickness);
        int start = 135;

        for (int i = 0; i < mItemCount; i++) {
            canvas.drawArc(rectF, start, size, false, (i + 1) <= progress ? selectPaint : unSelectPaint);
            start += size + span;
        }
    }

    /**
     * 获取当前进度
     *
     * @return
     */
    public int getProgress() {
        return progress;
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress > mItemCount) {
            throw new ArrayIndexOutOfBoundsException("设置进度大过总进度");
        }
        if (progress < 0)
            progress = 0;
        this.progress = progress;
        postInvalidate();
    }

    /**
     * 获取总进度
     *
     * @return
     */
    public int getTotal() {
        return mItemCount;
    }

    /**
     * 设置总进度
     *
     * @param total
     */
    public void setTotal(int total) {
        if (total < 1)
            throw new RuntimeException("总进度不能小于1");
        this.mItemCount = total;
        postInvalidate();
    }
}
