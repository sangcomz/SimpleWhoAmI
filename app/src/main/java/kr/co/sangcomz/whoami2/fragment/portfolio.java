package kr.co.sangcomz.whoami2.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.co.sangcomz.whoami2.R;
import kr.co.sangcomz.whoami2.bean.PortfolioData;

/**
 * A simple {@link Fragment} subclass.
 */
public class portfolio extends Fragment {
//import android.support.v4.app.Fragment; 변경해줘야함

    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<PortfolioData> portfolioDatas = new ArrayList<>();

    public portfolio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rv = inflater.inflate(
                R.layout.fragment_free, container, false);
        recyclerView = (RecyclerView) rv.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        portfolioDatas.add(new PortfolioData(R.mipmap.portfolio1, "BeOnTime", "지각비를 관리하는 앱"));
        portfolioDatas.add(new PortfolioData(R.mipmap.portfolio2, "메아리", "가속도 센서, 위치, 알람을 이용한 메모장 앱"));
        portfolioDatas.add(new PortfolioData(R.mipmap.portfolio3, "골라줘", "2~4가지를 선택해주는 앱"));
        portfolioDatas.add(new PortfolioData(R.mipmap.portfolio4, "골라조", "영화, 장소, 음식, 일반적인것을 선택해주는 앱"));
        setupRecyclerView(recyclerView);

        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), portfolioDatas);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public static class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        ArrayList<PortfolioData> portfolioDatas = new ArrayList<>();
        Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final ImageView imgPortfolio;
            public final TextView title;
            public final TextView content;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                imgPortfolio = (ImageView) view.findViewById(R.id.img_portfolio);
                title = (TextView) view.findViewById(R.id.txt_name);
                content = (TextView) view.findViewById(R.id.txt_contents);
            }
        }

        public RecyclerViewAdapter(Context context, ArrayList<PortfolioData> portfolioDatas) {
            this.context = context;
            this.portfolioDatas = portfolioDatas;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Glide.with(context).load(portfolioDatas.get(position).getImageResource()).centerCrop().into(holder.imgPortfolio);
            holder.content.setText(portfolioDatas.get(position).getContent());
            holder.title.setText(portfolioDatas.get(position).getTitle());
        }


        @Override
        public int getItemCount() {
            return portfolioDatas.size();
        }
    }
}
