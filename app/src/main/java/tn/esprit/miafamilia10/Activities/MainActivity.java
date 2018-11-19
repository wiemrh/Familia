package tn.esprit.miafamilia10.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tn.esprit.miafamilia10.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void Click(View v){

        switch (v.getId()){
            case(R.id.loginbt):
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case (R.id.signupbt):
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
        }



    }
}
