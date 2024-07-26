package com.example.appquiz.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;


import com.example.appquiz.R;
import com.example.appquiz.fragment.AccountFragment;
import com.example.appquiz.fragment.CategoryFragment;
import com.example.appquiz.fragment.LeaderBoardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout main_frame;
    private EditText editText_name;
    private Toolbar toolbar;
    private LeaderBoardFragment lbF=new LeaderBoardFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case  R.id.nav_home:
                            setFragement(new CategoryFragment());
                            return true;
                        case R.id.nav_leaderboard:
                            setFragement(new LeaderBoardFragment());
                            return true;
                        case R.id.nav_account:
                            setFragement(new AccountFragment());
                            return true;
                    }
                    return false;
                }
            };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        bottomNavigationView=findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        main_frame=findViewById(R.id.main_frame);
        setFragement(new CategoryFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sx_diem:

                lbF.sortPoint();
                return true;
            case R.id.sx_time:
                // Handle menu item 2 click
                lbF.sortTimeAscending();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setFragement(Fragment fragement)
    {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(),fragement);
        transaction.commit();
    }

    private void init()
    {
        toolbar=findViewById(R.id.app_bar);
    }
    public String getText()
    {
        return editText_name.getText().toString();
    }

    public void LeadrBoardOpen()
    {
        setFragement(new LeaderBoardFragment());
    }
}