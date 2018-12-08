package tn.esprit.miafamilia10.fragments.list.tocall;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentUpdateToCall extends Fragment {

        String HttpURL = "http://10.0.2.2/familiaapp/tocall/UpdateTasktocall.php";
        ProgressDialog progressDialog;
        String finalResult ;
        HashMap<String,String> hashMap = new HashMap<>();
        HttpParse httpParse = new HttpParse();
        EditText TaskName, TaskPhoneNumber , TaskDecription;
        Button UpdateStudent;
        String IdHolder, TaskNameHolder,TaskPhoneNumberHolder , TaskDescriptionHolder;


        public RootFragmentUpdateToCall() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Inflate the layout for this fragment
            View view =  inflater.inflate(R.layout.root_fragment_update_to_call, container, false);
            TaskName = view.findViewById(R.id.updateContactName);
            TaskPhoneNumber = view.findViewById(R.id.updatephoneNumber);
            TaskDecription = view.findViewById(R.id.updateDescription);
            UpdateStudent = view.findViewById(R.id.buttonupdatetocall);

            // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
            Bundle bundle = getArguments();
            IdHolder = bundle.getString("id");
            TaskNameHolder = bundle.getString("person");
            TaskPhoneNumberHolder = bundle.getString("phoneNumber");
            TaskDescriptionHolder = bundle.getString("description");

            // Setting Received Student Name, Phone Number, Class into EditText.
            TaskName.setText(TaskNameHolder);
            TaskPhoneNumber.setText(TaskPhoneNumberHolder);
            TaskDecription.setText(TaskDescriptionHolder);

            // Adding click listener to update button .
              UpdateStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Getting data from EditText after button click.
                    GetDataFromEditText();

                    // Sending Student Name, Phone Number, Class to method to update on server.
                    StudentRecordUpdate(IdHolder,TaskNameHolder,TaskPhoneNumberHolder,TaskDescriptionHolder);

                }
            });
            return view;
        }



        public void GetDataFromEditText(){

            TaskNameHolder = TaskName.getText().toString();
            TaskPhoneNumberHolder =TaskPhoneNumber.getText().toString();
            TaskDescriptionHolder =  TaskDecription.getText().toString();


        }

        // Method to Update Student Record.
        public void StudentRecordUpdate(final String ID, final String T_Name, final String T_PhoneNumber, final String T_Description){

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
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToCall()).commit();


                }

                @Override
                protected String doInBackground(String... params) {

                    hashMap.put("TaskID",params[0]);

                    hashMap.put("TaskPerson",params[1]);

                    hashMap.put("TaskPhoneNumber",params[2]);

                    hashMap.put("TaskDescription",params[3]);


                    finalResult = httpParse.postRequest(hashMap, HttpURL);

                    return finalResult;
                }
            }

            StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

            studentRecordUpdateClass.execute(ID,T_Name , T_PhoneNumber,T_Description);
        }





    }


