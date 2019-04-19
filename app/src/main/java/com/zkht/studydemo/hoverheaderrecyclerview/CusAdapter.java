package com.zkht.studydemo.hoverheaderrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkht.studydemo.R;

import java.util.ArrayList;

/**
 * @author 学无止境 ~ 2019/4/16 ~ 16:21
 */
public class CusAdapter extends RecyclerView.Adapter<CusAdapter.CardViewHolder> {

    public ArrayList<Bean> beans;
    public Context context;

    public CusAdapter(ArrayList<Bean> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.tv.setText(beans.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    //判断position对应的Item是否是组的第一项
    public boolean isItemheader(int position) {
        if (position == 0) {
            return true;
        } else {
            String lastGroupName = beans.get(position - 1).getGroupName();
            String currentGroupName = beans.get(position).getGroupName();
            //判断上一个数据的组别和下一个数据的组别是否一致，如果不一致则是不同组，也就是为第一项（头部）
            if (lastGroupName.equals(currentGroupName)) {
                return false;
            } else {
                return true;
            }
        }
    }

    //获取position对应的item组名
    public String getGroupName(int position) {
        return beans.get(position).getGroupName();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public CardViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
        }
    }

}
