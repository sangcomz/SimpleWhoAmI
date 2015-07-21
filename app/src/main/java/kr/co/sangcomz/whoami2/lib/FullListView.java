package kr.co.sangcomz.whoami2.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import kr.co.sangcomz.whoami2.fragment.Hobby;

/**
 * Created by 석원 on 2015-07-21.
 */
public class FullListView extends ListView {
    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View view = (View) getParent();

        final int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        System.out.println("view.widthSize :::: " + view.getMeasuredWidth());
        System.out.println("view.heightSize :::: " + view.getMeasuredHeight());


        System.out.println("view.widthSize :::: " + view.getWidth());
        System.out.println("view.heightSize :::: " + view.getHeight());

        System.out.println("widthSize :::: " + widthSize);
        System.out.println("heightSize :::: " + heightSize);

        if (view.getMeasuredHeight() != 0)
            setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }
}
