package tn.esprit.miafamilia10.fragments.list.tocall;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.miafamilia10.HttpServicesClass;
import tn.esprit.miafamilia10.Models.Task;
import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.adapters.ListAdapterClass;
import tn.esprit.miafamilia10.fragments.list.todo.RootFragmentToDoDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentToCall extends Fragment {


    ListView ToCallListView;
    FloatingActionButton addbt;
    ProgressBar progressBar;
    String HttpUrl = "http://10.0.2.2/familiaapp/tocall/AlltocalllistData.php";
    List<String> IdListtocall = new ArrayList<>();

    OnMessageSendListenertocall messageSendListenertocall;

    public  interface  OnMessageSendListenertocall{
        public void onMessageSendtoCall(String message);
        public void onAddSendtoCall();
    }



    public RootFragmentToCall() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View result =  inflater.inflate(R.layout.root_fragment_to_call, container, false);

        ToCallListView = result.findViewById(R.id.tocalllist);
        addbt = result.findViewById(R.id.addtocallbt);
        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // messageSendListenertocall.onAddSendtoCall();


             getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RootFragmentAddToCallList()).commit();

            }
        });
        progressBar = result.findViewById(R.id.progressBar);

        //Adding ListView Item click Listener.
        ToCallListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {

              //  Toast.makeText(getContext(), IdListtocall.get(p).toString(), Toast.LENGTH_SHORT).show();
               // messageSendListenertocall.onMessageSendtoCall(IdListtocall.get(p).toString());
             // getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToCallDetails()).commit();

                RootFragmentToCallDetails displayFragment = new RootFragmentToCallDetails();
                Bundle bundle = new Bundle();
                bundle.putString("msgtocall",IdListtocall.get(p).toString());
                displayFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.fragment_container , displayFragment , null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        new GetHttpResponse(getContext()).execute();
        return result ;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try{
            // messageSendtocallListener = (OnMessagetocallSendListener) activity ;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement on MessageSend ...");
        }
    }



    // JSON parse class started from here.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String JSonResult;

        List<Task> todoList;

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
            // Passing HTTP URL to HttpServicesClass Class.
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            Task task;

                            todoList = new ArrayList<Task>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                task = new Task();

                                jsonObject = jsonArray.getJSONObject(i);

                                // Adding Student Id TO IdList Array.
                                IdListtocall.add(jsonObject.getString("id").toString());

                                //Adding Student Name.
                                task.TaskName = jsonObject.getString("person").toString();

                                todoList.add(task);

                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
            progressBar.setVisibility(View.GONE);

            ToCallListView.setVisibility(View.VISIBLE);

            ListAdapterClass adapter = new ListAdapterClass(todoList, context);

            ToCallListView.setAdapter(adapter);

        }
    }
}


