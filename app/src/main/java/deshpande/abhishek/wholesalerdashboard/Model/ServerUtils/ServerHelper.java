package deshpande.abhishek.wholesalerdashboard.Model.ServerUtils;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.JsonObjectRequestCallback;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.SimpleCallback;

public class ServerHelper {

    public void sendFirebaseTokenToServer(String token, SimpleCallback callback) {
        try {
            JSONObject headers = new JSONObject();
            //TODO create headers

            JSONObject body = new JSONObject();
            body.put("token", token);

            JsonObjectRequest jsonObjectRequest = JsonRequestCreator.newJsonRequest(
                    "server path"
                    , Request.Method.POST
                    , headers
                    , body
                    , new JsonObjectRequestCallback() {
                        @Override
                        public void responseListener(JSONObject response) {
                            //TODO process response
                        }

                        @Override
                        public void ErrorListener(VolleyError error) {
                            //TODO handle error
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
