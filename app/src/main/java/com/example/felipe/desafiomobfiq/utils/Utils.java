package com.example.felipe.desafiomobfiq.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by felipe on 6/25/17.
 */

public class Utils {

    public final static String API_URL = "https://desafio.mobfiq.com.br";
    public final static String MOBIFQ = "MOBIFIQ";
    private final static String ERROR_MESSAGE = "Something went wrong.";
    final public static int GENERIC_RESOURCE_ID = 1;

    public static void onErrorHandler(Context context, String message){
        Log.e(Utils.MOBIFQ, String.format("Error: %s", message));
        Toast toast = Toast.makeText(context, ERROR_MESSAGE, Toast.LENGTH_LONG);
        toast.show();
    }

}
