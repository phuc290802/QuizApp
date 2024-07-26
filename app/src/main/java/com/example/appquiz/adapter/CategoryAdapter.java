package com.example.appquiz.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appquiz.R;
import com.example.appquiz.activity.TestActivity;
import com.example.appquiz.fragment.CategoryFragment;
import com.example.appquiz.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<CategoryModel> cat_list;
    private CategoryFragment cateF=new CategoryFragment();

    public CategoryAdapter(List<CategoryModel> cat_list) {
        this.cat_list = cat_list;
    }


    @Override
    public int getCount() {
        return cat_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View myview;
        if(view==null)
        {
            myview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cat_item_layout,viewGroup,false);

        }
        else {
            myview=view;
        }
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cateF.setCategorychoice(i+1);
                Intent intent= new Intent(view.getContext(), TestActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        TextView catName= myview.findViewById(R.id.catName);
        TextView noOfTests=myview.findViewById(R.id.no_of_tests);
        catName.setText(cat_list.get(i).getName());
        noOfTests.setText(String.valueOf(cat_list.get(i).getNoOfTests()));
        return myview;

    }

}
