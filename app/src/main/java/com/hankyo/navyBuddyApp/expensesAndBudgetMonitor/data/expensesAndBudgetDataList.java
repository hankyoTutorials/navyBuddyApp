package com.hankyo.navyBuddyApp.expensesAndBudgetMonitor.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class expensesAndBudgetDataList {

    @PrimaryKey(autoGenerate = true)
    public int id;
// use Upsert when you use an specific id
//    @PrimaryKey
//    public int id = 1;

    @ColumnInfo(name="incomeNamesAndValuesList")
    public String incomeNamesAndValuesListItems;

    @ColumnInfo(name="expensesAndBudgetDataList")
    public String  expensesNamesAndValuesListItems;
    //public ArrayList<String>  expensesAndBudgetDataListItems;

    //public expensesAndBudgetDataList(ArrayList<String> expensesAndBudgetDataListItems) { this.expensesAndBudgetDataListItems = expensesAndBudgetDataListItems;}
    //public expensesAndBudgetDataList(String expensesNamesAndValuesListItems) {this.expensesNamesAndValuesListItems = expensesNamesAndValuesListItems;}
    public expensesAndBudgetDataList(String incomeNamesAndValuesListItems,String expensesNamesAndValuesListItems) {
        this.incomeNamesAndValuesListItems = incomeNamesAndValuesListItems;
        this.expensesNamesAndValuesListItems = expensesNamesAndValuesListItems;
    }
    //public expensesAndBudgetDataList(ArrayList<String> expensesAndBudgetDataListItems) { this.expensesAndBudgetDataListItems = expensesAndBudgetDataListItems;}

//    //getters:
//    public String getIncomeNamesAndValuesListItems() {return incomeNamesAndValuesListItems;}
//    public String getExpensesNamesAndValuesListItems() {return expensesNamesAndValuesListItems;}
//
//    //setters:
//    public void setIncomeNamesAndValuesListItems(String incomeNamesAndValuesListItems){this.incomeNamesAndValuesListItems = incomeNamesAndValuesListItems;}
//    public void setExpensesNamesAndValuesListItems(String expensesNamesAndValuesListItems){this.expensesNamesAndValuesListItems = expensesNamesAndValuesListItems;}
}

//class DataTypeConverters {
//    @TypeConverter
//    public static ArrayList<String> fromString(String value) {
//        Type listType = new TypeToken<ArrayList<String>>() {
//        }.getType();
//        //implementation 'com.google.code.gson:gson:2.11.0'
//        return new Gson().fromJson(value, listType);
//
//    }
//
//    @TypeConverter
//    public static String fromArrayList(ArrayList<String> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        return json;
//    }
//}