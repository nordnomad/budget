package ua.budget.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey
    public Integer id;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public Double amount;

    public Account(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }
}
