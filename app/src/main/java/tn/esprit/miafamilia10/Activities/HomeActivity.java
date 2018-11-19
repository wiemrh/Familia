package tn.esprit.miafamilia10.Activities;


import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.adapters.HomePageTabsAdapter;


public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    HomePageTabsAdapter homepageAdapter;
    TabItem tabHome;
    TabItem tabChat;
    TabItem tabShopList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);


        tabLayout = findViewById(R.id.tablayout);
        tabHome = findViewById(R.id.tabHome);
        tabChat = findViewById(R.id.tabChat);
        tabShopList = findViewById(R.id.tabShopList);
        viewPager = findViewById(R.id.viewPager);

        homepageAdapter = new HomePageTabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
       viewPager.setAdapter(homepageAdapter);
       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




    }
}
