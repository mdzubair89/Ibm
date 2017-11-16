package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.util.logging.Level.INFO;

public class AddRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtViewTitle;
    private Spinner spinnerReqType;
    private EditText editTxtReqNo;
    private EditText editTxtPurpose;
    private EditText editTxttDate;
    private EditText ediTxtConPerson;
    private EditText editTxtPostal;
    private EditText editTxtPhone;
    private EditText editTxtReqPerson;
    private EditText editTxtIbmAmt;
    private EditText editTxtSepAmt;
    private EditText editSepList;
    private EditText editTxtReqStatus;

    /*Medical request*/
    private EditText editTxtTreatment;
    private EditText editTxtPatientName;
    private EditText editTxtTreatmentCost;
    private TextView txtViewTreatment;
    private TextView txtViewTreatmentCost;
    private TextView txtViewPatientName;

    private Button buttonSubmit;
    private FirebaseAuth firebaseAuth;
    private ArrayAdapter spinnerReqTypeArrAdap;
    int sepContr = 0;
    String requestType = "GENERAL";


    DatabaseReference databaseRequests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        firebaseAuth = FirebaseAuth.getInstance();

        int reqNo = 0;

        databaseRequests = FirebaseDatabase.getInstance().getReference("Requests");

        editTxtReqNo = (EditText) findViewById(R.id.editTxtReqNo);

        /* Medical request*/
        editTxtTreatment = (EditText) findViewById(R.id.editTxtTreatment);
        editTxtPatientName = (EditText) findViewById(R.id.editTxtPatientNmae);
        editTxtTreatmentCost = (EditText) findViewById(R.id.editTxtTreatmentCost);

        txtViewTreatment = (TextView) findViewById(R.id.txtViewTxtTreatment);
        txtViewPatientName = (TextView) findViewById(R.id.txtViewTxtPatientName);
        txtViewTreatmentCost = (TextView) findViewById(R.id.txtViewTxtTreatmentCost);


        spinnerReqType = (Spinner) findViewById(R.id.spinnerReqType);
        spinnerReqTypeArrAdap = ArrayAdapter.createFromResource(this, R.array.requestType,android.R.layout.simple_spinner_dropdown_item);
        spinnerReqTypeArrAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReqType.setAdapter(spinnerReqTypeArrAdap);

        spinnerReqType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:  editTxtTreatment.setVisibility(View.GONE);
                             editTxtPatientName.setVisibility(View.GONE);
                             editTxtTreatmentCost.setVisibility(View.GONE);
                             txtViewTreatment.setVisibility(View.GONE);
                             txtViewPatientName.setVisibility(View.GONE);
                             txtViewTreatmentCost.setVisibility(View.GONE);
                             requestType = "General";
                        break;
                    case 1:
                            editTxtTreatment.setVisibility(View.GONE);
                            editTxtPatientName.setVisibility(View.GONE);
                            editTxtTreatmentCost.setVisibility(View.GONE);
                            txtViewTreatment.setVisibility(View.GONE);
                            txtViewPatientName.setVisibility(View.GONE);
                            txtViewTreatmentCost.setVisibility(View.GONE);
                            requestType = "Marriage";
                        break;
                    case 2:
                            editTxtTreatment.setVisibility(View.VISIBLE);
                            editTxtPatientName.setVisibility(View.VISIBLE);
                            editTxtTreatmentCost.setVisibility(View.VISIBLE);
                            txtViewTreatment.setVisibility(View.VISIBLE);
                            txtViewPatientName.setVisibility(View.VISIBLE);
                            txtViewTreatmentCost.setVisibility(View.VISIBLE);
                            requestType = "Medical";
                            break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTxtPurpose = (EditText) findViewById(R.id.editTxtPurpose);
        editTxttDate = (EditText) findViewById(R.id.editTxttDate);
        ediTxtConPerson = (EditText) findViewById(R.id.ediTxtConPerson);
        editTxtPostal = (EditText) findViewById(R.id.editTxtPostal);
        editTxtPhone = (EditText) findViewById(R.id.editTxtPhone);
        editTxtReqPerson = (EditText) findViewById(R.id.editTxtReqPerson);
        editTxtIbmAmt = (EditText) findViewById(R.id.editTxtIbmAmt);
        editTxtSepAmt = (EditText) findViewById(R.id.editTxtSepAmt);
        editSepList = (EditText) findViewById(R.id.editSepList);
        editTxtSepAmt.setEnabled(false);
        txtViewTitle  =  (TextView) findViewById(R.id.txtViewTitle);
        editTxtReqStatus = (EditText) findViewById(R.id.editTxtReqStatus);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
        editSepList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int num = 0;
                int length = 0;
                sepContr = 0;
                if(s.length() != 0) {
                    String reqSepList = editSepList.getText().toString().trim();
                    String lines[] = reqSepList.split("\\r?\\n");
                    if (lines != null) {
                        for (String line : lines) {
                            if(line.contains("-")) {
                                String[] parts = line.split("-");
                                length = parts.length;
                                if (length > 1) {
                                    //value.replaceAll("[^0-9]", "");
                                    parts[1] = parts[1].replaceAll("[^0-9]","");
                                    if (!(parts[1].isEmpty())) {
                                        num = Integer.parseInt(parts[1]);
                                        sepContr += num;
                                    }
                                }
                            }
                        }
                        editTxtSepAmt.setText(String.valueOf(sepContr));
                    }
                }
            }
        });

        Intent myIntent = getIntent();
        String stReqNo = myIntent.getStringExtra("requestNo");
        if (stReqNo != null) {
            reqNo = Integer.parseInt(stReqNo);
            if (reqNo != 0)
            {
                Toast.makeText(this,"Request no:" + reqNo + " edit request",Toast.LENGTH_LONG).show();
                buttonSubmit.setText("Update");
                txtViewTitle.setText("UPDATE REQUEST");
                setText(myIntent);

            }
        }
        else
        {
            buttonSubmit.setText("Add");
            txtViewTitle.setText("ADD REQUEST");
        }
    }

    private void setText(Intent myIntent) {
        editTxtReqNo.setText(myIntent.getStringExtra("requestNo"));
        editTxtReqNo.setEnabled(false);
        editTxtPurpose.setText(myIntent.getStringExtra("requestPurpose"));
        editTxttDate.setText(myIntent.getStringExtra("requestDate"));
        ediTxtConPerson.setText(myIntent.getStringExtra("requestConPerson"));
        editTxtPhone.setText(myIntent.getStringExtra("requestPhone"));
        editTxtReqPerson.setText(myIntent.getStringExtra("requestPerson"));
        editTxtPostal.setText(myIntent.getStringExtra("requestPostal"));
        editSepList.setText(myIntent.getStringExtra("requestSepList"));
        editTxtIbmAmt.setText(myIntent.getStringExtra("requestIbmAmt"));
        editTxtSepAmt.setText(myIntent.getStringExtra("requestSepAmt"));
        editTxtReqStatus.setText(myIntent.getStringExtra("requestStatus"));

        String requestType = myIntent.getStringExtra("requestType");
        if(requestType.contentEquals("Medical"))
        {
            spinnerReqType.setSelection(2);
            String patientName = myIntent.getStringExtra("requestPatientName");
            String treatment = myIntent.getStringExtra("requestTreatment");
            String treatmentCost = myIntent.getStringExtra("requestTreatmentCost");
            if(!(patientName.contentEquals("null")))
            editTxtPatientName.setText(patientName);
            if(!(treatment.contentEquals("null")))
            editTxtTreatment.setText(treatment);
            if(!(treatmentCost.contentEquals("null")))
            editTxtTreatmentCost.setText(treatmentCost);
        }
        else if(requestType.contentEquals("Marriage"))
        {
            spinnerReqType.setSelection(1);
        }
        else if(requestType.contentEquals("General"))
        {
            spinnerReqType.setSelection(0);
        }
        else
        {
            spinnerReqType.setSelection(0); /* Set default to general */
        }
    }

    private void addRequest() {
        Request request = new Request();
        getReqDetailsFromView(request);

        String id = databaseRequests.push().getKey();
        // databaseRequests.child(id).setValue(request);
        databaseRequests.child(id).setValue(request, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    showToast("Request not added successfully. May be you are not having write permission!.Contact the admin.");

                } else {
                    showToast("Request added successfully");
                    startViewReq();
                }
            }
        });

    }

    private void startViewReq() {
        startActivity(new Intent(this, ViewRequestActivity.class));
    }

    @Override
    public void onClick(View view) {
        if((view == buttonSubmit) && (buttonSubmit.getText() == "Add"))
        {
            addRequest();
        }
        else  if ((view == buttonSubmit) && (buttonSubmit.getText() == "Update"))
        {
            updateRequest();
        }
    }

    private void updateRequest() {

        final Request request = new Request();
        getReqDetailsFromView(request);

        final Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("requestNo",request.requestNo);
        childUpdates.put("requestPurpose",request.requestPurpose);
        childUpdates.put("requestDate",request.requestDate);
        childUpdates.put("requestConPerson",request.requestConPerson);
        childUpdates.put("requestPostal",request.requestPostal);
        childUpdates.put("requestPhone",request.requestPhone);
        childUpdates.put("requestPerson",request.requestPerson);
        childUpdates.put("requestIbmAmt",request.requestIbmAmt);
        childUpdates.put("requestSepAmt",request.requestSepAmt);
        childUpdates.put("requestSepList",request.requestSepList);
        childUpdates.put("requestStatus",request.requestStatus);
        childUpdates.put("requestType", requestType);
        if(requestType.contentEquals("Medical")) {
            childUpdates.put("requestTreatment", request.requestTreatment);
            childUpdates.put("requestTreatmentCost", request.requestTreatmentCost);
            childUpdates.put("requestPatientName", request.requestPatientName);
        }





        Query query = databaseRequests.orderByChild("requestNo").equalTo(request.requestNo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();
                String key = nodeDataSnapshot.getKey();
                String path = "/" + key;
                databaseRequests.child(path).updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null)
                        {
                            showToast("You may not have permission to update the request. Please contact admin!");
                        }
                        else
                        {
                            showToast("Request updated successfully");
                            //finish();
                            Intent myIntent = new Intent(AddRequestActivity.this, ViewDetReqActivity.class);
                            myIntent.putExtra("requestNo", String.valueOf(request.requestNo));
                            startActivity(myIntent);
                        }
                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showToast("Failed to update the request. Please contact admin!");

            }
        });
    }

    private void showToast(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    private void getReqDetailsFromView(Request request) {

        int reqIbmAmt =0;
        int reqSepAmt =0;
        String stReqNo = editTxtReqNo.getText().toString();
        request.requestNo = Integer.parseInt(stReqNo);
        request.requestPurpose = editTxtPurpose.getText().toString().trim();
        request.requestDate = editTxttDate.getText().toString().trim();
        request.requestConPerson = ediTxtConPerson.getText().toString().trim();
        request.requestPostal = editTxtPostal.getText().toString().trim();
        request.requestPhone = PhoneNumberUtils.formatNumber(editTxtPhone.getText().toString());
        request.requestPerson= editTxtReqPerson.getText().toString().trim();
        request.requestType = requestType;

        if(request.requestType.contentEquals("Medical"))
        {
            request.requestTreatment = editTxtTreatment.getText().toString().trim();
            request.requestTreatmentCost = editTxtTreatmentCost.getText().toString().trim();
            request.requestPatientName = editTxtPatientName.getText().toString().trim();
        }

        if (!(TextUtils.isEmpty(editTxtIbmAmt.getText().toString()))){
            request.requestIbmAmt = Integer.parseInt(editTxtIbmAmt.getText().toString());
        }
        if (!(TextUtils.isEmpty(editTxtSepAmt.getText().toString())))
        {
            request.requestSepAmt = Integer.parseInt(editTxtSepAmt.getText().toString());
        }
        request.requestSepList = editSepList.getText().toString().trim();
        request.requestStatus = editTxtReqStatus.getText().toString().trim();

        if(!((TextUtils.isEmpty(stReqNo))&&(TextUtils.isEmpty(request.requestPurpose))) ) {
            request.requestNo = Integer.parseInt(stReqNo);
        }
        else
        {
            Toast.makeText(this," You should enter a valid Request number or Request purpose..",Toast.LENGTH_LONG).show();
        }


    }
}
