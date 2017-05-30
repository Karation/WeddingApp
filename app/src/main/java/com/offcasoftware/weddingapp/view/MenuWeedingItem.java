package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuWeedingItem extends RelativeLayout {

    @BindView(R.id.menu_item_image)
    ImageView mImageView;

    @BindView(R.id.menu_item_text)
    TextView mLabel;

    public MenuWeedingItem(final Context context) {
        super(context);
        init(null);
    }

    public MenuWeedingItem(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MenuWeedingItem(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MenuWeedingItem(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(final AttributeSet attrs) {
        inflate(getContext(), R.layout.view_menu_item, this);

        if (isInEditMode()) {
            return;
        }

        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white_light));

        ButterKnife.bind(this);

        if (attrs != null) {
            final TypedArray typedArray =
                    getContext().obtainStyledAttributes(attrs, R.styleable.MenuWeedingItem);

            CharSequence label = typedArray.getText(R.styleable.MenuWeedingItem_label);
            mLabel.setText(label);

            int icon = typedArray.getResourceId(R.styleable.MenuWeedingItem_icon_res_id, 0);
            mImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));

            typedArray.recycle();
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
