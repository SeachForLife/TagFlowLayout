package com.loren_yang.tagflowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.loren_yang.taglayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardViewTagActivity extends AppCompatActivity {

    @BindView(R.id.card_taglayout)
    TagFlowLayout cardTaglayout;
    @BindView(R.id.card_taglayout1)
    TagFlowLayout cardTaglayout1;

    private LayoutInflater mInflater;

    private String[] mValues = new String[]
            {
                    "android", "RecycleView", "ListView",
                    "GPS", "RFID", "安卓", "快速编程",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_tag);
        ButterKnife.bind(this);

        init();
        System.out.println("enter the CardVeiw");
    }

    private void init() {
        mInflater = LayoutInflater.from(this);
        for (int i = 0; i < mValues.length; i++) {

            CardView rl1 = (CardView) mInflater.inflate(R.layout.context, cardTaglayout, false);
            TextView tx1 = (TextView) rl1.findViewById(R.id.text2);
            tx1.setText(mValues[i]);
            tx1.setPadding(25, 25, 25, 25);
            cardTaglayout.addView(rl1);

            CardView rl2 = (CardView) mInflater.inflate(R.layout.context, cardTaglayout1, false);
            TextView tx = (TextView) rl2.findViewById(R.id.text2);
            tx.setText(mValues[i]);
            tx.setPadding(25, 25, 25, 25);
            cardTaglayout1.addView(rl2);

        }

    }
}
