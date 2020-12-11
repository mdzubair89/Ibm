package ibm.imfras_baithul_mal;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static ibm.imfras_baithul_mal.Constants.FIRST_COLUMN;
import static ibm.imfras_baithul_mal.Constants.FOURTH_COLUMN;
import static ibm.imfras_baithul_mal.Constants.SECOND_COLUMN;
import static ibm.imfras_baithul_mal.Constants.THIRD_COLUMN;

public class DistributionActivity extends Activity   implements EasyPermissions.PermissionCallbacks, View.OnClickListener {


        DatabaseReference databaseRequests;
        private TextView textViewRequestNo;
        private EditText editTextRequestPurpose;
        TextView columnHeader1;
        TextView columnHeader2;
        TextView columnHeader3;
        TextView columnHeader4;
        TextView txtFirst;
        ListView listView;
        private ProgressDialog progressDialog;
        public String layout = "distribution";

        private Spinner spinnerDistYear;

        private ArrayList<HashMap<String, String>> list;

        private TextView textViewDistribution;

        static final int REQUEST_ACCOUNT_PICKER = 1000;
        static final int REQUEST_AUTHORIZATION = 1001;
        static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
        static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
        private static final String BUTTON_TEXT = "Call Google Sheets API";
        private static final String PREF_ACCOUNT_NAME = "accountName";
        private static final String[] SCOPES = { SheetsScopes.SPREADSHEETS_READONLY };
        GoogleAccountCredential mCredential;
        ProgressDialog mProgress;
        private ArrayList<String> spinnerYearArray;
        Button buttonDistGo;

        private static String distYear ;
        public static final String INITIAL="INITIAL";
        public static final String DISTRIBUTION="DISTRIBUTION";
        private static String querySel = INITIAL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution);
        TextView columnHeader1 = (TextView) findViewById(R.id.balHeader_line1);
        TextView columnHeader2 = (TextView) findViewById(R.id.balHeader_line2);
        TextView columnHeader3 = (TextView) findViewById(R.id.balHeader_line3);
        TextView columnHeader4 = (TextView) findViewById(R.id.balHeader_line4);

        columnHeader1.setText("REQ NO");
        columnHeader2.setText("DATE");
        columnHeader3.setText("PARTICULARS");
        columnHeader4.setText("CONTRIB.");

        textViewDistribution = (TextView) findViewById(R.id.textViewDistribution);


        buttonDistGo = (Button) findViewById(R.id.buttonDistGo);
        buttonDistGo.setOnClickListener(this);

        spinnerDistYear = (Spinner) findViewById(R.id.spinnerDistYear);
        spinnerDistYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                distYear = spinnerDistYear.getSelectedItem().toString();
                list.clear();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        listView=(ListView)findViewById(R.id.balListView);

        populateList();

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Fetching data ...Please wait");

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

        querySel = INITIAL;

        getResultsFromApi();
    }

    private void populateList() {

        list = new ArrayList<HashMap<String, String>>();
        /*HashMap<String, String> temp = new HashMap<String, String>();
        temp.put(FIRST_COLUMN, "");
        temp.put(SECOND_COLUMN, "");
        temp.put(THIRD_COLUMN, "");
        temp.put(FOURTH_COLUMN, "");
        list.add(temp);*/

    }


    private void showToast(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    private void getResultsFromApi() {
        if (! isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (! isDeviceOnline()) {
            showToast("No network connection available.");
        } else {
            new DistributionActivity.MakeRequestTask(mCredential).execute();
        }
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, android.Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    android.Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode code indicating the result of the incoming
     *     activity result.
     * @param data Intent (containing result data) returned by incoming
     *     activity result.
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    showToast(
                            "This app requires Google Play Services. Please install " +
                                    "Google Play Services on your device and relaunch this app.");
                } else {
                    getResultsFromApi();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        getResultsFromApi();
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    getResultsFromApi();
                }
                break;
        }
    }

    /**
     * Respond to requests for permissions at runtime for API 23 and above.
     * @param requestCode The request code passed in
     *     requestPermissions(android.app.Activity, String, int, String[])
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    /**
     * Callback for when a permission is granted using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }


    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                DistributionActivity.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonDistGo)
        {
            list.clear();
            textViewDistribution.setText("DISTRIBUTION\n" + distYear);
            getResultsFromApi();
        }
    }

    /**
     * An asynchronous task that handles the Google Sheets API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;

        MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Google Sheets API Android Quickstart")
                    .build();
        }

        /**
         * Background task to call Google Sheets API.
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<String> doInBackground(Void... params) {

            if ( querySel.contentEquals(INITIAL))
            {
                try {
                    getYearDataFromApi();
                } catch (Exception e) {
                    mLastError = e;
                    cancel(true);
                    return null;
                }
            }

            try {
                querySel = DISTRIBUTION;
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }



        private void getYearDataFromApi() throws IOException {

            int count = 1;
            String spreadsheetId = "1JRYW5c0ZWA7ZfHzbcXuvCFznZCB_vK7JHm6G9fV-Kk0";

            spinnerYearArray = new ArrayList<String>();

            String query =  "COMMON!C5:C";

            ValueRange response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, query)
                    .execute();
            List<List<Object>> values = response.getValues();

            if (values != null) {


                for (List row : values) {

                    if (count == 1)
                        distYear = row.get(0).toString();
                    spinnerYearArray.add(row.get(0).toString());
                    count++;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setSpinnerYear();
                    }
                });
            }
        }

        /**
         * Fetch a list of names and majors of students in a sample spreadsheet:
         * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
         * @return List of names and majors
         * @throws IOException
         */
        private List<String> getDataFromApi() throws IOException {
            //String spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
            //String spreadsheetId = "1uLUhqMsFvB5JFSjj7vxQztn0QwHP1krwF3izKUWiyt8";
            String spreadsheetId = "1JRYW5c0ZWA7ZfHzbcXuvCFznZCB_vK7JHm6G9fV-Kk0";
            String query =  "Distribution_"+ distYear + "!B4:G";
            //String query =  "Distribution_2017!B4:G";

            String range = query;
            List<String> results = new ArrayList<String>();
            ValueRange response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            //int rowId = getRowId(query);
            if (values != null) {
                results.add("Name       Balance");
                for (List row : values) {
                    populate(row.get(0),row.get(1),row.get(2),row.get(5));
                }
            }

            return results;
        }

        private void populate(Object acc, Object actual, Object interest, Object total) {

            String stAcc = acc.toString();
            String stActual = actual.toString();
            String stInterest = interest.toString();
            String stTotal = total.toString();

            HashMap<String,String> temp=new HashMap<String, String>();
            temp.put(FIRST_COLUMN, stAcc);
            temp.put(SECOND_COLUMN,stActual);
            temp.put(THIRD_COLUMN, stInterest);
            temp.put(FOURTH_COLUMN, stTotal);

            list.add(temp);
            //TransactionListViewAdapter adapter= new TransactionListViewAdapter(ViewTransactionActivity.this,list);
            //listView.setAdapter(adapter);

        }



        @Override
        protected void onPreExecute() {
            //showToast("");
            mProgress.show();
        }

        @Override
        protected void onPostExecute(List<String> output) {
            mProgress.hide();
            if (output == null || output.size() == 0) {
                showToast("No results returned.");
            } else {
                //output.add(0, "Data retrieved using the Google Sheets API:");
                setListadapter();
                //reverseListAndSetAdapter();
                //showToast(TextUtils.join("\n", output));
            }
        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            ConnectIBMActivity.REQUEST_AUTHORIZATION);
                } else {
                    showToast("The following error occurred:\n"
                            + mLastError.getMessage());
                }
            } else {
                showToast("Request cancelled.");
            }
        }
    }

    private void setListadapter() {
        ListViewAdapter adapter= new ListViewAdapter(DistributionActivity.this,list,layout);
        listView.setAdapter(adapter);

    }


    private void setSpinnerYear() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, spinnerYearArray);
        spinnerDistYear.setAdapter(spinnerArrayAdapter);
        textViewDistribution.setText("DISTRIBUTION\n" + distYear);
    }

}
