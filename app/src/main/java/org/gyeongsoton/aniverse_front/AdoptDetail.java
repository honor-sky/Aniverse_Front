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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdoptDetail extends AppCompatActivity {



    private ImageView ani_img;
    private TextView species_gender_neu, age, weight, vaccination, disease, findSpot, animalInfo, deadline, adoptCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Adopt_list에서 클릭한 동물의 인덱스 전달
        //Intent intent = getIntent(); /*데이터 수신*/
        //animalIdx = intent.getExtras().getString("animalIdx");


        ani_img = findViewById(R.id.ani_img);
        species_gender_neu = findViewById(R.id.species_gender_age);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        vaccination = findViewById(R.id.vaccination);
        disease = findViewById(R.id.disease);
        findSpot = findViewById(R.id.findSpot);
        animalInfo = findViewById(R.id.animalInfo);
        deadline = findViewById(R.id.deadline); //입양신청 마감일
        adoptCondition = findViewById(R.id.adoptCondition); //입양 조건


        Response.Listener<String> responseListener = new Response.Listener<String>() {

            String img_url, animalSpecies, animalAge, animalGender, animalNeutralization, animalWeight, animalVaccinated,
                    animalDiseases,adoptEnd,animalFind, animalIntro, adoptcondition;

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        img_url = jsonResponse.getString("animalImage");
                        animalSpecies = jsonResponse.getString("animalSpecies");
                        animalAge = jsonResponse.getString("animalAge");
                        animalGender = jsonResponse.getString("animalGender");
                        animalNeutralization = jsonResponse.getString("animalNeutralization");
                        animalWeight = jsonResponse.getString("animalWeight");
                        animalVaccinated = jsonResponse.getString("animalVaccinated");
                        animalDiseases = jsonResponse.getString("animalDiseases");
                        animalFind = jsonResponse.getString("animalFind");
                        animalIntro = jsonResponse.getString("animalIntro");
                        adoptEnd = jsonResponse.getString("adoptEnd");
                        adoptcondition = jsonResponse.getString("adoptCondition");

                        //동물정보 셋팅
                        Glide.with(AdoptDetail.this).load(img_url).into(ani_img); //사진
                        species_gender_neu.setText("품종: " + animalSpecies + "   성별: " + animalGender + "   중성화 여부:  " + animalNeutralization); //종성별중성화
                        age.setText("추정 나이: " + animalAge);//나이
                        weight.setText("무게: " + animalWeight);//무게
                        vaccination.setText("품종: " + animalVaccinated);//접종여부
                        disease.setText("병력: " + animalDiseases);//병력
                        findSpot.setText("발견장소: " + animalFind);//발견장소
                        animalInfo.setText("특성 및 성격: " + animalIntro);//특성 및 성격
                        deadline.setText("입양마감일: "+adoptEnd);//입양마감일
                        adoptCondition.setText("입양조건 및 기타사항: "+adoptcondition);//입양조건 및 기타사항

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        adoptdetail_Request request = new adoptdetail_Request( responseListener); //입양 진행중
        RequestQueue queue = Volley.newRequestQueue(AdoptDetail.this);
        queue.add(request);

/*
        Button request_btn = (Button) findViewById(R.id.request_btn);
        request_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Adopt_info.this, Adopt_request.class );
                startActivity( intent );
            }
        });

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( AdoptDetail.this, AdoptList.class );
                startActivity( intent );
            }
        });
*/


        //'신청하기' 버튼 누르면 신청자 정보 입력 화면으로 넘어감
    /*    Button request_btn = (Button) findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_request.class);

                startActivity(intent);
            }
        });*/

        //뒤로가기 버튼
    /*    ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra("userAuth",userAuth);
                startActivity(intent);
            }
        });*/

    }
}