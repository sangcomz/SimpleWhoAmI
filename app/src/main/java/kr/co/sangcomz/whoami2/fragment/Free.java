package kr.co.sangcomz.whoami2.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.sangcomz.whoami2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Free extends Fragment {
//import android.support.v4.app.Fragment; 변경해줘야함

    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;

    public Free() {
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
        setupRecyclerView(recyclerView);

        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public static class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final CardView cardView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                cardView = (CardView) view.findViewById(R.id.card_view);
            }
        }
        public RecyclerViewAdapter(Context context) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.cardView
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }


        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
