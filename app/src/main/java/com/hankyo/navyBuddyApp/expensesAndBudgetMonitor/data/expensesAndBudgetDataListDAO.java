package com.hankyo.navyBuddyApp.expensesAndBudgetMonitor.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Upsert;

import java.util.List;

@Dao
public interface expensesAndBudgetDataListDAO {
    @Upsert
    void addExpensesAndBudgetDataToList(expensesAndBudgetDataList expensesAndBudgetDataList);

    @Query("select * from expensesAndBudgetDataList")
    //public LiveData<List<expensesAndBudgetDataList>> getExpensesAndBudgetDataListItems();
    List<expensesAndBudgetDataList> getExpensesAndBudgetDataListItems();
}
