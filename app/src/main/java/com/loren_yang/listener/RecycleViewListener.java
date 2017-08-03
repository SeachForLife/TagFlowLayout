package com.loren_yang.listener;

import android.view.View;

/**
 * Created by Loren Yang on 2017/8/2.
 */

public class RecycleViewListener {

    public interface OnItemOnClickListener{
        void onItemOnClick(View view, int Postioon,String tagName);
    }
}
