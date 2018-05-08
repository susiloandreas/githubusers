package co.webtra.githubusers.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by andreassusilo on 08/05/18.
 */

public class DateUtil {

    public static String getFormatedDate(String originFormat, String destinationFormat, String date){
        String time = null;
        try {
            DateFormat original = new SimpleDateFormat(originFormat);
            DateFormat formatted = new SimpleDateFormat(destinationFormat);
            time = formatted.format(original.parse(date));
        } catch (ParseException e) {
            Log.e("f.ninaber", "Error format : " + e);
        }
        return time;
    }

}
