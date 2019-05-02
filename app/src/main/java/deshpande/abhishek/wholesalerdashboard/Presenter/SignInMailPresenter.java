package deshpande.abhishek.wholesalerdashboard.Presenter;

import android.content.Intent;
import android.text.Editable;

import deshpande.abhishek.wholesalerdashboard.Activity.SignInMailActivity;
import deshpande.abhishek.wholesalerdashboard.Contract.SignInMailContract;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.ModelCallback;
import deshpande.abhishek.wholesalerdashboard.Model.DataManager;

public class SignInMailPresenter implements SignInMailContract.Presenter {

    private SignInMailContract.View view;
    private DataManager dataManager;

    public SignInMailPresenter(SignInMailContract.View view, DataManager dataManager) {
        this.view = view;
    }

    @Override
    public void verifyMail(Editable mailEditable) {
        view.setProgressbarVisibility(true);
        String mail = mailEditable.toString().trim();
        if (validateEmail(mail)) {
            dataManager.checkIfMailExists(mail, new ModelCallback() {
                @Override
                public void onSuccess() {
                    view.setProgressbarVisibility(false);
                    view.gotoSignInActivity();
                }

                @Override
                public void onFailure() {
                    view.setProgressbarVisibility(false);
                    view.showSnackBar("Wrong Email !"); //extract string resource :: require context
                }
            });

        } else {
            view.showSnackBar("Enter right Email");
        }
    }

    private boolean validateEmail(String mail) {

        //TODO validate mail

        return true;
    }

}