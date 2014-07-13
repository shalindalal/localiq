package com.datahack.localiq.app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListCallers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor cursor = ((LocalIQApp)getApplication()).getDatabase().listCustomers();

        // The desired columns to be bound
        String[] from = new String[] {
                CustomersDB.KEY_PHONE_NUMBER,
                CustomersDB.KEY_CALLED_AT,
                CustomersDB.KEY_VISITED_AT,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.textCustomerNumber,
                R.id.textCalledAt,
                R.id.textVisitedAt,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                this, R.layout.list_item_customer,
                cursor,
                from,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
