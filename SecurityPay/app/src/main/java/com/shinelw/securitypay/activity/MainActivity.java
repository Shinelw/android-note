package com.shinelw.securitypay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shinelw.securitypay.R;
import com.shinelw.securitypay.service.MainService;
import com.shinelw.securitypay.untils.Constant;

/**
 * Created by shinelw on 1/4/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addressBtn;
    private TextView phoneNumber;
    private Button payBtn;
    private long exitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        startService(new Intent(MainActivity.this, MainService.class));
    }

    private void init() {

        addressBtn = (Button) findViewById(R.id.btn_address);
        addressBtn.setOnClickListener(this);
        phoneNumber = (TextView) findViewById(R.id.tv_number);
        payBtn = (Button) findViewById(R.id.btn_pay);
        payBtn.setOnClickListener(this);
        phoneNumber.setText("当前手机号码:" + Constant.phoneNumber + "\n账户绑定sim卡手机号码：" + Constant.simNumber);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_address:
                Intent intent = new Intent(MainActivity.this, AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_pay:
                startActivity(new Intent(MainActivity.this, PayActivity.class));
                break;

        }
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else {
            finish();
        }
    }
}
