package tn.esprit.miafamilia10.Activities;

import android.app.TaskStackBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.miafamilia10.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){

        startActivity(new Intent(getApplicationContext(),HomeActivity.class));

    }

    public void resetPassword (View v){ }

    public void loginWithFacebook(View v){}

    public  void loginWithGmail(View v){}



}
