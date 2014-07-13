package com.datahack.localiq.app;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.datahack.localiq.app.dummy.DummyContent;

/**
 * Created by sdalal on 7/12/14.
 */
public class CustomersAdapter extends FirebaseListAdapter<DummyContent.Customer> {

    public CustomersAdapter(Activity activity) {
        super(((LocalIQApp)activity.getApplication()).getDatabase().mCustomersRef.endAt(), DummyContent.Customer.class, R.layout.list_item_customer, activity);
    }

    @Override
    protected void populateView(View v, DummyContent.Customer model) {
        TextView number = (TextView) v.findViewById(R.id.textCustomerNumber);
        TextView calledAt = (TextView) v.findViewById(R.id.textCalledAt);
        TextView visitedAt = (TextView) v.findViewById(R.id.textVisitedAt);

        number.setText(model.phoneNumber);
        calledAt.setText(model.called_at);
        visitedAt.setText(model.visited_at);
    }
}
