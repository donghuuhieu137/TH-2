package com.itptit.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itptit.myapplication.R;
import com.itptit.myapplication.adapter.ListViewAdapter;
import com.itptit.myapplication.data.DBHelper;
import com.itptit.myapplication.model.MyModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    ListViewAdapter adapter;
    List<MyModel> listData;
    DBHelper dbHelper;
    ImageButton icSearch;
    EditText editTextSearch;

    void initView(View view) {
        dbHelper = new DBHelper(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView = view.findViewById(R.id.list_view_search);
        icSearch = view.findViewById(R.id.ic_search);
        editTextSearch = view.findViewById(R.id.edt_search);
    }

    public SearchFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initView(view);
        listData = dbHelper.getAllData();
        setAdapter(container.getContext());

        icSearch.setOnClickListener(v -> {
            searchByKeyword();
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {

           public void afterTextChanged(Editable s) {}

           public void beforeTextChanged(CharSequence s, int start,
             int count, int after) {
           }

           public void onTextChanged(CharSequence s, int start, int before, int count) {
             searchByKeyword();
           }
        });

        return view;
    }

    void searchByKeyword() {
        List<MyModel> result = new ArrayList<>();
        String keyword = editTextSearch.getText().toString();
        for(MyModel element : dbHelper.getAllData()) {
            if(element.getJobName().trim().toLowerCase().contains(keyword.trim().toLowerCase()) ||
                element.getJobDetail().trim().toLowerCase().contains(keyword.trim().toLowerCase())
            ){
                result.add(element);
            }
       }
        listData = result;
       setAdapter(getContext());
    }

    void setAdapter(Context context) {
        adapter = new ListViewAdapter(listData, context, dbHelper);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}