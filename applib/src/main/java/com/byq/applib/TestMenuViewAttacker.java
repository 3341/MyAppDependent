package com.byq.applib;

import android.view.View;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

public class TestMenuViewAttacker {

    public static void attach(View view,String[] list,OnSelectListener listener) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new XPopup.Builder(view.getContext()).asBottomList("测试选项", list,listener).show();
                return true;
            }
        });
    }
}
