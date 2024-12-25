package com.hankyo.navyBuddyApp.checkListsUpdate;

import androidx.room.Dao;
//import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import java.util.List;

@Dao
public interface checkListDAO {

    @Upsert
    void addCheckListToDatabase(checkList checkList);

//    @Delete
//    public void deleteCheckListFromDatabase(checkList checkList);

    @Upsert
    void addCurrentCheckListIdToDatabase(currentCheckList currentCheckList);

    @Query("DELETE FROM checkList WHERE checkListId = :deletingCheckListId")
    void deleteCheckListById(int deletingCheckListId);

    @Query("select * from checkList")
     List<checkList> getAllCheckListItems();

    @Query("select * from currentCheckList")
     List<currentCheckList> getCurrentCheckListId();
}
