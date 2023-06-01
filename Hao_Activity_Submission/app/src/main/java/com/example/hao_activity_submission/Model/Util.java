package com.example.hao_activity_submission.Model;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public static void showToast(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
        System.out.println(msg);
    }
}
