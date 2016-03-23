package com.shinelw.securitypay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.baidu.mapapi.model.LatLng;
import com.shinelw.securitypay.R;
import com.shinelw.securitypay.untils.Constant;
import com.shinelw.securitypay.untils.DistanceUntils;

public class AddressActivity extends AppCompatActivity {
    private TextView phoneLoc;
    private TextView simLoc;
    private TextView distance;
    private double simLat;
    private double simLng;
    private double curLat;
    private double curLng;
    private LatLng simLatLng;
    private LatLng curLatLng;
    private TextView noteText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        init();
    }

    private void init() {
        phoneLoc = (TextView) findViewById(R.id.tv_phone);
        simLoc = (TextView) findViewById(R.id.tv_sim);
        distance = (TextView) findViewById(R.id.tv_distance);
        noteText = (TextView) findViewById(R.id.tv_note);
        curLat = Double.valueOf(Constant.curLat);
        curLng = Double.valueOf(Constant.curLng);
        curLatLng = new LatLng(curLat, curLng);
        phoneLoc.setText("Lat:" + curLat + "\nLng:" + curLng);
        AVQuery<AVObject> query = new AVQuery<AVObject>("SimLocation");
        query.getInBackground(Constant.simObjectId, new GetCallback<AVObject>() {
            public void done(AVObject post, AVException e) {
                if (e == null) {
                    String lat_lng = post.getString("lat_lng");
                    int mid = lat_lng.lastIndexOf("_");
                    simLat = Double.valueOf(lat_lng.substring(0, mid));
                    simLng = Double.valueOf(lat_lng.substring(mid + 1, lat_lng.length()));
                    simLatLng = new LatLng(simLat, simLng);
                    simLoc.setText("Lat:" + simLat + "\nLng:" + simLng);
                    distance.setText(DistanceUntils.getDistance(curLatLng, simLatLng) + "m");
                } else {
                    Log.e("获取地理信息", "错误: " + e.getMessage());
                }
            }
        });
        if (!Constant.phoneNumber.equals(Constant.simNumber)) {
            noteText.setText("警告！当前登陆手机与账户绑定手机不一致！");
        }else {
            noteText.setText("安全！当前登陆手机与账户绑定手机一致！");
        }
    }

}
