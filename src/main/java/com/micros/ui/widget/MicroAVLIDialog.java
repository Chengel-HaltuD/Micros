package com.micros.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.micros.R;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/13 11:47
 * version：V1.0
 */
public class MicroAVLIDialog extends Dialog {


    public MicroAVLIDialog(Context context) {
        super(context, R.style.dialog_prog);
        View view=LayoutInflater.from(getContext()).inflate(R.layout.progress_avld,null);
        setContentView(view);
        setCanceledOnTouchOutside(false);
    }
}