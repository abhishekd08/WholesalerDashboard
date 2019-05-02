package deshpande.abhishek.wholesalerdashboard.Model.SharedPrefUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefHelper {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = this.sharedPreferences.edit();
    }

    public void setMail(String mail) {
        editor.putString("mail", mail);
        editor.commit();
    }

    public String getMail() {
        return sharedPreferences.getString("mail", "");
    }

}
