package com.shian.app.shian_cemetery.tools;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zm.
 */

public class CheckUtils {

    /**
     * 是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 是否是电话号码
     *
     * @param input
     * @return
     */
    public static boolean isPhoneNumber(CharSequence input) {
        if (input == null) {
            return false;
        } else {
            String regex = "(\\+\\d+)?1[3458]\\d{9}$";
            return Pattern.matches(regex, input);
        }
    }


    /**
     * 检查权限
     * @param permition
     * @param permitionList
     * @return
     */
    public static boolean checkPermition(String permition, List<String> permitionList) {
        boolean isPermition = false;
        for (String data : permitionList) {
            if (data.equals(permition)) {
                isPermition = true;
                break;
            }
        }
        return isPermition;
    }
}
