package com.example.studenthub.ui.gallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.studenthub.R;

public class GalleryFragment extends Fragment {
    private GridView grid;
    private GridAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView= inflater.inflate(R.layout.fragment_gallery, container, false);
        grid=(GridView)myFragmentView.findViewById(R.id.gridView);
        adapter=new GridAdapter(getContext());
        grid.setAdapter((adapter));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),FullScreenActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
        return myFragmentView;
    }
}