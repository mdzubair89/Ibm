package ibm.imfras_baithul_mal;

/**
 * Created by mohamedzubair on 10/28/2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static ibm.imfras_baithul_mal.Constants.FIRST_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SECOND_COLUMN;
import static ibm.imfras_baithul_mal.Constants.THIRD_COLUMN;

public class SubscriptionListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;

    public String layout;

    public SubscriptionListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity = activity;
        this.list = list;
        //this.layout = layout;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder{
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView = inflater.inflate(R.layout.subscription_layout, null);

            holder = new ViewHolder();

            holder.txtFirst=(TextView) convertView.findViewById(R.id.TextFirst);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.TextSecond);
            holder.txtThird=(TextView) convertView.findViewById(R.id.TextThird);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));
        holder.txtThird.setText(map.get(THIRD_COLUMN));

        return convertView;
    }

}