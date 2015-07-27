package kr.co.sangcomz.whoami2.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import kr.co.sangcomz.whoami2.R;

/**
 * Created by sangcomz on 2015-07-23.
 */
public class Dialogs {


    public void DialogHobby(Context context) {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_hobby);

        TextView txtAdd = (TextView)dialog.findViewById(R.id.btn_add);
        TextView txtcancel = (TextView)dialog.findViewById(R.id.btn_cancel);

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
