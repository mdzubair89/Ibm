package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManReqActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonViewReq;
    private Button buttonAddReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_req);

        buttonViewReq = (Button) findViewById(R.id.buttonViewReq);
        buttonAddReq =   (Button) findViewById(R.id.buttonAddReq);

        buttonAddReq.setOnClickListener(this);
        buttonViewReq.setOnClickListener(this);
    }

    private void viewRequest()
    {
        finish();
        startActivity(new Intent(this,ViewRequestActivity.class));
    }

    private void addRequest()
    {
        finish();
        startActivity(new Intent(this,AddRequestActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view == buttonViewReq)
        {
            viewRequest();
        }
        else if(view ==  buttonAddReq)
        {
            addRequest();
        }
        else{

        }
    }
}
