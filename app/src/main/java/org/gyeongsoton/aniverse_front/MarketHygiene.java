package org.gyeongsoton.aniverse_front;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MarketHygiene extends Fragment {

    RecyclerView recyclerView;
    ListRecycleAdapter aniAdapter;
    ArrayList<ListRecyclerItem> mList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view =  (ViewGroup) inflater.inflate(R.layout.fragment_marketitemlist, container, false);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("isSuccess"); //response 제대로 왔는지 확인

                    if (success) {
                        System.out.println("성공");

                        JSONArray respArr = (JSONArray) jsonResponse.get("result");
                        System.out.println(respArr.length());

                        mContext=getContext();
                        mList = new ArrayList<>();
                        //어댑터 객체
                        aniAdapter = new ListRecycleAdapter(5,mContext,mList);
                        //리사이클러뷰 객체
                        recyclerView = (RecyclerView) view.findViewById(R.id.itemRecyclerView);
                        recyclerView.setAdapter(aniAdapter);
                        //레이아웃 지정
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

                        for(int i=0;i<respArr.length();i++){
                            ListRecyclerItem item= new ListRecyclerItem();
                            JSONObject obj = null;
                            try {
                                obj = (JSONObject)respArr.get(i);
                                if (obj.getInt("categoryIdx")==2){
                                    item.setImage(obj.getString("productImage"));
                                    item.setInfo(obj.getString("productName"));
                                    aniAdapter.setArrayData(item);
                                }

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
        marketlist_Request request = new marketlist_Request(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

        return view;
    }
}