package ibm.imfras_baithul_mal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    TextView textViewReqCommentsFixed;
    TextView textViewReqComments;
    TextView textViewReqIbmAmt;
    TextView textViewReqSepAmt;
    TextView textViewReqTotAmt;

    /* Medical*/
    TextView textViewReqTreatment;
    TextView textViewReqTreatmentFixed;
    TextView textViewReqTreatmentCost;
    TextView textViewReqTreatmentCostFixed;
    TextView textViewReqPatientName;
    TextView textViewReqPatientNameFixed;

    /* Marriage */
    TextView textViewMarriageDataFixed;
    TextView textViewMarriageDate;

    /* Button */
    Button buttonEdit;
    Button buttonDelete;
    Button buttonMenu;
    Button buttonView;
    Request request;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_det_req);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

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
        textViewReqCommentsFixed = (TextView) findViewById(R.id.textViewReqCommentsFixed);
        textViewReqComments = (TextView) findViewById(R.id.textViewReqComments);
        textViewReqIbmAmt = (TextView) findViewById(R.id.textViewReqIbmAmt);
        textViewReqSepAmt = (TextView) findViewById(R.id.textViewReqSepAmt);
        textViewReqTotAmt = (TextView) findViewById(R.id.textViewReqTotAmt);

        /*Medical*/
        textViewReqTreatment = (TextView) findViewById(R.id.textViewReqTreatment);
        textViewReqTreatmentFixed = (TextView) findViewById(R.id.textViewReqTreatmentFixed);
        textViewReqTreatmentCost = (TextView) findViewById(R.id.textViewReqTreatmentCost);
        textViewReqTreatmentCostFixed = (TextView) findViewById(R.id.textViewReqTreatmentCostFixed);
        textViewReqPatientName = (TextView) findViewById(R.id.textViewReqPatientName);
        textViewReqPatientNameFixed = (TextView) findViewById(R.id.textViewReqPatientNameFixed);

        /*Marriage */
        textViewMarriageDate = (TextView) findViewById(R.id.textViewMarriageDate);
        textViewMarriageDataFixed = (TextView) findViewById(R.id.textViewMarrigeDateFixed);

        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonMenu = (Button) findViewById(R.id.buttonMenu);
        buttonView = (Button) findViewById(R.id.buttonView);

        buttonEdit.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonMenu.setOnClickListener(this);
        buttonView.setOnClickListener(this);

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
                    request.requestType = (String) messageSnapshot.child("requestType").getValue();

                    if(messageSnapshot.hasChild("requestComments"))
                        request.requestComments = (String )messageSnapshot.child("requestComments").getValue();

                    if(request.requestType.contentEquals("Medical"))
                    {
                        if(messageSnapshot.hasChild("requestTreatment"))
                        request.requestTreatment = (String) messageSnapshot.child("requestTreatment").getValue();
                        if(messageSnapshot.hasChild("requestTreatmentCost"))
                        request.requestTreatmentCost = (String) messageSnapshot.child("requestTreatmentCost").getValue();
                        if(messageSnapshot.hasChild("requestPatientName"))
                        request.requestPatientName = (String) messageSnapshot.child("requestPatientName").getValue();

                    }
                    else if(request.requestType.contentEquals("Marriage"))
                    {
                        if(messageSnapshot.hasChild("requestMarriageDate"))
                        request.requestMarriageDate = (String) messageSnapshot.child("requestMarriageDate").getValue();
                    }

                    populateReqLayout();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void populateReqLayout() {

        int requestTotAmt = request.requestIbmAmt + request.requestSepAmt;

        textViewReqNo.setText("REQUEST:" + String.valueOf(request.requestNo));
        textViewReqStatus.setText("STATUS:" + String.valueOf(request.requestStatus));
        if(request.requestStatus.contentEquals("CLOSED")) {
            textViewReqStatus.setTextColor(Color.parseColor("#008000"));
        }
        else
        {
            textViewReqStatus.setTextColor(Color.parseColor("#FF0000"));
        }

        textViewReqPurpose.setText(request.requestPurpose);

        if(!(request.requestDate.isEmpty())) {
            textViewReqDate.setText(request.requestDate);
        }
        else
        {
            textViewReqDate.setVisibility(View.GONE);
            textViewReqDateFixed.setVisibility(View.GONE);

        }
        if(!(request.requestConPerson.isEmpty())) {
            textViewReqConPer.setText(request.requestConPerson);
        }
        else
        {
            textViewReqConPer.setVisibility(View.GONE);
            textViewReqConPerFixed.setVisibility(View.GONE);
        }
        if(!(request.requestPhone.isEmpty())) {
            textViewReqPhone.setText(request.requestPhone);
        }
        else
        {
            textViewReqPhone.setVisibility(View.GONE);
            textViewReqPhoneFixed.setVisibility(View.GONE);
        }
        if(!(request.requestPerson.isEmpty())) {
            textViewReqPer.setText(request.requestPerson);
        }
        else
        {
            textViewReqPer.setVisibility(View.GONE);
            textViewReqPerFixed.setVisibility(View.GONE);
        }
        if(!(request.requestPostal.isEmpty())) {
            textViewReqAdd.setText(request.requestPostal);
        }
        else
        {
            textViewReqAdd.setVisibility(View.GONE);
            textViewReqAddFixed.setVisibility(View.GONE);
        }
        if(!(request.requestSepList.isEmpty())) {
            textViewReqSepList.setText(request.requestSepList);
        }
        else
        {
            textViewReqSepList.setVisibility(View.GONE);
            textViewReqSepListFixed.setVisibility(View.GONE);
        }

        if(request.requestType.contentEquals("Medical"))
        {
            /* Marriage*/
            textViewMarriageDataFixed.setVisibility(View.GONE);
            textViewMarriageDate.setVisibility(View.GONE);

            if(!(request.requestTreatment.contentEquals("")))
            {
                if(!(request.requestTreatment.isEmpty()))
                textViewReqTreatment.setText(request.requestTreatment);
            }
            else
            {
                textViewReqTreatment.setVisibility(View.GONE);
                textViewReqTreatmentFixed.setVisibility(View.GONE);
            }

            if(!((request.requestTreatmentCost.contentEquals(""))&& (request.requestTreatmentCost.isEmpty())))
            {
                textViewReqTreatmentCost.setText(request.requestTreatmentCost);
            }
            else
            {
                textViewReqTreatmentCostFixed.setVisibility(View.GONE);
                textViewReqTreatmentCost.setVisibility(View.GONE);
            }

            if(!((request.requestPatientName.contentEquals("")) && (request.requestPatientName.isEmpty())))
            {
                textViewReqPatientName.setText(request.requestPatientName);
            }
            else
            {
                textViewReqPatientNameFixed.setVisibility(View.GONE);
                textViewReqPatientName.setVisibility(View.GONE);
            }
        }
        else if (request.requestType.contentEquals("Marriage"))
        {
            /* Medical */
            textViewReqPatientName.setVisibility(View.GONE);
            textViewReqTreatmentCost.setVisibility(View.GONE);
            textViewReqTreatment.setVisibility(View.GONE);
            textViewReqPatientNameFixed.setVisibility(View.GONE);
            textViewReqTreatmentCostFixed.setVisibility(View.GONE);
            textViewReqTreatmentFixed.setVisibility(View.GONE);

            /*Marriage*/
            textViewMarriageDataFixed.setVisibility(View.VISIBLE);
            textViewMarriageDate.setVisibility(View.VISIBLE);
            if(!((request.requestMarriageDate.contentEquals("")) && (request.requestMarriageDate.isEmpty())))
            {
                textViewMarriageDate.setText(request.requestMarriageDate);
            }
            else
            {
                textViewMarriageDate.setVisibility(View.GONE);
                textViewMarriageDataFixed.setVisibility(View.GONE);
            }
        }
        else
        {
            /* Medical */
            textViewReqPatientName.setVisibility(View.GONE);
            textViewReqTreatmentCost.setVisibility(View.GONE);
            textViewReqTreatment.setVisibility(View.GONE);
            textViewReqPatientNameFixed.setVisibility(View.GONE);
            textViewReqTreatmentCostFixed.setVisibility(View.GONE);
            textViewReqTreatmentFixed.setVisibility(View.GONE);

            /* Marriage*/
            textViewMarriageDataFixed.setVisibility(View.GONE);
            textViewMarriageDate.setVisibility(View.GONE);
        }

        if(!((request.requestComments.contentEquals(""))&& (request.requestComments.isEmpty())))
        {
            textViewReqComments.setText(request.requestComments);
        }
        else
        {
            textViewReqCommentsFixed.setVisibility(View.GONE);
            textViewReqComments.setVisibility(View.GONE);
        }

        textViewReqIbmAmt.setText(String.valueOf(request.requestIbmAmt));
        textViewReqSepAmt.setText(String.valueOf(request.requestSepAmt));
        textViewReqTotAmt.setText(String.valueOf(requestTotAmt));
    }



    @Override
    public void onClick(View view) {


            if (view == buttonDelete) {
                    popUpDialog();
            } else if (view == buttonEdit) {
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
                myIntent.putExtra("requestComments", request.requestComments);
                myIntent.putExtra("requestIbmAmt", String.valueOf(request.requestIbmAmt));
                myIntent.putExtra("requestSepAmt", String.valueOf(request.requestSepAmt));
                myIntent.putExtra("requestType", String.valueOf(request.requestType));
                if(request.requestType.contentEquals("Medical")) {
                    myIntent.putExtra("requestTreatment", String.valueOf(request.requestTreatment));
                    myIntent.putExtra("requestTreatmentCost", String.valueOf(request.requestTreatmentCost));
                    myIntent.putExtra("requestPatientName", String.valueOf(request.requestPatientName));
                }
                else if(request.requestType.contentEquals("Marriage"))
                {
                    myIntent.putExtra("requestMarriageDate", String.valueOf(request.requestMarriageDate));
                }
                startActivity(myIntent);
            }
            else if(view == buttonMenu)
            {
                startActivity(new Intent(this,ProfileActivity.class));
            }
            else if(view == buttonView)
            {
                startActivity(new Intent(this,ViewRequestActivity.class));
            }
        }


    private void popUpDialog() {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete request");
        builder.setMessage("Are you sure you want to delete the request?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestDelete();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("Request Delete Cancelled");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void requestDelete() {

        progressDialog.setMessage("Deleting request information.Please wait");
        progressDialog.show();

        Query query = databaseRequests.orderByChild("requestNo").equalTo(request.requestNo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    messageSnapshot.getRef().removeValue();
                    messageSnapshot.getRef().removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                progressDialog.dismiss();
                                showToast("Request delete failure. Please contact admin");
                            }
                            else
                            {
                                progressDialog.dismiss();
                                showToast("Request Deleted successfully");
                                //finish();
                                startViewReq();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showToast("Request Delete error.");
            }
        });
    }

    private void startViewReq() {
        startActivity(new Intent(this, ViewRequestActivity.class));
    }

    private void showToast(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
