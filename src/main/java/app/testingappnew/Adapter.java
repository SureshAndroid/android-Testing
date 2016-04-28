package app.testingappnew;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.testingappnew.Bean;
import app.testingappnew.R;

/**
 * Created by ASUS on 29-Mar-16.
 */
public class Adapter extends BaseAdapter {
    Context context;
    List<Bean> rowItems;

    public Adapter(Context context, List<Bean> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView txtCityname;
        TextView txtpopulation;
        TextView txtLang;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_data, null);
            holder = new ViewHolder();
            holder.txtCityname = (TextView) convertView.findViewById(R.id.cityname);
            holder.txtpopulation = (TextView) convertView.findViewById(R.id.population);
            holder.txtLang = (TextView) convertView.findViewById(R.id.lng);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtCityname.setText(rowItems.get(position).getcityname());
        holder.txtpopulation.setText(rowItems.get(position).getPopulation());
        holder.txtLang.setText(rowItems.get(position).getlng());
        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}
