package com.example.practico2;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.Telephony;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class ServiceContent extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Cursor cur = getContentResolver().query(Uri.parse("content://sms/inbox"),
                null, null, null, null);

        String msg = "";
        int aux=0;

        if(cur.getCount()>0 ){

        while (cur.moveToNext() && aux<5)
        {
                    int num = cur.getColumnIndex(Telephony.Sms.ADDRESS);
                    int date = cur.getColumnIndex(Telephony.Sms.DATE);
                    String phNumber = cur.getString(num);
                    String fecha = (String) DateFormat.format("dd/MM/yy k:mm", cur.getLong(date));

                    msg += "Recibiste un mensaje de " + phNumber + ". En la fecha y hora: " + fecha + ". ";
            aux++;
        }

                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}