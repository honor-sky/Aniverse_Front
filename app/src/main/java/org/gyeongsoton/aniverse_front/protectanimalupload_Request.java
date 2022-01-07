package org.gyeongsoton.aniverse_front;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class protectanimalupload_Request extends StringRequest {

    final static private String URL = "";
    private Map<String, String> map;

    public protectanimalupload_Request( String Species, String Age, String Sex,String Vaccination,
                                        String Disease,String Deadline,String Period,String ProtectCondition,
                                        String AnimalInfo,String FindSpot, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("Species", Species);
        map.put("Age", Age);
        map.put("Sex", Sex);
        map.put("Vaccination", Vaccination);
        map.put("Disease", Disease);
        map.put("Deadline", Deadline);
        map.put("Period", Period);
        map.put("ProtectCondition", ProtectCondition);
        map.put("AnimalInfo", AnimalInfo);
        map.put("FindSpot", FindSpot);


    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}