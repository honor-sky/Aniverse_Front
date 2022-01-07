package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class fundungmonitor_Request extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/funding/review";
    private Map<String, String> map;

    public fundungmonitor_Request(String fundingTitle, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("fundingTitle", fundingTitle);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}