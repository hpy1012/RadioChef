package com.hpy1012.radiochef.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public class ShowUtil {
    public static void toastShow(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void snackbarShow(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
    }
}
