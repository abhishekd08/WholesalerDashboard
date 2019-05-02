package deshpande.abhishek.wholesalerdashboard.Model.ServerUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.JsonObjectRequestCallback;

public class JsonRequestCreator {

    private static String baseURL = "";

    public static JsonObjectRequest newJsonRequest(String path, int method, final JSONObject headers, final JSONObject body, final JsonObjectRequestCallback callback) {

        String url = baseURL + path;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, headers
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.responseListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.ErrorListener(error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headersMap = new HashMap<>();
                headersMap.put("Content-Type", "application/json");
                try {
                    headersMap.put("Authorization", headers.getString("Authorization"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return headersMap;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return body == null ? null : body.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        return jsonObjectRequest;
    }

}
