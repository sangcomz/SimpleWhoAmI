package kr.co.sangcomz.whoami2.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.sangcomz.whoami2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Hobby extends Fragment {
//import android.support.v4.app.Fragment; 변경해줘야함

    ListView listView;
    ArrayList<String> hobbys;

    public Hobby() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hobby, container, false);

        listView = (ListView)rootView.findViewById(R.id.lv);


        hobbys = new ArrayList<String>();
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");
        hobbys.add("야구");
        hobbys.add("농구");
        hobbys.add("축구");



        listView.setAdapter(new HobbyAdapter(getActivity(), hobbys));

        return rootView;
    }




    public class HobbyAdapter extends BaseAdapter{
        ArrayList<String> hobbys;
        ViewHolder holder;
        private LayoutInflater inflater;
        int height = 0;

        HobbyAdapter(Context context, ArrayList<String> hobbys){
            this.hobbys = hobbys;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return hobbys.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public int getViewHeight(){return height;}



        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view == null)
            {
                view = inflater.inflate(R.layout.hobby_list_item, viewGroup, false);
                holder = new ViewHolder();
                holder.txtNum = (TextView)view.findViewById(R.id.txt_num);
                holder.txtHobby = (TextView)view.findViewById(R.id.txt_hobby);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.txtNum.setText("");
            holder.txtHobby.setText("");


            holder.txtNum.setText(String.valueOf(position + 1));
            holder.txtHobby.setText(hobbys.get(position));

            height = view.getMeasuredHeight();

            return view;
        }

        private class ViewHolder {
            private TextView txtNum;
            private TextView txtHobby;

        }
    }

    private void DialogHobby(Context context){
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_hobby);

        dialog.show();

    }


}
