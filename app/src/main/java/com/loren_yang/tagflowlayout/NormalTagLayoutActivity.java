package com.loren_yang.tagflowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loren_yang.taglayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NormalTagLayoutActivity extends AppCompatActivity {

    @BindView(R.id.normal_taglayout)
    TagFlowLayout normalTaglayout;
    @BindView(R.id.normal_taglayout1)
    TagFlowLayout normalTaglayout1;

    private LayoutInflater mInflater;
    private String[] mValues = new String[]
            {
                    "android", "RecycleView", "ListView",
                    "GPS", "RFID", "安卓", "快速编程",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_tag_layout);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mInflater = LayoutInflater.from(this);
        for (int i = 0; i < mValues.length; i++) {

            TextView tv1=new TextView(NormalTagLayoutActivity.this);
            tv1.setText(mValues[i]);
            tv1.setBackgroundResource(R.drawable.flag_1);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            tv1.setLayoutParams(params1);
            normalTaglayout.addView(tv1);

            TextView tv2=new TextView(NormalTagLayoutActivity.this);
            tv2.setText(mValues[i]);
            tv2.setBackgroundResource(R.drawable.flag);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            tv2.setLayoutParams(params2);
            normalTaglayout1.addView(tv2);
        }
    }
}
