package ibm.imfras_baithul_mal;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class UpdateRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request);

        Intent myIntent = getIntent();
        String reqNo = myIntent.getStringExtra("requestNo");
        Toast.makeText(this,"Request no:" + reqNo,Toast.LENGTH_LONG).show();
    }
}
