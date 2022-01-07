package org.gyeongsoton.aniverse_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class FundingMonitorTotal extends AppCompatActivity {

    ImageView fund_img,review_img1;
    TextView fundingTitle,fundingAmount,fundingPurpose,review_text1;
    String Image,ReviewText,ReviewFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundingmonitortotal);


        Intent intent= getIntent();
        String fundingIdx= intent.getStringExtra("fundingIdx");

        fund_img=findViewById(R.id.fund_img);
        fundingTitle=findViewById(R.id.fundingTitle);
        fundingAmount=findViewById(R.id.fundingAmount);
        fundingPurpose=findViewById(R.id.fundingPurpose);
        review_img1=findViewById(R.id.review_img1);
        review_text1=findViewById(R.id.review_text1);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        ReviewText=jsonResponse.getString("fundingReviewText");
                        Image=jsonResponse.getString("fundingImage"); //큰 이미지
                        ReviewFile=jsonResponse.getString("fundingReviewFile1");

                        //동물정보 셋팅
                        Glide.with(FundingMonitorTotal.this).load(Image).into(fund_img); //사진
                        Glide.with(FundingMonitorTotal.this).load(ReviewFile).into(review_img1);
                        review_text1.setText(ReviewText);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        fundungmonitor_Request request = new fundungmonitor_Request("moongchi", responseListener); //입양 진행중
        RequestQueue queue = Volley.newRequestQueue(FundingMonitorTotal.this );
        queue.add(request);

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });

        Button news_add_btn=findViewById(R.id.news_add_btn);
        news_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FundingMonitorUpload.class);
                startActivity(intent);
            }
        });
    }
}