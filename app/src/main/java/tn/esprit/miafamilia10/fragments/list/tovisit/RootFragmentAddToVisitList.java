package tn.esprit.miafamilia10.fragments.list.tovisit;


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
import tn.esprit.miafamilia10.fragments.list.tocall.RootFragmentToCall;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentAddToVisitList extends Fragment {

//  return


    EditText PLACE, DESCRIPTION;
    Button Registertovisit;
    String PLACEHolder, DESCRIPTIONHolder ;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    //key,  value
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://10.0.2.2/familiaapp/tovisit/RegisterToVisitData.php";

    public RootFragmentAddToVisitList() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =   inflater.inflate(R.layout.root_fragment_add_to_visit_list, container, false);
        PLACE = view.findViewById(R.id.Place);
        DESCRIPTION =  view.findViewById(R.id.addDescription);
        Registertovisit = view.findViewById(R.id.buttonSubmit);

        Registertovisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    ToDoRegistration(PLACEHolder, DESCRIPTIONHolder);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToVisit()).addToBackStack(null).commit();

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(getContext(), "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });




        return view ;
    }

    public void ToDoRegistration(final String S_Place ,final String S_Description){

        class StudentRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(getContext(),"Loading Data",null,true,true);
            }



            @Override
            protected String doInBackground(String... params) {

                hashMap.put("Place",params[0]);

                hashMap.put("Description",params[1]);

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

        studentRegistrationClass.execute(S_Place ,S_Description);
    }










//VERIFICATION SI LES CHAMPS SONT VIDE OU PAS
    public void CheckEditTextIsEmptyOrNot(){

        PLACEHolder = PLACE.getText().toString();
        DESCRIPTIONHolder =DESCRIPTION.getText().toString();

        if(TextUtils.isEmpty(PLACEHolder) || TextUtils.isEmpty(PLACEHolder)  )
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

}



