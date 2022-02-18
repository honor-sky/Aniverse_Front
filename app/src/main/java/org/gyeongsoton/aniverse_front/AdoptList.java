package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// [입양] 버튼 눌렀을 때 동물 리스트
// 프래그먼트, 리사이클러뷰 사용
public class AdoptList extends Fragment {

    RecyclerView recyclerView;
    ListRecycleAdapter aniAdapter;
    ArrayList<ListRecyclerItem> mList;
    private Context mContext;

    /*
    public static SlideAdoptFragment newInstance(int number){
        SlideAdoptFragment adoptFragment = new SlideAdoptFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        adoptFragment.setArguments(bundle);
        return adoptFragment;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_adoptlist, container, false); //프래그먼트 레이아웃을 클래스에 올려줌
        System.out.println("입양: onCreateView 실행");


        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //response 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        JSONArray respArr = (JSONArray) jsonResponse.get("adoptListRows");
                        System.out.println(respArr.length());

                        mContext=getContext();
                        mList = new ArrayList<>();
                        //어댑터 객체
                        aniAdapter = new ListRecycleAdapter(1,mContext,mList);
                        //리사이클러뷰 객체
                        recyclerView = (RecyclerView) view.findViewById(R.id.aniRecyclerView);
                        recyclerView.setAdapter(aniAdapter);
                        //레이아웃 지정
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

                        for(int i=0;i<respArr.length();i++){
                            ListRecyclerItem item= new ListRecyclerItem();
                            JSONObject obj = null;
                            try {
                                obj = (JSONObject)respArr.get(i);
                                item.setImage(obj.getString("animalImage"));
                                item.setInfo(obj.getString("animalSpecies")+" "+obj.getString("animalAge")+"세");
                                aniAdapter.setArrayData(item);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        animallist_Request request = new animallist_Request("S",responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

        return view;
    }
}


