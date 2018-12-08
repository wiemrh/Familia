package tn.esprit.miafamilia10.fragments.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tn.esprit.miafamilia10.R;
import tn.esprit.miafamilia10.fragments.list.tocall.RootFragmentToCall;
import tn.esprit.miafamilia10.fragments.list.todo.RootFragmentTodo;
import tn.esprit.miafamilia10.fragments.list.toremember.RootFragmentToRemember;
import tn.esprit.miafamilia10.fragments.list.tovisit.RootFragmentToVisit;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragmentToDoListCategory extends Fragment {
    Button todoBt , tovisitBt , torememberBt , tocallBt ;





    public RootFragmentToDoListCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =inflater.inflate(R.layout.root_fragment_todo_list_category, container, false);

     // todo fragment
        todoBt = result.findViewById(R.id.todolistbt);
        todoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentTodo()).commit();


            }
        });

     // tovisit fragment
        tovisitBt =result.findViewById(R.id.tovisitlistbt);
        tovisitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToVisit()).commit();


            }
        });
        // toremember fragment
        torememberBt =result.findViewById(R.id.torememberlistbt);
        torememberBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToRemember()).commit();


            }
        });

      // tocall fragment
        tocallBt =result.findViewById(R.id.tocalllistbt);
        tocallBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RootFragmentToCall()).commit();


            }
        });





        return result;


    }
}

