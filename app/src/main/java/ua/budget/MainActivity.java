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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        accountListAdapter = new AccountListAdapter(new AccountListAdapter.AccountDiff());
        recyclerView.setAdapter(accountListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        var toolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);

        var db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "budget")
                .build();


//        var listView = (ListView) findViewById(R.id.list_view);
//        accounts.add(new Account(1, "Yevhen", 1000.67));
//        accounts.add(new Account(2,"Ganna", 10.67));
//        accountListAdapter = new AccountListAdapter(this, R.layout.list_item, db.accountDao().getAll());
//        listView.setAdapter(accountListAdapter);
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
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder {
        private final ImageView accountIcon;
        private final TextView accountName;
        private final TextView accountAmount;

        public AccountViewHolder(View itemView) {
            super(itemView);
            accountIcon = itemView.findViewById(R.id.list_item_icon);
            accountName = itemView.findViewById(R.id.list_item_name);
            accountAmount = itemView.findViewById(R.id.list_item_amount);
        }

        public void bind(String text, String amount) {
            accountIcon.setImageResource(android.R.drawable.ic_dialog_info);
            accountName.setText(text);
            accountAmount.setText(amount);
        }

        static AccountViewHolder create(ViewGroup parent) {
            var view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new AccountViewHolder(view);
        }
    }

    private static class AccountListAdapter extends ListAdapter<Account, AccountViewHolder> {

        Context context;
        List<Account> values;

        protected AccountListAdapter(@NonNull DiffUtil.ItemCallback<Account> diffCallback) {
            super(diffCallback);
        }

        @NonNull
        @Override
        public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return AccountViewHolder.create(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
            var current = getItem(position);
            holder.bind(current.name, current.amount.toString());
        }

        static class AccountDiff extends DiffUtil.ItemCallback<Account> {

            @Override
            public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
                return oldItem.name.equals(newItem.name);
            }
        }
    }

}
