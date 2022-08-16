package com.example.oase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class OnBoardingViewPagerAdapter extends PagerAdapter {

    Context context;

//    membuat aray judul dan deskripsi
    int judul[] = {
            R.string.judulSatu,
            R.string.judulDua,
            R.string.judulTiga
    };
    int deskripsi[] = {
            R.string.deskSatu,
            R.string.deskDua,
            R.string.deskTiga
    };

    public OnBoardingViewPagerAdapter (Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return judul.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_on_boarding_layout, container, false);

        TextView sliderJudul = (TextView) view.findViewById(R.id.txtJudulOB);
        TextView sliderDeskripsi = (TextView) view.findViewById(R.id.txtDeskOB);

        sliderJudul.setText(judul[position]);
        sliderDeskripsi.setText(deskripsi[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
