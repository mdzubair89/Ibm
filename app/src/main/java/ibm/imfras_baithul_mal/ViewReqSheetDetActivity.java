package ibm.imfras_baithul_mal;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.jvm.internal.CharSpreadBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class ViewReqSheetDetActivity extends AppCompatActivity {

    TextView requestnoHeadline_TextView;
    TextView purposeTextViewValue;
    TextView contactNameTextViewValue;
    TextView addressTextViewValue;
    TextView reqDateTextViewValue;
    TextView contactNoTextViewValue;
    TextView ibmContribTextViewValue;
    TextView sepContribTextViewValue;
    TextView totalTextViewValue;
    TextView statusTextViewValue;
    TextView sepContribListTextViewValue;
    TextView commentsTextViewValue;

    TableRow TableRow_PurposeReq;
    TableRow TableRow_ContactName;
    TableRow TableRow_Address;
    TableRow TableRow_ReqDate;
    TableRow TableRow_ContactNo;
    TableRow TableRow_IbmContrib;
    TableRow TableRow_SepContrib;
    TableRow TableRow_Total;
    TableRow TableRow_Status;
    TableRow TableRow_SepContribList;
    TableRow TableRow_Comments;


    public static final String REQUEST_REQNO="REQ NO";
    public static final String REQUEST_PURPOSE_OF_REQ="PURPOSE OF REQUEST";
    public static final String REQUEST_CONTACT_NAME="CONTACT NAME";
    public static final String REQUEST_ADDRESS="ADDRESS";
    public static final String REQUEST_REQDATE="REQ DATE";
    public static final String REQUEST_CONTACTNO="CONTACT NO";
    public static final String REQUEST_IBMCONTRIB="IBM CONTRIB";
    public static final String REQUEST_SEPCONTRIB="SEPERATE CONTRIB";
    public static final String REQUEST_TOTAL="TOTAL";
    public static final String REQUEST_STATUS="STATUS";
    public static final String REQUEST_SEPCONTRIBLIST="SEPERATE CONTRIBUTION LIST";
    public static final String REQUEST_COMMENTS="COMMENTS";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_req_sheet_det);

        //String reqCol[] = {"REQ NO","PURPOSE OF REQUEST", "CONTACT NAME" , "ADDRESS" , "REQ DATE" , "CONTACT NO" , "IBM CONTRIB" , "SEPERATE CONTRIB" , "TOTAL" , "STATUS" , "SEPERATE CONTRIBUTION LIST" , "COMMENTS"};

        Intent myIntent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>)myIntent.getSerializableExtra("map");

        requestnoHeadline_TextView = (TextView) findViewById(R.id.requestnoHeadline_TextView);
        purposeTextViewValue = (TextView) findViewById(R.id.purposeTextViewValue);
        contactNameTextViewValue = (TextView) findViewById(R.id.contactNameTextViewValue);
        addressTextViewValue = (TextView) findViewById(R.id.addressTextViewValue);
        reqDateTextViewValue = (TextView) findViewById(R.id.reqDateTextViewValue);
        contactNoTextViewValue = (TextView) findViewById(R.id.contactNoTextViewValue);
        ibmContribTextViewValue = (TextView) findViewById(R.id.ibmContribTextViewValue);
        sepContribTextViewValue = (TextView) findViewById(R.id.sepContribTextViewValue);
        totalTextViewValue = (TextView) findViewById(R.id.totalTextViewValue);
        statusTextViewValue = (TextView) findViewById(R.id.statusTextViewValue);
        sepContribListTextViewValue = (TextView) findViewById(R.id.sepContribListTextViewValue);
        commentsTextViewValue = (TextView) findViewById(R.id.commentsTextViewValue);

        TableRow_PurposeReq = (TableRow) findViewById(R.id.TableRow_PurposeReq);
        TableRow_ContactName = (TableRow) findViewById(R.id.TableRow_ContactName);
        TableRow_Address = (TableRow) findViewById(R.id.TableRow_Address);
        TableRow_ReqDate = (TableRow) findViewById(R.id.TableRow_ReqDate);
        TableRow_ContactNo = (TableRow) findViewById(R.id.TableRow_ContactNo);
        TableRow_IbmContrib = (TableRow) findViewById(R.id.TableRow_IbmContrib);
        TableRow_SepContrib = (TableRow) findViewById(R.id.TableRow_SepContrib);
        TableRow_Total = (TableRow) findViewById(R.id.TableRow_Total);
        TableRow_Status = (TableRow) findViewById(R.id.TableRow_Status);
        TableRow_SepContribList = (TableRow) findViewById(R.id.TableRow_SepContribList);
        TableRow_Comments = (TableRow) findViewById(R.id.TableRow_Comments);

        String str_reqNo  = hashMap.get(REQUEST_REQNO);
        String str_reqPurpose  = hashMap.get(REQUEST_PURPOSE_OF_REQ);
        String str_reqContactName  = hashMap.get(REQUEST_CONTACT_NAME);
        String str_reqAddress  = hashMap.get(REQUEST_ADDRESS);
        String str_reqDate  = hashMap.get(REQUEST_REQDATE);
        String str_reqContactNo  = hashMap.get(REQUEST_CONTACTNO);
        String str_reqIbmContrib  = hashMap.get(REQUEST_IBMCONTRIB);
        String str_reqSepContrib  = hashMap.get(REQUEST_SEPCONTRIB);
        String str_reqTotal  = hashMap.get(REQUEST_TOTAL);
        String str_reqStatus  = hashMap.get(REQUEST_STATUS);
        String str_reqSepContribList  = hashMap.get(REQUEST_SEPCONTRIBLIST);
        String str_reqComments  = hashMap.get(REQUEST_COMMENTS);

        requestnoHeadline_TextView.setText("REQ NO :" + str_reqNo);

        purposeTextViewValue.setText(str_reqPurpose);
        if((str_reqContactName != null) && (!str_reqContactName.isEmpty())) {
            contactNameTextViewValue.setText(str_reqContactName);
        }
        else{
            TableRow_ContactName.setVisibility(View.GONE);
        }

        if((str_reqAddress != null) && (!str_reqContactName.isEmpty())) {
            addressTextViewValue.setText(str_reqAddress);
        }
        else{
            TableRow_Address.setVisibility(View.GONE);
        }

        if((str_reqDate != null) && (!str_reqContactName.isEmpty())) {
            reqDateTextViewValue.setText(str_reqDate);
        }
        else{
            TableRow_ReqDate.setVisibility(View.GONE);
        }

        if((str_reqContactNo != null) && (!str_reqContactName.isEmpty())) {
            contactNoTextViewValue.setText(str_reqContactNo);
        }
        else{
            TableRow_ContactNo.setVisibility(View.GONE);
        }

        ibmContribTextViewValue.setText(str_reqIbmContrib);
        sepContribTextViewValue.setText(str_reqSepContrib);
        totalTextViewValue.setText(str_reqTotal);
        statusTextViewValue.setText(str_reqStatus);

        if((str_reqSepContribList != null) && (!str_reqContactName.isEmpty())) {
            sepContribListTextViewValue.setText(str_reqSepContribList);
        }
        else{
            TableRow_SepContribList.setVisibility(View.GONE);
        }

        if((str_reqComments != null) && (!str_reqContactName.isEmpty())) {
            commentsTextViewValue.setText(str_reqComments);
        }
        else{
            TableRow_Comments.setVisibility(View.GONE);
        }



       /* reqDateTextViewValue.setText(str_reqDate);
        contactNoTextViewValue.setText(str_reqContactNo);
        ibmContribTextViewValue.setText(str_reqIbmContrib);
        sepContribTextViewValue.setText(str_reqSepContrib);
        totalTextViewValue.setText(str_reqTotal);
        statusTextViewValue.setText(str_reqStatus);
        sepContribListTextViewValue.setText(str_reqSepContribList);
        commentsTextViewValue.setText(str_reqComments);*/





       /* Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }*/
    }
}