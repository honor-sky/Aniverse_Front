package org.gyeongsoton.aniverse_front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class FundingDetail extends AppCompatActivity {

    String Title,Purpose,Period,Image;
    int Amount,Goal; //현재모인금액, 목표모금금액
    private TextView fundingTitle,fundingPurpose,fundingPeriod;
    private ImageView fund_img;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundingdetail);

        fund_img = findViewById(R.id.fund_img);
        fundingTitle = findViewById(R.id.fundingTitle);
        fundingPurpose = findViewById(R.id.fundingPurpose);
        fundingPeriod = findViewById(R.id.fundingPeriod);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        //Amount=jsonResponse.getInt("fundingAmount"); //현재 모인 금액
                        //Goal=jsonResponse.getInt("fundingAmount"); //목표 모금 금액
                        Purpose=jsonResponse.getString("fundingPurpose");
                        Title=jsonResponse.getString("fundingName");
                        Image=jsonResponse.getString("fundingImage");
                        Period = jsonResponse.getString("fundingPeriod");

                        //동물정보 셋팅
                        fundingTitle.setText(Title); //펀딩명
                        Glide.with(FundingDetail.this).load(Image).into(fund_img);
                        fundingPurpose.setText("펀딩 내용: "+fundingPurpose);//펀딩내용
                        fundingPeriod.setText("펀딩기간: "+fundingPeriod);//펀딩기간

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        fundingdetail_Request request = new fundingdetail_Request(responseListener); //입양 진행중
        RequestQueue queue = Volley.newRequestQueue(FundingDetail.this );
        queue.add(request);


        Button d_btn = (Button)findViewById(R.id.fund_btn);
        d_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), String.format("젤리 기부 감사합니다!!"), Toast.LENGTH_SHORT).show();

                /*Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "isSuccess" );

                            //업로드 성공시
                            if (success) {
                                Toast.makeText(getApplicationContext(), String.format("젤리 "+jellyNum+"개를 기부했습니다."), Toast.LENGTH_SHORT).show();

                                //업로드 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "젤리 기부를 하지 못했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }*/


                //서버로 Volley를 이용해서 요청
                // funding_Request request = new funding_Request("1","2","18",jellyNum,responseListener);
                // RequestQueue queue = Volley.newRequestQueue( Funding_info.this );
                // queue.add( request );
            }
        });


        SeekBar seekbar = findViewById(R.id.jellyBar);
        TextView textView = findViewById(R.id.countJelly);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 10){
                    progress = 10;
                }else if(progress>100){
                    progress = 100;
                }else{
                    //NOP
                }
                textView.setText(progress+"개");
            }

            ProgressBar progressBar = findViewById(R.id.progressBar);
            TextView textView2 = findViewById(R.id.percent);

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar) {
            }

        });

    }

}