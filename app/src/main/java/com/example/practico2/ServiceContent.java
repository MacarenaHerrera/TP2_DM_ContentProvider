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
import android.util.Log;

public class ServiceContent extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread tarea = new Thread(new Runnable() {
        @Override
        public void run() {
            Uri sms = Uri.parse("content://sms/inbox");
            ContentResolver cr = getContentResolver();

            while (true) {
                Cursor c = cr.query(sms,
                        null,
                        null,
                        null,
                        null);

                String nro = null;
                String fecha = null;

                if (c.getCount()>0 && c != null) {

                    int i = 1;
                    while (c.moveToNext() && i < 6) {

                        nro = c.getString(c.getColumnIndex(Telephony.Sms.ADDRESS));
                        fecha = c.getString(c.getColumnIndex(Telephony.Sms.DATE_SENT));

                        Log.d("mensaje", nro + " " + fecha);
                        i++;
                    }
                    try {
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                c.close();
            }
        }
    });
        tarea.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}