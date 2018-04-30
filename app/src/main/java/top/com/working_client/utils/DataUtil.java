package top.com.working_client.utils;

/**
 * Created by ttop on 2018/2/18.
 */

public class DataUtil {
    /**
     * 转换为xxxx.xx.xx
     *
     * @param y 年
     * @param m 月
     * @param d 日
     * @return
     */
    public static String convertNextDay(int y, int m, int d) {
        int year = y;
        int mounth = m;
        int day = d;

        if (mounth == 1 || mounth == 3 || mounth == 5 || mounth == 7 || mounth == 8 || mounth == 10 || mounth == 12) {
            if (day == 31) {
                day = 1;
                if (mounth == 12) {
                    year += 1;
                    mounth = 1;
                    return new String(year + "." + mounth + "." + day);
                } else {
                    mounth += 1;
                    return new String(year + "." + mounth + "." + day);
                }
            }
        } else if (mounth == 4 || mounth == 6 || mounth == 9 || mounth == 11) {
            if (day == 31) {
                day = 1;
                mounth += 1;
                return new String(year + "." + mounth + "." + day);
            }
        } else {
            if (year % 4 == 0 && day == 29) {
                mounth = 3;
                day = 1;
                return new String(year + "." + mounth + "." + day);
            } else if ((year % 4) != 0 && day == 28) {
                mounth = 3;
                day = 1;
                return new String(year + "." + mounth + "." + day);
            }
        }

        day += 1;
        return new String(year + "." + mounth + "." + day);
    }

    public static String formatDate(String date) {
        StringBuilder sb = new StringBuilder();
        int i = date.indexOf(".");
        int j = date.lastIndexOf(".");
        sb.append(date.substring(0, i) + "." + date.substring(i + 1, j) + "." + date.substring(j + 1));
        return sb.toString();
    }

    /**
     * 将xxxx.xx.xx的格式转换为[0] 年 [1]月 [2]日
     *
     * @param date
     * @return
     */
    public static int[] formatDateArray(String date) {
        int i = date.indexOf(".");
        int j = date.lastIndexOf(".");
        int year = Integer.parseInt(date.substring(0, i));
        int mounth = Integer.parseInt(date.substring(i + 1, j));
        int day = Integer.parseInt(date.substring(j + 1));
        int[] nums = {year, mounth, day};
        return nums;
    }
}

