package deshpande.abhishek.wholesalerdashboard.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import deshpande.abhishek.wholesalerdashboard.Contract.SignInMailContract;
import deshpande.abhishek.wholesalerdashboard.R;

public class SignInMailActivity extends AppCompatActivity implements SignInMailContract.View {

    private TextInputEditText mailEdittext;
    private ConstraintLayout parentLayout;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_mail);

        parentLayout = findViewById(R.id.signinmail_parentlayout);
        mailEdittext = findViewById(R.id.signinmail_edittext);

        nextBtn = findViewById(R.id.signinmail_nextbtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO call presenter method
            }
        });
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressbarVisibility(boolean isVisible) {
        if (isVisible) {
            //TODO set progressBar visible
        } else {
            //TODO set progressbar inVisible
        }
    }

    @Override
    public void gotoSignInActivity() {
        Intent intent = new Intent(SignInMailActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
