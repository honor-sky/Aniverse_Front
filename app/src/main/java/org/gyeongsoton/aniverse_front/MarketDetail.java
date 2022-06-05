/*
package org.gyeongsoton.aniverse_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MarketDetail extends AppCompatActivity {

    private static ImageView product_image;
    private static TextView product_name, product_price, product_info, product_cate;
    private static int productIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketdetail);

        //이전 액티비티에서 인덱스 받아옴
        Intent intent = getIntent();
        productIdx = intent.getExtras().getInt("productIdx");


        product_image = findViewById(R.id.productImage);
        product_name = findViewById(R.id.productName);
        product_price = findViewById(R.id.productPrice);
        product_info = findViewById(R.id.productInfo);
        product_cate = findViewById(R.id.productCate);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            String productImage,productName, productPrice, productInfo, productCate;
            int productCateIdx;

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //reponse 제대로 왔는지 확인

                    if (success) {
                        //데이터 배열 전체 파싱
                        JSONArray respArr = (JSONArray) jsonResponse.get("result");
                        JSONObject obj = (JSONObject)respArr.get(productIdx);

                        productImage = obj.getString("productImage");
                        productName = obj.getString("productName");
                        //productPrice = obj.getString("productPrice");
                        //productInfo = obj.getString("productInfo");
                        productCateIdx = obj.getInt("categoryIdx");

                        if(productCateIdx==1){productCate="의류";}
                        else if(productCateIdx==2){productCate="위생용품";}
                        else if(productCateIdx==3){productCate="간식";}
                        else if(productCateIdx==4){productCate="장난감";}


                        Glide.with(MarketDetail.this).load(productImage).into(product_image); //사진
                        product_name.setText(productName);
                        //product_price.setText(productPrice+"원");
                        //product_info.setText(productInfo);
                        product_cate.setText(productCate);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        marketlist_Request request = new marketlist_Request( responseListener); //입양 진행중
        RequestQueue queue = Volley.newRequestQueue(MarketDetail.this);
        queue.add(request);



        */
/*버튼*//*

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MarketList.class);
                startActivity(intent);
            }
        });

        Button buy_btn = (Button)findViewById(R.id.buy_btn);
        final ProductOption_bottomsheet bottomSheetFragment = new ProductOption_bottomsheet();

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

    }
}
*/
