package tn.esprit.miafamilia10.fragments.list.toremember;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import tn.esprit.miafamilia10.HttpParse;
import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.fragments.list.todo.RootFragmentTodo;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentAddtoremeberList extends Fragment {

     EditText ToDoName,  ToDoDescription;
    Button RegistertoDo;
    String ToDoNameHolder,  ToDoDescriptionHolder;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://10.0.2.2/familiaapp/toremember/RegisterToRememberData.php";


    public RootFragmentAddtoremeberList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.root_fragment_addtoremeberlist, container, false);
        ToDoName = view.findViewById(R.id.editNametoR);
        ToDoDescription =  view.findViewById(R.id.editDescriptiontoR);
        RegistertoDo = view.findViewById(R.id.buttonSubmit);

        RegistertoDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    ToDoRegistration(ToDoNameHolder, ToDoDescriptionHolder);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToRemember()).commit();

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(getContext(), "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });




        return view ;
    }

    public void ToDoRegistration(final String S_Name, final String S_Class){

        class StudentRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(getContext(),"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(getContext(),httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("ToRememberTaskTitle",params[0]);

                hashMap.put("ToRememberTaskDescription",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRegistrationClass studentRegistrationClass = new StudentRegistrationClass();

        studentRegistrationClass.execute(S_Name,S_Class);
    }











    public void CheckEditTextIsEmptyOrNot(){

        ToDoNameHolder = ToDoName.getText().toString();
        ToDoDescriptionHolder = ToDoDescription.getText().toString();

        if(TextUtils.isEmpty(ToDoNameHolder)  || TextUtils.isEmpty(ToDoDescriptionHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

}

