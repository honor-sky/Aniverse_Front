package org.gyeongsoton.aniverse_front;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class AdoptList extends Fragment {

    private ImageView ani_1;
    private TextView ani_info1;
    String animalImage, animalSpecies, animalAge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_adoptlist, container, false);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        ani_info1 = view.findViewById(R.id.ani_info1);
        ani_1 = view.findViewById(R.id.ani_img1);

        //이미지 누르면 세부화면으로 전환
        ani_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdoptDetail.class);
                startActivity(intent);
            }
        });

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        animalImage = jsonResponse.getString("animalImage");
                        animalSpecies = jsonResponse.getString("animalSpecies");
                        animalAge = jsonResponse.getString("animalAge");

                        //첫번째 동물 정보 셋팅
                        ani_info1.setText(animalSpecies + " " + animalAge + "세");
                        Glide.with(AdoptList.this).load(animalImage).into(ani_1);
                        ani_1.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        animallist_Request request = new animallist_Request(responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(request);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

