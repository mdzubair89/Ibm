package ibm.imfras_baithul_mal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManReqActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonViewReq;
    private Button buttonAddReq;
    private Button buttonViewTransaction;
    private Button buttonViewBalance;
    private Button buttonViewDistribution;
    private Button buttonViewSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_req);

        buttonViewReq = (Button) findViewById(R.id.buttonViewReq);
        buttonAddReq =   (Button) findViewById(R.id.buttonAddReq);
        buttonViewTransaction =   (Button) findViewById(R.id.buttonTransaction);
        buttonViewBalance =   (Button) findViewById(R.id.buttonViewBalance);
        buttonViewDistribution =   (Button) findViewById(R.id.buttonViewDistribution);
        buttonViewSubscription =   (Button) findViewById(R.id.buttonViewSubscription);

        buttonAddReq.setOnClickListener(this);
        buttonViewReq.setOnClickListener(this);
        buttonViewTransaction.setOnClickListener(this);
        buttonViewBalance.setOnClickListener(this);
        buttonViewDistribution.setOnClickListener(this);
        buttonViewSubscription.setOnClickListener(this);
    }

    private void viewRequest()
    {
        startActivity(new Intent(this,ViewRequestActivity.class));
    }

    private void addRequest()
    {
        startActivity(new Intent(this,AddRequestActivity.class));
    }

    private void  viewTransaction() {  startActivity(new Intent(this, ViewTransactionActivity.class));    }


    private void viewBalance() {
        startActivity(new Intent(this, BalanceActivity.class));
    }

    private void viewDistribution() {
        startActivity(new Intent(this, DistributionActivity.class));
    }

    private void viewSubscription() {
        startActivity(new Intent(this, SubscriptionActivity.class));
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
        else if (view == buttonViewTransaction){
            viewTransaction();
        }
        else if (view == buttonViewBalance){
            viewBalance();
        }
        else if (view == buttonViewDistribution)
        {
            viewDistribution();
        }
        else if (view == buttonViewSubscription)
        {
            viewSubscription();
        }

    }


}
