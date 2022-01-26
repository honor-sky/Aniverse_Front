package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;

public class ProtectList extends Fragment {

    private ImageView ani_img1;
    private TextView ani_info1;
    String Image, Info;
    AnimalList animalList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        animalList = (AnimalList) getActivity();
        System.out.println("임시보호: onAttach 실행");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("임시보호: onDestroyView 실행");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("임시보호: onDestroy 실행");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_protectlist, container, false);
        System.out.println("임시보호: onCreateView 실행");

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());//예외처리 핸들러

        //Bundle bundle = getArguments();

        ani_info1=view.findViewById(R.id.ani_info1);
        ani_img1=view.findViewById(R.id.ani_img1);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //reponse 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        //데이터 배열 전체 파싱
                        JSONArray respArr = (JSONArray) jsonResponse.get("adoptListRows");
                        //반복문으로 하나하나 파싱
                        for(int i=0;i<respArr.length();i++){
                            Image = jsonResponse.getString("protectImage");
                            Info=jsonResponse.getString("animalSpecies");
                            System.out.println(Image);

                            Glide.with(ProtectList.this).load(Image).into(ani_img1);
                            ani_img1.setClipToOutline(true);
                        }
                        /*
                        sleep을 통해 이미지가 로드가 프래그먼트 죽은 후 로드 되는 상황을 해결하려고 함
                        try {
                                TimeUnit.SECONDS.sleep(3);

                        }catch(Exception e) {
                            System.out.println(e);
                        }*/

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        protectlist_Request request= new protectlist_Request(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(request);

        ani_info1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), ProtectDetail.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("임시보호: onViewCreated 실행 : before super");
        super.onViewCreated(view, savedInstanceState);
        System.out.println("임시보호: onViewCreated 실행 : after super");

    }
}
