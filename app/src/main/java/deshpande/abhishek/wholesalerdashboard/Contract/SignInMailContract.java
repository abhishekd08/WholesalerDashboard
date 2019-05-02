package deshpande.abhishek.wholesalerdashboard.Contract;

import android.text.Editable;

public interface SignInMailContract {

    interface View {
        public void showSnackBar(String msg);

        public void setProgressbarVisibility(boolean isVisible);

        public void gotoSignInActivity();
    }

    interface Presenter {
        public void verifyMail(Editable mailEditable);
    }

}
