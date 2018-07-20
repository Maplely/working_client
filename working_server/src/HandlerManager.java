import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class HandlerManager {
    public static int finalrow = -1;
    public static int num = -1;
    public static int finalcolumncouns = -1;
    private static PrintWriter pw;

    public static String handlerSheet(String path, String mounth) throws IOException, BiffException {
        String inputMouth;
        if(mounth.length()==2){
            inputMouth=mounth.substring(0,1);
        }else{
            inputMouth=mounth.substring(0,2);
        }
        StringBuilder json = new StringBuilder("{" + "\"" + "results" + "\"" + ":[{");
        json.append("\"mouth\":" + "\"" + inputMouth + "\",");
        File file = new File(path);
        if (!file.getName().endsWith("xls")) {
            return new String("请选择正确的文件!");
        }
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet("在线" + mounth + "班务");
        if (sheet == null) {
            return new String("没有找到相应月份的数据!");
        }
        finalcolumncouns = sheet.getColumns() - 2;
        json.append("\"count\":" + "\"" + finalcolumncouns + "\",");
        for (int i = 0; i < sheet.getRows(); i++) {
            Cell cell = sheet.getCell(1, i);
            if (cell.getContents().equals("郝婧妍")) {
                finalrow = i;
                break;
            }
        }
        if (finalrow == -1) {
            return new String("没有找到目标 请正确操作!");
        }
        for (int j = 2; j < sheet.getColumns(); j++) {

            num = j - 1;
            Cell cell = sheet.getCell(j, finalrow);
            String contents = cell.getContents();
            json.append("\"" + num + "\":");
            if (j == sheet.getColumns() - 1) {
                json.append("\"" + cell.getContents() + "\"}");
            } else {
                json.append("\"" + cell.getContents() + "\",");
            }
        }
        json.append("]}");
        workbook.close();
        return json.toString();
    }

    public static void saveFile(String res, String path) {
        int i=path.lastIndexOf(File.separator);
        String filePath=path.substring(0,i);
        filePath+="/WorkTask.json";
        File file = new File(filePath);
        try {
            if(file.isFile()){
                file.delete();
            }

            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            pw = new PrintWriter(file);
            pw.print(res);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
    }

}
