package com.zhengxyou.mainmodule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhengxyou.basemodule.base.BaseApplication;
import com.zhengxyou.commonlibrary.utils.SharedPreferencesUtil;

@Route(path = "/main/main")
public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    SharedPreferencesUtil util;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.main_navigation_home) {
                mTextMessage.setText(R.string.main_title_home);
                ARouter.getInstance().build("/other/login").navigation();
                return true;
            } else if (i == R.id.main_navigation_dashboard) {
                mTextMessage.setText(R.string.main_title_dashboard);
                util.putString("key", "this is save");
                return true;
            } else if (i == R.id.main_navigation_notifications) {
                mTextMessage.setText(util.getString("key", "failed"));
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        util = new SharedPreferencesUtil(BaseApplication.getContext(), "main");
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
