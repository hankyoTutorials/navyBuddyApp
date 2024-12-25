package com.hankyo.navyBuddyApp.checkListsUpdate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class checkList {

//    @PrimaryKey(autoGenerate = true)
//    public int id;
    @PrimaryKey(autoGenerate = false)
    public int checkListId;

    @ColumnInfo(name="checkList_name")
    public String checkListName;

    @ColumnInfo(name="checklist_items")
    public String checkListItems;

    public checkList(Integer checkListId, String checkListName, String checkListItems) {
        this.checkListId = checkListId;
        this.checkListName = checkListName;
        this.checkListItems = checkListItems;
    }

}

