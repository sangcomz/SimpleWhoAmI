package kr.co.sangcomz.whoami2.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.sangcomz.whoami2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Hobby extends Fragment {
    ListView listView;


    //좋지 않은 패턴!
    public static HobbyAdapter hobbyAdapter; //어댑터 선언
    public static ArrayList<String> hobbys;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment inflate 설명 :::: http://aroundck.tistory.com/39
        View rootView = inflater.inflate(R.layout.fragment_hobby, container, false);

        listView = (ListView)rootView.findViewById(R.id.lv);

        hobbys = new ArrayList<>();

        hobbyAdapter = new HobbyAdapter(getActivity(), hobbys);

        listView.setAdapter(hobbyAdapter);

        return rootView;
    }

    //어댑터뷰 보면 좋을것같은 자료 http://www.slideshare.net/yjaeseok/20140808-android-study12adapterview
    public class HobbyAdapter extends BaseAdapter{
        ArrayList<String> hobbyList;
        ViewHolder holder;
        private LayoutInflater inflater;

        HobbyAdapter(Context context, ArrayList<String> hobbys){
            this.hobbyList = hobbys;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return hobbyList.size();
        }

        @Override
        public Object getItem(int i) {
            return hobbyList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            //view는 재사용이 가능하도록 설계
            if(view == null)
            {
                view = inflater.inflate(R.layout.hobby_list_item, viewGroup, false);
                holder = new ViewHolder();
                holder.txtNum = (TextView)view.findViewById(R.id.txt_num);
                holder.txtHobby = (TextView)view.findViewById(R.id.txt_hobby);
                view.setTag(holder); // 뷰는 setTag로 Object타입의 인스턴스를 저장할 수 있음 ViewHolder 패턴을 구성하는 핵심원리
            } else {
                holder = (ViewHolder) view.getTag();// setTag로 넣어두었던 ViewHolder 인스턴스를 가져온다.
            }

            holder.txtNum.setText("");
            holder.txtHobby.setText("");

            holder.txtNum.setText(String.valueOf(position + 1));
            holder.txtHobby.setText(hobbyList.get(position));

            return view;
        }

        //ViewHolder 설명 :::: http://theeye.pe.kr/archives/1253
        //http://bellgori.tistory.com/entry/Android-pattern-01-ViewHolder-pattern
        private class ViewHolder {
            private TextView txtNum;
            private TextView txtHobby;
        }
    }
}
