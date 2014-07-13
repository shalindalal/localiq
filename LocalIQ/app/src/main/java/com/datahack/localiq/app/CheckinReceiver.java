package com.datahack.localiq.app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sdalal on 7/12/14.
 */
public class CheckinReceiver extends BroadcastReceiver {

    public static final String TAG = "CheckinReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String address = device.getAddress().toLowerCase();
            String name = device.getName();
            Intent visitIntent = new Intent(CustomerCallService.ACTION_UPDATE_VISIT);
            visitIntent.putExtra(CustomerCallService.EXTRA_BLUETOOTH_UUID, address);
            visitIntent.putExtra(CustomerCallService.EXTRA_BLUETOOTH_NAME, name);
            visitIntent.setClass(context, CustomerCallService.class);
            context.startService(visitIntent);
            //Log.i(TAG, name + " device with address = " + address);
            // When discovery is finished, change the Activity title
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
        }
    }
}
