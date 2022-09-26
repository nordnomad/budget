package ua.budget.domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ua.budget.AppDatabase;

public class AccountRepository {
    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;

    AccountRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mAccountDao = db.accountDao();
        mAllAccounts = mAccountDao.getAll();
    }

    LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
    }

    void insert(Account account) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.insertAll(account);
        });
    }

}
