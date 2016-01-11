package com.shinelw.securitypay.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.shinelw.securitypay.R;

/**
 * Created by shinelw on 1/5/16.
 */
public class LoadingDialog extends Dialog {

    private TextView title;
    private TextView load;
    private View bar;
    private String titleStr = "提示";
    private String loadStr  = "加载中...";


    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int titleId, int loadId) {
        super(context, R.style.customer_dialog);
        this.titleStr = getContext().getResources().getString(titleId);
        this.loadStr = getContext().getResources().getString(loadId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setDialogSize();
        init();
    }

    private void init() {
        title = (TextView) findViewById(R.id.tv_title);
        load = (TextView) findViewById(R.id.tv_load);
        bar = findViewById(R.id.pb_load);
        title.setText(titleStr);
        load.setText(loadStr);
    }

    private void setDialogSize() {
        Window dialogWindow = getWindow();
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.33); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.75
        dialogWindow.setAttributes(p);
    }

}
