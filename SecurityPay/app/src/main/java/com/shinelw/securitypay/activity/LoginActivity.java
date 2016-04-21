package com.shinelw.securitypay.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.shinelw.securitypay.R;
import com.shinelw.securitypay.untils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AVAnalytics.trackAppOpened(getIntent());
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(this);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        registerText = (TextView) findViewById(R.id.tv_register);
        registerText.setOnClickListener(this);
        Constant.phoneIMSI = getPhoneSIMI();
        Log.i("phonesimi", getPhoneSIMI());
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        registerText.setText(deviceId);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("phonesimi", getPhoneSIMI());
        getPhoneObjectId(Constant.phoneIMSI);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.tv_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.email_sign_in_button:
                if (mEmailView.getText().toString().length() != 11 || TextUtils.isEmpty(mPasswordView.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入相关账号密码", Toast.LENGTH_SHORT).show();
                }else {
                    login();
                }
                break;
        }
    }

    private void login() {
        showProgress(true);
        AVUser.logInInBackground(mEmailView.getText().toString(), mPasswordView.getText().toString(), new LogInCallback() {
            public void done(AVUser user, AVException e) {
                if (e == null) {
                    // 登录成功
                    Constant.simIMSI = user.getString("imsi");
                    getSimObjectId(Constant.simIMSI);
                } else {
                    // 登录失败
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }
            }
        });
    }

    private void getPhoneObjectId(String imsi) {

        AVQuery<AVObject> individualPosts = AVQuery.getQuery("SimLocation");
        individualPosts.whereEqualTo("imsi", imsi);

        List<AVQuery<AVObject>> queries = new ArrayList<AVQuery<AVObject>>();
        queries.add(individualPosts);

        AVQuery<AVObject> mainQuery = AVQuery.and(queries);
        mainQuery.findInBackground(new FindCallback<AVObject>() {
            public void done(List<AVObject> results, AVException e) {
                if (results != null && results.size() == 1) {
                    Constant.phoneObjectId = results.get(0).getObjectId();
                    Constant.phoneNumber = results.get(0).getString("phone");
                    Log.i("phoneObjectId", Constant.phoneObjectId);
                }
            }
        });
    }
    private void getSimObjectId(String imsi) {

        AVQuery<AVObject> individualPosts = AVQuery.getQuery("SimLocation");
        individualPosts.whereEqualTo("imsi", imsi);

        List<AVQuery<AVObject>> queries = new ArrayList<AVQuery<AVObject>>();
        queries.add(individualPosts);

        AVQuery<AVObject> mainQuery = AVQuery.and(queries);
        mainQuery.findInBackground(new FindCallback<AVObject>() {
            public void done(List<AVObject> results, AVException e) {
                if (results != null && results.size() == 1) {
                    Constant.simObjectId = results.get(0).getObjectId();
                    Log.i("phoneObjectId", Constant.simObjectId);
                    showProgress(false);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Constant.simNumber = mEmailView.getText().toString();
                    finish();
                }
            }
        });
    }

    private String getPhoneSIMI() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }
}

