package tn.esprit.miafamilia10.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import tn.esprit.miafamilia10.Models.Article;
import tn.esprit.miafamilia10.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentShopList extends Fragment {

    JSONArray jsonArray = null;
    JSONObject jsonObject = null ;
    Article[] articleArray;
    ListView listArticles;
    Button btAdd;
    EditText editTextArticle;
    ArrayList<String> articleData ;
    ArrayAdapter<String> adapter ;
    Gson gson ;
    String newArticle;
    String pos;
    Object a;
    public RootFragmentShopList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =inflater.inflate(R.layout.fragment_root_shop_list, container, false);
        listArticles =  result.findViewById(R.id.listArticles);
        editTextArticle =  result.findViewById(R.id.etArticle);
        btAdd =  result.findViewById(R.id.btAdd);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newArticle = editTextArticle.getText().toString();


                new InsertArticle().execute(newArticle);
            }
        });
listArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

         pos = Integer.toString(i);
        a = listArticles.getItemAtPosition(i);
        Toast.makeText(getActivity() , a.toString(),Toast.LENGTH_SHORT).show();
        new SuppArticle().execute(a.toString());
    }
});

        new GetAllArticles().execute();


        return result;


    }



    //1- string enter 2- integer progress 3- string result
    private class InsertArticle extends AsyncTask<String , Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Toast.makeText(getActivity() , "Inserting data ...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            //1--make connection with database (web service)

            try {

                URL url = new URL("http://10.0.2.2/familiaapp/newArticles.php?NomA="+strings[0].replace(" " , "%20"));//strings elli f doinbackground
                URLConnection urlConnection = url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne;
                while((ligne = bufferedReader.readLine())!=null){
                    jsonObject = new JSONObject(ligne);
                }
                Log.d("result : ",jsonArray.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                boolean isInserted = jsonObject.getBoolean("result");
                articleData.add(newArticle);
                adapter.notifyDataSetChanged();
                if(isInserted){
                    Toast.makeText(getActivity(), "new article added with success", Toast.LENGTH_SHORT).show();
                    editTextArticle.setText("");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




    private class GetAllArticles extends AsyncTask<String , Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            //1--make connection with database (web service)

            try {

                URL url = new URL("http://10.0.2.2/familiaapp/getAllArticles.php");
                URLConnection urlConnection = url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne;
                while((ligne = bufferedReader.readLine())!=null){
                    jsonArray = new JSONArray(ligne);
                }
                Log.d("result : ",jsonArray.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //2--convert data from json array to java objects
            gson = new Gson();
            articleArray = gson.fromJson(jsonArray.toString(),Article[].class);

            articleData = new ArrayList<>();
            for(Article task :articleArray){
                articleData.add(task.getNomA());
            }
            adapter = new ArrayAdapter<>(getActivity() , android.R.layout.simple_list_item_1,articleData);
            listArticles.setAdapter(adapter);

        }}


    private class SuppArticle extends AsyncTask<String , Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity() , "Deleting data ...",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected String doInBackground(String... strings) {
            //1--make connection with database (web service)





            try {


                URL url = new URL("http://10.0.2.2/familiaapp/suppressionArticle.php?id="+strings[0]);//strings elli f doinbackground
                URLConnection urlConnection = url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String ligne;
                while((ligne = bufferedReader.readLine())!=null){
                    jsonObject = new JSONObject(ligne);
                }
                Log.d("result : ",jsonArray.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                boolean isDeleted = jsonObject.getBoolean("result");



               articleData.remove(a.toString());
                adapter.notifyDataSetChanged();
                if(isDeleted){
                    Toast.makeText(getActivity(), "the article deleted with success", Toast.LENGTH_SHORT).show();
                  //  editTextArticle.setText("");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
