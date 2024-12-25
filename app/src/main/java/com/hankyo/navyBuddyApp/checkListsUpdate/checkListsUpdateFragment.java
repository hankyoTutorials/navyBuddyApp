package com.hankyo.navyBuddyApp.checkListsUpdate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.hankyo.navyBuddyApp.R;
import com.hankyo.navyBuddyApp.databinding.CheckListsUpdateFragmentBinding;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class checkListsUpdateFragment extends Fragment {


    private CheckListsUpdateFragmentBinding binding;

//delette button set
//--global variables--------------------------------------------
    int selectedCheckBoxesCount = 0;
    public int checkListId;
    public int newCheckListId;
    public boolean isAddNewListButtonClicked;
    public boolean isRunningSpinnerOnStartup;
    public boolean isRunningSpinnerOnSaveOrDeleteButton;
    public int currentCheckListId;
    public List<checkList> checkListFromDatabase;
    public List<currentCheckList> currentCheckListIdFromDatabase;
    //Boolean onBackPressedOnceExecuted = false;
    String confirmationWindowResult;
    String checkListEditorEditMode;
    String checkListNameEdit;

//--done! completely defined globle variables-------------------

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = CheckListsUpdateFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();


//--onClickListener actions for buttons------------------------

        //show help window
        Button helpButton = binding.helpButtonInCheckListUpdate;
        helpButton.setOnClickListener(view -> binding.helpWindowInCheckListUpdate.setVisibility(View.VISIBLE));
        //hide help window
        Button closeHelpButton = binding.closeHelpWindowButtonInCheckListUpdate;
        closeHelpButton.setOnClickListener(view -> binding.helpWindowInCheckListUpdate.setVisibility(View.INVISIBLE));

      //--process data and communicate When "Edit List" or "New List" button press-----
        ActivityResultLauncher<Intent> activityResultLauncherForCheckListEditor = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        System.out.println("activityResultLauncherForCheckList is performed.");
                        Intent checkListEditorWindowData = result.getData();
                        checkListEditorEditMode=checkListEditorWindowData.getExtras().getString("checkListEditorEditModeText");
                        checkListNameEdit=checkListEditorWindowData.getExtras().getString("checkListNameEdit");
                        System.out.println("dataFromCheckListEditorWindow, checkListEditorEditMode:"+ checkListEditorEditMode +" checkListNameEdit:"+ checkListNameEdit);

//                        if(checkListNameEdit.isEmpty()){
//                            System.out.println("checkListName is empty!.");
//                            Toast.makeText(requireContext(),"මෙම list එක සදහා ඔබ නමක් යොදා නොමැත",Toast.LENGTH_SHORT).show();
//                        }
//                        else {
                            //hide checkListEdit window:
                            //@binding.checkListEditor.setVisibility(View.GONE);
                            //@binding.helpButtonInCheckListUpdate.setVisibility(View.VISIBLE);

                            //--prepare new or edited list data to store---------------------------------------


                            //recognize newList id
//                if(checkListFromDatabase.isEmpty()){
//                    newCheckListId = 1;
//                    currentCheckListId = 0;
//                    System.out.println("checkLists database is empty! ");
//                }
//                if(checkListFromDatabase.size() == 1){
//                    System.out.println("isAddNewListbuttonClicked: "+ isAddNewListButtonClicked);
//                    isAddNewListButtonClicked = false;
//                    newCheckListId = (checkListFromDatabase.get(checkListFromDatabase.size()-1).checkListId + 1);
//                    //@currentCheckListId = checkListFromDatabase.get(checkListFromDatabase.size()-1).checkListId;
//                    currentCheckListId = checkListFromDatabase.size();
//                    System.out.println("checkList database only has one checkList! - newCheckListId:"+ newCheckListId +" currentCheckListId:"+ currentCheckListId);
//                }
                            if(isAddNewListButtonClicked){
                                System.out.println("isAddNewListbuttonClicked: "+ isAddNewListButtonClicked);
                                isAddNewListButtonClicked = false;
                                receiveDataFromRoomDB();
                                //@newCheckListId = (checkListFromDatabase.get(checkListFromDatabase.size()-1).checkListId + 1);
                                newCheckListId = (checkListFromDatabase.get(checkListFromDatabase.size()-1).checkListId + 1);
                                //newCheckListId = 0;
                                currentCheckListId = checkListFromDatabase.size();
                                System.out.println("newCheckList - id:"+ newCheckListId +" currentCheckListId:"+ currentCheckListId);
                            }
                            else{
                                receiveDataFromRoomDB();
                                //newCheckListId = currentCheckListIdFromDatabase.get(0).currentCheckListId;
                                Spinner spinner = binding.checkListsShowSpinner;
                                currentCheckListId = spinner.getSelectedItemPosition();
                                newCheckListId = checkListFromDatabase.get(currentCheckListId).checkListId;
                                System.out.println("savingAeditedCheckList - checkListId:"+ newCheckListId +" currentCheckListId:"+ currentCheckListId);
                            }

                            //updated checkList:
                            checkListId = newCheckListId;
                            //String checkListName = String.valueOf(binding.checkListNameEdit.getText());
                            //String checkListItems = String.valueOf(binding.checkListEditorEditMode.getText());
                            String checkListName = String.valueOf(checkListNameEdit);
                            String checkListItems = String.valueOf(checkListEditorEditMode);
                            System.out.println("Upserted checkList: checkListId:"+ checkListId +" checkListName:"+ checkListName +" currentCheckListId:"+ currentCheckListId +" checkListItems:"+ checkListItems);
                            //--done! preparing data completed----------------------------------------------------------------------------

                            //--add data to database--------------------------------------------------------------------------------------
                            checkListDatabase checkListDB = Room.databaseBuilder(requireContext(),
                                    checkListDatabase.class, "checkLists.db").allowMainThreadQueries().build();
                            checkList updatedCheckList = new checkList(checkListId, checkListName, checkListItems);
                            checkListDB.checkListDAO().addCheckListToDatabase(updatedCheckList);
                            currentCheckList currentCheckListIdToDB = new currentCheckList(currentCheckListId);
                            checkListDB.checkListDAO().addCurrentCheckListIdToDatabase(currentCheckListIdToDB);
                            //checkListDB.checkListDAO().addCurrentCheckListIdToDatabase(currentCheckListIdToDB);
                            //System.out.println("Upserted checkList: checkListId:"+ checkListId +" checkListName:"+ checkListName +" currentCheckListId:"+ currentCheckListId +" checkListItems:"+ checkListItems);
                            //--done! stored data on database-----------------------------------------------------------------------------



                            //--add current list text and name to checkListEditWindow-------------------------------------------------
                            receiveDataFromRoomDB();
                            //binding.checkListEditorEditMode.setText(checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems);
                            //binding.checkListNameEdit.setText(checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName);
                            checkListEditorEditMode= checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems;
                            checkListNameEdit= checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName;
                            //--done! added data to checkListWindow-------------------------------------------------------------------

                            //--edited list show in checkboxes------------------------------------------------------------------------

                            //--convert edited checkList into checkBoxes-------------------------------
                            stringArrayToCheckBoxes();
                            //--done! converted the edited stringArray into checkBoxes-----------------
                            //--done! edited list showing in checkboxes---------------------------------------------------------------


                            //--show current checkList name on spinner when added a newCheckList or editedAnCheckListName---------------------------------
                            isRunningSpinnerOnSaveOrDeleteButton = true;
                            spinnerDropdownMenu();
                            //--done! successfully showing current checkList name on spinner when added a newCheckList or editedAnCheckListName-----------

                        }


//                    }
                }
        );
      //--done! communication between activities and list data processing completed----


        //add new list button click actions:
        Button addNewListButton = binding.addNewListButton;
        addNewListButton.setOnClickListener(view -> {
            isAddNewListButtonClicked = true;
            //checkListEditorWindowIsShowing = true;
            //binding.helpButtonInCheckListUpdate.setVisibility(View.INVISIBLE);
            //binding.checkListEditorEditMode.setText("");
            //binding.checkListNameEdit.setText("");
            checkListEditorEditMode = "";
            checkListNameEdit = "";
            //binding.checkListEditor.setVisibility(View.VISIBLE);
            Intent intent = new Intent(requireContext(), checkListEditorActivity.class);
            intent.putExtra("checkListEditorEditModeText",checkListEditorEditMode);
            intent.putExtra("checkListNameEdit",checkListNameEdit);
            //intent.putExtra("checkListEditorEditModeText","hello");
            //intent.putExtra("checkListNameEdit","helloName");
            activityResultLauncherForCheckListEditor.launch(intent);

        });


      //--communicate data between fragment and "checkListDeleteConfirmWindow"---------
        ActivityResultLauncher<Intent> checkListDeleteActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent confirmationWindowData = result.getData();
                        confirmationWindowResult = confirmationWindowData.getExtras().getString("confirmationWindowResult");
                        System.out.println("receiveConfirmationWindowResult:"+ confirmationWindowResult);
                        //Toast.makeText(requireContext(),receiveConfirmationWindowResult.toString(),Toast.LENGTH_SHORT).show();


                        if(Objects.equals(confirmationWindowResult, "delete")) {
                            System.out.println("confirmationWindow: user is confirmed! deleting the checkList.");
                            //--deleting the current checkList-------------------------------------------
                            receiveDataFromRoomDB();
                            int getCurrentCheckListId = checkListFromDatabase.get(currentCheckListId).checkListId;
                            checkListDatabase checkListDB = Room.databaseBuilder(requireContext(),
                                    checkListDatabase.class, "checkLists.db").allowMainThreadQueries().build();
                            checkListDB.checkListDAO().deleteCheckListById(getCurrentCheckListId);
                            receiveDataFromRoomDB();
                            System.out.println("deletedCheckListId:" + getCurrentCheckListId + " currentDatabaseCheckListsCount:" + checkListFromDatabase.size() + " currentCheckListId:" + currentCheckListId);
                            //--done! successfully deleted the current checkList-------------------------

                            //--add a sample checklist if database is empty------------------------------
                            if (checkListFromDatabase.isEmpty()) {
                                String checkListItems = "පොඩි trip එකක් යනව\nවගාව නඩත්තු කරනව\nbike repair\nනෑදෑ ගෙදරක යනව\nගෙදර හා වටපිටාව පිරිසිදු කරනව\nගෙදරට අවශ්\u200Dය බඩුටික ගන්නවා";
                                checkList updatedCheckList = new checkList(1, "checkListSample", checkListItems);
                                checkListDB.checkListDAO().addCheckListToDatabase(updatedCheckList);
                                System.out.println("afterDeletedAllCheckList: added a sample checkList!.");
                                Toast.makeText(requireContext(), "ඔබගේ List සියල්ල Delete කර ඇත, අලුත් List එකක් සාදන්න", Toast.LENGTH_SHORT).show();
                            }
                            //--done! successfully added a sample checkList to database------------------

                            //--store the changed currentCheckListId-------------------------------------
                            receiveDataFromRoomDB();
                            currentCheckListId = checkListFromDatabase.size() - 1;
                            System.out.println("afterDeletedTheCheckList: currentCheckList" + currentCheckListId);
                            currentCheckList currentCheckListIdToDB = new currentCheckList(currentCheckListId);
                            checkListDB.checkListDAO().addCurrentCheckListIdToDatabase(currentCheckListIdToDB);
                            //--done! successfully stored the new currentCheckListId---------------------


                            //--add current list text and name to checkListEditWindow--------------------
                            receiveDataFromRoomDB();
                            //binding.checkListEditorEditMode.setText(checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems);
                            //binding.checkListNameEdit.setText(checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName);
                            checkListEditorEditMode= checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems;
                            checkListNameEdit= checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName;
                            //--done! added data to checkListWindow--------------------------------------

                            //--convert edited checkList into checkBoxes---------------------------------
                            stringArrayToCheckBoxes();
                            //--done! converted the edited stringArray into checkBoxes-------------------

                            //--show current checkList name on spinner when added a newCheckList or editedAnCheckListName-----------------------------
                            isRunningSpinnerOnSaveOrDeleteButton = true;
                              spinnerDropdownMenu();
                            //--done! successfully showing current checkList name on spinner when added a newCheckList or editedAnCheckListName-------

                            Toast.makeText(requireContext(),"Successfully deleted the List!..",Toast.LENGTH_SHORT).show();
                        }
                        else if(Objects.equals(confirmationWindowResult, "cancel")){
                            System.out.println("confirmationWindow: checkList deletion is cancelled by user!.");
                        }
                    }
                }
        );
      //--done! data communication between activities is successfully completed--------

      //--delete button click actions--------------------------------------------------
        Button  deleteCheckListButton = binding.checkListDeleteButton;
        deleteCheckListButton.setOnClickListener(view -> {

            System.out.println("deleteCheckListActivity Intent is Launched!");
            Intent intent = new Intent(requireContext(), checkListDeleteConfirmWindowActivity.class);
            receiveDataFromRoomDB();
            intent.putExtra("deletingCheckListName",checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName);
            intent.putExtra("deletingCheckListItems", checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems);
            //startActivity(intent);
            checkListDeleteActivityResultLauncher.launch(intent);
        });
      //--done! successfully added delete button click actions-------------------------

      //--open the checkListEditorWindow and edit current checkList--------------------
        Button checkListEditButton = binding.checkListEditButton;
        checkListEditButton.setOnClickListener(view -> {
            //@binding.checkListEditor.setVisibility(View.VISIBLE);
            //binding.helpButtonInCheckListUpdate.setVisibility(View.INVISIBLE);

          //--hide checkListEditor when backbutton is pressed---------------------------------------
//            checkListEditorWindowIsShowing = true;
//            if(!onBackPressedOnceExecuted) {
//                callback.remove();
//                System.out.println("onBackPressed once executed:"+ onBackPressedOnceExecuted);
//                System.out.println("callback is enabled:"+ callback.isEnabled());
//                requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
//                onBackPressedOnceExecuted = true;
//                //reqselectedCheckBoxesCountShowuireActivity().getOnBackPressedDispatcher().onBackPressed();
//            }
//            requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
//                @Override
//                public void handleOnBackPressed() {
//                    binding.checkListEditor.setVisibility(View.INVISIBLE);
//                    binding.helpButtonInCheckListUpdate.setVisibility(View.VISIBLE);
//                    isAddNewListButtonClicked = false;
//                    System.out.println("backbutton is pressed inside the newCheckListEditMode & checkListEditor!.");
//                }
//            });

          //--done! now checkListEditor window hides when back button is pressed--------------------

          //--receive DB data & add currentCheckList items and name into checkListEditor------------
            receiveDataFromRoomDB();
            //binding.checkListEditorEditMode.setText(checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems);
            //binding.checkListNameEdit.setText(checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName);
            checkListEditorEditMode= checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems;
            checkListNameEdit= checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListName;
          //--done! successfully added currentCheckList data into checkListEditor-------------------


            Intent intent = new Intent(requireContext(),checkListEditorActivity.class);
            intent.putExtra("checkListEditorEditModeText",checkListEditorEditMode);
            intent.putExtra("checkListNameEdit",checkListNameEdit);
            activityResultLauncherForCheckListEditor.launch(intent);
        });
      //--done! successfully edited the current checkList------------------------------

//--done! onClickListener actions are applied for buttons-------------------------------------------------------------------------

        return root;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.check_lists_update_fragment, container, false);
    }










    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

      //--startup add saved list to checkboxes----------------------------------------------------------------
        stringArrayToCheckBoxes();
      //--done! successfully added saved checkList into checkBoxes at startup---------------------------------

      //--startup show current checkListName on spinner dropdown menu-----------------------------------------
        isRunningSpinnerOnStartup = true;
        spinnerDropdownMenu();
        //always run stringArrayToCheckBoxes() before this to add checkListSample in first app start
      //--done! successfully showing current checkListName on spinner at startup------------------------------

    }







  //--function to receive data from room database--------------------------------------
    public void receiveDataFromRoomDB() {
        checkListDatabase checkListDB = Room.databaseBuilder(requireContext(),
                checkListDatabase.class, "checkLists.db").allowMainThreadQueries().build();
        checkListFromDatabase = checkListDB.checkListDAO().getAllCheckListItems();
        currentCheckListIdFromDatabase = checkListDB.checkListDAO().getCurrentCheckListId();
    }
  //--done! received the data from room db---------------------------------------------







  //--function to convert stringArray into checkBoxes and get total and selected count--------------------------
    public void stringArrayToCheckBoxes() {

      //--make total and selected count to zero----------------------------------
        binding.selectedCheckBoxesCountShow.setText("00");
        binding.checkListDataInfo.setText("මුළු ගණන: 00 , තෝරාගෙන ඇති ප්‍රමාණය:");
        selectedCheckBoxesCount = 0;
      //--done! now counts are zero----------------------------------------------

        receiveDataFromRoomDB();
        String checkListItems;
        if(checkListFromDatabase.isEmpty()){
          //--add a sample checklist if database is empty---------------------------------------
            checkListItems = "පොඩි trip එකක් යනව\nවගාව නඩත්තු කරනව\nbike repair\nනෑදෑ ගෙදරක යනව\nගෙදර හා වටපිටාව පිරිසිදු කරනව\nගෙදරට අවශ්\u200Dය බඩුටික ගන්නවා";
            checkListDatabase checkListDB = Room.databaseBuilder(requireContext(),
                    checkListDatabase.class, "checkLists.db").allowMainThreadQueries().build();
            checkList updatedCheckList = new checkList(1,"checkListSample", checkListItems);
            checkListDB.checkListDAO().addCheckListToDatabase(updatedCheckList);
            currentCheckList currentCheckListIdToDB = new currentCheckList(0);
            checkListDB.checkListDAO().addCurrentCheckListIdToDatabase(currentCheckListIdToDB);
          //--done! successfully added a sample checkList to database---------------------------
        }
        else{
            checkListItems = checkListFromDatabase.get(currentCheckListIdFromDatabase.get(0).currentCheckListId).checkListItems;
        }

        String[] convertStrAsArrayElements = checkListItems.split("\n");
        List<String> checkListItemsArray;
        checkListItemsArray = Arrays.asList(convertStrAsArrayElements);
        //goto all element in array one by one/per element
        //for(String s: checkListItemsArray){
        //Log.d("arrayElements:",s);
        //}

        ListView listViewData;
        ArrayAdapter<String> adapter;
        listViewData = binding.listViewData;
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_multiple_choice, checkListItemsArray);
        listViewData.setAdapter(adapter);


        listViewData.setOnItemClickListener((parent, view1, position, id) -> {
            //String thisCheckBoxIsClicked = listViewData.getItemAtPosition(position)+"";
            int totalCheckboxesCount = listViewData.getCount();
            String checkboxIsChecked = String.valueOf(listViewData.isItemChecked(position));
            // listViewData.isItemChecked();

            if(checkboxIsChecked.equals("true")){
                selectedCheckBoxesCount += 1;
            }
            else if(checkboxIsChecked.equals("false")){
                selectedCheckBoxesCount -= 1;
            }
            binding.checkListDataInfo.setText("මුළු ගණන: "+ totalCheckboxesCount +"යි,  තෝරාගෙන ඇති ප්‍රමාණය:");
            binding.selectedCheckBoxesCountShow.setText(""+selectedCheckBoxesCount);
            //Toast.makeText(requireContext(),"totalCheckBoxesCount:"+ totalCheckBoxesCount +"\nthisCheckBoxIsClicked:"+ thisCheckBoxIsClicked +"\ncheckoxIsChecked:"+ checkboxIsChecked,Toast.LENGTH_SHORT).show();
        });
    }
  //--done! now showing choosed or saved checkList in checkboxes------------------------------------------------







//--function to run spinner dropdown list menu----------------------------------------------------------------
    public void spinnerDropdownMenu(){
    //--get checkListNames and save them to stringArray----------------------------
        receiveDataFromRoomDB();
        String[] checkListNamesStringArray = new String[checkListFromDatabase.size()];
        for(int index=0;index<checkListFromDatabase.size();index++){
            String checkListName = checkListFromDatabase.get(index).checkListName;
            checkListNamesStringArray[index] = checkListName;
        }
        System.out.println("checkListNames: "+ Arrays.toString(checkListNamesStringArray));
    //--done! received database data & saved checkListNames to an stringArray------

    //--show all checkListNames in spinner-----------------------------------------
        Spinner spinner = binding.checkListsShowSpinner;
        //ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                // R.array.checkListsArray,
                android.R.layout.simple_spinner_dropdown_item,
                checkListNamesStringArray

        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    //--done! showed the checkListNames on the spinner--------------------------------------------------------

    //--prepair actions when item selected or not selected from spinner---------------------------------------
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(isRunningSpinnerOnStartup || isRunningSpinnerOnSaveOrDeleteButton) {
                  //--show current checkList on spinner at startUp or newCheckListAdded or editedCheckAnListName---------------
                    isRunningSpinnerOnStartup = false;
                    isRunningSpinnerOnSaveOrDeleteButton = false;
                    currentCheckListId = currentCheckListIdFromDatabase.get(0).currentCheckListId;
                    System.out.println("spinner: currentCheckListId:"+ currentCheckListId);
                    System.out.println("spinner: showing current checkList on spinner added startUp or newCheckListAdded or editedAnCheckListName");
                    //currentCheckListId cat get from saveCheckListButton.onClickListener section:
                    //set new created checkList Name as the default selection
                  //--done! now showing currentCheckList on spinner at startup or newCheckListAdded or editedAnCheckListName---
                }
                else{
                  //--add currentCheckListId to database when an checkList selected--------------------------------------------
                    System.out.println("selectedCheckList: id:"+ spinner.getSelectedItemId() +" position:"+ spinner.getSelectedItemPosition() +" name:"+ spinner.getSelectedItem());
                    currentCheckListId = spinner.getSelectedItemPosition();
                    checkListDatabase checkListDB = Room.databaseBuilder(requireContext(),
                            checkListDatabase.class, "checkLists.db").allowMainThreadQueries().build();
                    currentCheckList currentCheckListIdToDB = new currentCheckList(currentCheckListId);
                    checkListDB.checkListDAO().addCurrentCheckListIdToDatabase(currentCheckListIdToDB);
                  //--done! added currentCheckListId to db---------------------------------------------------------------------
                }

              //--add selected checkList info to spinner,checkListEditorWindow,checkBoxes-------
                System.out.println("onItemSelectedSection: currentCheckListId:"+ currentCheckListId +" currentCheckListName:"+ checkListFromDatabase.get(currentCheckListId).checkListName +" currentCheckListItems:"+ checkListFromDatabase.get(currentCheckListId).checkListItems);
                spinner.setSelection(currentCheckListId);
                //binding.checkListEditorEditMode.setText(checkListFromDatabase.get(currentCheckListId).checkListItems);
                //binding.checkListNameEdit.setText(checkListFromDatabase.get(currentCheckListId).checkListName);
                checkListEditorEditMode= checkListFromDatabase.get(currentCheckListId).checkListItems;
                checkListNameEdit= checkListFromDatabase.get(currentCheckListId).checkListName;
                receiveDataFromRoomDB();
                stringArrayToCheckBoxes();
              //--done! successfully added selected checkList data------------------------------
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("noting selected from checkLists.");
            }
        });
    //--done! successfully added actions to onSelected and onNothingSelected----------------------------------

    //--change spinner background-----------------------------------------------------------------------------
        spinner.setPopupBackgroundResource(R.drawable.black_bg_and_purple_border);
    //--done! changed spinner background----------------------------------------------------------------------
    }
//--done! successfully running spinner dropdown list menu when function called--------------------------------









//--adds onBackPress support for checkListEditor and newCheckListEditor windows-------------------------------
//    final OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//        @Override
//        public void handleOnBackPressed() {
//////            setEnabled(true);
////        //    if (checkListEditorWindowIsShowing) {
////                //if(isEnabled()){setEnabled(false);System.out.println("setEnabled: false");}
////                binding.checkListEditor.setVisibility(View.INVISIBLE);
////                binding.helpButtonInCheckListUpdate.setVisibility(View.VISIBLE);
////                System.out.println("backbutton is pressed inside the newCheckListEditMode & checkListEditor!.");
////                checkListEditorWindowIsShowing = false;
////                //requireActivity().getOnBackPressedDispatcher().onBackPressed();
////                //if(isEnabled()) {
////                //setEnabled(false);
////                    //requireActivity().getOnBackPressedDispatcher().onBackPressed();
////                    //System.out.println("onBackPressed is disabled!..");
////                //this.remove();
////                //System.out.println("removed the callback");
////               // onBackPressedOnceExecuted = false;
////                //setEnabled(false);
////                //}
//////            }
//////            } else {
//////                //setEnabled(false);
//////                this.remove();
//////                //requireActivity().getOnBackPressedDispatcher().onBackPressed();
//////                System.out.println("onBackPressed is disabled!..");
//////            }
////            System.out.println("isEnabled:"+ isEnabled());
////            if(isEnabled()){
////                //setEnabled(true);
////                setEnabled(false);
////                requireActivity().getOnBackPressedDispatcher().onBackPressed();
////                System.out.println("tried onbackPresss again");
////                setEnabled(true);
////            }
//        }
//    };
//--done! successfully added onBackPress support for checkListEditor and newCheckListEditor windows-----------
    
    
    

}



