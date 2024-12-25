package com.hankyo.navyBuddyApp.checkListsUpdate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class currentCheckList {
    //Boolean onBackPressedOnceExecuted = false;

    @PrimaryKey(autoGenerate = false)
    public int id = 1;

    @ColumnInfo
    public Integer currentCheckListId;

    public currentCheckList(Integer currentCheckListId){
        this.currentCheckListId = currentCheckListId;
    }

}
