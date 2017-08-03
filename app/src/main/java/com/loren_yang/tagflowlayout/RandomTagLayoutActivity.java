package com.loren_yang.tagflowlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loren_yang.taglayout.RandomTextView;
import com.loren_yang.taglayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RandomTagLayoutActivity extends AppCompatActivity {

    @BindView(R.id.random_taglayout)
    TagFlowLayout randomTaglayout;
    @BindView(R.id.random_taglayout1)
    TagFlowLayout randomTaglayout1;

    private String[] mValues = new String[]
            {
                    "android", "RecycleView", "ListView",
                    "GPS", "RFID", "安卓", "快速编程",
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_tag_layout);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        for (int i = 0; i < mValues.length; i++) {
            RandomTextView rt = new RandomTextView(RandomTagLayoutActivity.this);
            rt.setmText(mValues[i]);
            rt.setmSize(40);
            int[] colo = {Color.RED, getResources().getColor(R.color.oranger), 0xFF55FF3B};
            rt.setRandomColor(colo);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            rt.setLayoutParams(params);
            randomTaglayout.addView(rt);

            RandomTextView rt1 = new RandomTextView(RandomTagLayoutActivity.this);
            rt1.setmText(mValues[i]);
            rt1.setmSize(60);
            int[] colo1 = {Color.BLUE, getResources().getColor(R.color.oranger), 0xFF000000};
            rt1.setRandomColor(colo1);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            rt1.setLayoutParams(params1);
            randomTaglayout1.addView(rt1);
        }

    }
}
