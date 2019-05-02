package deshpande.abhishek.wholesalerdashboard.Presenter;

import android.text.Editable;

import deshpande.abhishek.wholesalerdashboard.Contract.SignInContract;
import deshpande.abhishek.wholesalerdashboard.GeneralUtils.Callbacks.ModelCallback;
import deshpande.abhishek.wholesalerdashboard.Model.DataManager;

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View view;
    private DataManager dataManager;

    public SignInPresenter(SignInContract.View view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void signIn(Editable mailEditable, Editable passEditable) {
        String mail = mailEditable.toString().trim();
        String pass = passEditable.toString().trim();
        if (validateData(mail, pass)) {
            dataManager.signIn(mail, pass, new ModelCallback() {
                @Override
                public void onSuccess() {
                    view.setProgressbarVisibility(false);
                    view.gotoHomeActivity();
                }

                @Override
                public void onFailure() {
                    view.setProgressbarVisibility(false);
                    //TODO show right message on snackbar
                }
            });
        } else {
            view.setProgressbarVisibility(false);
            //TODO parse validatData() response ::: show right message
        }
    }

    private boolean validateData(String mail, String pass) {
        //TODO validate mail n pass

        return false;
    }
}
