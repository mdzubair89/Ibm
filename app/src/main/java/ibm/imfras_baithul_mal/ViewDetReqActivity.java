package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewDetReqActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseRequests;
    TextView textViewReqNo;
    TextView textViewReqStatus;
    TextView textViewReqPurpose;
    TextView textViewReqDate;
    TextView textViewReqDateFixed;
    TextView textViewReqConPer;
    TextView textViewReqConPerFixed;
    TextView textViewReqPhone;
    TextView textViewReqPhoneFixed;
    TextView textViewReqPer;
    TextView textViewReqPerFixed;
    TextView textViewReqAdd;
    TextView textViewReqAddFixed;
    TextView textViewReqSepList;
    TextView textViewReqSepListFixed;
    TextView textViewReqIbmAmt;
    TextView textViewReqSepAmt;
    TextView textViewReqTotAmt;
    Button buttonEdit;
    Button buttonBack;
    Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_det_req);

        request = new Request();

        textViewReqNo = (TextView) findViewById(R.id.textViewRequestNo);
        textViewReqStatus = (TextView) findViewById(R.id.textViewRequestStatus);
        textViewReqPurpose = (TextView) findViewById(R.id.textViewReqPur);
        textViewReqDate = (TextView) findViewById(R.id.textViewReqDate);
        textViewReqDateFixed = (TextView) findViewById(R.id.textViewReqDateFixed);
        textViewReqConPer = (TextView) findViewById(R.id.textViewReqConPer);
        textViewReqConPerFixed = (TextView) findViewById(R.id.textViewReqConPerFixed);
        textViewReqPhone = (TextView) findViewById(R.id.textViewReqPhone);
        textViewReqPhoneFixed = (TextView) findViewById(R.id.textViewReqPhoneFixed);
        textViewReqPer = (TextView) findViewById(R.id.textViewReqPer);
        textViewReqPerFixed = (TextView) findViewById(R.id.textViewReqPerFixed);
        textViewReqAdd = (TextView) findViewById(R.id.textViewReqAdd);
        textViewReqAddFixed = (TextView) findViewById(R.id.textViewReqAddFixed);
        textViewReqSepList = (TextView) findViewById(R.id.textViewReqSepList);
        textViewReqSepListFixed = (TextView) findViewById(R.id.textViewReqSepListFixed);
        textViewReqIbmAmt = (TextView) findViewById(R.id.textViewReqIbmAmt);
        textViewReqSepAmt = (TextView) findViewById(R.id.textViewReqSepAmt);
        textViewReqTotAmt = (TextView) findViewById(R.id.textViewReqTotAmt);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonEdit.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        Intent myIntent = getIntent();
        String stReqNo = myIntent.getStringExtra("requestNo");
        int reqNo = Integer.parseInt(stReqNo);
//        Toast.makeText(this,"Request no:" + reqNo,Toast.LENGTH_LONG).show();

        databaseRequests = FirebaseDatabase.getInstance().getReference("Requests");
        Query query = databaseRequests.orderByChild("requestNo").equalTo(reqNo);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    request.requestNo = (int) messageSnapshot.child("requestNo").getValue(Integer.class);
                    request.requestStatus = (String) messageSnapshot.child("requestStatus").getValue();
                    request.requestDate = (String) messageSnapshot.child("requestDate").getValue();
                    request.requestPurpose = (String) messageSnapshot.child("requestPurpose").getValue();
                    request.requestSepList = (String) messageSnapshot.child("requestSepList").getValue();
                    request.requestPostal = (String) messageSnapshot.child("requestPostal").getValue();
                    request.requestPerson = (String) messageSnapshot.child("requestPerson").getValue();
                    request.requestConPerson = (String) messageSnapshot.child("requestConPerson").getValue();
                    request.requestPhone = (String) messageSnapshot.child("requestPhone").getValue();
                    request.requestIbmAmt = (int) messageSnapshot.child("requestIbmAmt").getValue(Integer.class);
                    request.requestSepAmt = (int) messageSnapshot.child("requestSepAmt").getValue(Integer.class);
                    populateReqLayout(request.requestNo,request.requestStatus,request.requestPurpose,request.requestDate,request.requestConPerson,request.requestPhone
                            ,request.requestPerson,request.requestPostal,request.requestSepList,request.requestIbmAmt,request.requestSepAmt);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void populateReqLayout(int requestNo, String requestStatus,String requestPurpose, String requestDate,  String requestConPerson,
                                   String requestPhone, String requestPerson, String requestPostal,String requestSepList,int requestIbmAmt, int requestSepAmt) {

        int requestTotAmt = requestIbmAmt + requestSepAmt;

        textViewReqNo.setText("REQUEST:" + String.valueOf(requestNo));
        textViewReqStatus.setText("STATUS:" + String.valueOf(requestStatus));
        if(requestStatus.contentEquals("CLOSED")) {
            textViewReqStatus.setTextColor(Color.parseColor("#008000"));
        }
        else
        {
            textViewReqStatus.setTextColor(Color.parseColor("#FF0000"));
        }

        textViewReqPurpose.setText(" " +requestPurpose);

        if(!(requestDate.isEmpty())) {
            textViewReqDate.setText(" " + requestDate);
        }
        else
        {
            textViewReqDate.setVisibility(View.GONE);
            textViewReqDateFixed.setVisibility(View.GONE);

        }
        if(!(requestConPerson.isEmpty())) {
            textViewReqConPer.setText(" " + requestConPerson);
        }
        else
        {
            textViewReqConPer.setVisibility(View.GONE);
            textViewReqConPerFixed.setVisibility(View.GONE);
        }
        if(!(requestPhone.isEmpty())) {
            textViewReqPhone.setText(" " + requestPhone);
        }
        else
        {
            textViewReqPhone.setVisibility(View.GONE);
            textViewReqPhoneFixed.setVisibility(View.GONE);
        }
        if(!(requestPerson.isEmpty())) {
            textViewReqPer.setText(" " + requestPerson);
        }
        else
        {
            textViewReqPer.setVisibility(View.GONE);
            textViewReqPerFixed.setVisibility(View.GONE);
        }
        if(!(requestPostal.isEmpty())) {
            textViewReqAdd.setText(" " + requestPostal);
        }
        else
        {
            textViewReqAdd.setVisibility(View.GONE);
            textViewReqAddFixed.setVisibility(View.GONE);
        }
        if(!(requestSepList.isEmpty())) {
            textViewReqSepList.setText(" " + requestSepList);
        }
        else
        {
            textViewReqSepList.setVisibility(View.GONE);
            textViewReqSepListFixed.setVisibility(View.GONE);
        }
        textViewReqIbmAmt.setText(" " +String.valueOf(requestIbmAmt));
        textViewReqSepAmt.setText(" " +String.valueOf(requestSepAmt));
        textViewReqTotAmt.setText(" " +String.valueOf(requestTotAmt));
    }

    @Override
    public void onClick(View view) {

        if(view == buttonBack)
        {

        }
        else if(view == buttonEdit)
        {
            Intent myIntent = new Intent(ViewDetReqActivity.this, AddRequestActivity.class);
            myIntent.putExtra("requestNo", String.valueOf(request.requestNo));
            myIntent.putExtra("requestStatus", String.valueOf(request.requestStatus));
            myIntent.putExtra("requestPurpose", request.requestPurpose);
            myIntent.putExtra("requestDate", request.requestDate);
            myIntent.putExtra("requestConPerson", request.requestConPerson);
            myIntent.putExtra("requestPostal", request.requestPostal);
            myIntent.putExtra("requestPhone", request.requestPhone);
            myIntent.putExtra("requestPerson", request.requestPerson);
            myIntent.putExtra("requestSepList", request.requestSepList);
            myIntent.putExtra("requestIbmAmt", String.valueOf(request.requestIbmAmt));
            myIntent.putExtra("requestSepAmt", String.valueOf(request.requestSepAmt));
            startActivity(myIntent);

        }


    }
}
