package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest{

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/signup";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public SignupRequest(String UserID, String UserName, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("userAmmo", "12345");
        map.put("userId", UserID);
        map.put("userPhoneNum", "8328-7690");
        map.put("userName", UserName);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}