package com.datahack.localiq.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * Created by sdalal on 7/12/14.
 */
public class IncomingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if(TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Intent storeNumberIntent = new Intent(CustomerCallService.ACTION_UPDATE_CALL);
                storeNumberIntent.putExtra(CustomerCallService.EXTRA_PHONE_NUMBER, phoneNumber);
                storeNumberIntent.setClass(context, CustomerCallService.class);
                context.startService(storeNumberIntent);
            }
        }
    }
}
