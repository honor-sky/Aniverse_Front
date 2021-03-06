package org.gyeongsoton.aniverse_front;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
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

import java.io.File;
import java.io.FileWriter;

public class LoginNormal extends AppCompatActivity {

    private EditText login_ID, login_pass;
    private Button signup_btn,login_btn,normal_btn,centerbtn,sellerbtn;
    private ImageButton back_btn;
    String userIdx,userAuth=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginnormal);

        normal_btn = findViewById( R.id.normal_btn );
        normal_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginNormal.this, LoginNormal.class );
                startActivity( intent );
            }
        });

        /*centerbtn=  findViewById( R.id.centerbtn );
        centerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_normal.this, Login_center.class );
                startActivity( intent );
            }
        });

        sellerbtn=findViewById( R.id.sellerbtn );
        sellerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_normal.this, Login_seller.class );
                startActivity( intent );
            }
        }); */

        signup_btn = findViewById( R.id.signup_btn );
        signup_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginNormal.this, Signup.class );
                startActivity( intent );
            }
        });

        back_btn = findViewById( R.id.back_btn );
        back_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginNormal.this, Mypage.class );
                startActivity( intent );
            }
        });

        login_ID = findViewById( R.id.login_ID );
        login_pass = findViewById( R.id.login_pass );


        login_btn = findViewById( R.id.login_button );
        login_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserID = login_ID.getText().toString();
                String UserPass = login_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "isSuccess" );

                            if(success) {//????????? ?????????

                                userIdx = Integer.toString(jsonObject.getInt( "userIdx" ));
                                userAuth  = jsonObject.getString( "userAuth" ); //????????? ??????

                                Toast.makeText( getApplicationContext(), String.format("%s??? ???????????????.", UserID), Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( LoginNormal.this, Mypage.class ); //????????? ????????? ?????????????????? ?????? ??????
                                //?????? ?????? ??? ???????????? ??????
                                intent.putExtra( "userIdx", userIdx );
                                intent.putExtra( "userAuth", userAuth );
                                startActivity( intent );

                            } else {//????????? ?????????
                                Toast.makeText( getApplicationContext(), "???????????? ?????????????????????.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                login_Request rquest = new login_Request( UserID, UserPass, responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginNormal.this );
                queue.add( rquest );

            }
        });
    }
}