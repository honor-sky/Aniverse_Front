package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class protectlist_Request extends StringRequest {
    final static private String URL = "http://3.36.175.200/protect/list";

    public protectlist_Request(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);

    }
}
