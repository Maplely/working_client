package top.com.working_client.database;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ttop on 2018/3/13.
 */
@DatabaseTable(tableName = "workingtime")
public class WorkingTime {
    public WorkingTime() {
    }
    @DatabaseField(columnName = "morning_go", dataType = DataType.STRING, generatedId = true)
    private String morning_go;
    @DatabaseField(columnName = "morning_leave", dataType = DataType.STRING, generatedId = true)
    private String morning_leave;
    @DatabaseField(columnName = "afertnoon_gotime", dataType = DataType.STRING, generatedId = true)
    private String afertnoon_gotime;
    @DatabaseField(columnName = "afertnon_leavetime", dataType = DataType.STRING, generatedId = true)
    private String afertnon_leavetime;
    @DatabaseField(columnName = "evening_gotime", dataType = DataType.STRING, generatedId = true)
    private String evening_gotime;
    @DatabaseField(columnName = "evening_leavetime", dataType = DataType.STRING, generatedId = true)
    private String evening_leavetime;
    @DatabaseField(columnName = "bai_gotime", dataType = DataType.STRING, generatedId = true)
    private String bai_gotime;
    @DatabaseField(columnName = "bai_leavetime", dataType = DataType.STRING, generatedId = true)
    private String bai_leavetime;

    public String getMorning_go() {
        return morning_go;
    }

    public void setMorning_go(String morning_go) {
        this.morning_go = morning_go;
    }

    public String getMorning_leave() {
        return morning_leave;
    }

    public void setMorning_leave(String morning_leave) {
        this.morning_leave = morning_leave;
    }

    public String getAfertnoon_gotime() {
        return afertnoon_gotime;
    }

    public void setAfertnoon_gotime(String afertnoon_gotime) {
        this.afertnoon_gotime = afertnoon_gotime;
    }

    public String getAfertnon_leavetime() {
        return afertnon_leavetime;
    }

    public void setAfertnon_leavetime(String afertnon_leavetime) {
        this.afertnon_leavetime = afertnon_leavetime;
    }

    public String getEvening_gotime() {
        return evening_gotime;
    }

    public void setEvening_gotime(String evening_gotime) {
        this.evening_gotime = evening_gotime;
    }

    public String getEvening_leavetime() {
        return evening_leavetime;
    }

    public void setEvening_leavetime(String evening_leavetime) {
        this.evening_leavetime = evening_leavetime;
    }

    public String getBai_gotime() {
        return bai_gotime;
    }

    public void setBai_gotime(String bai_gotime) {
        this.bai_gotime = bai_gotime;
    }

    public String getBai_leavetime() {
        return bai_leavetime;
    }

    public void setBai_leavetime(String bai_leavetime) {
        this.bai_leavetime = bai_leavetime;
    }
}