package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonIbmMembers;
    private Button buttonManReq;
    private Button buttonIbmAcc;
    private Button buttonAdvOptions;
    private Button buttonIbmObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() ==null )
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Asslamu Alaikum "+user.getDisplayName()+",");
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonManReq = (Button) findViewById(R.id.buttonManReq);
        buttonIbmMembers = (Button) findViewById(R.id.buttonIbmMembers);
        buttonIbmAcc = (Button) findViewById(R.id.buttonIbmAccount);
        buttonAdvOptions = (Button) findViewById(R.id.buttonAdvOptions);
        buttonIbmObj = (Button) findViewById(R.id.buttonIbmObj);

        buttonLogout.setOnClickListener(this);
        buttonManReq.setOnClickListener(this);
        buttonIbmMembers.setOnClickListener(this);
        buttonIbmAcc.setOnClickListener(this);
        buttonIbmObj.setOnClickListener(this);
        buttonAdvOptions.setOnClickListener(this);
    }

    private void logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void manageReq()
    {
        startActivity(new Intent(this,ManReqActivity.class));
    }
    @Override
    public void onClick(View view) {

        if( view == buttonLogout)
        {
            //Logout event
            logout();
        }
        else if( view == buttonManReq)
        {
            manageReq();
        }
        else if( view == buttonIbmMembers)
        {
            viewIBMMembers();
        }
        else if (view == buttonIbmAcc)
        {
            viewIBMAcc();
        }
        else if (view == buttonAdvOptions)
        {
            //advanceOptions();
            launchConnectIBM();
        }
        else if (view == buttonIbmObj)
        {
            viewIBMObj();
        }
    }

    private void viewIBMObj() {
        startActivity(new Intent(this, ObjectiveActivity.class));
    }

    private void advanceOptions() {
        startActivity(new Intent(this, AdvanceOptionsActivity.class));
    }

    private void launchConnectIBM() {
        startActivity(new Intent(this, ConnectIBMActivity.class));
    }

    private void viewIBMAcc() {
        startActivity(new Intent(this,ViewBankAccActivity.class));
    }

    private void viewIBMMembers() {

        startActivity(new Intent(this,ViewIbmMembersActivity.class));
    }
}
