package com.shinelw.securitypay.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.baidu.mapapi.model.LatLng;
import com.shinelw.securitypay.R;
import com.shinelw.securitypay.untils.Constant;
import com.shinelw.securitypay.untils.DistanceUntils;
import com.shinelw.securitypay.widget.MyProgressDialog;

/**
 * Created by shinelw on 1/4/16.
 */
public class PayActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button payBtn;
    private EditText yanzhengma;
    private LinearLayout msgLayout;
    private boolean isNeedMsg = false;
    private double simLat;
    private double simLng;
    private double curLat;
    private double curLng;
    private LatLng simLatLng;
    private LatLng curLatLng;
    private TextView noteText;
    private MyProgressDialog dialog;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            dialog.mDialog.dismiss();
            Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        init();
    }

    private void init() {
        dialog = new MyProgressDialog(this, "支付中....");
        payBtn = (Button) findViewById(R.id.btn_pay);
        yanzhengma = (EditText) findViewById(R.id.et_yanzhengma);
        msgLayout = (LinearLayout) findViewById(R.id.layout_msg);
        noteText = (TextView) findViewById(R.id.tv_note);
        payBtn.setOnClickListener(this);
        curLat = Double.valueOf(Constant.curLat);
        curLng = Double.valueOf(Constant.curLng);
        curLatLng = new LatLng(curLat, curLng);
        AVQuery<AVObject> query = new AVQuery<AVObject>("SimLocation");
        query.getInBackground(Constant.simObjectId, new GetCallback<AVObject>() {
            public void done(AVObject post, AVException e) {
                if (e == null) {
                    String lat_lng = post.getString("lat_lng");
                    int mid = lat_lng.lastIndexOf("_");
                    simLat = Double.valueOf(lat_lng.substring(0, mid));
                    simLng = Double.valueOf(lat_lng.substring(mid + 1, lat_lng.length()));
                    simLatLng = new LatLng(simLat, simLng);
                    isNeedMsg = setIsNeedMsg();
                } else {
                    Log.e("获取地理信息", "错误: " + e.getMessage());
                }
            }
        });


        if (!Constant.phoneNumber.equals(Constant.simNumber)) {
            noteText.setText("警告！当前登陆手机与账户绑定手机不一致！");
        }else {
            noteText.setText("安全！当前登陆手机与账户绑定手机一致！可直接支付！");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_pay:
                if (!isNeedMsg) {
                    dialog.mDialog.show();
                    handler.sendEmptyMessageDelayed(1, 1500);

                }else {
                    if (msgLayout.getVisibility() == View.GONE) {
                        msgLayout.setVisibility(View.VISIBLE);
                        sendMsg();
                    }else {
                        confirmMsg();
                    }
                }
                break;

        }
    }

    private void sendMsg () {
        Log.i("simNumber", Constant.simNumber);
        AVOSCloud.requestSMSCodeInBackground(AVUser.getCurrentUser().getUsername(), "SecPay", "异地支付请求", 10, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(PayActivity.this, "发送短信成功", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Home.OperationVerify", e.getMessage());
                }
            }
        });
    }

    private void confirmMsg() {
        AVOSCloud.verifyCodeInBackground(yanzhengma.getText().toString(), Constant.simNumber, new AVMobilePhoneVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(PayActivity.this, "验证成功，正在进行支付", Toast.LENGTH_SHORT).show();
                    dialog.mDialog.show();
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    Toast.makeText(PayActivity.this, "验证码错误，支付失败", Toast.LENGTH_SHORT).show();
                    Log.e("Home.DoOperationVerify", e.getMessage());
                }
            }
        });
    }

    private boolean setIsNeedMsg() {
        if (Constant.phoneNumber.equals(Constant.simNumber)) {
            return false;
        }
        Double distance = DistanceUntils.getDistance(curLatLng, simLatLng);
        Log.i("distance", distance + "");
        if (distance < 1) {
            return false;
        }else {
            return true;
        }

    }
}
