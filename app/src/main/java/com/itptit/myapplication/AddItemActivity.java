package com.itptit.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itptit.myapplication.data.DBHelper;
import com.itptit.myapplication.model.MyModel;

public class AddItemActivity extends AppCompatActivity {

    EditText edtAddName, edtAddContent;
    Button btnConfirm;
    DBHelper dbHelper;

    void initView() {
        dbHelper = new DBHelper(this);
        btnConfirm = findViewById(R.id.btn_confirm);
        edtAddName = findViewById(R.id.edt_add_name);
        edtAddContent = findViewById(R.id.edt_add_content);
    }

    boolean isValid() {
        return !edtAddName.getText().toString().isEmpty() &&
                !edtAddContent.getText().toString().isEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        initView();

        btnConfirm.setOnClickListener(v -> {
            if(isValid()) {
                MyModel newItem = new MyModel();
                newItem.setId(0);
                newItem.setJobName(edtAddName.getText().toString());
                newItem.setJobDetail(edtAddContent.getText().toString());
                dbHelper.addData(newItem);
                finish();
            } else {
                Toast.makeText(this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}