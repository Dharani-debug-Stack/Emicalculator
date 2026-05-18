package utilities;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtils {
//Write in Excel

    Workbook wb;
    Sheet sheet;

    public ExcelUtils(String sheetName) {
        wb = new XSSFWorkbook();
        sheet = wb.createSheet(sheetName);
    }

    public void write(int row, int col, String value) {
        Row r = sheet.getRow(row);
        if (r == null) r = sheet.createRow(row);

        Cell c = r.createCell(col);
        c.setCellValue(value);
    }

    public void save(String path) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        fos.close();
    }
}
