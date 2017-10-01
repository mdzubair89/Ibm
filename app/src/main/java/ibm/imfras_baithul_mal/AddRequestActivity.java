package ibm.imfras_baithul_mal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

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
       int reqNo = Integer.parseInt(editTxtReqNo.getText().toString());
        String reqPurpose = editTxtPurpose.getText().toString().trim();
        String reqDate = editTxttDate.getText().toString().trim();
        String reqConPerson = ediTxtConPerson.getText().toString().trim();
        String reqPostal = editTxtPostal.getText().toString().trim();
        int reqPhone = Integer.parseInt(editTxtPhone.getText().toString());
        String reqReqPerson = editTxtReqPerson.getText().toString().trim();
        int reqIbmAmt = Integer.parseInt(editTxtIbmAmt.getText().toString());
        int reqSepAmt = Integer.parseInt(editTxtSepAmt.getText().toString());
        String reqSepList = editSepList.getText().toString().trim();

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSubmit)
        {
            addRequest();
        }
    }
}
