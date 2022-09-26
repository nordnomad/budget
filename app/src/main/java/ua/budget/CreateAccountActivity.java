package ua.budget;

import static java.util.Objects.requireNonNull;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import java.util.Objects;

import ua.budget.domain.Account;

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        var toolbar = (Toolbar) findViewById(R.id.create_account_toolbar);
        setSupportActionBar(toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        var db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "budget")
                .build();

        var button = (Button) findViewById(R.id.save_account_btn);
        var accountET = (EditText) findViewById(R.id.account_name_et);
        button.setOnClickListener(v -> {
            var name = accountET.getText().toString();
            db.accountDao().insertAll(new Account(name, 11.99));
            finish();
        });
    }


}
