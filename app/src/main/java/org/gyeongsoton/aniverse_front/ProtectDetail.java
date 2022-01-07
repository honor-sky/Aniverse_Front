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

public class ProtectDetail extends AppCompatActivity {

    String Species,Age,Gender,Neutralization, Weight,Vaccinated, Diseases, FindSpot, Info,Deadline,Peroid,protectFile,Condition;
    private ImageView ani_img;
    private TextView species_gender_age,neutering,weight,vaccination,disease,findSpot,animalInfo,deadline,period,protectCondition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Adopt_list에서 클릭한 동물의 인덱스 전달

        ani_img = findViewById(R.id.ani_img);
        species_gender_age = findViewById(R.id.species_gender_age);
        neutering = findViewById(R.id.neutering);
        weight = findViewById(R.id.weight);
        vaccination = findViewById(R.id.vaccination);
        disease = findViewById(R.id.disease);
        findSpot = findViewById(R.id.findSpot);
        animalInfo = findViewById(R.id.animalInfo);
        deadline = findViewById(R.id.deadline);
        period = findViewById(R.id.period);
        protectCondition = findViewById(R.id.protectCondition);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        Species=jsonResponse.getString("animalSpecies");
                        Age=jsonResponse.getString("animalAge");
                        Gender = jsonResponse.getString("animalGender");
                        Neutralization = jsonResponse.getString("animalNeutralization");
                        Weight = jsonResponse.getString("animalWeight");
                        Vaccinated = jsonResponse.getString("animalVaccinated");
                        Diseases = jsonResponse.getString("animalDiseases");
                        FindSpot = jsonResponse.getString("animalFind");
                        Info = jsonResponse.getString("animalIntro");
                        Deadline = jsonResponse.getString("protectDateStart");
                        Peroid = jsonResponse.getString("protectDateEnd");
                        protectFile=jsonResponse.getString("protectFile");
                        Condition = jsonResponse.getString("adoptCondition");

                        //동물정보 셋팅
                        Glide.with(ProtectDetail.this).load(protectFile).into(ani_img); //사진
                        species_gender_age.setText("품종: "+Species+"   성별: "+ Gender+"   나이:  "+Age); //종성별중성화
                        neutering.setText("품종: "+Neutralization);//중성화 여부
                        vaccination.setText("품종: "+Vaccinated);//접종여부
                        disease.setText("병력: "+Diseases);//병력
                        weight.setText("무게: "+Weight);//무게
                        findSpot.setText("발견장소: "+FindSpot);//발견장소
                        animalInfo.setText("특성 및 성격: "+Info);//특성 및 성격
                        period.setText("임시보호 기간: Peroid");//임시보호 기간
                        deadline.setText("임시보호 기간: Deadline");//임시보호 신청 마감일
                        protectCondition.setText("임시보호 조건 및 기타사항: "+Condition);//입양조건 및 기타사항

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        protectdetail_Request request = new protectdetail_Request(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(ProtectDetail.this );
        queue.add(request);


        //'신청하기' 버튼 누르면 신청자 정보 입력 화면으로 넘어감
        Button request_btn = (Button) findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GuardApply.class);
                startActivity(intent);
            }
        });

        //뒤로가기 버튼
        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalList.class);

                startActivity(intent);
            }
        });

    }
}
