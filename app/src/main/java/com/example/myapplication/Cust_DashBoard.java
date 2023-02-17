package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Cust_DashBoard extends AppCompatActivity {

    private int selectedInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_dash_board);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout qrLayout = findViewById(R.id.qrLayout);
        final LinearLayout cartLayout = findViewById(R.id.cartLayout);
        final LinearLayout userLayout = findViewById(R.id.userLayout);

        final ImageView homeImg = findViewById(R.id.homeImg);
        final ImageView qrImg = findViewById(R.id.qrImg);
        final ImageView cartImg = findViewById(R.id.cartImg);
        final ImageView userImg = findViewById(R.id.userImg);

        final TextView homeTxt = findViewById(R.id.homeTxt);
        final TextView qrTxt = findViewById(R.id.qrTxt);
        final TextView cartTxt = findViewById(R.id.cartTxt);
        final TextView userTxt = findViewById(R.id.userTxt);

        // set home fragment by default
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_container,Cust_Fragment.class,null).commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedInt != 1) {

                    getSupportFragmentManager().
                            beginTransaction().
                            setReorderingAllowed(true).
                            replace(R.id.fragment_container,Cust_Fragment.class,null).
                            commit();


                    // unselect other tabs expect home tab
                    qrTxt.setVisibility(View.GONE);
                    cartTxt.setVisibility(View.GONE);
                    userTxt.setVisibility(View.GONE);

                    qrImg.setImageResource(R.drawable.search_u);
                    cartImg.setImageResource(R.drawable.cart_u);
                    userImg.setImageResource(R.drawable.profile_u);

                    qrLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    cartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    userLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select home tab
                    homeTxt.setVisibility(view.VISIBLE);
                    homeImg.setImageResource(R.drawable.home);
                    homeLayout.setBackgroundResource(R.drawable.round_back);

                    // crate animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    // set 1st tab as selected tab
                    selectedInt = 1;

                }

            }
        });

        qrLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedInt != 2) {

                    getSupportFragmentManager().
                            beginTransaction().
                            setReorderingAllowed(true).
                            replace(R.id.fragment_container,Cust_Qr_Fragment.class,null).
                            commit();

                    // unselect other tabs expect qr tab
                    homeTxt.setVisibility(View.GONE);
                    cartTxt.setVisibility(View.GONE);
                    userTxt.setVisibility(View.GONE);

                    homeImg.setImageResource(R.drawable.home_u);
                    cartImg.setImageResource(R.drawable.cart_u);
                    userImg.setImageResource(R.drawable.profile_u);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    cartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    userLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select home tab
                    qrTxt.setVisibility(view.VISIBLE);
                    qrImg.setImageResource(R.drawable.search);
                    qrLayout.setBackgroundResource(R.drawable.round_back);

                    // crate animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    qrLayout.startAnimation(scaleAnimation);

                    // set 1st tab as selected tab
                    selectedInt = 2;

                }

            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedInt != 3) {

                    getSupportFragmentManager().
                            beginTransaction().
                            setReorderingAllowed(true).
                            replace(R.id.fragment_container,Cust_Cart_Fragment.class,null).
                            commit();

                    // unselect other tabs expect qr tab
                    homeTxt.setVisibility(View.GONE);
                    qrTxt.setVisibility(View.GONE);
                    userTxt.setVisibility(View.GONE);

                    homeImg.setImageResource(R.drawable.home_u);
                    qrImg.setImageResource(R.drawable.search_u);
                    userImg.setImageResource(R.drawable.profile_u);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    qrLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    userLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select home tab
                    cartTxt.setVisibility(view.VISIBLE);
                    cartImg.setImageResource(R.drawable.cart);
                    cartLayout.setBackgroundResource(R.drawable.round_back);

                    // crate animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    cartLayout.startAnimation(scaleAnimation);

                    // set 1st tab as selected tab
                    selectedInt = 3;

                }

            }
        });

        userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedInt != 4) {

                    getSupportFragmentManager().
                            beginTransaction().
                            setReorderingAllowed(true).
                            replace(R.id.fragment_container,Cust_User_Fragment.class,null).
                            commit();

                    // unselect other tabs expect qr tab
                    homeTxt.setVisibility(View.GONE);
                    qrTxt.setVisibility(View.GONE);
                    cartTxt.setVisibility(View.GONE);

                    homeImg.setImageResource(R.drawable.home_u);
                    qrImg.setImageResource(R.drawable.search_u);
                    cartImg.setImageResource(R.drawable.cart_u);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    qrLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    cartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select home tab
                    userTxt.setVisibility(view.VISIBLE);
                    userImg.setImageResource(R.drawable.profile);
                    userLayout.setBackgroundResource(R.drawable.round_back);

                    // crate animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    cartLayout.startAnimation(scaleAnimation);

                    // set 1st tab as selected tab
                    selectedInt = 4;

                }

            }
        });

    }
}