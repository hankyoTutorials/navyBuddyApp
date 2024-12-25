package com.hankyo.navyBuddyApp.expensesAndBudgetMonitor.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {expensesAndBudgetDataList.class},version = 1)
//@TypeConverters(DataTypeConverters.class)
public abstract class expensesAndBudgetDataListDatabase extends RoomDatabase {
    public abstract expensesAndBudgetDataListDAO expensesAndBudgetDataListDAO();
}
