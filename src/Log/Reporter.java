package Log;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Reporter {

    public static void writeReport(ArrayList<ArrayList<String>> dataLog, String fileName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet dataSheet = workbook.createSheet("Data");
        //HSSFSheet infoSheet = workbook.createSheet("Information");

        //for data of experiments
        for (int rowNum = 0; rowNum < dataLog.size(); ++rowNum) {
            ArrayList<String> data = dataLog.get(rowNum);
            HSSFRow row = dataSheet.createRow(rowNum);

            for (int cellNum = 0; cellNum < data.size(); ++cellNum) {
                HSSFCell cell = row.createCell(cellNum);
                cell.setCellValue(data.get(cellNum));
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(fileName);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeReport(ArrayList<ArrayList<String>> dataLog) {
        DateFormat df = new SimpleDateFormat("dd-MM-yy(HH-mm-ss)");
        String fileName = "experiment-" + df.format(new Date()) + ".xls";
        writeReport(dataLog, fileName);
    }
}
