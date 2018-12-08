package tn.esprit.miafamilia10.fragments.list.tocall;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import tn.esprit.miafamilia10.HttpParse;
import tn.esprit.miafamilia10.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentToCallDetails extends Fragment {
    HttpParse httpParsetoc = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter Student Data from Id Sent from previous activity.
    String HttpURLFilter = "http://10.0.2.2/familiaapp/tocall/filtertaskData.php";

    // Http URL for delete Already Open Student Record.
    String HttpUrlDelete = "http://10.0.2.2/familiaapp/tocall/DeleteTask.php";

    String finalResulttor ;
    HashMap<String,String> hashMap = new HashMap<>();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    TextView TITLE,DESCRIPTIONR , PHONENUMBER;
    String ContactNameHolder,PhoneNumberHolder , DescriptionCallHolder;
    Button UpdateButton, DeleteButton ;
    String TempItem;
    ProgressDialog progressDialog3;


    public RootFragmentToCallDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =  inflater.inflate(R.layout.root_fragment_to_call_details, container, false);


        TITLE = result.findViewById(R.id.ContactPerson);
        PHONENUMBER = result.findViewById(R.id.textNPhone);
        DESCRIPTIONR = result.findViewById(R.id.textDescription);


        UpdateButton = result.findViewById(R.id.buttonUpdatetocall);
        DeleteButton = result.findViewById(R.id.buttonDeletetocall);
        //  AffichageButton = result.findViewById(R.id.buttonAffichage);
        //Receiving the ListView Clicked item value send by previous activity.
        Bundle bundle = getArguments();
        TempItem = bundle.getString("msgtocall");
        //Calling method to filter Student Record and open selected record.
        HttpWebCall(TempItem);


           UpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RootFragmentUpdateToCall displayFragment = new RootFragmentUpdateToCall();
                    Bundle bundle = new Bundle();
                    bundle.putString("id",TempItem);
                    bundle.putString("person",ContactNameHolder);
                    bundle.putString("phoneNumber",PhoneNumberHolder);
                    bundle.putString("description",DescriptionCallHolder);
                    displayFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, displayFragment ).addToBackStack(null).commit();


                }
            });

        // Add Click listener on Delete button.
           DeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Calling Student delete method to delete current record using Student ID.
                    StudentDelete(TempItem);

                }
            });






        return result ;
    }




    // Method to Delete Student Record
    public void StudentDelete(final String StudentID) {

        class StudentDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog3 = ProgressDialog.show(getContext(), "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog3.dismiss();

                Toast.makeText(getContext(), httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToCall()).commit();

                //  finish();

            }

            @Override
            protected String doInBackground(String... params) {

                // Sending STUDENT id.
                hashMap.put("tocalllistID", params[0]);

                finalResulttor = httpParsetoc.postRequest(hashMap, HttpUrlDelete);

                return finalResulttor;
            }
        }

        StudentDeleteClass studentDeleteClass = new StudentDeleteClass();

        studentDeleteClass.execute(StudentID);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(getContext(),"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(getContext()).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("tocallID",params[0]);

                ParseResult = httpParsetoc.postRequest(ResultHash, HttpURLFilter);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing Student Name, Phone Number, Class into Variables.
                            ContactNameHolder = jsonObject.getString("person").toString() ;
                            PhoneNumberHolder =jsonObject.getString("phoneNumber").toString();
                            DescriptionCallHolder = jsonObject.getString("description").toString() ;


                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            TITLE.setText(ContactNameHolder);
            PHONENUMBER.setText(PhoneNumberHolder);
            DESCRIPTIONR.setText(DescriptionCallHolder);


        }
    }





}













