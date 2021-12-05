package com.example.studenthub.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.studenthub.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.comp,"Computer Science", "Hello"));
        list.add(new BranchModel(R.drawable.mech,"Mechanical", "Hello"));
        list.add(new BranchModel(R.drawable.civil,"Civil", "Hello"));
//        list.add(new BranchModel(R.drawable.elec,"Electronic", "Hello"));

        adapter = new BranchAdapter(getContext(),list);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.image);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/student-hub-19434.appspot.com/o/rd1.jpg?alt=media&token=e9e1a1e8-86ed-4199-9526-ec9bdeacfdfe")
                .into(imageView);

        return view;
    }
}