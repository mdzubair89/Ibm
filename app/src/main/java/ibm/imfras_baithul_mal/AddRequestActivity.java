package ibm.imfras_baithul_mal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AddRequestActivity extends AppCompatActivity implements View.OnClickListener {

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
    private Button buttonSubmit;

    DatabaseReference databaseRequests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

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
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);

    }

    private void addRequest()
    {
        /* reqNo in int need to be converted*/
        String stReqNo = editTxtReqNo.getText().toString();
        String reqPurpose = editTxtPurpose.getText().toString().trim();
        String reqDate = editTxttDate.getText().toString().trim();
        String reqConPerson = ediTxtConPerson.getText().toString().trim();
        String reqPostal = editTxtPostal.getText().toString().trim();
        String reqPhone = PhoneNumberUtils.formatNumber(editTxtPhone.getText().toString());
        String reqReqPerson = editTxtReqPerson.getText().toString().trim();
        int reqIbmAmt = Integer.parseInt(editTxtIbmAmt.getText().toString());
        int reqSepAmt = Integer.parseInt(editTxtSepAmt.getText().toString());
        String reqSepList = editSepList.getText().toString().trim();

       if(!((TextUtils.isEmpty(stReqNo))&&(TextUtils.isEmpty(reqPurpose))) )
        {
            int reqNo = Integer.parseInt(stReqNo);
            String id = databaseRequests.push().getKey();
            Request request = new Request(reqNo,reqPurpose,reqDate,reqConPerson,reqPostal,reqPhone,reqReqPerson,
                    reqIbmAmt,reqSepAmt,reqSepList);
            databaseRequests.child(id).setValue(request);
            Toast.makeText(this,"Request added",Toast.LENGTH_SHORT).show();

        }
        else{
           Toast.makeText(this," You should enter a valid Request number or Request purpose..",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSubmit)
        {
            addRequest();
        }
    }
}
