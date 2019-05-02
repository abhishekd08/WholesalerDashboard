package deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface JsonObjectRequestCallback {
    public void responseListener(JSONObject response);
    public void ErrorListener(VolleyError error);
}
