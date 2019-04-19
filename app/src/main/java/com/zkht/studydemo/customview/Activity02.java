package com.zkht.studydemo.customview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zkht.studydemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity02 extends AppCompatActivity {

    @BindView(R.id.circle_view)
    CircleView circleView;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.tv)
    TextView tv;
    private AlertDialog dialog;
    private ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02);
        ButterKnife.bind(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                circleView.setText(i);
            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        initDialog();
    }

    private void initDialog() {
        list = new ArrayList<>();
        list.add(R.drawable.blue);
        list.add(R.drawable.black);
        list.add(R.drawable.red);
        list.add(R.drawable.gree);
        list.add(R.drawable.yellow);
        list.add(R.drawable.green);
        final String[] color = {"蓝色", "黑色", "红色", "灰色", "黄色", "绿色"};
        DialogAdapter adapter = new DialogAdapter(list, this);
        dialog = new AlertDialog
                .Builder(this)
                .setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("WHY", i + "");
                        dialog.dismiss();
                        tv.setText("你选的颜色是:\r\r\r\r\r" + color[i]);
                    }
                }).create();
    }

    @OnClick({R.id.btn})
    public void onBtnCilck(View view) {
        if (view.getId() == R.id.btn) {
            dialog.show();
        }
    }

}
