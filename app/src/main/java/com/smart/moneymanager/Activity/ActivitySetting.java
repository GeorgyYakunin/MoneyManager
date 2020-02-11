package com.smart.moneymanager.Activity;

import android.Manifest;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.smart.moneymanager.DataController.MySharedPreferences;
import com.smart.moneymanager.R;
import com.smart.moneymanager.Utility.ResourceManager;

import java.util.Locale;

public class ActivitySetting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    MySharedPreferences mySharedPreferences;
    private Switch switchNotification;
    private boolean isNotificationEnabled;
//    private Spinner spLanguage;
    boolean isSpinnerInitialized = false;
    private RelativeLayout btnImportExport;

    private ImageView ivBarLeft,ivBarRight;
    private TextView tvBarText;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ActivityStart.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);

        tvBarText = (TextView) findViewById(R.id.tvBarText);
        ivBarLeft = (ImageView) findViewById(R.id.ivBarLeft);
        ivBarRight = (ImageView) findViewById(R.id.ivBarRight);
        ivBarRight.setVisibility(View.GONE);
        tvBarText.setText(getResources().getString(R.string.setting));
        ivBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityStart.class));
            }
        });


        isSpinnerInitialized = false;
        mySharedPreferences = new MySharedPreferences(getApplicationContext());
        //TextView tvDayLimitPanel = (TextView)findViewById(R.id.tvDayLimitPanel);
       // final TextView tvDayLimitValue = (TextView)findViewById(R.id.tvDayLimitValue);
       // final TextView tvMonthlyLimitValue = (TextView) findViewById(R.id.tvMonthlyLimitValue);
      //  final TextView tvYearlyLimitValue = (TextView)findViewById(R.id.tvYearlyLimitValue);
        btnImportExport = (RelativeLayout) findViewById(R.id.btnImportExport);
        btnImportExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted

                    startActivity(new Intent(getApplicationContext(), ActivityExportImport.class).putExtra("isImport", "none"));


                }else {
                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ActivitySetting.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(ActivitySetting.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                10);

                    }
                }
            }
        });
        switchNotification = (Switch) findViewById(R.id.switchNotification);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        isNotificationEnabled = mySharedPreferences.getIsNotificationEnabled();
        switchNotification.setChecked(isNotificationEnabled);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mySharedPreferences.setIsNotificationEnabled(isChecked);
            }
        });

        assert getSupportActionBar() != null;


        final long dailyLimit = new MySharedPreferences(getApplicationContext()).getDayilyLimit();
        final long monthlyLimit = new MySharedPreferences(getApplicationContext()).getMonthlyLimit();
        final long yearlyLimit = new MySharedPreferences(getApplicationContext()).getYearlyLimit();


    }


    private void changeLanguage(String languageCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            conf.setLocale(new Locale((languageCode.toLowerCase())));
        }else {
            conf.locale = new Locale(languageCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    startActivity(new Intent(getApplicationContext(), ActivityExportImport.class).putExtra("isImport", "none"));
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(isSpinnerInitialized == true){
            Log.d("Clicked", (String.valueOf(position)));
            if (position == 0) {
                ResourceManager.changeLanguage(this,"en");
                mySharedPreferences.setLanguage("en");

            } else {
                ResourceManager.changeLanguage(this,"ru");
                mySharedPreferences.setLanguage("ru");
            }
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else{
            isSpinnerInitialized = true;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
