package net.caixiaomi.smartrefresh.layout.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.R;

import net.caixiaomi.smartrefresh.layout.api.OnTwoLevelListener;
import net.caixiaomi.smartrefresh.layout.api.RefreshHeader;
import net.caixiaomi.smartrefresh.layout.api.RefreshKernel;
import net.caixiaomi.smartrefresh.layout.api.RefreshLayout;
import net.caixiaomi.smartrefresh.layout.constant.RefreshState;
import net.caixiaomi.smartrefresh.layout.constant.SpinnerStyle;
import net.caixiaomi.smartrefresh.layout.impl.RefreshHeaderWrapper;
import net.caixiaomi.smartrefresh.layout.internal.InternalAbstract;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 二级刷新
 * Created by SCWANG on 2017/5/26.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class TwoLevelHeader extends InternalAbstract implements RefreshHeader/*, InvocationHandler*/ {

    //<editor-fold desc="属性字段">
    protected int mSpinner;
    protected float mPercent = 0;
    protected float mMaxRage = 2.5f;
    protected float mFloorRage = 1.9f;
    protected float mRefreshRage = 1f;
    protected boolean mEnableTwoLevel = true;
    protected boolean mEnablePullToCloseTwoLevel = true;
    protected int mFloorDuration = 1000;
    protected int mHeaderHeight;
//    protected int mPaintAlpha;
//    protected Paint mPaint;
    protected RefreshHeader mRefreshHeader;
    protected RefreshKernel mRefreshKernel;
    protected OnTwoLevelListener mTwoLevelListener;
//    protected SpinnerStyle mSpinnerStyle = SpinnerStyle.FixedBehind;
//    protected Method mRequestDrawBackgroundForHeaderMethod;
    //</editor-fold>

    //<editor-fold desc="构造方法">
    public TwoLevelHeader(@NonNull Context context) {
        this(context, null);
    }

    public TwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSpinnerStyle = SpinnerStyle.FixedBehind;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TwoLevelHeader);

        mMaxRage = ta.getFloat(R.styleable.TwoLevelHeader_srlMaxRage, mMaxRage);
        mFloorRage = ta.getFloat(R.styleable.TwoLevelHeader_srlFloorRage, mFloorRage);
        mRefreshRage = ta.getFloat(R.styleable.TwoLevelHeader_srlRefreshRage, mRefreshRage);
        mFloorDuration = ta.getInt(R.styleable.TwoLevelHeader_srlFloorDuration, mFloorDuration);
        mEnableTwoLevel = ta.getBoolean(R.styleable.TwoLevelHeader_srlEnableTwoLevel, mEnableTwoLevel);
        mEnablePullToCloseTwoLevel = ta.getBoolean(R.styleable.TwoLevelHeader_srlEnablePullToCloseTwoLevel, mEnablePullToCloseTwoLevel);

        ta.recycle();
    }

    //</editor-fold>

    //<editor-fold desc="生命周期">
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final ViewGroup thisGroup = this;
        for (int i = 0, len = thisGroup.getChildCount(); i < len; i++) {
            View childAt = thisGroup.getChildAt(i);
            if (childAt instanceof RefreshHeader) {
                mWrapperView = childAt;
                mRefreshHeader = (RefreshHeader) childAt;
                thisGroup.bringChildToFront(childAt);
                break;
            }
        }
        if (mRefreshHeader == null) {
            mRefreshHeader = new RefreshHeaderWrapper(this);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mSpinnerStyle = SpinnerStyle.MatchLayout;
        if (mRefreshHeader == null) {
            mRefreshHeader = new RefreshHeaderWrapper(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSpinnerStyle = SpinnerStyle.FixedBehind;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRefreshHeader.getView() != this) {
            int mode = MeasureSpec.getMode(heightMeasureSpec);
            if (mode == MeasureSpec.AT_MOST) {
                mRefreshHeader.getView().measure(widthMeasureSpec, heightMeasureSpec);
                int height = mRefreshHeader.getView().getMeasuredHeight();
                super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), widthMeasureSpec), height);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

//    @Override
//    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//        boolean isInEditMode = isInEditMode();
//        if (child == mRefreshHeader.getView() && mPaint != null) {
//            canvas.drawRect(0, 0, getWidth(), ((isInEditMode) ? mHeaderHeight : mSpinner) + child.getTop(), mPaint);
//        }
//        return super.drawChild(canvas, child, drawingTime);
//    }

    //</editor-fold>

    //<editor-fold desc="Header实现">
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        Object returnValue = null;
//        if (mRefreshKernel != null) {
//            if (method.equals(mRequestDrawBackgroundForHeaderMethod)) {
//                int backgroundColor = (int) args[0];
//                if (backgroundColor == 0) {
//                    mPaint = null;
//                } else {
//                    if (mPaint == null) {
//                        mPaint = new Paint();
//                    }
//                    mPaint.setColor(backgroundColor);
//                    mPaintAlpha = ((backgroundColor & 0xFF000000) >> 24);
//                }
//                returnValue = proxy;
//            } else {
//                returnValue = method.invoke(mRefreshKernel, args);
//            }
//        }
//        if (method.getReturnType().equals(RefreshKernel.class)) {
//            if (mRefreshKernel == null && RefreshKernel.class.equals(method.getDeclaringClass())) {
//                if (mRequestDrawBackgroundForHeaderMethod == null) {
//                    mRequestDrawBackgroundForHeaderMethod = method;
//                }
//            }
//            return proxy;
//        }
//        return returnValue;
//    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        final View thisView = this;
        if (1f * (maxDragHeight + height) / height != mMaxRage && mHeaderHeight == 0) {
            mHeaderHeight = height;
            kernel.getRefreshLayout().setHeaderMaxDragRate(mMaxRage);
            return;
        }
        if (!thisView.isInEditMode() && mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate
                && mRefreshKernel == null) {
            MarginLayoutParams params = (MarginLayoutParams) mRefreshHeader.getView().getLayoutParams();
            params.topMargin -= height;
            mRefreshHeader.getView().setLayoutParams(params);
        }

//        RefreshKernel proxy = (RefreshKernel) Proxy.newProxyInstance(RefreshKernel.class.getClassLoader(), new Class[]{RefreshKernel.class}, this);
//        proxy.requestDrawBackgroundForHeader(0);
//        proxy.requestNeedTouchEventWhenRefreshing(false);

        mHeaderHeight = height;
        mRefreshKernel = kernel;
        mRefreshKernel.requestFloorDuration(mFloorDuration);
        mRefreshHeader.onInitialized(mRefreshKernel, height, maxDragHeight);
//        mRefreshHeader.onInitialized(proxy, height, maxDragHeight);
        mRefreshKernel.requestNeedTouchEventFor(this, !mEnablePullToCloseTwoLevel);
//        mRefreshKernel.requestNeedTouchEventWhenRefreshing(!mEnablePullToCloseTwoLevel);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && mPaint != null) {
//            mRefreshHeader.getView().animate().setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    if (animation.getAnimatedValue() instanceof Float) {
//                        mPaint.setAlpha(((int)((mRefreshHeader.getView().getAlpha())*mPaintAlpha)));
//                    }
//                }
//            });
//        }
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        mRefreshHeader.onStateChanged(refreshLayout, oldState, newState);
        switch (newState) {
            case TwoLevelReleased:
                if (mRefreshHeader.getView() != this) {
                    mRefreshHeader.getView().animate().alpha(0).setDuration(mFloorDuration / 2);
                }
//                if (mPaint != null) {
//                    invalidate();
//                }
                mRefreshKernel.startTwoLevel(mTwoLevelListener == null || mTwoLevelListener.onTwoLevel(refreshLayout));
                break;
            case TwoLevel:
                break;
            case TwoLevelFinish:
                if (mRefreshHeader.getView() != this) {
                    mRefreshHeader.getView().animate().alpha(1).setDuration(mFloorDuration / 2);
                }
                break;
            case PullDownToRefresh:
                if (mRefreshHeader.getView().getAlpha() == 0 && mRefreshHeader.getView() != this) {
                    mRefreshHeader.getView().setAlpha(1);
                }
                break;
        }
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        moveSpinner(offset);
        mRefreshHeader.onMoving(isDragging, percent, offset, height, maxDragHeight);
        if (isDragging) {
            if (mPercent < mFloorRage && percent >= mFloorRage && mEnableTwoLevel) {
                mRefreshKernel.setState(RefreshState.ReleaseToTwoLevel);
            } else if (mPercent >= mFloorRage && percent < mRefreshRage) {
                mRefreshKernel.setState(RefreshState.PullDownToRefresh);
            } else if (mPercent >= mFloorRage && percent < mFloorRage) {
                mRefreshKernel.setState(RefreshState.ReleaseToRefresh);
            }
            mPercent = percent;
        }
    }

//    @Override
//    public void onPulling(float percent, int offset, int height, int maxDragHeight) {
//        moveSpinner(offset);
//        mRefreshHeader.onPulling(percent, offset, height, maxDragHeight);
//        if (mPercent < mFloorRage && percent >= mFloorRage && mEnableTwoLevel) {
//            mRefreshKernel.setState(RefreshState.ReleaseToTwoLevel);
//        } else if (mPercent >= mFloorRage && percent < mRefreshRage) {
//            mRefreshKernel.setState(RefreshState.PullDownToRefresh);
//        } else if (mPercent >= mFloorRage && percent < mFloorRage) {
//            mRefreshKernel.setState(RefreshState.ReleaseToRefresh);
//        }
//        mPercent = percent;
//    }
//
//    @Override
//    public void onReleasing(float percent, int offset, int height, int maxDragHeight) {
//        moveSpinner(offset);
//        mRefreshHeader.onReleasing(percent, offset, height, maxDragHeight);
//    }

    protected void moveSpinner(int spinner) {
        if (mSpinner != spinner && mRefreshHeader.getView() != this) {
            mSpinner = spinner;
            switch (mRefreshHeader.getSpinnerStyle()) {
                case Translate:
                    mRefreshHeader.getView().setTranslationY(spinner);
                    break;
                case Scale:{
                    View view = mRefreshHeader.getView();
                    view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getTop() + Math.max(0, spinner));
                    break;
                }
            }
//            if (mPaint != null) {
//                invalidate();
//            }
        }
    }
//
//    @Override
//    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
//        mRefreshHeader.onReleased(refreshLayout, height, maxDragHeight);
//    }
//
//    @NonNull
//    @Override
//    public SpinnerStyle getSpinnerStyle() {
//        return mSpinnerStyle;
//    }
//
//    @Override
//    public void setPrimaryColors(int... colors) {
//        mRefreshHeader.setPrimaryColors(colors);
//    }
//
//    @Override
//    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
//        mRefreshHeader.onHorizontalDrag(percentX, offsetX, offsetMax);
//    }
//
//    @Override
//    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
//        mRefreshHeader.onStartAnimator(refreshLayout, height, maxDragHeight);
//    }
//
//    @Override
//    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
//        return mRefreshHeader.onFinish(refreshLayout, success);
//    }
//
//    @Override
//    public boolean isSupportHorizontalDrag() {
//        return mRefreshHeader.isSupportHorizontalDrag();
//    }
    //</editor-fold>

    //<editor-fold desc="开放接口 - API">

    /**
     * 设置指定的Header
     */
    public TwoLevelHeader setRefreshHeader(RefreshHeader header) {
        return setRefreshHeader(header, MATCH_PARENT, WRAP_CONTENT);
    }

    /**
     * 设置指定的Header
     */
    public TwoLevelHeader setRefreshHeader(RefreshHeader header, int width, int height) {
        final ViewGroup thisGroup = this;
        if (header != null) {
            if (mRefreshHeader != null) {
                thisGroup.removeView(mRefreshHeader.getView());
            }
            this.mRefreshHeader = header;
            if (header.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                thisGroup.addView(mRefreshHeader.getView(), 0, new LayoutParams(width, height));
            } else {
                thisGroup.addView(mRefreshHeader.getView(), width, height);
            }
        }
        return this;
    }

    /**
     * 设置下拉Header的最大高度比值
     * @param rate MaxDragHeight/HeaderHeight
     */
    public TwoLevelHeader setMaxRage(float rate) {
        if (this.mMaxRage != rate) {
            this.mMaxRage = rate;
            if (this.mRefreshKernel != null) {
                this.mHeaderHeight = 0;
                this.mRefreshKernel.getRefreshLayout().setHeaderMaxDragRate(mMaxRage);
            }
        }
        return this;
    }

    /**
     * 是否禁止在二极状态是上滑关闭状态回到初态
     * @param enabled 是否启用
     */
    public TwoLevelHeader setEnablePullToCloseTwoLevel(boolean enabled) {
        this.mEnablePullToCloseTwoLevel = enabled;
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.requestNeedTouchEventFor(this, !enabled);
//            this.mRefreshKernel.requestNeedTouchEventWhenRefreshing(disable);
        }
        return this;
    }

    public TwoLevelHeader setFloorRage(float rate) {
        this.mFloorRage = rate;
        return this;
    }

    public TwoLevelHeader setRefreshRage(float rate) {
        this.mRefreshRage = rate;
        return this;
    }

    public TwoLevelHeader setEnableTwoLevel(boolean enabled) {
        this.mEnableTwoLevel = enabled;
        return this;
    }

    public TwoLevelHeader setFloorDuration(int duration) {
        this.mFloorDuration = duration;
        return this;
    }

    public TwoLevelHeader setOnTwoLevelListener(OnTwoLevelListener listener) {
        this.mTwoLevelListener = listener;
        return this;
    }

    public TwoLevelHeader finishTwoLevel() {
        this.mRefreshKernel.finishTwoLevel();
        return this;
    }

    //</editor-fold>
}