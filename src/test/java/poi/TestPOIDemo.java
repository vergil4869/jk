package poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Aug 21, 2022
 */
public class TestPOIDemo {
	@Test
	public void HSSF() throws FileNotFoundException, IOException {
		//1. 创建一个工作薄excel文件
		Workbook wb = new HSSFWorkbook();//HSSF操作excel 2003以下版本
		//2. 创建一个工作表sheet
		Sheet sheet = wb.createSheet();
		//3. 创建一个行对象Row
		Row nRow = sheet.createRow(5-1);//第五行, 坐标从0开始
		//4. 创建一个单元格对象, 指定列
		Cell nCell = nRow.createCell(4-1);//第四列
		//5. 给单元格设置内容
		nCell.setCellValue("传智播客万年长!");
		//6. 保存
//		FileOutputStream stream = new FileOutputStream(new File("D://testPOI.xls"));
		FileOutputStream stream = new FileOutputStream("D://testPOI.xls");
		wb.write(stream);
		//7. 关闭
		stream.close();
	}
	
	//带格式
	@Test
	public void HSSFStyle() throws FileNotFoundException, IOException {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		Row nRow = sheet.createRow(5-1);
		Cell nCell = nRow.createCell(4-1);
		nCell.setCellValue("传智播客万年长!");

		//创建一个单元格的样式
		CellStyle nStyle = wb.createCellStyle();
		//创建一个字体对象
		Font nFont = wb.createFont();
//		titleFont.setFontName("华文行楷");//设置字体
//		titleFont.setFontHeightInPoints((short) 26);//设置字体大小
//		titleStyle.setFont(titleFont);
		nCell.setCellStyle(this.titleStyle(wb, nStyle, nFont));
		
		//再创建一个单元格
		Row xRow = sheet.createRow(6-1);
		Cell xCell = xRow.createCell(6-1);
		xCell.setCellValue("java.itcast.cn");
//		CellStyle textStyle = wb.createCellStyle();
//		Font textFont = wb.createFont();
//		textFont.setFontName("Times New Roman");
//		textFont.setFontHeightInPoints((short)12);
//		textStyle.setFont(textFont);
		
		//样式初始化
		nStyle = wb.createCellStyle();
		nFont = wb.createFont();
		xCell.setCellStyle(this.textStyle(wb, nStyle, nFont));

		FileOutputStream stream = new FileOutputStream(new File("D://testPOI.xls"));
		wb.write(stream);
		stream.close();
	}
	
	//标题样式
	public CellStyle titleStyle(Workbook wb, CellStyle nStyle, Font nFont) {
		nFont.setFontName("华文行楷");
		nFont.setFontHeightInPoints((short) 28);
		nStyle.setFont(nFont);
		return nStyle;
	}
	
	//文字样式
	public CellStyle textStyle(Workbook wb, CellStyle nStyle, Font nFont) {
		nFont.setFontName("Times New Roman");
		nFont.setFontHeightInPoints((short) 12);
		nStyle.setFont(nFont);
		return nStyle;
	}
}
