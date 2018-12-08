package tn.esprit.miafamilia10.fragments.list.tovisit;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import tn.esprit.miafamilia10.HttpParse;
import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.fragments.list.toremember.RootFragmentToRemember;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentUpdateToVisitList extends Fragment {


     String HttpURL = "http://10.0.2.2/familiaapp/tovisit/UpdateTasktovisit.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText PLACE,  DESCRIPTION;
    Button UpdateTASK;
    String IdHolder, PlaceHolder, DescriptionHolder;


    public RootFragmentUpdateToVisitList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.root_fragment_update_to_visit_list, container, false);
        PLACE = view.findViewById(R.id.editPlace);
        DESCRIPTION = view.findViewById(R.id.editDescription);
        UpdateTASK = view.findViewById(R.id.UpdateButton);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        Bundle bundle = getArguments();
        IdHolder = bundle.getString("id");
        PlaceHolder = bundle.getString("place");
        DescriptionHolder = bundle.getString("description");

        // Setting Received Student Name, Phone Number, Class into EditText.
        PLACE.setText(PlaceHolder);
        DESCRIPTION.setText(DescriptionHolder);

        // Adding click listener to update button .
        UpdateTASK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending Student Name, Phone Number, Class to method to update on server.
                StudentRecordUpdate(IdHolder,PlaceHolder,DescriptionHolder);

            }
        });
        return view;
    }



    public void GetDataFromEditText(){

        PlaceHolder = PLACE.getText().toString();
        DescriptionHolder =  DESCRIPTION.getText().toString();


    }

    // Method to Update Student Record.
    public void StudentRecordUpdate(final String ID, final String T_Place,  final String T_Description){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

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
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToVisit()).commit();


            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("TaskID",params[0]);

                hashMap.put("TaskPlace",params[1]);

                hashMap.put("TaskDescription",params[2]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,T_Place,T_Description);
    }





}
