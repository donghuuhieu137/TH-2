package com.itptit.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itptit.myapplication.AddItemActivity;
import com.itptit.myapplication.R;
import com.itptit.myapplication.adapter.ListViewAdapter;
import com.itptit.myapplication.data.DBHelper;
import com.itptit.myapplication.model.MyModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListviewFragment extends Fragment {

    RecyclerView recyclerView;
    ListViewAdapter adapter;
    List<MyModel> listData;
    FloatingActionButton fab;
    DBHelper dbHelper;

    public ListviewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        dbHelper = new DBHelper(container.getContext());
        fab = view.findViewById(R.id.add_fab);
        fab.setOnClickListener(v -> {
            Intent myIntent = new Intent(container.getContext(), AddItemActivity.class);
            startActivity(myIntent);
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);

        listData = dbHelper.getAllData();

        setAdapter(container.getContext());

        return view;
    }

    void setAdapter(Context context) {
        adapter = new ListViewAdapter(listData, context, dbHelper);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        System.out.println("On resume");
        listData = dbHelper.getAllData();
        setAdapter(getContext());
        super.onResume();
    }
}