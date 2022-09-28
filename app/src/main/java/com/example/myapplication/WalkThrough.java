package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WalkThrough extends AppCompatActivity {

    private ViewPager mslideViewPager;
    private LinearLayout mDotLayout;

    private Button nNextBtn;
    private Button nBackBtn;

    private int mCurrentPage;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();

        mslideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dots);

        nBackBtn = (Button) findViewById(R.id.backBtn);
        nNextBtn = (Button) findViewById(R.id.nextBtn);

        sliderAdapter = new SliderAdapter(this);
        mslideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mslideViewPager.addOnPageChangeListener(viewListener);

        //OnClickListeners

        nNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mslideViewPager.setCurrentItem(mCurrentPage + 1);
                startActivity(new Intent(WalkThrough.this,LoginActivity.class));
                finish();
            }
        });

        nBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mslideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.white));

            mDotLayout.addView(mDots[i]);

        }

        if(mDots.length > 0 ){
            mDots[position].setTextColor(getResources().getColor(R.color.purple_light));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
            mCurrentPage = i;

            if (i == 0){
                nNextBtn.setEnabled(true);
                nBackBtn.setEnabled(false);
                nBackBtn.setVisibility(View.INVISIBLE);

                nNextBtn.setText("Next");
                nBackBtn.setText("");

            } else if ( i == mDots.length - 1 ) {
                nNextBtn.setEnabled(true);
                nBackBtn.setEnabled(true);
                nBackBtn.setVisibility(View.VISIBLE);

                nNextBtn.setText("Finish");
                nBackBtn.setText("Back");

            } else {
                nNextBtn.setEnabled(true);
                nBackBtn.setEnabled(true);
                nBackBtn.setVisibility(View.VISIBLE);

                nNextBtn.setText("Next");
                nBackBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}