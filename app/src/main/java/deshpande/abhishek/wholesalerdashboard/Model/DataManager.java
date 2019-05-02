package deshpande.abhishek.wholesalerdashboard.Model;

import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.FirebaseAuthCallback;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.ModelCallback;
import deshpande.abhishek.wholesalerdashboard.Model.FirebaseUtils.FirebaseHelper;

public class DataManager {

    private static DataManager mInstance;
    private FirebaseHelper firebaseHelper;

    private DataManager() {
        mInstance = this;
        if (firebaseHelper == null) {
            firebaseHelper = new FirebaseHelper();
        }
    }

    public static synchronized DataManager getmInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
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

    public void signIn(String mail, String pass, final ModelCallback callback) {
        firebaseHelper.signInToFirebase(mail, pass, new FirebaseAuthCallback() {
            @Override
            public void onSuccess() {
                //TODO save mail and pass to sharedPref
                //TODO get Token from firebase and send to AWS
                callback.onSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure();
            }
        });
    }

    public void signOut() {
        firebaseHelper.signOutFromFirebase();
        //TODO sign out from AWS
        //TODO clear local data
        //TODO update UI
    }


}
