package com.hankyo.navyBuddyApp.checkListsUpdate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hankyo.navyBuddyApp.R;


public class checkListDeleteConfirmWindowActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checklist_delete_confirm_window);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            //--receive data from previous activity-----------------------------------
            Bundle extras = getIntent().getExtras();
            assert extras != null;
            String deletingCheckListName = extras.getString("deletingCheckListName");
            String deletingCheckListItems = extras.getString("deletingCheckListItems");
            System.out.println("deletingCheckListName:"+ deletingCheckListName +"\ndeletingCheckListItems:\n"+ deletingCheckListItems);
            //--done! successfully received data from previous activity/fragment------

            //--show deleting checkList data before deleting--------------------------
            TextView addDeletingCheckListName = (TextView) findViewById(R.id.deletingCheckListName);
            addDeletingCheckListName.setText(deletingCheckListName);
            TextView addDeletingCheckListItems = (TextView) findViewById(R.id.deletingCheckListItems);
            addDeletingCheckListItems.setText(deletingCheckListItems);
            //--done! now showing checkList name and items before deleting------------

            //--return to previous activity when cancel button pressed----------------
            Button cancelAfterConfirmedButton = (Button) findViewById(R.id.cancelAfterConfirmedButton);
            cancelAfterConfirmedButton.setOnClickListener(view -> {
                setResult(RESULT_OK,new Intent().putExtra("confirmationWindowResult","cancel"));
                finish();
//                checkListDeleteConfirmWindowActivity.this.finish();
            });
            //--done! successfully returned back to previous activity------------------


          //--delete the user confirmed checkList & return to previous window-------------
            Button deleteAfterConfirmedButton = (Button) findViewById(R.id.deleteAfterConfirmedButton);
            deleteAfterConfirmedButton.setOnClickListener(view -> {
                setResult(RESULT_OK,new Intent().putExtra("confirmationWindowResult","delete"));
                finish();
          //--done! successfully deleted the checkList and returned to previous window----
            });

            return insets;
        });
    }

//    @Override
//    public View view(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        System.out.println("hello");
//    }
}