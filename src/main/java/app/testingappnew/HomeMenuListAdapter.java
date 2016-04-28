package app.testingappnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ASUS on 20-Apr-16.
 */
public class HomeMenuListAdapter extends BaseAdapter {

    Context context;
    String[] mTitle;
    int[] mIcon;
    LayoutInflater inflater;

    public HomeMenuListAdapter(Context context, String[] title, int[] icon)
    {
        this.context = context;
        this.mTitle = title;
        this.mIcon = icon;
    }


    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public Object getItem(int position) {        return position;    }

    @Override
    public long getItemId(int position) {        return position;    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtTitle;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawerlistitem,parent,false);

        txtTitle = (TextView) itemView.findViewById(R.id.title);

        txtTitle.setText(mTitle[position]);
        return itemView;
    }
}
