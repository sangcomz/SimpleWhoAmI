package kr.co.sangcomz.whoami2.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import kr.co.sangcomz.whoami2.R;

/**
 * Created by 석원 on 2015-07-21.
 */
public class FullGridView extends GridView {
    public FullGridView(Context context) {
        super(context);
    }

    public FullGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
////
////        View view = (View) getParent().getParent().getParent();
////
////        int heightDp = (int) getResources().getDimension(R.dimen.list_item_height);
////        int marginDp = (int) getResources().getDimension(R.dimen.list_view_margin);
////
////        setMeasuredDimension(view.getMeasuredWidth() - (marginDp*2), (heightDp) * getAdapter().getCount());
//    }
}
