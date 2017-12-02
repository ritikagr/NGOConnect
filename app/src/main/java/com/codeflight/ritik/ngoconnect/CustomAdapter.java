package com.codeflight.ritik.ngoconnect;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RAJAT-PC on 02-12-2017.
 */
public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<FeedItem> data;
    private Activity activity;
    private LayoutInflater inflater;
    private FeedItem temp = null;

    public CustomAdapter(Activity a, ArrayList<FeedItem> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        if(data.size()<0){
            return 0;
        }
        return data.size();
    }

    public FeedItem getItem(int position){
        return data.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder{
        public TextView id,activity,ngo_name;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View vi = convertView;
        ViewHolder holder;

        if(convertView == null){
            vi = inflater.inflate(R.layout.feed_item, null);
            holder = new ViewHolder();
            holder.activity = (TextView) vi.findViewById(R.id.cause_text);
            holder.ngo_name = (TextView)vi.findViewById(R.id.name_text);
            vi.setTag(holder);
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0) {
            holder.activity.setText("No Data");
        }
        else
        {
            temp = null;
            temp = data.get( position );
            holder.activity.setText(temp.Activity);
            holder.ngo_name.setText(temp.NGOName);
        }
        return vi;
    }

    public void onClick(View v){
    }
}
