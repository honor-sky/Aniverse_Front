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
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class AdoptAnimalUpload extends AppCompatActivity {

    private EditText species, sex, age, neutering ,vaccination, disease, deadline,
            findSpot, animalInfo, condition;
    private AlertDialog dialog;
    private final int GET_GALLERY_IMAGE=200;
    private ImageButton ani_img,back_btn;
    private Button upload_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoptanimalupload);


        species = findViewById(R.id.species);
        sex = findViewById(R.id.sex);
        neutering = findViewById(R.id.neutering);
        age = findViewById(R.id.age);
        vaccination = findViewById(R.id.vaccination);
        disease = findViewById(R.id.disease);
        deadline = findViewById(R.id.deadline);
        findSpot = findViewById(R.id.findSpot);
        animalInfo = findViewById(R.id.animalInfo);
        condition = findViewById(R.id.condition);
        ani_img = findViewById(R.id.ani_img);
        upload_button = findViewById(R.id.upload_button);


        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Species = species.getText().toString();
                final String Sex = sex.getText().toString();
                final String Neutering = neutering.getText().toString();
                final String Age = age.getText().toString();
                final String Vaccination = vaccination.getText().toString();
                final String Disease = disease.getText().toString();
                final String Deadline = deadline.getText().toString();
                final String FindSpot = findSpot.getText().toString();
                final String AnimalInfo = animalInfo.getText().toString();
                final String Condition = condition.getText().toString();


                //??? ???????????? ?????? ????????? ??????
                if (Species.equals("")  || Sex.equals("") ||Neutering.equals("") ||Age.equals("") || Vaccination.equals("") ||
                        Disease.equals("") || Deadline.equals("") || FindSpot.equals("") ||AnimalInfo.equals("") ||
                        Condition.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdoptAnimalUpload.this);
                    dialog = builder.setMessage("?????? ??????????????????.").setNegativeButton("??????", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //????????? ?????????
                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("????????? ??????"), Toast.LENGTH_SHORT).show();

                                //????????? ?????????
                            } else {
                                Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //????????? Volley??? ???????????? ??????
                adoptanimalupload_Request request = new adoptanimalupload_Request(Species,Sex,Neutering,Age,
                        Vaccination,Disease,Deadline,FindSpot,AnimalInfo,Condition,responseListener);
                RequestQueue queue = Volley.newRequestQueue( AdoptAnimalUpload.this );
                queue.add( request );
            }
        });

        //????????? ??????
        ani_img.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(intent);
        });

        //????????????
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalList.class);
                startActivity(intent);
            }
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