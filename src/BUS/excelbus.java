/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.*;
import DAO.truyvan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author user
 */
public class excelbus {
    
    public void nhapvaoexcel(JTable table,String tenfile) throws FileNotFoundException, IOException{
        new WorkbookFactory();
        Workbook wb = new XSSFWorkbook(); //Excell workbook
        Sheet sheet = wb.createSheet(); //WorkSheet
        Row row = sheet.createRow(1); //Row created at line 3
        TableModel model = table.getModel(); //Table model

        Row headerRow = sheet.createRow(0); //Create row at line 0
        for(int headings = 0; headings < model.getColumnCount(); headings++)
        { //For each column
        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }

        for(int rows = 0; rows < model.getRowCount(); rows++)
        { //For each table row
        for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }
        row = sheet.createRow((rows + 2)); 
        }

        String outputDirPath = tenfile + ".xlsx";
        FileOutputStream fileOut = new FileOutputStream(outputDirPath);
        wb.write(fileOut);
        fileOut.close();
    }
    String getcell(Cell cell)
    {
        String nhap="";
        switch (cell.getCellType()) 
            {
                case Cell.CELL_TYPE_STRING:
                nhap= cell.getStringCellValue();
                break;
                case Cell.CELL_TYPE_BOOLEAN:
                nhap=Boolean.toString(cell.getBooleanCellValue());
                break;
                case Cell.CELL_TYPE_NUMERIC:
                nhap=Double.toString(cell.getNumericCellValue());
                break;
            }
        return nhap;
    }
    public void layra(String tenfile,String tenbang) throws FileNotFoundException, IOException
    {
        String excelFilePath = tenfile+".xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = (Sheet) workbook.getSheetAt(0);
//        Iterator<Row> iterator = sheet.iterator();
        Row row;
//        while (iterator.hasNext()) 
//        {
//            Row nextRow = iterator.next();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//        
//            while (cellIterator.hasNext()) 
//            {
//                Cell cell = cellIterator.next();
//                getcell(cell);
//            }
//            System.out.println();
//        }
        switch(tenbang){
        case "Truyện":
            {
            TRUYENBUS tb=new TRUYENBUS();
            for(int i=1; i<=sheet.getLastRowNum(); i++)
            {
                row = sheet.getRow(i);           
                int c=Integer.valueOf(getcell(row.getCell(6)));
                int a=Integer.valueOf(getcell(row.getCell(7)));
                int b=Integer.valueOf(getcell(row.getCell(8)));
                TRUYENDTO tr=new TRUYENDTO(getcell(row.getCell(0)),getcell(row.getCell(1)),getcell(row.getCell(2)),getcell(row.getCell(3)),getcell(row.getCell(4)),
                getcell(row.getCell(5)),c,a,b,getcell(row.getCell(9)));
                tb.them(tr);
            }
            workbook.close();
            inputStream.close();
            }break;
        case "Thành viên":
            {
            THANHVIENBUS tvb=new THANHVIENBUS();
            for(int i=1; i<=sheet.getLastRowNum(); i++)
            {
                row = sheet.getRow(i);
                THANHVIENDTO tv=new THANHVIENDTO(getcell(row.getCell(0)),getcell(row.getCell(1)),getcell(row.getCell(2)),getcell(row.getCell(3)),getcell(row.getCell(4)));
                tvb.them(tv);
            }
            workbook.close();
            inputStream.close();
            }break;
        case "Nhân viên":
            {
            NHANVIENBUS nvb=new NHANVIENBUS();
            for(int i=1; i<=sheet.getLastRowNum(); i++)
            {
                row = sheet.getRow(i);
                NHANVIENDTO nv=new NHANVIENDTO(getcell(row.getCell(0)),getcell(row.getCell(1)),getcell(row.getCell(2)),getcell(row.getCell(3)),getcell(row.getCell(4)),getcell(row.getCell(5)));
                nvb.them(nv);
            }
            workbook.close();
            inputStream.close();
            }break;
        default:break;
        }
    }
}
