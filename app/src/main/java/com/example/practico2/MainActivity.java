package com.example.practico2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, 1000);
        }
    }
      catch (Exception ex){
          Toast.makeText(this,
                  "Error in MainActivity.onCreate: " + ex.getMessage(),
                  Toast.LENGTH_SHORT).show();
      }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ServiceContent.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mensaje", "Destruccion del main");
    }

}
