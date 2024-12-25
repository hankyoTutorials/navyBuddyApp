package com.hankyo.navyBuddyApp.checkListsUpdate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hankyo.navyBuddyApp.R;

public class checkListEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checklist_editor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

          //--receive data from previous activity--------------------------------------
            Bundle extras = getIntent().getExtras();
            assert extras != null;
            System.out.println("checkListEditorWindow, checkListEditorEditModeText:"+ extras.getString("checkListEditorEditModeText") +" checkListNameEdit:"+ extras.getString("checkListNameEdit"));
          //--done! successfully received the data-------------------------------------

          //--add data to checkListEditor----------------------------------------------
            EditText checkListEditorEditMode = findViewById(R.id.checkListEditorEditMode);
            checkListEditorEditMode.setText(extras.getString("checkListEditorEditModeText"));
            EditText checkListNameEdit = findViewById(R.id.checkListNameEdit);
            checkListNameEdit.setText(extras.getString("checkListNameEdit"));
          //--done! successfully added data into checkListEditor-----------------------

          //--send back checkListEditor data to previous activity & close window-------
            Button checkListSaveButton = findViewById(R.id.checkListSaveButton);
            checkListSaveButton.setOnClickListener(view -> {
                if(checkListNameEdit.getText().toString().isEmpty()){
                    System.out.println("checkListEditorWindow: checkListName is empty!.");
                    Toast.makeText(this,"මෙම list එක සදහා ඔබ නමක් යොදා නොමැත",Toast.LENGTH_SHORT).show();
                }
                else {
                    //System.out.println("checkListEditorWindowSaveButton, checkListEditorEditMode: "+ checkListEditorEditMode.getText() +" checkListNameEdit:"+ checkListNameEdit.getText());
                    setResult(RESULT_OK, new Intent().putExtra("checkListEditorEditModeText", checkListEditorEditMode.getText().toString()).putExtra("checkListNameEdit", checkListNameEdit.getText().toString()));
                    finish();
                }
            });
          //--done! successfully sent data to previous activity & closed the window----

            return insets;
        });
    }
}