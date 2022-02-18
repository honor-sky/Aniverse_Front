package org.gyeongsoton.aniverse_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

// 입양 신청하는 화면
public class GuardApply extends AppCompatActivity {


    String adoptListIdx,userIdx,userAuth,animalIdx;
    private TextInputEditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardapply);

        Intent intent= getIntent();

        //뒤로가기 버튼
        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdoptDetail.class);
                startActivity(intent);
            }
        });

        message = findViewById(R.id.message);
        final String messaAge = message.getText().toString();

        //정보 입력 후 진짜 신청 버튼
        Button request_btn = (Button)findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean( "isSuccess" );

                            if (success) {

                                Intent intent = new Intent(getApplicationContext(), GuardApply.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                guardapply_Request request = new guardapply_Request( messaAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue( GuardApply.this );
                queue.add( request );

            }
        });


    }
}