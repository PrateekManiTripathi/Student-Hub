package com.example.studenthub.ui.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.studenthub.R;

class GridAdapter extends BaseAdapter {
    private Context mContext;
    public static int[] imageArray=new int[]{
            R.drawable.rdg1, R.drawable.rdg2, R.drawable.rdg3, R.drawable.rdg4, R.drawable.rdg5, R.drawable.rdg6,
            R.drawable.rdg7, R.drawable.rdg8, R.drawable.rdg9, R.drawable.rdg10, R.drawable.rdg11, R.drawable.rdg12,
    };
    public GridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView =new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,340));
        return imageView;
    }
}
