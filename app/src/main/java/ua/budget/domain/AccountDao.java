package ua.budget.domain;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("SELECT * FROM account")
    LiveData<List<Account>> getAll();

    @Insert
    void insertAll(Account... accounts);
}
