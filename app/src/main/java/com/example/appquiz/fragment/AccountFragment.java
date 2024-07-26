package com.example.appquiz.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appquiz.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    private Button logoutB;
    private TextView profile_img_text,name,score,rank;
    private LinearLayout leaderB,profileB,bookmarkB;
    private BottomNavigationView bottomNavigationView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account,container,false);
//        initViews(view);

//        bookmarkB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        profileB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        leaderB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomNavigationView.setSelectedItemId(R.id.nav_leaderboard);
//            }
//        });

        return view;
    }
    private void initViews(View view)
    {
        logoutB=view.findViewById(R.id.logoutB);
        profile_img_text=view.findViewById(R.id.profile_img_text);
        name=view.findViewById(R.id.name);
        score=view.findViewById(R.id.total_score);
        rank=view.findViewById(R.id.rank);
        leaderB=view.findViewById(R.id.leaderB);
        bookmarkB=view.findViewById(R.id.bookmarkB);
        profileB=view.findViewById(R.id.profileB);
        bottomNavigationView=getActivity().findViewById(R.id.bottom_nav_bar);

    }
}