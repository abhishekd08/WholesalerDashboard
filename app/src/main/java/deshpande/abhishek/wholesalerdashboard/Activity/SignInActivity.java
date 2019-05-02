package deshpande.abhishek.wholesalerdashboard.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import deshpande.abhishek.wholesalerdashboard.Contract.SignInContract;
import deshpande.abhishek.wholesalerdashboard.Presenter.SignInPresenter;
import deshpande.abhishek.wholesalerdashboard.R;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private ConstraintLayout parentLayout;
    private EditText mailEdittext, passEdittext;
    private Button signInBtn;

    private SignInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mailEdittext = findViewById(R.id.signin_mailedittext);
        passEdittext = findViewById(R.id.signin_passedittext);
        parentLayout = findViewById(R.id.signin_parentlayout);

        signInBtn = findViewById(R.id.signin_signinbutton);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO call presenter method
            }
        });

    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void gotoHomeActivity() {
        Intent intent = new Intent(SignInActivity.this, SignInPresenter.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setProgressbarVisibility(boolean isVisible) {
        if (isVisible) {
            //TODO set progressbar visible
        } else {
            //TODO set progressbar inVisible
        }
    }
}
