package com.hankyo.navyBuddyApp.expensesAndBudgetMonitor;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.hankyo.navyBuddyApp.R;
import com.hankyo.navyBuddyApp.databinding.FragmentExpensesAndBudgetMonitorBinding;
import com.hankyo.navyBuddyApp.expensesAndBudgetMonitor.data.expensesAndBudgetDataList;
import com.hankyo.navyBuddyApp.expensesAndBudgetMonitor.data.expensesAndBudgetDataListDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class expensesAndBudgetMonitorFragment extends Fragment {

    private FragmentExpensesAndBudgetMonitorBinding binding;



//    @Override
//    public void onCreate(Bundle savedInstanceState,) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentExpensesAndBudgetMonitorBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

//        SpannableString eNbMonitorTitle = new SpannableString(binding.eNbMonitorTitle.getText());
//        eNbMonitorTitle.setSpan(new UnderlineSpan(),0,eNbMonitorTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        binding.eNbMonitorTitle.setText(eNbMonitorTitle);
//        expensesAndBudgetDataListDatabase expensesAndBudgetDataListDB = Room.databaseBuilder(requireContext(), expensesAndBudgetDataListDatabase.class,"expensesAndBudgetDataList-database").allowMainThreadQueries().build();
//        List<expensesAndBudgetDataList> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();
//        String expensesAndBudgetDataListItems = String.valueOf(binding.expense2Name.getText());
//       // do the rest using an array or json or any
//       expensesAndBudgetDataList updatedDataList = new expensesAndBudgetDataList(expensesAndBudgetDataListItems);
//        expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().addExpensesAndBudgetDataToList(updatedDataList);
//        Toast.makeText(requireContext(),receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems,Toast.LENGTH_SHORT).show();

//        //save data to room database:
//        expensesAndBudgetDataListDatabase expensesAndBudgetDataListDB = Room.databaseBuilder(requireContext(), expensesAndBudgetDataListDatabase.class,"expensesAndBudgetDataList-database").allowMainThreadQueries().build();
//        String expensesAndBudgetDataListItems = "hello";
//        expensesAndBudgetDataList latestExpensesAndBudgetDataList = new expensesAndBudgetDataList(expensesAndBudgetDataListItems);
//        expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().addExpensesAndBudgetDataToList(latestExpensesAndBudgetDataList);
//        //get data from room database:
//        List<expensesAndBudgetDataList> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();
//        System.out.println(receiveDataFromDatabase);
//        Toast.makeText(requireContext(), receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems, Toast.LENGTH_SHORT).show();



        expensesAndBudgetDataListDatabase expensesAndBudgetDataListDB = Room.databaseBuilder(requireContext(), expensesAndBudgetDataListDatabase.class,"expensesAndBudgetDataList-database.db").allowMainThreadQueries().build();
        List<expensesAndBudgetDataList> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();

        //convert json output data into a array format elements:
        //System.out.println("databaseIsEmpty:"+ receiveDataFromDatabase.isEmpty());
        if(!receiveDataFromDatabase.isEmpty()) { //JsonArray throws an error when database is empty
            Gson gson = new Gson();
            //getIncomeNamesAndValuesListFromDatabase:
            JsonArray getIncomeNamesAndValuesListFromDatabase = gson.fromJson(receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).incomeNamesAndValuesListItems,JsonArray.class);
            //getExpensesNamesAndValuesListFromDatabase:
            JsonArray getExpensesNamesAndValuesListFromDatabase = gson.fromJson(receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesNamesAndValuesListItems,JsonArray.class);

//        for(int i = 0;i<convertToJsonArray.size();i++){
//            //get json object value by element name
//            System.out.println("elements:"+i);
//
//        }
            //String myJsonStr = "{'test':'100'}";
            //JsonObject jsonObject = gson.fromJson(receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems,JsonObject.class);
            //JsonArray items = jsonObject.getAsJsonArray();
            //double result = jsonObject.get("test").getAsDouble();

            //restore previous data on text fields:

            //save textInput values as integers:
            //length is 2, because quotation marks("") of string count as characters
            //incomeNamesAndValuesList:
            //restore values from database:
            if(getIncomeNamesAndValuesListFromDatabase.get(1).toString().length() != 2){binding.income1Value.setText(String.valueOf(getIncomeNamesAndValuesListFromDatabase.get(1).getAsInt()));}
            if(getIncomeNamesAndValuesListFromDatabase.get(3).toString().length() != 2){binding.income2Value.setText(String.valueOf(getIncomeNamesAndValuesListFromDatabase.get(3).getAsInt()));}
            if(getIncomeNamesAndValuesListFromDatabase.get(5).toString().length() != 2){binding.income3Value.setText(String.valueOf(getIncomeNamesAndValuesListFromDatabase.get(5).getAsInt()));}
            if(getIncomeNamesAndValuesListFromDatabase.get(7).toString().length() != 2){binding.income4Value.setText(String.valueOf(getIncomeNamesAndValuesListFromDatabase.get(7).getAsInt()));}
            if(getIncomeNamesAndValuesListFromDatabase.get(9).toString().length() != 2){binding.income5Value.setText(String.valueOf(getIncomeNamesAndValuesListFromDatabase.get(9).getAsInt()));}
            if(getIncomeNamesAndValuesListFromDatabase.get(11).toString().length() != 2){binding.income6Value.setText(String.valueOf(getIncomeNamesAndValuesListFromDatabase.get(11).getAsInt()));}
            //restore names from database
            binding.income1Name.setText(getIncomeNamesAndValuesListFromDatabase.get(0).getAsString());
            binding.income2Name.setText(getIncomeNamesAndValuesListFromDatabase.get(2).getAsString());
            binding.income3Name.setText(getIncomeNamesAndValuesListFromDatabase.get(4).getAsString());
            binding.income4Name.setText(getIncomeNamesAndValuesListFromDatabase.get(6).getAsString());
            binding.income5Name.setText(getIncomeNamesAndValuesListFromDatabase.get(8).getAsString());
            binding.income6Name.setText(getIncomeNamesAndValuesListFromDatabase.get(10).getAsString());


            //expensesNamesAndValuesList:
            //restore values from database:
            //int expense1Value = convertToJsonArray.get(1).toString().length() == 2 ? 0 : convertToJsonArray.get(1).getAsInt();
            //System.out.println("length:"+ convertToJsonArray.get(3).toString().length() +" isNull:"+ convertToJsonArray.get(3));
            //if(convertToJsonArray.get(5).toString().length() == 2){System.out.println("element value is empty");
            if(getExpensesNamesAndValuesListFromDatabase.get(1).toString().length() != 2){binding.expense1Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(1).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(3).toString().length() != 2){binding.expense2Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(3).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(5).toString().length() != 2){binding.expense3Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(5).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(7).toString().length() != 2){binding.expense4Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(7).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(9).toString().length() != 2){binding.expense5Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(9).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(11).toString().length() != 2){binding.expense6Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(11).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(13).toString().length() != 2){binding.expense7Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(13).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(15).toString().length() != 2){binding.expense8Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(15).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(17).toString().length() != 2){binding.expense9Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(17).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(19).toString().length() != 2){binding.expense10Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(19).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(21).toString().length() != 2){binding.expense11Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(21).getAsInt()));}
            if(getExpensesNamesAndValuesListFromDatabase.get(23).toString().length() != 2){binding.expense12Value.setText(String.valueOf(getExpensesNamesAndValuesListFromDatabase.get(23).getAsInt()));}
            //            int expense2Value = convertToJsonArray.get(3).toString().length() == 2 ? 0 : convertToJsonArray.get(3).getAsInt();
            //restore names from database:
            //String expense1Name = convertToJsonArray.get(0).getAsString();
            binding.expense1Name.setText(getExpensesNamesAndValuesListFromDatabase.get(0).getAsString());
            // binding.expense1Value.setText(String.valueOf(expense1Value));
            binding.expense2Name.setText(getExpensesNamesAndValuesListFromDatabase.get(2).getAsString());
            binding.expense3Name.setText(getExpensesNamesAndValuesListFromDatabase.get(4).getAsString());
            binding.expense4Name.setText(getExpensesNamesAndValuesListFromDatabase.get(6).getAsString());
            binding.expense5Name.setText(getExpensesNamesAndValuesListFromDatabase.get(8).getAsString());
            binding.expense6Name.setText(getExpensesNamesAndValuesListFromDatabase.get(10).getAsString());
            binding.expense7Name.setText(getExpensesNamesAndValuesListFromDatabase.get(12).getAsString());
            binding.expense8Name.setText(getExpensesNamesAndValuesListFromDatabase.get(14).getAsString());
            binding.expense9Name.setText(getExpensesNamesAndValuesListFromDatabase.get(16).getAsString());
            binding.expense10Name.setText(getExpensesNamesAndValuesListFromDatabase.get(18).getAsString());
            binding.expense11Name.setText(getExpensesNamesAndValuesListFromDatabase.get(20).getAsString());
            binding.expense12Name.setText(getExpensesNamesAndValuesListFromDatabase.get(22).getAsString());


            //show results:
            //System.out.println(convertToJsonArray.get(convertToJsonArray.size()-2));
                System.out.println(getExpensesNamesAndValuesListFromDatabase);
                //Toast.makeText(requireContext(), "onCreateView:"+getExpensesNamesAndValuesListFromDatabase, Toast.LENGTH_SHORT).show();
        }


        //show help button:
        Button helpButton = binding.helpButtonInEnB;
        helpButton.setOnClickListener(view -> binding.helpWindowInEnB.setVisibility(View.VISIBLE));
        //hide help button:
        Button closeHelpButton = binding.closeHelpWindowButtonInEnB;
        closeHelpButton.setOnClickListener( view -> binding.helpWindowInEnB.setVisibility(View.INVISIBLE));


        return root;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_expenses_and_budget_monitor, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button processButton = binding.processButton;
        processButton.setOnClickListener(view1 -> {

            //receive incomesListItem values:
            String income1Name = binding.income1Name.getText().toString();
            String income1Value = binding.income1Value.getText().toString();
            String income2Name = binding.income2Name.getText().toString();
            String income2Value = binding.income2Value.getText().toString();
            String income3Name = binding.income3Name.getText().toString();
            String income3Value = binding.income3Value.getText().toString();
            String income4Name = binding.income4Name.getText().toString();
            String income4Value = binding.income4Value.getText().toString();
            String income5Name = binding.income5Name.getText().toString();
            String income5Value = binding.income5Value.getText().toString();
            String income6Name = binding.income6Name.getText().toString();
            String income6Value = binding.income6Value.getText().toString();

            //receive expensesListItem values:
            String expense1Name = binding.expense1Name.getText().toString();
            String expense1Value = binding.expense1Value.getText().toString();
            String expense2Name = binding.expense2Name.getText().toString();
            String expense2Value = binding.expense2Value.getText().toString();
            String expense3Name = binding.expense3Name.getText().toString();
            String expense3Value = binding.expense3Value.getText().toString();
            String expense4Name = binding.expense4Name.getText().toString();
            String expense4Value = binding.expense4Value.getText().toString();
            String expense5Name = binding.expense5Name.getText().toString();
            String expense5Value = binding.expense5Value.getText().toString();
            String expense6Name = binding.expense6Name.getText().toString();
            String expense6Value = binding.expense6Value.getText().toString();
            String expense7Name = binding.expense7Name.getText().toString();
            String expense7Value = binding.expense7Value.getText().toString();
            String expense8Name = binding.expense8Name.getText().toString();
            String expense8Value = binding.expense8Value.getText().toString();
            String expense9Name = binding.expense9Name.getText().toString();
            String expense9Value = binding.expense9Value.getText().toString();
            String expense10Name = binding.expense10Name.getText().toString();
            String expense10Value = binding.expense10Value.getText().toString();
            String expense11Name = binding.expense11Name.getText().toString();
            String expense11Value = binding.expense11Value.getText().toString();
            String expense12Name = binding.expense12Name.getText().toString();
            String expense12Value = binding.expense12Value.getText().toString();

            //put incomeDataList editText values into an array:
            String[] incomeNamesAndValues = { income1Name, income1Value, income2Name, income2Value, income3Name, income3Value, income4Name, income4Value, income5Name, income5Value, income6Name, income6Value };
            ArrayList<String> incomeNamesAndValuesList = new ArrayList<>(Arrays.asList(incomeNamesAndValues));
            Collections.addAll(incomeNamesAndValuesList);
            System.out.println(incomeNamesAndValuesList);

            //put expensesDataList editText values into an array:
            String[] expensesNamesAndValues = { expense1Name, expense1Value, expense2Name, expense2Value, expense3Name, expense3Value, expense4Name, expense4Value, expense5Name, expense5Value, expense6Name, expense6Value, expense7Name, expense7Value, expense8Name, expense8Value, expense9Name, expense9Value, expense10Name, expense10Value, expense11Name, expense11Value, expense12Name, expense12Value };
            //String[] expensesNamesWithValues = { expense1Name, expense1Value, expense2Name, expense2Value };
            ArrayList<String> expensesNamesAndValuesList = new ArrayList<>(Arrays.asList(expensesNamesAndValues));
            Collections.addAll(expensesNamesAndValuesList);
            //System.out.println(expensesNamesWithValuesList);



            //convert the array into json objects format:
            Gson gson = new Gson();
            //incomeNamesAndValuesList:
           String incomeNamesAndValuesListJson = gson.toJson(incomeNamesAndValuesList);
           System.out.println("incomeNamesAndValuesListJson: "+incomeNamesAndValuesListJson);

            //expensesNamesAndValuesList:
            String expensesNamesAndValuesListJson = gson.toJson(expensesNamesAndValuesList);
            System.out.println("expensesNamesAndValuesListJson: "+expensesNamesAndValuesListJson);

            //save data to room database:
            expensesAndBudgetDataListDatabase expensesAndBudgetDataListDB = Room.databaseBuilder(requireContext(), expensesAndBudgetDataListDatabase.class,"expensesAndBudgetDataList-database.db").allowMainThreadQueries().build();
            //expensesAndBudgetDataList latestExpensesAndBudgetDataList = new expensesAndBudgetDataList(expensesNamesWithValuesList.toString());
            expensesAndBudgetDataList latestExpensesAndBudgetDataList = new expensesAndBudgetDataList(incomeNamesAndValuesListJson,expensesNamesAndValuesListJson);
            expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().addExpensesAndBudgetDataToList(latestExpensesAndBudgetDataList);

            //get data from room database:
            //LiveData<List<expensesAndBudgetDataList>> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();
            List<expensesAndBudgetDataList> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();
            //System.out.println(receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems);
            //Toast.makeText(requireContext(), receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems, Toast.LENGTH_SHORT).show();

            //convert json into a array format elements:
            //Array convertedJsonToArray = gson.fromJson(json,Array.class);
            //getIncomeNamesAndValuesListFromDatabase:
            JsonArray getIncomeNamesAndValuesListFromDatabase = gson.fromJson(receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).incomeNamesAndValuesListItems,JsonArray.class);
            System.out.println("getIncomeNamesAndValuesListFromDatabase: "+getIncomeNamesAndValuesListFromDatabase);
            JsonArray getExpensesNamesAndValuesListFromDatabase = gson.fromJson(receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesNamesAndValuesListItems,JsonArray.class);
            System.out.println("getExpensesNamesAndValuesListFromDatabase: "+getExpensesNamesAndValuesListFromDatabase);
            //Toast.makeText(requireContext(), convertToJsonArray.toString(), Toast.LENGTH_SHORT).show();

            //process the total income count:
            int i;
            int totalIncomeAmount = 0;
            for(i = 0; i < incomeNamesAndValuesList.size(); i++){
                if( i%2 == 1 && !incomeNamesAndValuesList.get(i).isEmpty() ){
                    System.out.println("IncomeIndexesLength:"+ incomeNamesAndValuesList.size() +" currentIndex:"+ i +" currentValue:"+ incomeNamesAndValuesList.get(i));

                    totalIncomeAmount = totalIncomeAmount + Integer.parseInt(incomeNamesAndValuesList.get(i));
                    System.out.println("totalIncomeAmount:"+ totalIncomeAmount);
                }
            }


            //process the total expenses count:
            //int totalExpensesCount = (Integer.parseInt(expense1Value) + Integer.parseInt(expense2Value) + Integer.parseInt(expense3Value) + Integer.parseInt(expense4Value) + Integer.parseInt(expense5Value) + Integer.parseInt(expense6Value) + Integer.parseInt(expense7Value) + Integer.parseInt(expense8Value) + Integer.parseInt(expense9Value) + Integer.parseInt(expense10Value));
            int x;
            int totalExpensesAmount = 0;
            for(x = 0; x < expensesNamesAndValuesList.size(); x++){
                if( x%2 == 1 && !expensesNamesAndValuesList.get(x).isEmpty()) {
                    System.out.println("expensesIndexesLength:"+ expensesNamesAndValuesList.size() +" currentIndex:"+ x +" currentValue:"+ expensesNamesAndValuesList.get(x)); //search for "System.out" in logcat

                    totalExpensesAmount = totalExpensesAmount + Integer.parseInt(expensesNamesAndValuesList.get(x)); //empty values doesnt wark on integers error fix
                    System.out.println("totalExpensesAmount:"+totalExpensesAmount);

                }

            }


            //process remaining credits:
            int remainingBudgetAmount = totalIncomeAmount - totalExpensesAmount;
            System.out.println("remainingBudgetAmount:"+ remainingBudgetAmount);
            //process remaining credits percentage(%):
            double remainingBudgetAmountPercentage = (double) (totalIncomeAmount - totalExpensesAmount)/totalIncomeAmount*100;
            System.out.println("remainingBudgetPercentage:"+ Math.round(remainingBudgetAmountPercentage) +"%");


            //show results:
            //binding.showProcessedResults.setText("ඔබගේ ආදායම් මාර්ග වලින් මාසිකව රු:"+ totalIncomeAmount +"ක ආදායමක් ලබාගන්නා අතර\nඔබගේ වියදම් මාර්ග මගින් මාසික වියදම ලෙස රු:"+ totalExpensesAmount +"ක මුදලක් වියදම් කරයි\nඔබගේ මාසික ආදායමෙන් ඉතිරි වන්නේ රු:"+ remainingBudgetAmount +"ක මුදලක් එනම් මාසික ආදායමෙන් "+ Math.round(remainingBudgetAmountPercentage) +"% ක ප්‍රතිශතයකි.");
            binding.showProcessedResults.setText("▶️මෙම මාසයේ ඔබගේ ආදායම රු: "+ totalIncomeAmount +" කි\n▶️මෙම මාසයේ ඔබගේ වියදම රු: "+ totalExpensesAmount +" කි\n▶️මෙම මාසයේ ඉතිරිවන මුදල රු: "+ remainingBudgetAmount +" කි\n▶️ඉතිරි වන මුදලේ ප්‍රතිශතය "+ Math.round(remainingBudgetAmountPercentage) +"% කි.");

        });


//        //save data to room database:
//        expensesAndBudgetDataListDatabase expensesAndBudgetDataListDB = Room.databaseBuilder(requireContext(), expensesAndBudgetDataListDatabase.class,"expensesAndBudgetDataList-database").allowMainThreadQueries().build();
//        String expensesAndBudgetDataListItems = "hello";
//        expensesAndBudgetDataList latestExpensesAndBudgetDataList = new expensesAndBudgetDataList(expensesAndBudgetDataListItems);
//        expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().addExpensesAndBudgetDataToList(latestExpensesAndBudgetDataList);
//        //get data from room database:
//        List<expensesAndBudgetDataList> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();
//        System.out.println(receiveDataFromDatabase);
//        Toast.makeText(requireContext(), receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems, Toast.LENGTH_SHORT).show();


//        for(int i = 0; i < expensesNamesWithValues.length; i++){
//            expensesNamesWithValuesList.add( expensesNamesWithValues[i] );
//            //Collections.addAll(expensesNamesWithValues);
//        }
        //https://ngima.medium.com/how-to-save-list-of-data-in-table-column-in-room-using-type-converter-gson-691aa780ab19
        //https://amit-bhandari.medium.com/storing-java-objects-other-than-primitive-types-in-room-database-11e45f4f6d22

//        expensesAndBudgetDataListDatabase expensesAndBudgetDataListDB = Room.databaseBuilder(requireContext(), expensesAndBudgetDataListDatabase.class,"expensesAndBudgetDataList-database").allowMainThreadQueries().build();
//     //   List<expensesAndBudgetDataList> receiveDataFromDatabase = expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().getExpensesAndBudgetDataListItems();
//    // do the rest using an array or json or any
//        expensesAndBudgetDataList updatedDataList = new expensesAndBudgetDataList(expensesNamesWithValuesList);
//        expensesAndBudgetDataListDB.expensesAndBudgetDataListDAO().addExpensesAndBudgetDataToList(updatedDataList);
        //Toast.makeText(requireContext(),receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1).expensesAndBudgetDataListItems,Toast.LENGTH_SHORT).show();
        //Toast.makeText(requireContext(), (CharSequence) receiveDataFromDatabase.get(receiveDataFromDatabase.size()-1),Toast.LENGTH_SHORT).show();

    }
}


