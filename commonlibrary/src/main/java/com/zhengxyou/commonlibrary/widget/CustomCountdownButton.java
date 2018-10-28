package com.zhengxyou.commonlibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 短信验证码倒计时
 */
@SuppressLint("AppCompatCustomView")
public class CustomCountdownButton extends Button {
    private boolean isRun;
    private int time = 60;
    private final Handler mHandler = new Handler();

    public CustomCountdownButton(Context context) {
        super(context);
    }

    public CustomCountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start() {
        if (!isRun) {
            isRun = true;
            time = 60;
            run();
        }
    }

    private void stop() {
        this.setEnabled(true);
        this.setText("重新获取");
//		TimeButton.this.setBackgroundColor(Color.parseColor("#ff2b00"));
        time = 0;
        isRun = false;
    }

    @SuppressLint("SetTextI18n")
    private void run() {
        if (!isRun) {
            return;
        }
        mHandler.postDelayed(() -> {
            time--;
            if (time > 0) {
                this.setText("(" + time + ")重新获取");
            } else {
                stop();
                return;
            }
            this.setEnabled(false);
            run();

        }, 1000);
    }

}
