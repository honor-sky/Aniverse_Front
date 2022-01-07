package org.gyeongsoton.aniverse_front;

import static com.android.volley.Request.Method.GET;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class fundingdetail_Request extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/funding/ing";
    private Map<String, String> map;

    public fundingdetail_Request(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null); //서버에 요청

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

}
