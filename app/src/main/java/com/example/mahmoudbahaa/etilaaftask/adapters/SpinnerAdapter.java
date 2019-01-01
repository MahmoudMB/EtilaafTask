package com.example.mahmoudbahaa.etilaaftask.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.models.Option;

import java.util.List;

/**
 * Created by MahmoudBahaa on 31/12/2018.
 */


    public class SpinnerAdapter extends ArrayAdapter<Option> {

        LayoutInflater flater;

        public SpinnerAdapter(Activity context, int resouceId, int textviewId, List<Option> list){

            super(context,resouceId,textviewId, list);
//        flater = context.getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return rowview(convertView,position);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return rowview(convertView,position);
        }

        private View rowview(View convertView , int position){

            Option rowItem = getItem(position);

            viewHolder holder ;
            View rowview = convertView;

            if (rowview==null) {

                holder = new viewHolder();
                flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowview = flater.inflate(R.layout.item_spinner, null, false);

                holder.txtTitle = (TextView) rowview.findViewById(R.id.title);
                holder.imageView = (ImageView) rowview.findViewById(R.id.icon);
                rowview.setTag(holder);
            }else{
                holder = (viewHolder) rowview.getTag();
            }
            holder.imageView.setImageResource(rowItem.getImage());
            holder.txtTitle.setText(rowItem.getName());

            return rowview;
        }

        private class viewHolder{
            TextView txtTitle;
            ImageView imageView;
        }
    }

