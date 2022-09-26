package ua.budget.domain;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {
    private AccountRepository mRepository;
    private final LiveData<List<Account>> mAllAccounts;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AccountRepository(application);
        mAllAccounts = mRepository.getAllAccounts();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
    }

    public void insert(Account account) {
        mRepository.insert(account);
    }
}
