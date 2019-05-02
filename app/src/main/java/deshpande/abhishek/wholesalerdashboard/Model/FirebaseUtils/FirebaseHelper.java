package deshpande.abhishek.wholesalerdashboard.Model.FirebaseUtils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import org.json.JSONException;
import org.json.JSONObject;

import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.FirebaseAuthCallback;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.JsonDataCallback;

public class FirebaseHelper {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public FirebaseHelper() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void checkMailExists(String mail, final FirebaseAuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(mail, "CONSTANT_PASS_631_181")
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthInvalidUserException) {
                            //Email not registered
                            callback.onSuccess();
                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            //Email registered
                            callback.onFailure(e);
                        } else {
                            Log.e("FirebaseHelper", "Exception not handled !");
                        }
                    }
                });
    }

    public void signInToFirebase(String mail, String pass, final FirebaseAuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(mail, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //TODO handler universal exceptions here
                        //TODO and return callback with specific exception
                    }
                });
    }

    public void signOutFromFirebase() {
        firebaseAuth.signOut();
    }

    public void getFirebaseToken(final JsonDataCallback callback){
        if (firebaseAuth == null){
            firebaseUser = firebaseAuth.getCurrentUser();
        }
        firebaseUser.getIdToken(true)
                .addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                    @Override
                    public void onSuccess(GetTokenResult getTokenResult) {
                        try{
                            JSONObject response = new JSONObject();
                            response.put("token",getTokenResult.getToken());
                            callback.onSuccess(response);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //TODO handler universal exception here
                        callback.onFailure(e);
                    }
                });
    }

}
