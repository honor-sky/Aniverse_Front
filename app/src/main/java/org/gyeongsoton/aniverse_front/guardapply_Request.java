package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class guardapply_Request extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/adopt/userinfo";
    private Map<String, String> map;

    public guardapply_Request(String message,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("message", message);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}