package com.zkht.studydemo.ScrollZoomView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.zkht.studydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScorllZoomActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    SlideZoomListView listView;
    String[] title;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorll_zoom);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title = new String[]{
                "item------------1",
                "item------------2",
                "item------------3",
                "item------------4",
                "item------------5",
                "item------------6",
                "item------------7",
                "item------------8",
                "item------------9",
                "item------------10",
                "item------------11",
                "item------------12",
                "item------------13",
                "item------------14"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_view, null);
        listView.setAdapter(adapter);
        listView.addHeaderView(headerView);
        iv = headerView.findViewById(R.id.header_img);
        listView.setZoomImageView(iv);
    }
}
