package tn.esprit.miafamilia10.fragments.list.todo;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

import tn.esprit.miafamilia10.HttpServicesClass;

import tn.esprit.miafamilia10.Models.Task;
import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.adapters.ListAdapterClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentTodo extends Fragment {

    ListView ToDoListView;
    FloatingActionButton addbt;
    ProgressBar progressBar;
    String HttpUrl = "http://10.0.2.2/familiaapp/todo/AlltodolistData.php";
    List<String> IdList = new ArrayList<>();

    OnMessageSendListener messageSendListener;


    public  interface  OnMessageSendListener{
        public void onMessageSend(String message);
        public void onAddSend();
    }




    public RootFragmentTodo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View result =  inflater.inflate(R.layout.root_fragment_todo, container, false);

        ToDoListView = result.findViewById(R.id.todolist);
        addbt = result.findViewById(R.id.addbt);
        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageSendListener.onAddSend();
            }
        });

        progressBar = result.findViewById(R.id.progressBar);

        //Adding ListView Item click Listener.
        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         messageSendListener.onMessageSend(IdList.get(position).toString());
            }
        });

      new GetHttpResponse(getContext()).execute();
        return result ;
    }

  public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try{
            messageSendListener = (OnMessageSendListener) activity ;
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
                                IdList.add(jsonObject.getString("id").toString());

                                //Adding Student Name.
                                task.TaskName = jsonObject.getString("title").toString();

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

            ToDoListView.setVisibility(View.VISIBLE);

            ListAdapterClass adapter = new ListAdapterClass(todoList, context);

            ToDoListView.setAdapter(adapter);

        }
    }
}
