package deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks;

import org.json.JSONObject;

public interface JsonDataCallback {
    public void onSuccess(JSONObject response);
    public void onFailure(Exception e);
}
