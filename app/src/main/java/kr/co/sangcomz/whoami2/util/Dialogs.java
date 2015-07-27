package kr.co.sangcomz.whoami2.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
<<<<<<< HEAD
import android.widget.Button;
=======
import android.widget.EditText;
>>>>>>> 4b1b3fb89fd38eff85fc7f46ddb605ca2af8dfb0
import android.widget.TextView;

import kr.co.sangcomz.whoami2.MainActivity;
import kr.co.sangcomz.whoami2.R;
import kr.co.sangcomz.whoami2.fragment.Hobby;

/**
 * Created by sangcomz on 2015-07-23.
 */
public class Dialogs {
    public void DialogHobby(Context context) {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_hobby);

<<<<<<< HEAD
        TextView txtAdd = (TextView)dialog.findViewById(R.id.btn_add);
        TextView txtcancel = (TextView)dialog.findViewById(R.id.btn_cancel);

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
=======
        final EditText etHobby = (EditText) dialog.findViewById(R.id.et_hobby);
        TextView txtAdd = (TextView) dialog.findViewById(R.id.txt_add);
        TextView txtCancel = (TextView) dialog.findViewById(R.id.txt_cancel);

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etHobby.getText().toString().length() > 0) {
                    MainActivity.hobbys.add(etHobby.getText().toString());
                    Hobby.hobbyAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
>>>>>>> 4b1b3fb89fd38eff85fc7f46ddb605ca2af8dfb0

        txtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
