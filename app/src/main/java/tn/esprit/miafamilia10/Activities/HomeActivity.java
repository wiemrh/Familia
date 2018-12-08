package tn.esprit.miafamilia10.Activities;


import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;



import tn.esprit.miafamilia10.R;

import tn.esprit.miafamilia10.fragments.RootFragmentHome;
import tn.esprit.miafamilia10.fragments.RootFragmentProfil;
import tn.esprit.miafamilia10.fragments.RootFragmentSearch;
import tn.esprit.miafamilia10.fragments.list.tocall.RootFragmentAddToCallList;
import tn.esprit.miafamilia10.fragments.list.tocall.RootFragmentToCall;
import tn.esprit.miafamilia10.fragments.list.todo.RootFragmentAddtodoList;
import tn.esprit.miafamilia10.fragments.list.todo.RootFragmentToDoDetails;
import tn.esprit.miafamilia10.fragments.list.RootFragmentToDoListCategory;
import tn.esprit.miafamilia10.fragments.list.todo.RootFragmentTodo;
import tn.esprit.miafamilia10.fragments.list.toremember.RootFragmentAddtoremeberList;
import tn.esprit.miafamilia10.fragments.list.toremember.RootFragmentToRemember;
import tn.esprit.miafamilia10.fragments.list.toremember.RootFragmentToRememberListDetails;
import tn.esprit.miafamilia10.fragments.list.tovisit.RootFragmentAddToVisitList;
import tn.esprit.miafamilia10.fragments.list.tovisit.RootFragmentToVisit;
import tn.esprit.miafamilia10.fragments.list.tovisit.RootFragmentToVisitDetails;


public class HomeActivity extends AppCompatActivity implements

        RootFragmentTodo.OnMessageSendListener ,
        RootFragmentToRemember.OnMessageSendListenertorem,
        RootFragmentToCall.OnMessageSendListenertocall,
        RootFragmentToVisit.OnMsgSendListenerToVisit
{



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
                            selectedFragment = new RootFragmentToDoListCategory();
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

//to do list method
    @Override
    public void onMessageSend(String message) {
        RootFragmentToDoDetails displayFragment = new RootFragmentToDoDetails();
        Bundle bundle = new Bundle();
        bundle.putString("msg",message);
        displayFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , displayFragment , null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAddSend() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new RootFragmentAddtodoList(), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //to remember list method
    @Override
    public void onMessageSendtoRem(String message) {
        RootFragmentToRememberListDetails displayFragment = new RootFragmentToRememberListDetails();
        Bundle bundle = new Bundle();
        bundle.putString("msgto",message);
        displayFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , displayFragment , null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



    @Override
    public void onAddSendtoRem() {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new RootFragmentAddtoremeberList(), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

// to call list method
    @Override
    public void onMessageSendtoCall(String message) {

    }

    @Override
    public void onAddSendtoCall() {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new RootFragmentAddToCallList(), null);
      //  fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void onMessageSendtoVisit(String message) {
        RootFragmentToVisitDetails displayFragment = new RootFragmentToVisitDetails();
        Bundle bundle = new Bundle();
        bundle.putString("msgidtovisit",message);
        displayFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , displayFragment , null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAddSendtoVisit() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new RootFragmentAddToVisitList(), null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
