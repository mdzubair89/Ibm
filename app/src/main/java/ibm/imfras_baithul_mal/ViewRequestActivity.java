package ibm.imfras_baithul_mal;

import static ibm.imfras_baithul_mal.Constants.FIRST_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SECOND_COLUMN;
import static ibm.imfras_baithul_mal.Constants.THIRD_COLUMN;
import static ibm.imfras_baithul_mal.Constants.FOURTH_COLUMN;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ViewRequestActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;

    DatabaseReference databaseRequests;
    private TextView textViewRequestNo;
    private EditText editTextRequestPurpose;
    TextView columnHeader1;
    TextView columnHeader2;
    TextView columnHeader3;
    TextView columnHeader4;
    TextView txtFirst;
    ListView listView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        progressDialog = new ProgressDialog(this);

        TextView columnHeader1 = (TextView) findViewById(R.id.header_line1);
        TextView columnHeader2 = (TextView) findViewById(R.id.header_line2);
        TextView columnHeader3 = (TextView) findViewById(R.id.header_line3);
        TextView columnHeader4 = (TextView) findViewById(R.id.header_line4);

        columnHeader1.setText("REQ NO");
        columnHeader2.setText("PURPOSE");
        columnHeader3.setText("IBM");
        columnHeader4.setText("SEP");

        listView=(ListView)findViewById(R.id.listView1);
        populateList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
                TextView textView = (TextView) arg1.findViewById(R.id.TextFirst);
                String reqNo = textView.getText().toString();
                Intent myIntent = new Intent(ViewRequestActivity.this, ViewDetReqActivity.class);
                myIntent.putExtra("requestNo", reqNo);
                //myIntent.putExtra("requestStatus", reqNo);
                startActivity(myIntent);
            }
        });

        databaseRequests = FirebaseDatabase.getInstance().getReference("Requests");
        progressDialog.setMessage("Getting all Requests information. Please wait...");
        progressDialog.show();
        Query query = databaseRequests.orderByChild("requestNo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)

            {       list.clear();
            progressDialog.dismiss();
                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    int requestNo = (int) messageSnapshot.child("requestNo").getValue(Integer.class);
                    String requestDate = (String) messageSnapshot.child("requestDate").getValue();
                    String requestPurpose = (String) messageSnapshot.child("requestPurpose").getValue();
                    int requestIbmAmt = (int) messageSnapshot.child("requestIbmAmt").getValue(Integer.class);
                    int requestSepAmt = (int) messageSnapshot.child("requestSepAmt").getValue(Integer.class);
                    populateRequests(requestNo,requestPurpose,requestIbmAmt,requestSepAmt);

                }
                reverseListAndSetAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void reverseListAndSetAdapter() {
        Collections.reverse(list);
        ListViewAdapter adapter= new ListViewAdapter(ViewRequestActivity.this,list);
        listView.setAdapter(adapter);
    }

    private void populateRequests(int requestNo, String requestPurpose, int requestIbmAmt, int requestSepAmt) {



        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(FIRST_COLUMN, String.valueOf(requestNo));
        temp.put(SECOND_COLUMN,requestPurpose);
        temp.put(THIRD_COLUMN, String.valueOf(requestIbmAmt));
        temp.put(FOURTH_COLUMN, String.valueOf(requestSepAmt));
        list.add(temp);

        /*ListViewAdapter adapter= new ListViewAdapter(ViewRequestActivity.this,list);
        listView.setAdapter(adapter);*/

    }

    private void populateList() {

        list=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(FIRST_COLUMN,"");
        temp.put(SECOND_COLUMN,"");
        temp.put(THIRD_COLUMN,"");
        temp.put(FOURTH_COLUMN,"");
        list.add(temp);

/*        HashMap<String,String> temp1=new HashMap<String, String>();
        temp1.put(FIRST_COLUMN,"Hi");
        temp1.put(SECOND_COLUMN,"This ");
        temp1.put(THIRD_COLUMN,"is ");
        temp1.put(FOURTH_COLUMN,"Himana");
        list.add(temp1);
        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put(FIRST_COLUMN,"Hi");
        temp2.put(SECOND_COLUMN,"This ");
        temp2.put(THIRD_COLUMN,"is ");
        temp2.put(FOURTH_COLUMN,"Mohamed");
        list.add(temp2);
        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put(FIRST_COLUMN,"Hi");
        temp3.put(SECOND_COLUMN,"This ");
        temp3.put(THIRD_COLUMN,"is ");
        temp3.put(FOURTH_COLUMN,"Parveen");
        list.add(temp3);*/

    }

}
