package com.hankyo.navyBuddyApp.checkListsUpdate;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {checkList.class,currentCheckList.class},version = 1)
public abstract class checkListDatabase extends RoomDatabase {
    public abstract checkListDAO checkListDAO();
}
