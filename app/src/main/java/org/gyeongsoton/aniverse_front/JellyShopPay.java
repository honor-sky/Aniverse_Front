package org.gyeongsoton.aniverse_front;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class JellyShopPay extends AppCompatActivity {

    private AlertDialog dialog;
    private TextView jellyCount,price;
    private Button buy_button;
    private String pur_way;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jellyshoppay);

        Intent intent= getIntent();
        String JellyCount= intent.getStringExtra("jelly");
        String Price= intent.getStringExtra("price");

        jellyCount = findViewById(R.id.jellyCount);
        price =  findViewById(R.id.price);
        jellyCount.setText(JellyCount+"개");
        price.setText(Price+"원");

        buy_button=findViewById(R.id.buy_btn);
        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "isSuccess" );

                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("구매하였습니다."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JellyShopPay.this, Mypage.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "구매하지 못하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                jellybuy_Request request = new jellybuy_Request(JellyCount, pur_way, responseListener);
                RequestQueue queue = Volley.newRequestQueue( JellyShopPay.this );
                queue.add(request);
            }
        });

        RadioGroup purchaseWay=findViewById(R.id.purchaseWay);
        RadioButton card=findViewById(R.id.card);
        RadioButton account=findViewById(R.id.account);
        RadioButton normal=findViewById(R.id.normal);

        pur_way=card.getText().toString();

        purchaseWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i){
                if (i==R.id.card){
                    pur_way=card.getText().toString();
                }
                else if(i==R.id.account){
                    pur_way=account.getText().toString();
                }
                else{
                    pur_way=normal.getText().toString();
                }
            }
        });

    }
}
