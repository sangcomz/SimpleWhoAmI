package kr.co.sangcomz.whoami2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.sangcomz.whoami2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Album extends Fragment {
//import android.support.v4.app.Fragment; 변경해줘야함

    public Album() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }


}
