package com.byq.alldendent;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.BottomPopupView;

/**
 * 底部弹窗模板
 */
public class BottomDialogUtil extends BottomPopupView {
    private View contentView;

    //Views
    private TextView mTitleView;
    private TextView mSubTitle;
    private LinearLayout mContentView;
    private MaterialButton mCloseButton;


    public BottomDialogUtil(@NonNull Context context,View contentView) {
        super(context);
        setContentView(contentView);
    }

    public BottomDialogUtil(@NonNull Context context) {
        super(context);
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    public TextView getmTitleView() {
        return mTitleView;
    }

    public TextView getmSubTitle() {
        return mSubTitle;
    }

    public LinearLayout getmContentView() {
        return mContentView;
    }

    public MaterialButton getmCloseButton() {
        return mCloseButton;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.bottom_dialog_layout;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        mTitleView = findViewById(R.id.titleView);
        mSubTitle = findViewById(R.id.subTitle);
        mContentView = findViewById(R.id.contentView);
        mCloseButton = findViewById(R.id.closeButton);

        mCloseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public BasePopupView show() {
        return new XPopup.Builder(getContext()).asCustom(this).show();
    }
}
