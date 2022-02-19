package org.gyeongsoton.aniverse_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

// 젤리샵
public class JellyShop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jellyshop);


        Button j10 = (Button)findViewById(R.id.jelly_10);
        j10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JellyShopPay.class);
                intent.putExtra("jelly", "10");
                intent.putExtra("price", "1000");
                startActivity(intent);
            }
        });

        Button j30 = (Button)findViewById(R.id.jelly_30);
        j30.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JellyShopPay.class);
                intent.putExtra("jelly", "30");
                intent.putExtra("price", "3000");
                startActivity(intent);
            }
        });

        Button j50 = (Button)findViewById(R.id.jelly_50);
        j50.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JellyShopPay.class);
                intent.putExtra("jelly", "50");
                intent.putExtra("price", "5000");
                startActivity(intent);
            }
        });

        Button j100 = (Button)findViewById(R.id.jelly_100);
        j100.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JellyShopPay.class);
                intent.putExtra("jelly", "100");
                intent.putExtra("price", "10000");
                startActivity(intent);
            }
        });

        Button j300 = (Button)findViewById(R.id.jelly_300);
        j300.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JellyShopPay.class);
                intent.putExtra("jelly", "300");
                intent.putExtra("price", "30000");
                startActivity(intent);
            }
        });

    }
}