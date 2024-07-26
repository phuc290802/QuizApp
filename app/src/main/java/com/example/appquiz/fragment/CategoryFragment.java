package com.example.appquiz.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.appquiz.adapter.CategoryAdapter;
import com.example.appquiz.model.CategoryModel;
import com.example.appquiz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }
    private GridView catView;
    public static int categoryChoice;
    public static List<CategoryModel> castList= new ArrayList<>();
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_category,container,false);
        catView=view.findViewById(R.id.cat_grid);
        loadCategories();
        CategoryAdapter adapter= new CategoryAdapter(castList);
        catView.setAdapter(adapter);
        return view;
    }

    public void loadCategories()
    {
        castList.clear();
        castList.add(new CategoryModel(1,"MATH",20));
        castList.add(new CategoryModel(2,"ENGLISH",30));
        castList.add(new CategoryModel(3,"HISTORY",10));
        castList.add(new CategoryModel(4,"SCIENCE",25));
    }
    public void setCategorychoice(int a)
    {
       this.categoryChoice=a;
    }
    public int getCategoryChoice()
    {
        return this.categoryChoice;
    }
    private String name;
    public String getNameC()
    {
        for (CategoryModel c:castList)
        {
            if(c.getDocID()==getCategoryChoice())
            {
                name=c.getName();
            }
        }
        return name;
    }
}