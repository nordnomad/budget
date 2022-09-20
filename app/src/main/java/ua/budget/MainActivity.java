package ua.budget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ua.budget.domain.Account;

public class MainActivity extends AppCompatActivity {

    public static List<Account> accounts = new ArrayList<>();
private AccountListAdapter accountListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);

        var listView = (ListView) findViewById(R.id.list_view);
        accounts.add(new Account(1, "Yevhen", 1000.67));
        accounts.add(new Account(2,"Ganna", 10.67));
        accountListAdapter = new AccountListAdapter(this, R.layout.list_item, accounts);
        listView.setAdapter(accountListAdapter);
//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            var item = (String) parent.getItemAtPosition(position);
//            Toast.makeText(this, item, LENGTH_SHORT).show();
//        });

//        var addAccountBtn = (Button) findViewById(R.id.button_add_account);
//        addAccountBtn.setOnClickListener(view -> {
//            accounts.add(new Account("Cash", 2837.89));
//            accountListAdapter.notifyDataSetChanged();
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        accountListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        accountListAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.account_add_bnt) {
            startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu. action_bar, menu);
        return true;
    }

    private static class AccountListAdapter extends ArrayAdapter<Account> {

        Context context;
        List<Account> values;

        public AccountListAdapter(Context context, int resource, List<Account> values) {
            super(context, resource, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            var inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            var rowView = inflater.inflate(R.layout.list_item, parent, false);
            var nameView = (TextView) rowView.findViewById(R.id.list_item_name);
            nameView.setText(values.get(position).name);
            var amountView = (TextView) rowView.findViewById(R.id.list_item_amount);
            amountView.setText(values.get(position).amount.toString());
            var iconView = (ImageView) rowView.findViewById(R.id.list_item_icon);
            iconView.setImageResource(android.R.drawable.ic_dialog_info);
            return rowView;

        }
    }

}
