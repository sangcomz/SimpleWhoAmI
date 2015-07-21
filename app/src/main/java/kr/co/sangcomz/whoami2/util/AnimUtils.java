package kr.co.sangcomz.whoami2.util;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;

/**
 * Created by pmueller on 24.6.15.
 */
public class AnimUtils {

    public static final FastOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR
            = new FastOutSlowInInterpolator();

    public static final FastOutLinearInInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR
            = new FastOutLinearInInterpolator();

    public static final LinearOutSlowInInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR
            = new LinearOutSlowInInterpolator();

}
