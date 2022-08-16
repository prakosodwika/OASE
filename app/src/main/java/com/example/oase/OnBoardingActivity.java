package com.example.oase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oase.Helpers.SaveState;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager mSliderViewPager;
    LinearLayout mDotsLayout;
    Button backBtn, nextBtn, getStartedBtn;

    TextView[] dots;
    OnBoardingViewPagerAdapter onBoardingViewPagerAdapter;
    SaveState saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        backBtn = findViewById(R.id.btnBack);
        nextBtn = findViewById(R.id.btnNext);
        getStartedBtn = findViewById(R.id.btnGetStarted);

        saveState = new SaveState(OnBoardingActivity.this, "OB");
        if (saveState.getState() == 1) {
            startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
            finish();
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) > 0) {
                    mSliderViewPager.setCurrentItem(getitem(-1), true);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) < 3){
                    mSliderViewPager.setCurrentItem(getitem(1), true);
                }
            }
        });

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveState.setState(1);
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                finish();
            }
        });

        mSliderViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        mDotsLayout = (LinearLayout) findViewById(R.id.indikatorLayout);

        onBoardingViewPagerAdapter = new OnBoardingViewPagerAdapter(this);

        mSliderViewPager.setAdapter(onBoardingViewPagerAdapter);

        setUpIndikator(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);

    }

    public void setUpIndikator(int position) {
        dots = new TextView[3];
        mDotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            mDotsLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndikator(position);

//            visible next & getStarted button
            if (position > 1){
                nextBtn.setVisibility(View.INVISIBLE);
                getStartedBtn.setVisibility(View.VISIBLE);
            } else {
                nextBtn.setVisibility(View.VISIBLE);
                getStartedBtn.setVisibility(View.INVISIBLE);
            }

//            visible back button
            if (position > 0) {
                backBtn.setVisibility(View.VISIBLE);
            } else {
                backBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem (int i){
        return mSliderViewPager.getCurrentItem() + i;
    }

}