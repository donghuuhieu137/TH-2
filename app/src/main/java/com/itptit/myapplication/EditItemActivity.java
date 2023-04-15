package com.itptit.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.itptit.myapplication.data.DBHelper;
import com.itptit.myapplication.model.MyModel;

public class EditItemActivity extends AppCompatActivity {

    MyModel itemData;
    DBHelper dbHelper;
    EditText edtUpdateName, edtUpdateContent;
    Button buttonUpdate;

    void initView() {
        dbHelper = new DBHelper(this);
        itemData = (MyModel) getIntent().getSerializableExtra("item_data");
        edtUpdateName = findViewById(R.id.edt_update_name);
        edtUpdateContent = findViewById(R.id.edt_update_content);
        buttonUpdate = findViewById(R.id.btn_update);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        initView();

        edtUpdateName.setText(itemData.getJobName());

        edtUpdateContent.setText(itemData.getJobDetail());

        buttonUpdate.setOnClickListener(v -> {
            dbHelper.updateData(new MyModel(
                itemData.getId(),
                edtUpdateName.getText().toString(),
                edtUpdateContent.getText().toString(),
                itemData.getFinishDate(),
                itemData.getJobStatus(),
                itemData.getCollaboration()
            ));
           finish();
        });
    }
}