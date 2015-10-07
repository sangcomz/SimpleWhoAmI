package kr.co.sangcomz.whoami2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.sangcomz.whoami2.R;


/**`
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
//import android.support.v4.app.Fragment; 변경해줘야함


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        return rootView;
    }


}
