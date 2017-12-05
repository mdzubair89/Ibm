package ibm.imfras_baithul_mal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static ibm.imfras_baithul_mal.Constants.FIRST_COLUMN;
import static ibm.imfras_baithul_mal.Constants.FOURTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SECOND_COLUMN;
import static ibm.imfras_baithul_mal.Constants.THIRD_COLUMN;
import static ibm.imfras_baithul_mal.Constants.FIFTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SIXTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SEVENTH_COLUMN;

/**
 * Created by mdzub on 03-12-2017.
 */

public class TransactionListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    TextView txtFifth;
    /*TextView txtSixth;
    TextView txtSeventh;*/
    public TransactionListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
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
        TextView txtFourth;
        TextView txtFifth;
        /*TextView txtSixth;
        TextView txtSeventh;*/

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.transaction_layout, null);
            holder = new TransactionListViewAdapter.ViewHolder();

            holder.txtFirst=(TextView) convertView.findViewById(R.id.TransactionTextFirst);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.TransactionTextSecond);
            holder.txtThird=(TextView) convertView.findViewById(R.id.TransactionTextThird);
            holder.txtFourth=(TextView) convertView.findViewById(R.id.TransactionTextFourth);
            holder.txtFifth=(TextView) convertView.findViewById(R.id.TransactionTextFifth);
            /*holder.txtSixth=(TextView) convertView.findViewById(R.id.TransactionTextSixth);
            holder.txtSeventh=(TextView) convertView.findViewById(R.id.TransactionTextSeventh);*/
            convertView.setTag(holder);

        }
        else
        {
            holder = (TransactionListViewAdapter.ViewHolder) convertView.getTag();
        }


        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));
        holder.txtThird.setText(map.get(THIRD_COLUMN));
        holder.txtFourth.setText(map.get(FOURTH_COLUMN));
        holder.txtFifth.setText(map.get(FIFTH_COLUMN));
       /* holder.txtThird.setText(map.get(SIXTH_COLUMN));
        holder.txtFourth.setText(map.get(SEVENTH_COLUMN));*/

        return convertView;
    }
}
