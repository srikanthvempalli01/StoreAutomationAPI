package com.StoreAutomation.Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class XLUtility 
{
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sh;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	public XLUtility(String path)
	{
		this.path=path;
	}
	public int getRowCount(String xlsheet) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		int rowCount=sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;	
	}
	public int getCellCount(String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rownum);
		int cellCount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;	
	}
	public String getCellData(String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		DataFormatter formatter=new DataFormatter();
		try
		{
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;	
	}
	public void setCellData(String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		if(wb.getSheetIndex(xlsheet)==-1)
			wb.createSheet(xlsheet);
		sh=wb.getSheet(xlsheet);
		if(sh.getRow(rownum)==null)
			sh.createRow(rownum);
		row=sh.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.close();
		fi.close();
		fo.close();	
	}
}
