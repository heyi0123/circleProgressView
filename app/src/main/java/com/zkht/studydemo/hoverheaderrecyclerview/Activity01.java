package com.zkht.studydemo.hoverheaderrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zkht.studydemo.R;

import java.util.ArrayList;

public class Activity01 extends AppCompatActivity {

    private ArrayList<Bean> list;
    private CusAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);
        initViewAndData();
    }

    private void initViewAndData() {
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new Bean(String.format("第一组%d号", i + 1), "第一组"));
        }
        for (int i = 0; i < 6; i++) {
            list.add(new Bean(String.format("第二组%d号", i + 1), "第二组"));
        }
        for (int i = 0; i < 6; i++) {
            list.add(new Bean(String.format("第三组%d号", i + 1), "第三组"));
        }
        for (int i = 0; i < 6; i++) {
            list.add(new Bean(String.format("第四组%d号", i + 1), "第四组"));
        }
        for (int i = 0; i < 6; i++) {
            list.add(new Bean(String.format("第五组%d号", i + 1), "第五组"));
        }
        for (int i = 0; i < 6; i++) {
            list.add(new Bean(String.format("第六组%d号", i + 1), "第六组"));
        }


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemDecoration(this));
        adapter = new CusAdapter(list, this);
        recyclerView.setAdapter(adapter);

    }
}
