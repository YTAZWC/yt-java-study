package top.ytazwc.poi.util;

/**
 * @author yt
 * 2024/7/1
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.math.BigDecimal;
import java.util.*;

//读取excel工具类
public class ImportExcelUtil {
    /**
     * 读取Sheet 的所有数据
     * @param rows
     * @return
     */
    public static List<Map<String, String>> getSheetData(Sheet rows){
        Map<Integer, String> tableHeader=new HashMap<Integer, String>();
        List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        int rowNum=0;
        Iterator<Row> rowIterator = rows.rowIterator();
        while (rowIterator.hasNext()){

            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            int column=0;
            Map<String, String> tdata=new HashMap<>();
            while(cellIterator.hasNext()){
                Cell next = cellIterator.next();
                int columnIndex = next.getColumnIndex();
                String cellValue = getCellValue(next);
                if(rowNum == 0){
                    //第0行,表头
                    tableHeader.put(column,cellValue);
                }else {
                    tdata.put(tableHeader.get(columnIndex),cellValue);
                }
                column++;
            }
            if(rowNum != 0){
                list.add(tdata);
            }
            rowNum ++;
        }
        return list;
    }

    private static String getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING){
            String stringCellValue = cell.getStringCellValue();
            return stringCellValue;
        }else if (cellType == CellType.NUMERIC){
            Double value = cell.getNumericCellValue();
            BigDecimal bd1 = new BigDecimal(Double.toString(value));
            // 去掉后面无用的零  如小数点后面全是零则去掉小数点
            String s = "";
            if (bd1.toPlainString().contains(".")){
                s = bd1.toPlainString().replaceAll("0+?$", "").replaceAll("[.]$", "");
            }else{
                s = bd1.toPlainString();
            }

            return s;
        }
        return "";
    }
}
