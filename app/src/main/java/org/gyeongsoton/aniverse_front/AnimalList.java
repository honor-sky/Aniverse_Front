package org.gyeongsoton.aniverse_front;

import static android.content.ContentValues.TAG;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class AnimalList extends AppCompatActivity {

    private ImageView ani_1;
    private TextView textView1;

    //프래그먼트 식별 변수
    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;

    //프래그먼트 객체 생성(객체 변수를 전역변수로 만들며 프래그먼트 오류 해결) //Animal Activity가 생성되면서 그 위에 올라갈 프래그먼트들도 함께 생성
    Fragment fragment1=new AdoptList();
    Fragment fragment2=new ProtectList();
    Fragment fragment3=new CompleteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animallist);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        Button add_btn = (Button)findViewById(R.id.add_btn);

        //AdoptList, ProtectList upload가 서로 다름
    /*    add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_protect_upload.class);

                startActivity(intent);
            }
        });*/

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnimalList.class);
                startActivity(intent);
            }
        });

        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
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
        ImageButton market_btn = (ImageButton)findViewById(R.id.market_btn);
        /*market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Market_list.class);
                startActivity(intent);
            }
        });*/
        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });

        /*입양, 입양완료 버튼 전환*/
        Button adopt_tab = (Button)findViewById(R.id.adopt_tab);
        Button protect_tab = (Button)findViewById(R.id.protect_tab);
        Button complete_tab = (Button)findViewById(R.id.complete_tab);

        adopt_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //fragment1 = new AdoptList();
                //sleep(3);
                FragmentView(Fragment_1);
                adopt_tab.setPressed(true);
                protect_tab.setPressed(false);
                complete_tab.setPressed(false);
                //ResponseListener와 Volley 가 있는 클래스 호출 (어떤 프래그먼트 호출할건지 알려줄만한 정보, Volley요청 시 필요한 인자 함께 전달)
                return true;
            }
        });

        protect_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //fragment2 = new ProtectList();
                FragmentView(Fragment_2);
                protect_tab.setPressed(true);
                adopt_tab.setPressed(false);
                complete_tab.setPressed(false);
                return true;

            }
        });

        complete_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //fragment3 = new CompleteList();
                FragmentView(Fragment_3);
                complete_tab.setPressed(true);
                adopt_tab.setPressed(false);
                protect_tab.setPressed(false);
                return true;

            }
        });

        //기본상태(뺄지 말지 고민중)
        //adopt_tab.setPressed(true);
        //protect_tab.setPressed(false);
        //complete_tab.setPressed(false);

    }


    /*프래그먼트 버튼 클릭 시 동작*/
    private void FragmentView(int fragment) {
        switch (fragment) { //입양
            case 1:
                //Fragment fragment1 = new AdoptList();
                getSupportFragmentManager().beginTransaction().replace(R.id.aniaml_list_container,fragment1).commit(); //프래그먼트 변경 //변경사항 반영 되나?....
                break;

            case 2: //임시보호
                //Fragment fragment2 = new ProtectList();
                getSupportFragmentManager().beginTransaction().replace(R.id.aniaml_list_container,fragment2).commit();
                break;

            case 3: //완료
                //Fragment fragment3 = new CompleteList();
                getSupportFragmentManager().beginTransaction().replace(R.id.aniaml_list_container,fragment3).commit();
                break;
        }
    }

}