package org.gyeongsoton.aniverse_front;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProtectAnimalUpload extends AppCompatActivity {

    private EditText species, sex, age, vaccination, disease, findSpot, animalInfo,deadline,period,protectCondition;

    private final int GET_GALLERY_IMAGE=200;
    private ImageButton ani_img;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protectanimalupload);

        species = findViewById(R.id.species);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        vaccination = findViewById(R.id.vaccination);
        disease = findViewById(R.id.disease);
        findSpot = findViewById(R.id.findSpot);
        animalInfo = findViewById(R.id.animalInfo);
        deadline = findViewById(R.id.deadline);
        period = findViewById(R.id.period);
        protectCondition = findViewById(R.id.protectCondition);

        Button upload_button = findViewById(R.id.upload_button);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Species = species.getText().toString();
                final String Sex = sex.getText().toString();
                final String Age = age.getText().toString();
                final String Vaccination = vaccination.getText().toString();
                final String Disease = disease.getText().toString();
                final String FindSpot = findSpot.getText().toString();
                final String AnimalInfo = animalInfo.getText().toString();
                final String Deadline = deadline.getText().toString();
                final String Period = period.getText().toString();
                final String ProtectCondition = protectCondition.getText().toString();


                //한 칸이라도 입력 안했을 경우
                if (Species.equals("")  || Age.equals("") || Vaccination.equals("") || Disease.equals("") || FindSpot.equals("") || AnimalInfo.equals("") ||
                        Sex.equals("") || Deadline.equals("") || Period.equals("")|| ProtectCondition.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProtectAnimalUpload.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //업로드 성공시
                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("업로드 성공"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProtectAnimalUpload.this, GuardApplyConfirm.class);
                                startActivity(intent);

                                //업로드 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 Volley를 이용해서 요청
                protectanimalupload_Request request = new protectanimalupload_Request(Species, Age, Sex,Vaccination,
                        Disease,Deadline,Period,ProtectCondition,AnimalInfo,FindSpot,responseListener);
                RequestQueue queue = Volley.newRequestQueue( ProtectAnimalUpload.this );
                queue.add(request);
            }
        });



        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdoptList.class);
                startActivity(intent);
            }
        });

        ani_img = (ImageButton) findViewById(R.id.ani_img);
        ani_img.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(intent);
        });

    }
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        ani_img.setImageURI(uri);
                    }
                }
            });

}
