package com.shinelw.securitypay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.shinelw.securitypay.R;
import com.shinelw.securitypay.untils.Constant;
import com.shinelw.securitypay.widget.MyProgressDialog;

/**
 * Created by shinelw on 1/4/16.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText phone;
    private EditText password;
    private Button registerBtn;
    private MyProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        dialog = new MyProgressDialog(this, "请稍候....");
        phone = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        registerBtn = (Button) findViewById(R.id.email_sign_in_button);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.email_sign_in_button:
                if (phone.getText().toString().length() != 11 || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入相关账号密码", Toast.LENGTH_SHORT).show();
                }else {
                    userRegister();
                }
                break;
        }
    }

    private void userRegister() {
        AVUser user = new AVUser();
        user.setUsername(phone.getText().toString());
        user.setPassword(password.getText().toString());
        user.put("imsi", Constant.phoneIMSI);
        dialog.mDialog.show();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // successfully
                    dialog.mDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    AVObject object = new AVObject("SimLocation");
                    object.put("phone", phone.getText().toString());
                    object.put("imsi", Constant.phoneIMSI);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                // 保存成功
                                Log.i("phoneIMSI", "success");
                                finish();
                            } else {
                                // 保存失败，输出错误信息
                                finish();
                            }
                        }
                    });
                } else {
                    // failed
                    dialog.mDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
