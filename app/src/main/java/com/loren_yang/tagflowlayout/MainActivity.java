package com.loren_yang.tagflowlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.loren_yang.adapter.CatalogAdapter;
import com.loren_yang.domain.TagDomain;
import com.loren_yang.listener.RecycleViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    private CatalogAdapter cAdapter;
    private List<TagDomain> dataList;

    private List<Class> activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData(){

        activityList=new ArrayList();
        activityList.add(NormalTagLayoutActivity.class);
        activityList.add(CardViewTagActivity.class);
        activityList.add(RandomTagLayoutActivity.class);

        dataList=new ArrayList<>();
        String[] tagName=getResources().getStringArray(R.array.tagName);
        String[] tagInstruction=getResources().getStringArray(R.array.tagInstruction);
        for(int i=0;i<tagName.length;i++){
            TagDomain td=new TagDomain();
            td.setTagName(tagName[i]);
            td.setTagInstruction(tagInstruction[i]);
            dataList.add(td);
        }
    }

    private void initView(){
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(lm);

        cAdapter=new CatalogAdapter();
        cAdapter.setData(dataList);
        recycleView.setAdapter(cAdapter);
        cAdapter.setmOnItemOnClickListener(new RecycleViewListener.OnItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int postioon, String tagName) {
                Toast.makeText(MainActivity.this,tagName+"::"+postioon,Toast.LENGTH_LONG).show();
                if(activityList.get(postioon)!=null) {
                    Intent intent = new Intent(MainActivity.this, activityList.get(postioon));
                    startActivity(intent);
                }
            }

        });
    }

}
