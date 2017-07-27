package com.amazon.aka.lssh.flickrdemo.utils;

import android.content.Context;
import android.content.Intent;

import com.amazon.aka.lssh.flickrdemo.view.ShowActivity;

/**
 * Created by lssh on 7/26/17.
 */

public class IntentUtils {

    public static Intent getShowViewIntent(Context context, String url) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra("url", url);
        return intent;
    }
}
