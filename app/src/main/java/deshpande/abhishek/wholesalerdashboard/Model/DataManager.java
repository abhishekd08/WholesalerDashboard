package deshpande.abhishek.wholesalerdashboard.Model;

import org.json.JSONException;
import org.json.JSONObject;

import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.FirebaseAuthCallback;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.JsonDataCallback;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.ModelCallback;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.SimpleCallback;
import deshpande.abhishek.wholesalerdashboard.Model.FirebaseUtils.FirebaseHelper;
import deshpande.abhishek.wholesalerdashboard.Model.ServerUtils.ServerHelper;
import deshpande.abhishek.wholesalerdashboard.Model.SharedPrefUtils.SharedPrefHelper;

public class DataManager {

    private static DataManager mInstance;
    private FirebaseHelper firebaseHelper;
    private SharedPrefHelper sharedPrefHelper;
    private ServerHelper serverHelper;

    private DataManager(FirebaseHelper firebaseHelper, SharedPrefHelper sharedPrefHelper, ServerHelper serverHelper) {
        mInstance = this;
        this.firebaseHelper = firebaseHelper;
        this.sharedPrefHelper = sharedPrefHelper;
        this.serverHelper = serverHelper;
    }

    public static synchronized DataManager getmInstance() {
        if (mInstance == null) {
            //GIVE DEPENDANCIES TO DATA MANAGER :::: REMOVE NULL BELOW
            mInstance = new DataManager(null, null, null);
        }
        return mInstance;
    }

    public void checkIfMailExists(String mail, final ModelCallback callback) {
        firebaseHelper.checkMailExists(mail, new FirebaseAuthCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure();
            }
        });
    }

    public void signIn(final String mail, String pass, final ModelCallback callback) {
        firebaseHelper.signInToFirebase(mail, pass, new FirebaseAuthCallback() {
            @Override
            public void onSuccess() {
                sharedPrefHelper.setMail(mail);
                firebaseHelper.getFirebaseToken(new JsonDataCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            String token = response.getString("token");

                            if (validateFirebaseToken(token)) {
                                serverHelper.sendFirebaseTokenToServer(token, new SimpleCallback() {
                                    @Override
                                    public void onSuccess() {
                                        //TODO do right action
                                    }

                                    @Override
                                    public void onFailure() {
                                        //TODO do right action
                                    }
                                });

                            } else {
                                //TODO some problem with token ::: do right action
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
                callback.onSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure();
            }
        });
    }

    private boolean validateFirebaseToken(String token) {
        return false;
    }

    public void signOut() {
        firebaseHelper.signOutFromFirebase();
        //TODO sign out from AWS
        //TODO clear local data
        //TODO update UI
    }


}
