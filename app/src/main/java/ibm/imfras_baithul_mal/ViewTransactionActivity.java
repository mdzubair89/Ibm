package ibm.imfras_baithul_mal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static ibm.imfras_baithul_mal.Constants.FIFTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.FIRST_COLUMN;
import static ibm.imfras_baithul_mal.Constants.FOURTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SECOND_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SEVENTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SIXTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.THIRD_COLUMN;

public class ViewTransactionActivity extends AppCompatActivity {
    TextView columnHeader1;
    TextView columnHeader2;
    TextView columnHeader3;
    TextView columnHeader4;
    TextView columnHeader5;
    /*TextView columnHeader6;
    TextView columnHeader7;*/
    TextView txtFirst;
    ListView listView;
    private ArrayList<HashMap<String, String>> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        TextView columnHeader1 = (TextView) findViewById(R.id.transaction_header_line1);
        TextView columnHeader2 = (TextView) findViewById(R.id.transaction_header_line2);
        TextView columnHeader3 = (TextView) findViewById(R.id.transaction_header_line3);
        TextView columnHeader4 = (TextView) findViewById(R.id.transaction_header_line4);
        TextView columnHeader5 = (TextView) findViewById(R.id.transaction_header_line5);
        /*TextView columnHeader6 = (TextView) findViewById(R.id.transaction_header_line6);
        TextView columnHeader7 = (TextView) findViewById(R.id.transaction_header_line7);*/

        columnHeader1.setText("DATE");
        columnHeader2.setText("PURPOSE");
        columnHeader3.setText("C/D");
        columnHeader4.setText("ACC.");
        columnHeader5.setText("BAL.");
        /*columnHeader6.setText("ACCOUNT");
        columnHeader7.setText("BALANCE");*/
        listView=(ListView)findViewById(R.id.transactionListView1);
        populateList();

    }

    private void populateList() {

        list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put(FIRST_COLUMN, "1");
        temp.put(SECOND_COLUMN, "2");
        temp.put(THIRD_COLUMN, "3");
        temp.put(FOURTH_COLUMN, "4");
        temp.put(FIFTH_COLUMN, "5");
        /*temp.put(SIXTH_COLUMN, "6");
        temp.put(SEVENTH_COLUMN, "7");*/
        list.add(temp);
        HashMap<String, String> temp1 = new HashMap<String, String>();
        temp.put(FIRST_COLUMN, "Hi");
        temp.put(SECOND_COLUMN, "my");
        temp.put(THIRD_COLUMN, "love");
        temp.put(FOURTH_COLUMN, "!");
        temp.put(FIFTH_COLUMN, "!");
        /*temp.put(SIXTH_COLUMN, "6");
        temp.put(SEVENTH_COLUMN, "7");*/
        list.add(temp1);

        /*ReadFromIBM readIbm = new ReadFromIBM();
        readIbm.getResultsFromApi();*/
        //readIbm.readOnActivityResult();
        TransactionListViewAdapter adapter= new TransactionListViewAdapter(ViewTransactionActivity.this,list);
        listView.setAdapter(adapter);
    }
}
