package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class adoptdetail_Request extends StringRequest{

    final static private String URL = "http://3.36.175.200/adopt/imgclick";
    private Map<String, String> map;

    public adoptdetail_Request( int adoptListIdx,Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("adoptListIdx", Integer.toString(adoptListIdx));

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

