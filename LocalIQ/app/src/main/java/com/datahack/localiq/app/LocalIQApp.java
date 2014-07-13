package com.datahack.localiq.app;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.datahack.localiq.app.dummy.DummyContent;


/**
 * Created by sdalal on 7/12/14.
 */
public class LocalIQApp extends Application{

    public CustomersDB mCustomersDB;
    public Handler mHandler;
    public CheckinReceiver mReceiver = new CheckinReceiver();
    public BluetoothAdapter mBtAdapter;

    public Runnable checkinRunnable = new Runnable() {
        @Override
        public void run() {
            // If we're already discovering, stop it
            if (mBtAdapter.isDiscovering()) {
                mBtAdapter.cancelDiscovery();
            }
            // Request discover from BluetoothAdapter
            mBtAdapter.startDiscovery();

            mHandler.postDelayed(this, 30000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mCustomersDB = new CustomersDB(this);
        mCustomersDB = mCustomersDB.open();

        mCustomersDB.insertCustomer(DummyContent.ITEMS.get(0));

        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();

        mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(checkinRunnable);
    }

    public CustomersDB getDatabase() {
        return mCustomersDB;
    }
}
