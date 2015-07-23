package kr.co.sangcomz.whoami2.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import kr.co.sangcomz.whoami2.R;

/**
 * Created by sangcomz on 2015-07-23.
 */
public class Dialogs {


    public void DialogHobby(Context context) {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_hobby);

        dialog.show();

    }
}
