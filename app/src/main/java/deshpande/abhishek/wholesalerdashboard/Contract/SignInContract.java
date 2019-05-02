package deshpande.abhishek.wholesalerdashboard.Contract;

import android.text.Editable;

public interface SignInContract {
    interface View {
        public void showSnackBar(String message);

        public void gotoHomeActivity();

        public void setProgressbarVisibility(boolean isVisible);
    }

    interface Presenter {
        public void signIn(Editable mail, Editable pass);
    }
}
