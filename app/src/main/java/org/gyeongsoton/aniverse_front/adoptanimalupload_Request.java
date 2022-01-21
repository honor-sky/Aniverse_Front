package org.gyeongsoton.aniverse_front;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class adoptanimalupload_Request extends StringRequest {
    final static private String URL = "http://3.34.166.156/";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public adoptanimalupload_Request(String Species,String Sex,String Neutering,String Age,String Vaccination,String Disease,
                                     String Deadline,String FindSpot,String AnimalInfo,String Condition,Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("animalSpecies", Species);
        map.put("animalGender", Sex);
        map.put("animalNeutralization", Neutering);
        map.put("animalAge", Age);
        map.put("animalVaccinated", Vaccination);
        map.put("animalDiseases", Disease);
        map.put("Deadline", Deadline);
        map.put("animalFind", FindSpot);
        map.put("animalIntro", AnimalInfo);
        map.put("adoptCondition", Condition);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}