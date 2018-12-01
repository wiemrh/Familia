package tn.esprit.miafamilia10.Activities;


import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;



import tn.esprit.miafamilia10.R;

import tn.esprit.miafamilia10.fragments.RootFragmentHome;
import tn.esprit.miafamilia10.fragments.RootFragmentProfil;
import tn.esprit.miafamilia10.fragments.RootFragmentSearch;
import tn.esprit.miafamilia10.fragments.todolist.RootFragmentShopList;


public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RootFragmentHome()).commit();
        }

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected( MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.ic_home:
                            selectedFragment = new RootFragmentHome();
                            break;
                        case R.id.ic_search:
                            selectedFragment = new RootFragmentSearch();
                            break;
                        case R.id.ic_todolist:
                            selectedFragment = new RootFragmentShopList();
                            break;
                        case R.id.ic_profil:
                            selectedFragment = new RootFragmentProfil();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


}
