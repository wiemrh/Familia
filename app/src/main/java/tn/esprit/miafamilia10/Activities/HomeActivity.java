package tn.esprit.miafamilia10.Activities;


import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.adapters.HomePageTabsAdapter;



public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}
