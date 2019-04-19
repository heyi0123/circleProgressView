package com.zkht.studydemo.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zkht.studydemo.R;

import java.util.ArrayList;

/**
 * @author 学无止境 ~ 2019/4/19 ~ 12:40
 */
public class DialogAdapter extends BaseAdapter {

    ArrayList<Integer> list;
    Context context;
    LayoutInflater inflater;

    public DialogAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if (i == getCount() || list == null) {
            return null;
        }
        return list.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_dialog, null);
            holder.img = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setImageResource((Integer) getItem(i));
        return convertView;
    }

    public static class ViewHolder {
        public ImageView img;
    }
}
