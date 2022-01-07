package org.gyeongsoton.aniverse_front;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Mypage extends AppCompatActivity {

    private Button login_btn,signup_btn;
    private ImageButton jelly_shop_btn;
    private ImageView myFund_img1;
    private TextView jellyCount,myFund_title1;

    String userId,fundingImage,fundingName,userJellyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        // Intent intent= getIntent();
        //userIdx= intent.getStringExtra("userIdx");
        // userAuth= intent.getStringExtra("userAuth");

        login_btn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.signup_btn);
        jelly_shop_btn = findViewById(R.id.jellyShop_btn);
        myFund_img1 = findViewById(R.id.myFund_img1);
        myFund_title1 = findViewById(R.id.myFund_title1);
        jellyCount=findViewById(R.id.jellyCount);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginNormal.class);
                startActivity(intent);
            }
        });


            //로그인 버튼을 아이디로 바꿔줘야 함
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("isSuccess");

                            if (success) {

                                userId = jsonObject.getString("userId");
                                fundingName = jsonObject.getString("fundingName");
                                fundingImage = jsonObject.getString("fundingImage");
                                // userJellyNum= jsonObject.getString("userJellyNum");

                                System.out.println(userId);
                                login_btn.setText(userId);

                                Glide.with(Mypage.this).load(fundingImage).into(myFund_img1);
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                mypage_Request request = new mypage_Request("28", responseListener);
                RequestQueue queue = Volley.newRequestQueue(Mypage.this);
                queue.add(request );


        //회원가입
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        //jelly_shop 연결
        jelly_shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JellyShop.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        /*하단바*/
        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalList.class);
                // intent.putExtra( "userIdx", userIdx);
                // intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton funding_btn = (ImageButton)findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FundingList.class);
                // intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });
        /*
        ImageButton market_btn = (ImageButton)findViewById(R.id.market_btn);
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Market_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });*/

        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

    }
}
