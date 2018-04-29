package top.com.working_client.utils;

import android.content.Context;
import android.util.Log;

import com.avos.avoscloud.AVObject;

import java.util.HashMap;
import java.util.List;

import top.com.working_client.MainActivity;
import top.com.working_client.database.DaoInterface;
import top.com.working_client.database.WorkingSheet;
import top.com.working_client.database.WorkingSheetDao_new;
import top.com.working_client.database.WorkingTime;
import top.com.working_client.database.WorkingTimeDao;

/**
 * Created by ttop on 2018/2/20.
 */

/**
 * 将List<AVObject> list转化为map对象 并储存在数据库中
 */
public class ConverUtil {
    private static DaoInterface mDao;
    public static HashMap<String, String> data = new HashMap<>();

    /**
     *
     * @param list
     * @param ctx
     * @return
     */
    public static WorkingTime converInternetTime(List<AVObject> list,Context ctx){
        mDao=null;
        mDao=new WorkingTimeDao(ctx);
        mDao.clearAll();
        Log.e(MainActivity.TAG, "数据库删除成功");
        WorkingTime wt=new WorkingTime();
        if(null!=list){
            for (int i = 0; i < list.size(); i++) {
                AVObject avObject = list.get(i);
                wt.setMorning_go((String) avObject.get("morning_go"));
                wt.setMorning_leave((String) avObject.get("morning_leave"));
                wt.setAfertnoon_gotime((String) avObject.get("afertnoon_gotime"));
                wt.setAfertnon_leavetime((String) avObject.get("afertnon_leavetime"));
                wt.setEvening_gotime((String) avObject.get("evening_gotime"));
                wt.setEvening_leavetime((String) avObject.get("evening_leavetime"));
                wt.setBai_gotime((String) avObject.get("bai_gotime"));
                wt.setBai_leavetime((String) avObject.get("bai_leavetime"));
                mDao.add(wt);
                Log.e(MainActivity.TAG, "数据库储存成功");
            }
        }
        return wt;
    }
    /**
     * @param list
     * @param ctx
     * @return
     */
    public static HashMap<String, String> converInternetData(List<AVObject> list, Context ctx) {
        StringBuffer sb = new StringBuffer();
            mDao = new WorkingSheetDao_new(ctx);
        mDao.clearAll();
        Log.e(MainActivity.TAG, "数据库删除成功");
        WorkingSheet ws = new WorkingSheet();
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                AVObject avObject = list.get(i);
                String mouth = (String) avObject.get("mouth");
                String count = (String) avObject.get("count");
                int num_count = Integer.parseInt(count);
                int num_mouth = Integer.parseInt(mouth);
                ws.setCount(num_count);
                ws.setMouth(num_mouth);

                for (int j = 1; j <= num_count; j++) {
                    String work=null;

                        work = converType((String) avObject.get(j + ""));
                    sb.delete(0, sb.length());
                    sb.append("2018." + mouth + "." + j);
                    data.put(sb.toString(), work);
                }
                    ws.setDay1(converType((String) avObject.get("1")));
                    ws.setDay2(converType((String) avObject.get("2")));
                    ws.setDay3(converType((String) avObject.get("3")));
                    ws.setDay4(converType((String) avObject.get("4")));
                    ws.setDay5(converType((String) avObject.get("5")));
                    ws.setDay6(converType((String) avObject.get("6")));
                    ws.setDay7(converType((String) avObject.get("7")));
                    ws.setDay8(converType((String) avObject.get("8")));
                    ws.setDay9(converType((String) avObject.get("9")));
                    ws.setDay10(converType((String) avObject.get("10")));
                    ws.setDay11(converType((String) avObject.get("11")));
                    ws.setDay12(converType((String) avObject.get("12")));
                    ws.setDay13(converType((String) avObject.get("13")));
                    ws.setDay14(converType((String) avObject.get("14")));
                    ws.setDay15(converType((String) avObject.get("15")));
                    ws.setDay16(converType((String) avObject.get("16")));
                    ws.setDay17(converType((String) avObject.get("17")));
                    ws.setDay18(converType((String) avObject.get("18")));
                    ws.setDay19(converType((String) avObject.get("19")));
                    ws.setDay20(converType((String) avObject.get("20")));
                    ws.setDay21(converType((String) avObject.get("21")));
                    ws.setDay22(converType((String) avObject.get("22")));
                    ws.setDay23(converType((String) avObject.get("23")));
                    ws.setDay24(converType((String) avObject.get("24")));
                    ws.setDay25(converType((String) avObject.get("25")));
                    ws.setDay26(converType((String) avObject.get("26")));
                    ws.setDay27(converType((String) avObject.get("27")));
                    ws.setDay28(converType((String) avObject.get("28")));
                    if (num_count > 28) {
                        ws.setDay29(converType((String) avObject.get("29")));
                    }
                    if (num_count > 29) {
                        ws.setDay30(converType((String) avObject.get("30")));
                    }
                    if (num_count > 30)
                        ws.setDay31(converType((String) avObject.get("31")));

                mDao.add(ws);
                Log.e(MainActivity.TAG, "数据库储存成功");
            }

        }
        mDao = null;
        return data;
    }

    /**
     * @param list
     * @param ctx
     * @param
     * @return
     */
    public static HashMap<String, String> converDatabaseData(List<WorkingSheet> list, Context ctx) {

            mDao = new WorkingSheetDao_new(ctx);
        for (int i = 0; i < list.size(); i++) {
            WorkingSheet workingSheet = list.get(i);
            int mouth = workingSheet.getMouth();
            int count = workingSheet.getCount();
                data.put(2018 + "." + mouth + ".1", workingSheet.getDay1());
                data.put(2018 + "." + mouth + ".2", workingSheet.getDay2());
                data.put(2018 + "." + mouth + ".3", workingSheet.getDay3());
                data.put(2018 + "." + mouth + ".4", workingSheet.getDay4());
                data.put(2018 + "." + mouth + ".5", workingSheet.getDay5());
                data.put(2018 + "." + mouth + ".6", workingSheet.getDay6());
                data.put(2018 + "." + mouth + ".7", workingSheet.getDay7());
                data.put(2018 + "." + mouth + ".8", workingSheet.getDay8());
                data.put(2018 + "." + mouth + ".9", workingSheet.getDay9());
                data.put(2018 + "." + mouth + ".10", workingSheet.getDay10());
                data.put(2018 + "." + mouth + ".11", workingSheet.getDay11());
                data.put(2018 + "." + mouth + ".12", workingSheet.getDay12());
                data.put(2018 + "." + mouth + ".13", workingSheet.getDay13());
                data.put(2018 + "." + mouth + ".14", workingSheet.getDay14());
                data.put(2018 + "." + mouth + ".15", workingSheet.getDay15());
                data.put(2018 + "." + mouth + ".16", workingSheet.getDay16());
                data.put(2018 + "." + mouth + ".17", workingSheet.getDay17());
                data.put(2018 + "." + mouth + ".18", workingSheet.getDay18());
                data.put(2018 + "." + mouth + ".19", workingSheet.getDay19());
                data.put(2018 + "." + mouth + ".20", workingSheet.getDay20());
                data.put(2018 + "." + mouth + ".21", workingSheet.getDay21());
                data.put(2018 + "." + mouth + ".22", workingSheet.getDay22());
                data.put(2018 + "." + mouth + ".23", workingSheet.getDay23());
                data.put(2018 + "." + mouth + ".24", workingSheet.getDay24());
                data.put(2018 + "." + mouth + ".25", workingSheet.getDay25());
                data.put(2018 + "." + mouth + ".26", workingSheet.getDay26());
                data.put(2018 + "." + mouth + ".27", workingSheet.getDay27());
                data.put(2018 + "." + mouth + ".28", workingSheet.getDay28());
                if (count > 28) {
                    data.put(2018 + "." + mouth + ".29", workingSheet.getDay29());
                }
                if (count > 29) {
                    data.put(2018 + "." + mouth + ".30", workingSheet.getDay30());
                }
                if (count > 30) {
                    data.put(2018 + "." + mouth + ".31", workingSheet.getDay31());
                }

        }
        mDao = null;
        return data;
    }

    public static String converType(String type) {
        switch (type) {
            case "1":
                return "晚";
            case "2":
                return "中";
            case "3":
                return "白";
            case "4":
                return "早";
            case "休":
                return "休";
        }
        return "错误";
    }
}