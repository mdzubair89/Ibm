package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import static java.util.logging.Level.INFO;

public class AddRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtViewTitle;
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
    private Button buttonSubmit;

    DatabaseReference databaseRequests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        int reqNo = 0;

        databaseRequests = FirebaseDatabase.getInstance().getReference("Requests");

        editTxtReqNo = (EditText) findViewById(R.id.editTxtReqNo);
        editTxtPurpose = (EditText) findViewById(R.id.editTxtPurpose);
        editTxttDate = (EditText) findViewById(R.id.editTxttDate);
        ediTxtConPerson = (EditText) findViewById(R.id.ediTxtConPerson);
        editTxtPostal = (EditText) findViewById(R.id.editTxtPostal);
        editTxtPhone = (EditText) findViewById(R.id.editTxtPhone);
        editTxtReqPerson = (EditText) findViewById(R.id.editTxtReqPerson);
        editTxtIbmAmt = (EditText) findViewById(R.id.editTxtIbmAmt);
        editTxtSepAmt = (EditText) findViewById(R.id.editTxtSepAmt);
        editSepList = (EditText) findViewById(R.id.editSepList);
        txtViewTitle  =  (TextView) findViewById(R.id.txtViewTitle);
        editTxtReqStatus = (EditText) findViewById(R.id.editTxtReqStatus);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);

        Intent myIntent = getIntent();
        String stReqNo = myIntent.getStringExtra("requestNo");
        if (stReqNo != null) {
            reqNo = Integer.parseInt(stReqNo);
            if (reqNo != 0)
            {
                Toast.makeText(this,"Request no:" + reqNo + " edit request",Toast.LENGTH_LONG).show();
                buttonSubmit.setText("Update");
                txtViewTitle.setText("Update Request");
                setText(myIntent);

            }
        }
        else
        {
            buttonSubmit.setText("Add");
            txtViewTitle.setText("Add Request");
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
        editTxtReqStatus.setText(myIntent.getStringExtra("requestSepAmt"));
    }

    private void addRequest()
    {
        /* reqNo in int need to be converted*/
        int reqIbmAmt =0;
        int reqSepAmt =0;
        String stReqNo = editTxtReqNo.getText().toString();
        String reqPurpose = editTxtPurpose.getText().toString().trim();
        String reqDate = editTxttDate.getText().toString().trim();
        String reqConPerson = ediTxtConPerson.getText().toString().trim();
        String reqPostal = editTxtPostal.getText().toString().trim();
        String reqPhone = PhoneNumberUtils.formatNumber(editTxtPhone.getText().toString());
        String reqReqPerson = editTxtReqPerson.getText().toString().trim();
        if (!(TextUtils.isEmpty(editTxtIbmAmt.getText().toString()))){
            reqIbmAmt = Integer.parseInt(editTxtIbmAmt.getText().toString());
        }
        if (!(TextUtils.isEmpty(editTxtSepAmt.getText().toString())))
        {
           reqSepAmt = Integer.parseInt(editTxtSepAmt.getText().toString());
        }
        String reqSepList = editSepList.getText().toString().trim();
        String reqStatus = editTxtReqStatus.getText().toString().trim();

       if(!((TextUtils.isEmpty(stReqNo))&&(TextUtils.isEmpty(reqPurpose))) )
        {
            int reqNo = Integer.parseInt(stReqNo);
            String id = databaseRequests.push().getKey();
            Request request = new Request(reqNo,reqPurpose,reqDate,reqConPerson,reqPostal,reqPhone,reqReqPerson,
                    reqIbmAmt,reqSepAmt,reqSepList,reqStatus);
            databaseRequests.child(id).setValue(request);
            Toast.makeText(this,"Request added",Toast.LENGTH_SHORT).show();

        }
        else{
           Toast.makeText(this," You should enter a valid Request number or Request purpose..",Toast.LENGTH_LONG).show();
        }

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

        Request request = new Request();
        getReqDetailsFromView(request);

        final Map<String, Object> childUpdates = new HashMap<>();

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

        Query query = databaseRequests.orderByChild("requestNo").equalTo(request.requestNo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();
                String key = nodeDataSnapshot.getKey();
                String path = "/" + key;
                databaseRequests.child(path).updateChildren(childUpdates);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Logger(, ">>> Error:" + "find onCancelled:" + databaseError);

            }
        });
    }

    private void getReqDetailsFromView(Request request) {

        int reqIbmAmt =0;
        int reqSepAmt =0;
        String stReqNo = editTxtReqNo.getText().toString();
        request.requestPurpose = editTxtPurpose.getText().toString().trim();
        request.requestDate = editTxttDate.getText().toString().trim();
        request.requestConPerson = ediTxtConPerson.getText().toString().trim();
        request.requestPostal = editTxtPostal.getText().toString().trim();
        request.requestPhone = PhoneNumberUtils.formatNumber(editTxtPhone.getText().toString());
        request.requestPerson= editTxtReqPerson.getText().toString().trim();

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
