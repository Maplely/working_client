package top.com.working_client.database;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by ttop on 2018/2/20.
 */
@DatabaseTable(tableName = "WorkingSheet")
public class WorkingSheet {
    @DatabaseField(columnName = "_id", dataType = DataType.INTEGER, generatedId = true)
    private int id;
    @DatabaseField(columnName = "mouth", dataType = DataType.INTEGER)
    private int mouth;
    @DatabaseField(columnName = "count", dataType = DataType.INTEGER)
    private int count;
    @DatabaseField(columnName = "1", dataType = DataType.STRING)
    private String day1;
    @DatabaseField(columnName = "2", dataType = DataType.STRING)
    private String day2;
    @DatabaseField(columnName = "3", dataType = DataType.STRING)
    private String day3;
    @DatabaseField(columnName = "4", dataType = DataType.STRING)
    private String day4;
    @DatabaseField(columnName = "5", dataType = DataType.STRING)
    private String day5;
    @DatabaseField(columnName = "6", dataType = DataType.STRING)
    private String day6;
    @DatabaseField(columnName = "7", dataType = DataType.STRING)
    private String day7;
    @DatabaseField(columnName = "8", dataType = DataType.STRING)
    private String day8;
    @DatabaseField(columnName = "9", dataType = DataType.STRING)
    private String day9;
    @DatabaseField(columnName = "10", dataType = DataType.STRING)
    private String day10;
    @DatabaseField(columnName = "11", dataType = DataType.STRING)
    private String day11;
    @DatabaseField(columnName = "12", dataType = DataType.STRING)
    private String day12;
    @DatabaseField(columnName = "13", dataType = DataType.STRING)
    private String day13;
    @DatabaseField(columnName = "14", dataType = DataType.STRING)
    private String day14;
    @DatabaseField(columnName = "15", dataType = DataType.STRING)
    private String day15;
    @DatabaseField(columnName = "16", dataType = DataType.STRING)
    private String day16;
    @DatabaseField(columnName = "17", dataType = DataType.STRING)
    private String day17;
    @DatabaseField(columnName = "18", dataType = DataType.STRING)
    private String day18;
    @DatabaseField(columnName = "19", dataType = DataType.STRING)
    private String day19;
    @DatabaseField(columnName = "20", dataType = DataType.STRING)
    private String day20;
    @DatabaseField(columnName = "21", dataType = DataType.STRING)
    private String day21;
    @DatabaseField(columnName = "22", dataType = DataType.STRING)
    private String day22;
    @DatabaseField(columnName = "23", dataType = DataType.STRING)
    private String day23;
    @DatabaseField(columnName = "24", dataType = DataType.STRING)
    private String day24;
    @DatabaseField(columnName = "25", dataType = DataType.STRING)
    private String day25;
    @DatabaseField(columnName = "26", dataType = DataType.STRING)
    private String day26;
    @DatabaseField(columnName = "27", dataType = DataType.STRING)
    private String day27;
    @DatabaseField(columnName = "28", dataType = DataType.STRING)
    private String day28;
    @DatabaseField(columnName = "29", dataType = DataType.STRING)
    private String day29;
    @DatabaseField(columnName = "30", dataType = DataType.STRING)
    private String day30;
    @DatabaseField(columnName = "31", dataType = DataType.STRING)
    private String day31;

    public WorkingSheet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMouth() {
        return mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }

    public String getDay8() {
        return day8;
    }

    public void setDay8(String day8) {
        this.day8 = day8;
    }

    public String getDay9() {
        return day9;
    }

    public void setDay9(String day9) {
        this.day9 = day9;
    }

    public String getDay10() {
        return day10;
    }

    public void setDay10(String day10) {
        this.day10 = day10;
    }

    public String getDay11() {
        return day11;
    }

    public void setDay11(String day11) {
        this.day11 = day11;
    }

    public String getDay12() {
        return day12;
    }

    public void setDay12(String day12) {
        this.day12 = day12;
    }

    public String getDay13() {
        return day13;
    }

    public void setDay13(String day13) {
        this.day13 = day13;
    }

    public String getDay14() {
        return day14;
    }

    public void setDay14(String day14) {
        this.day14 = day14;
    }

    public String getDay15() {
        return day15;
    }

    public void setDay15(String day15) {
        this.day15 = day15;
    }

    public String getDay16() {
        return day16;
    }

    public void setDay16(String day16) {
        this.day16 = day16;
    }

    public String getDay17() {
        return day17;
    }

    public void setDay17(String day17) {
        this.day17 = day17;
    }

    public String getDay18() {
        return day18;
    }

    public void setDay18(String day18) {
        this.day18 = day18;
    }

    public String getDay19() {
        return day19;
    }

    public void setDay19(String day19) {
        this.day19 = day19;
    }

    public String getDay20() {
        return day20;
    }

    public void setDay20(String day20) {
        this.day20 = day20;
    }

    public String getDay21() {
        return day21;
    }

    public void setDay21(String day21) {
        this.day21 = day21;
    }

    public String getDay22() {
        return day22;
    }

    public void setDay22(String day22) {
        this.day22 = day22;
    }

    public String getDay23() {
        return day23;
    }

    public void setDay23(String day23) {
        this.day23 = day23;
    }

    public String getDay24() {
        return day24;
    }

    public void setDay24(String day24) {
        this.day24 = day24;
    }

    public String getDay25() {
        return day25;
    }

    public void setDay25(String day25) {
        this.day25 = day25;
    }

    public String getDay26() {
        return day26;
    }

    public void setDay26(String day26) {
        this.day26 = day26;
    }

    public String getDay27() {
        return day27;
    }

    public void setDay27(String day27) {
        this.day27 = day27;
    }

    public String getDay28() {
        return day28;
    }

    public void setDay28(String day28) {
        this.day28 = day28;
    }

    public String getDay29() {
        return day29;
    }

    public void setDay29(String day29) {
        this.day29 = day29;
    }

    public String getDay30() {
        return day30;
    }

    public void setDay30(String day30) {
        this.day30 = day30;
    }

    public String getDay31() {
        return day31;
    }

    public void setDay31(String day31) {
        this.day31 = day31;
    }
}
