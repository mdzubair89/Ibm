package ibm.imfras_baithul_mal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubDetActivity extends AppCompatActivity {

    TextView textViewSubMember;
    TextView textViewJanAmt;
    TextView textViewFebAmt;
    TextView textViewMarAmt;
    TextView textViewAprilAmt;
    TextView textViewMayAmt;
    TextView textViewJuneAmt;
    TextView textViewJulyAmt;
    TextView textViewAugAmt;
    TextView textViewSeptAmt;
    TextView textViewOctAmt;
    TextView textViewNovAmt;
    TextView textViewDecAmt;
    LinearLayout myLinearLayout;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_det);

        String month[] = {"NAME","JAN", "FEB" , "MARCH" , "APRIL" , "MAY" , "JUNE" , "JULY" , "AUG" , "SEPT" , "OCT" , "NOV" , "DEC", "TOTAL"};

        Intent myIntent = getIntent();
        String subsYear = myIntent.getStringExtra("Year");
        HashMap<String, String> hashMap = (HashMap<String, String>)myIntent.getSerializableExtra("map");
        String name  = hashMap.get("NAME");

        String janAmt  = hashMap.get("JAN");
        String febAmt  = hashMap.get("FEB");
        String marAmt  = hashMap.get("MARCH");
        String aprilAmt  = hashMap.get("APRIL");
        String mayAmt  = hashMap.get("MAY");
        String juneAmt  = hashMap.get("JUNE");
        String julyAmt  = hashMap.get("JULY");
        String augAmt  = hashMap.get("AUG");
        String septAmt  = hashMap.get("SEPT");
        String octAmt  = hashMap.get("OCT");
        String novAmt  = hashMap.get("NOV");
        String decAmt  = hashMap.get("DEC");
        String totalAmt  = hashMap.get("TOTAL");

        textViewSubMember = (TextView) findViewById(R.id.textViewSubMember);

        textViewJanAmt = (TextView) findViewById(R.id.textViewJanAmt);
        textViewFebAmt = (TextView) findViewById(R.id.textViewFebAmt);
        textViewMarAmt = (TextView) findViewById(R.id.textViewMarAmt);
        textViewAprilAmt = (TextView) findViewById(R.id.textViewAprilAmt);
        textViewMayAmt = (TextView) findViewById(R.id.textViewMayAmt);
        textViewJuneAmt = (TextView) findViewById(R.id.textViewJuneAmt);
        textViewJulyAmt = (TextView) findViewById(R.id.textViewJulyAmt);
        textViewAugAmt = (TextView) findViewById(R.id.textViewAugAmt);
        textViewSeptAmt = (TextView) findViewById(R.id.textViewSeptAmt);
        textViewOctAmt = (TextView) findViewById(R.id.textViewOctAmt);
        textViewNovAmt = (TextView) findViewById(R.id.textViewNovAmt);
        textViewDecAmt = (TextView) findViewById(R.id.textViewDecAmt);

        myLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        textViewSubMember.setText("IBM MONTHLY SHEET\n" + subsYear + "\n" + name);
        textViewJanAmt.setText(janAmt);
        textViewFebAmt.setText(febAmt);
        textViewMarAmt.setText(marAmt);
        textViewAprilAmt.setText(aprilAmt);
        textViewMayAmt.setText(mayAmt);
        textViewJuneAmt.setText(juneAmt);
        textViewJulyAmt.setText(julyAmt);
        textViewAugAmt.setText(augAmt);
        textViewSeptAmt.setText(septAmt);
        textViewOctAmt.setText(octAmt);
        textViewNovAmt.setText(novAmt);
        textViewDecAmt.setText(decAmt);


        final int N = hashMap.size(); // total number of textviews to add
        int j = 0;
        Drawable dw = getApplicationContext().getResources().getDrawable(R.drawable.textview_border);

        final TextView[] myTextViews = new TextView[N]; // create an empty array;

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {

            final LinearLayout linearLayoutDyn = new LinearLayout(this);
            linearLayoutDyn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayoutDyn.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutDyn.setBackgroundColor(0x006);

            float scale = getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (4*scale + 0.5f);

            final TextView rowTextViewName = new TextView(this);
            final TextView rowTextViewVal = new TextView(this);

            rowTextViewName.setBackgroundResource(R.drawable.textview_border);
            rowTextViewName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,2));
            rowTextViewName.setTextColor(getResources().getColor(R.color.abc_input_method_navigation_guard));
            rowTextViewName.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
            rowTextViewName.setTypeface(rowTextViewName.getTypeface(), Typeface.BOLD);

            rowTextViewVal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,2));
            rowTextViewVal.setTextColor(getResources().getColor(R.color.abc_input_method_navigation_guard));
            rowTextViewVal.setBackgroundResource(R.drawable.textview_border);
            rowTextViewVal.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
            rowTextViewVal.setTypeface(rowTextViewName.getTypeface(), Typeface.BOLD);

            rowTextViewName.setCompoundDrawablesRelativeWithIntrinsicBounds(dw,null,null,null);
            rowTextViewVal.setCompoundDrawablesRelativeWithIntrinsicBounds(dw,null,null,null);

            String key = entry.getKey();
            String val = entry.getValue();

            if (!Arrays.asList(month).contains(key)) {

                rowTextViewName.setText(key);
                rowTextViewVal.setText(val);
                // add the textview to the linearlayout
                linearLayoutDyn.addView(rowTextViewName);
                linearLayoutDyn.addView(rowTextViewVal);
                myLinearLayout.addView(linearLayoutDyn);

            }
            // do something with key and/or tab
        }

        createTotalTextView(totalAmt);
    }

    @SuppressLint("NewApi")
    private void createTotalTextView(String totalAmt) {

        Drawable dw = getApplicationContext().getResources().getDrawable(R.drawable.textview_border);

        final LinearLayout linearLayoutDyn = new LinearLayout(this);
        linearLayoutDyn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayoutDyn.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutDyn.setBackgroundColor(0x006);

        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (4*scale + 0.5f);

        final TextView rowTextViewName = new TextView(this);
        final TextView rowTextViewVal = new TextView(this);

        rowTextViewName.setBackgroundResource(R.drawable.textview_border);
        rowTextViewName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,2));
        rowTextViewName.setTextColor(getResources().getColor(R.color.abc_input_method_navigation_guard));
        rowTextViewName.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
        rowTextViewName.setTypeface(rowTextViewName.getTypeface(), Typeface.BOLD);

        rowTextViewVal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,2));
        rowTextViewVal.setTextColor(getResources().getColor(R.color.abc_input_method_navigation_guard));
        rowTextViewVal.setBackgroundResource(R.drawable.textview_border);
        rowTextViewVal.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
        rowTextViewVal.setTypeface(rowTextViewName.getTypeface(), Typeface.BOLD);

        rowTextViewName.setCompoundDrawablesRelativeWithIntrinsicBounds(dw,null,null,null);
        rowTextViewVal.setCompoundDrawablesRelativeWithIntrinsicBounds(dw,null,null,null);

        rowTextViewName.setText("TOTAL");
        rowTextViewVal.setText(totalAmt);
        // add the textview to the linearlayout
        linearLayoutDyn.addView(rowTextViewName);
        linearLayoutDyn.addView(rowTextViewVal);
        myLinearLayout.addView(linearLayoutDyn);


    }
}
