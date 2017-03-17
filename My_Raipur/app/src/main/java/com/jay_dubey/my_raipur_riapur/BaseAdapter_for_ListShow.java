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
 * Created by Jay_Dubey on 3/7/2017.
 */

public class BaseAdapter_for_ListShow extends BaseAdapter {


    Context context;
    ArrayList<FacilityList> arrayListFacility;

    public BaseAdapter_for_ListShow(Context context, ArrayList<FacilityList> arrayListFacility) {
        this.context = context;
        this.arrayListFacility = arrayListFacility;

    }


    @Override
    public int getCount() {
        return arrayListFacility.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListFacility.get(position);
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
            convertView = inflater.inflate(R.layout.layout_for_sho_list, null);
            viewHolder.imgTol = (ImageView) convertView.findViewById(R.id.imgTol);
            viewHolder.edtSho = (TextView) convertView.findViewById(R.id.edtSho);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgTol.setImageResource(((FacilityList) arrayListFacility.get(position)).getImage());
        viewHolder.edtSho.setText(((FacilityList) arrayListFacility.get(position)).getName() + "\n" +
                ((FacilityList) arrayListFacility.get(position)).getAddress());

        return convertView;

    }

    class ViewHolder {
        ImageView imgTol;
        TextView edtSho;
    }
}
