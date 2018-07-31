package com.cnsunrun.library.radius;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.cnsunrun.library.radius.delegate.RadiusViewDelegate;
import com.cnsunrun.library.radius.helper.AlphaViewHelper;

/**
 * 用于需要圆角矩形框背景的LinearLayout的情况,减少直接使用LinearLayout时引入的shape资源文件
 */
public class RadiusLinearLayout extends LinearLayout {

    private RadiusViewDelegate delegate;
    private AlphaViewHelper mAlphaViewHelper;

    public RadiusLinearLayout(Context context) {
        this(context, null);
    }

    public RadiusLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        delegate = new RadiusViewDelegate(this, context, attrs);
        mAlphaViewHelper = new AlphaViewHelper(this);
    }

    /**
     * 获取代理类用于Java代码控制shape属性
     *
     * @return
     */
    public RadiusViewDelegate getDelegate() {
        return delegate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate != null && delegate.getWidthHeightEqualEnable() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate != null) {
            if (delegate.getRadiusHalfHeightEnable()) {
                delegate.setRadius(getHeight() / 2);
            }
            delegate.init();
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (delegate != null)
            delegate.setSelected(selected);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (delegate != null) {
            delegate.init();
            mAlphaViewHelper.onEnabledChanged(this, enabled);
        }
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        mAlphaViewHelper.onPressedChanged(this, pressed);
    }
}
