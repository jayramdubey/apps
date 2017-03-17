package com.jay_dubey.my_raipur_riapur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jay_Dubey on 3/4/2017.
 */

public class BaseAdapterForList extends BaseAdapter {
    Context context;
    ArrayList<NameValue> arrayList;

    public BaseAdapterForList(Context context, ArrayList<NameValue> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_for_row, null);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            viewHolder.txtList = (TextView) convertView.findViewById(R.id.txtList);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imgIcon.setImageResource(((NameValue) arrayList.get(position)).getImage());
        viewHolder.txtList.setText(((NameValue) arrayList.get(position)).getName());


        return convertView;
    }

    class ViewHolder {
        ImageView imgIcon;
        TextView txtList;
    }
}
