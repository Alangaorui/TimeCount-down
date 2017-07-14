package com.timefortime;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button,btn;
    private TextView mHour,mMins,mMiao;
    private Handler handler = new Handler();
    private int one=0,tow=0,three=0;
    int miao = 0;
    int minf = 0;
    int hour = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btnr);
        btn = (Button) findViewById(R.id.btnrs);
        mHour = (TextView) findViewById(R.id.hour);
        mMins = (TextView) findViewById(R.id.mint);
        mMiao = (TextView) findViewById(R.id.miao);
        if (one == 0 && tow==0 && three == 0) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //同时计时器时分秒这样是有一定的时间误差，是在程序跑的时候存在的误差
                    handler.post(myRunnable);
                    handler.post(towRunnable);
                    handler.post(HourRunnable);

                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //暂停
                    handler.removeCallbacks(myRunnable);
                    handler.removeCallbacks(towRunnable);
                    handler.removeCallbacks(HourRunnable);
                    miao = 0;
                    minf = 0;
                    hour = 0;
                }
            });
        } else if (one != 0 || tow!=0 || three != 0){
            miao=one;
            minf = tow;
            hour = three;
            if (minf <10) {
                mMins.setText("0"+String.valueOf(minf));
            } else {
                mMins.setText(String.valueOf(minf));
            }
            if (hour <10) {
                mHour.setText("0"+String.valueOf(hour));
            } else {
                mHour.setText(String.valueOf(hour));
            }
            handler.post(myRunnable);
        }
    }


    //时间不为空进来的时候加载
    private Runnable myRunnable= new Runnable() {
        public void run() {
                handler.postDelayed(this, 1000);
                miao++;
                if (miao<10) {
                    mMiao.setText("0"+String.valueOf(miao));
                } else if (miao>9 && miao<60){
                    mMiao.setText(String.valueOf(miao));
                } else if (miao>59) {
                    miao = 0;
                    mMiao.setText("0"+String.valueOf(miao));
                    String string = mMins.getText().toString();
                    int i = Integer.parseInt(string);
                    if ((i+1)<10) {
                        mMins.setText("0"+String.valueOf(i+1));
                    } else if ((i+1)>9 && (i+1) <60) {
                        mMins.setText(String.valueOf(i+1));
                    } else if ((i+1) >59) {
                        mMins.setText("00");
                        String string1 = mHour.getText().toString();
                        int j = Integer.parseInt(string1);
                        if ((j+1) <10) {
                            mHour.setText("0"+String.valueOf(j+1));
                        } else {
                            mHour.setText(String.valueOf(j+1));
                        }
                    }

                }
        }
    };


    private Runnable oneRunnable = new Runnable() {
        @Override
        public void run() {
                handler.postDelayed(this, 1000);
            miao++;
                 if (miao > 1 && miao < 10) {
                     mMiao.setText("0" + String.valueOf(miao));
                } else if (miao > 10 && miao < 60) {
                     mMiao.setText(String.valueOf(miao - 1));
                } else if (miao > 59) {
                     miao = 0;
                     mMiao.setText("0" + String.valueOf(miao));
                }
        }
    };

    private Runnable towRunnable = new Runnable() {
        @Override
        public void run() {
                handler.postDelayed(this, 60 * 1000);
                minf++;
                if (minf == 1) {
                    mMins.setText("00");
                } else if (minf > 1 && minf < 11) {
                    mMins.setText("0" + String.valueOf(minf - 1));
                } else if (minf > 10 && minf < 60) {
                    mMins.setText(String.valueOf(minf - 1));
                } else if (minf > 59) {
                    minf = 0;
                    mMins.setText("0" + String.valueOf(minf));
                }

        }
    };


    private Runnable HourRunnable = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(this, 60 * 60 * 1000);
            hour ++;
            if (hour == 1) {
                mHour.setText("00");
            } else if (hour>1 && hour<11) {
                mHour.setText("0"+String.valueOf(hour-1));
            } else{
                mHour.setText(String.valueOf(hour-1));
            }
        }
    };

}
