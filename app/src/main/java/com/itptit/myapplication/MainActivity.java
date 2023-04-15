package com.itptit.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.itptit.myapplication.fragment.InforFragment;
import com.itptit.myapplication.fragment.ListviewFragment;
import com.itptit.myapplication.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.list1);

    }
    ListviewFragment listviewFragment = new ListviewFragment();
    InforFragment inforFragment = new InforFragment();
    SearchFragment searchFragment = new SearchFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, listviewFragment).commit();
                return true;

            case R.id.info1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, inforFragment).commit();
                return true;

            case R.id.search1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                return true;
        }
        return false;
    }
}