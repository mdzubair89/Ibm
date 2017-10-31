package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewDetReqActivity extends AppCompatActivity {

    DatabaseReference databaseRequests;
    TextView textViewReqNo;
    TextView textViewReqPurpose;
    TextView textViewReqDate;
    TextView textViewReqConPer;
    TextView textViewReqPhone;
    TextView textViewReqPer;
    TextView textViewReqAdd;
    TextView textViewReqSepList;
    TextView textViewReqIbmAmt;
    TextView textViewReqSepAmt;
    TextView textViewReqTotAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_det_req);

        textViewReqNo = (TextView) findViewById(R.id.textViewRequestNo);
        textViewReqPurpose = (TextView) findViewById(R.id.textViewReqPur);
        textViewReqDate = (TextView) findViewById(R.id.textViewReqDate);
        textViewReqConPer = (TextView) findViewById(R.id.textViewReqConPer);
        textViewReqPhone = (TextView) findViewById(R.id.textViewReqPhone);
        textViewReqPer = (TextView) findViewById(R.id.textViewReqPer);
        textViewReqAdd = (TextView) findViewById(R.id.textViewReqAdd);
        textViewReqSepList = (TextView) findViewById(R.id.textViewReqSepList);
        textViewReqIbmAmt = (TextView) findViewById(R.id.textViewReqIbmAmt);
        textViewReqSepAmt = (TextView) findViewById(R.id.textViewReqSepAmt);
        textViewReqTotAmt = (TextView) findViewById(R.id.textViewReqTotAmt);

        Intent myIntent = getIntent();
        String stReqNo = myIntent.getStringExtra("requestNo");
        int reqNo = Integer.parseInt(stReqNo);
        Toast.makeText(this,"Request no:" + reqNo,Toast.LENGTH_LONG).show();

        databaseRequests = FirebaseDatabase.getInstance().getReference("Requests");
        Query query = databaseRequests.orderByChild("requestNo").equalTo(reqNo);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    int requestNo = (int) messageSnapshot.child("requestNo").getValue(Integer.class);
                    String requestDate = (String) messageSnapshot.child("requestDate").getValue();
                    String requestPurpose = (String) messageSnapshot.child("requestPurpose").getValue();
                    String requestSepList = (String) messageSnapshot.child("requestSepList").getValue();
                    String requestPostal = (String) messageSnapshot.child("requestPostal").getValue();
                    String requestPerson = (String) messageSnapshot.child("requestPerson").getValue();
                    String requestConPerson = (String) messageSnapshot.child("requestConPerson").getValue();
                    String requestPhone = (String) messageSnapshot.child("requestPhone").getValue();
                    int requestIbmAmt = (int) messageSnapshot.child("requestIbmAmt").getValue(Integer.class);
                    int requestSepAmt = (int) messageSnapshot.child("requestSepAmt").getValue(Integer.class);
                    populateReqLayout(requestNo,requestPurpose,requestDate,requestConPerson,requestPhone
                            ,requestPerson,requestPostal,requestSepList,requestIbmAmt,requestSepAmt);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void populateReqLayout(int requestNo,String requestPurpose, String requestDate,  String requestConPerson,
                                   String requestPhone, String requestPerson, String requestPostal,String requestSepList,int requestIbmAmt, int requestSepAmt) {

        int requestTotAmt = requestIbmAmt + requestSepAmt;

        textViewReqNo.setText("Request:" + String.valueOf(requestNo));
        textViewReqPurpose.setText(requestPurpose);
        textViewReqDate.setText(requestDate);
        textViewReqConPer.setText(requestConPerson);
        textViewReqPhone.setText(requestPhone);
        textViewReqPer.setText(requestPerson);
        textViewReqAdd.setText(requestPostal);
        textViewReqSepList.setText(requestSepList);
        textViewReqIbmAmt.setText(String.valueOf(requestIbmAmt));
        textViewReqSepAmt.setText(String.valueOf(requestSepAmt));
        textViewReqTotAmt.setText(String.valueOf(requestTotAmt));
    }
}
