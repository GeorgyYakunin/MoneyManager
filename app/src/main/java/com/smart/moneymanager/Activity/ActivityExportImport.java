package com.smart.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.smart.moneymanager.DataController.ImportExport;
import com.smart.moneymanager.R;

public class ActivityExportImport extends AppCompatActivity {
    private LinearLayout btnImport, btnExport;

    private ImageView ivBarLeft,ivBarRight;
    private TextView tvBarText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_export_import);

        tvBarText = (TextView) findViewById(R.id.tvBarText);
        ivBarLeft = (ImageView) findViewById(R.id.ivBarLeft);
        ivBarRight = (ImageView) findViewById(R.id.ivBarRight);
        tvBarText.setText("Import Export");
        ivBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivitySetting.class));
            }
        });
        ivBarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivitySetting.class));
            }
        });



        btnImport = (LinearLayout) findViewById(R.id.btnImport);
        btnExport = (LinearLayout) findViewById(R.id.btnExport);
        Intent intent = getIntent();
        if(intent.hasExtra("isImport")){
            if(intent.getExtras().getString("isImport","novalue").equals("success")){
                Snackbar.make(findViewById(R.id.clRootExportImport),"Imported successfully",Snackbar.LENGTH_LONG).show();
            }else if(intent.getExtras().getString("isImport", "novalue").equals("failed")){
                Snackbar.make(findViewById(R.id.clRootExportImport),"Import failed, try again.",Snackbar.LENGTH_LONG).show();
            }
        }

        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityFileExp.class));
            }
        });
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImportExport.exportDB(ActivityExportImport.this);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();

    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(this, ActivitySetting.class));
    }
}
