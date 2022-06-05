package org.gyeongsoton.aniverse_front;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class FundingList extends Fragment {

    private ImageView fund_img1;
    private TextView fund_info1;
    String fundingImage,fundingName;
    Button add_btn;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_fundinglist, container, false);

        fund_img1 = view.findViewById(R.id.fund_img1);
        fund_info1 = view.findViewById(R.id.fund_info1);
        add_btn = view.findViewById(R.id.add_btn);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        System.out.println("펀딩 성공");

                        fundingImage = jsonResponse.getString("fundingImage");
                        fundingName = jsonResponse.getString("fundingName");

                        //첫번째 펀딩 정보 셋팅
                        fund_info1.setText(fundingName);
                        Glide.with(FundingList.this).load(fundingImage).into(fund_img1);
                        fund_img1.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        fundinglist_Request request = new fundinglist_Request(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

        //이미지 누르면 세부화면으로 전환
        fund_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FundingDetail.class);
                startActivity(intent);
            }
        });

        Button funding_add_btn=(Button)view.findViewById(R.id.funding_add_btn);
        funding_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FundingUpload.class);
                startActivity(intent);
            }
        });


/*
        //하단바
        ImageButton home_btn = (ImageButton) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FundingList.this, Main.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton) findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalListRecyclerFragment.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton funding_btn = (ImageButton) findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FundingList.class);
                // intent.putExtra( "userIdx", userIdx);
                // intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });
        ImageButton market_btn = (ImageButton) findViewById(R.id.market_btn);
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });*/
        return view;
    }
}