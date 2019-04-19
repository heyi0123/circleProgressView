package com.zkht.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zkht.studydemo.ScrollZoomView.ScorllZoomActivity;
import com.zkht.studydemo.customview.Activity02;
import com.zkht.studydemo.hoverheaderrecyclerview.Activity01;
import com.zkht.studydemo.hoverheaderrecyclerview.CusAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView listView;
    private ArrayList<String> list;
    private CusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewAndData();
    }

    private void initViewAndData() {
        list = new ArrayList<>();
        list.add("分组头部悬停列表");
        list.add("自定义环形进度");
        list.add("ListView头部缩放");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(MainActivity.this, Activity01.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Activity02.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ScorllZoomActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
