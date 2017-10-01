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
    private Button buttonManReq;

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
        textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonManReq = (Button) findViewById(R.id.buttonManReq);

        buttonLogout.setOnClickListener(this);
        buttonManReq.setOnClickListener(this);
    }

    private void logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void manageReq()
    {
        finish();
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
    }
}
