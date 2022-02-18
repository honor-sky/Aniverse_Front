package org.gyeongsoton.aniverse_front;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// 메인화면
public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        TableLayout layout [] = { (TableLayout)findViewById(R.id.tablelayout1),(TableLayout)findViewById(R.id.tablelayout2),(TableLayout)findViewById(R.id.tablelayout3)};
        TableRow row [] = new TableRow[3];


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jArray = new JSONArray(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jArray.getJSONObject(0).getBoolean("isSuccess"); //제대로 받아왔는지 확인
                    if (success) {
                        System.out.println("성공");

                        for(int i=1; i<jArray.length(); i++){
                            JSONObject jobj = jArray.getJSONObject(i); //adopt, fund, market 차례대로 접근
                            ImageView img [] = new ImageView[jobj.length()];  //가져온 이미지 개수만큼 이미지뷰 만듬
                            /*이미지뷰의 속성 설정*/
                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100,100);
                            layoutParams.leftMargin = 10;

                            for(int j=0; j<jobj.length();j++){
                                img[j].setLayoutParams(layoutParams); //이미지뷰의 속성 변경
                                //img[j].setLayoutParams(new LinearLayout.LayoutParams(100,100)); //이미지뷰 크기 설정
                                row[i-1].addView(img[j]); //TableRow에 이미지뷰 부착
                                Glide.with(Main.this).load(jobj.getString("img")).into(img[j]); //이미지 셋팅

                            }
                            layout[i-1].addView(row[i-1]); //tableLayout에 TableRow 부착

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        main_Request mainrequest = new main_Request(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(Main.this);
        queue.add(mainrequest);

        /*imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdoptDetail.class);
                intent.putExtra( "animalIdx", adopt1Idx );
                startActivity(intent);
            }
        });


        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FundingDetail.class);
                intent.putExtra( "fundingIdx", funding1Idx );
                startActivity(intent);
            }
        });*/

        /*하단바(클래스나 메소드로 만들기)*/
        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalList.class);
                startActivity(intent);
            }
        });

        ImageButton funding_btn = (ImageButton)findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FundingList.class);
                startActivity(intent);
            }
        });

        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Mypage.class);
                startActivity(intent);
            }
        });

    }
}