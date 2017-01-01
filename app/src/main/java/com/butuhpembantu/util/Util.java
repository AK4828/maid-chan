package com.butuhpembantu.util;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;

import com.butuhpembantu.application.ButuhPembantuApplication;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by akm on 1/1/17.
 */

public class Util {

    private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static DateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static String base64(String text) {
        return new String(Base64.encode(text.trim().getBytes(), Base64.NO_WRAP));
    }

    public static String generatePassword(String password) {
        return base64(base64(password));
    }

    public static String monetaryFormat(double price) {
        DecimalFormat df = new DecimalFormat("#,###");
        return "Rp " + df.format(price);
    }

    public static String currencyFormat(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }

    public static String decimalFormat(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }

    public static String percentFormat(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("####");
        return decimalFormat.format(price) + "%";
    }

    public static String dateFormat(Date date) {
        return dateFormat.format(date);
    }

    public static String timeFormat(Date date) {
        return timeFormat.format(date);
    }

    public static String dateTimeFormat(Date date) {
        return dateTimeFormat.format(date);
    }

    public static File getCacheDirectory() {
        String state = Environment.getExternalStorageState();
        Context context = ButuhPembantuApplication.getInstance();
        File directory;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            directory = new File(context.getExternalCacheDir(), "");
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            directory = new File(context.getCacheDir(), "");
        } else {
            directory = new File(context.getCacheDir(), "");
        }

        return directory;
    }
}
