package tn.esprit.miafamilia10.fragments.todolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tn.esprit.miafamilia10.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentShopList extends Fragment {
    Button todoBt ;




    public RootFragmentShopList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =inflater.inflate(R.layout.fragment_root_shop_list, container, false);


        todoBt = result.findViewById(R.id.todolistbt);
        todoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentTodo()).commit();


            }
        });
        return result;


    }
}

