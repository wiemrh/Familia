package tn.esprit.miafamilia10.fragments.list.tocall;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentAddToCallList extends Fragment {


    EditText ToDoName, TocallPhoneNumber ,  ToDoDescription;
    Button RegistertoCall;
    String ToDoNameHolder, ToDoDescriptionHolder , ToCallPhoneNumberHolder;

    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    //key,  value
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://10.0.2.2/familiaapp/tocall/RegisterToCallData.php";

    public RootFragmentAddToCallList() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.root_fragment_add_to_call_list, container, false);
        ToDoName = view.findViewById(R.id.addContactName);
        TocallPhoneNumber = view.findViewById(R.id.phoneNumber);
        ToDoDescription =  view.findViewById(R.id.Description);
        RegistertoCall = view.findViewById(R.id.buttonSubmittocall);

        RegistertoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    ToDoRegistration(ToDoNameHolder,ToCallPhoneNumberHolder, ToDoDescriptionHolder);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToCall()).addToBackStack(null).commit();

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(getContext(), "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });




        return view ;
    }

    public void ToDoRegistration(final String S_ContactName, final String S_PhoneNumber ,final String S_Description){

        class StudentRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(getContext(),"Loading Data",null,true,true);
            }



            @Override
            protected String doInBackground(String... params) {

                hashMap.put("Person",params[0]);

                hashMap.put("PhoneNumber",params[1]);

                hashMap.put("Description",params[2]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }


            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                Toast.makeText(getContext(),httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }
        }

        StudentRegistrationClass studentRegistrationClass = new StudentRegistrationClass();

        studentRegistrationClass.execute(S_ContactName ,S_PhoneNumber,S_Description);
    }











    public void CheckEditTextIsEmptyOrNot(){

        ToDoNameHolder = ToDoName.getText().toString();
        ToCallPhoneNumberHolder = TocallPhoneNumber.getText().toString();
        ToDoDescriptionHolder = ToDoDescription.getText().toString();

        if(TextUtils.isEmpty(ToDoNameHolder) || TextUtils.isEmpty(ToCallPhoneNumberHolder)  || TextUtils.isEmpty(ToDoDescriptionHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

}


