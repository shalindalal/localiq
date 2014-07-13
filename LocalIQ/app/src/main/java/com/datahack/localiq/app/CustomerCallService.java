package com.datahack.localiq.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by sdalal on 7/12/14.
 */
public class CustomerCallService extends Service{

    public static final String ACTION_UPDATE_CALL = "com.localiq.app.action.UPDATE_CALL";
    public static final String ACTION_UPDATE_VISIT = "com.localiq.app.action.UPDATE_VISIT";
    public static final String EXTRA_PHONE_NUMBER = "phoneNumber";
    public static final String EXTRA_BLUETOOTH_UUID = "bluetoothUUID";
    public static final String EXTRA_BLUETOOTH_NAME = "bluetoothName";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(ACTION_UPDATE_CALL.equals(intent.getAction())) {
            String phoneNumber = intent.getStringExtra(EXTRA_PHONE_NUMBER);
            boolean updated = ((LocalIQApp) getApplication()).getDatabase().updateCustomerCall(phoneNumber);
            if(updated)
                Toast.makeText(this, phoneNumber + " added to database", Toast.LENGTH_SHORT).show();
        } else if(ACTION_UPDATE_VISIT.equals(intent.getAction())) {
            String bluetoothUUID = intent.getStringExtra(EXTRA_BLUETOOTH_UUID);
            String bluetoothName = intent.getStringExtra(EXTRA_BLUETOOTH_NAME);
            boolean updated = ((LocalIQApp) getApplication()).getDatabase().updateCustomerVisit(bluetoothUUID);
            if(updated)
                Toast.makeText(this, bluetoothName + " updated in database", Toast.LENGTH_SHORT).show();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
