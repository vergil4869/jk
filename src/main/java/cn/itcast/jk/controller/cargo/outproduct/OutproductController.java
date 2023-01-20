package cn.itcast.jk.controller.cargo.outproduct;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.service.OutproductService;
import cn.itcast.jk.vo.OutproductVO;
import cn.itcast.util.DownloadUtil;

/**
 * @Description:
 * @Author: Vergil
 * @Company: http://java.itcast.cn
 * @CreateDate: Aug 22, 2022
 */
@Controller
public class OutproductController extends BaseController{
	@Resource
	OutproductService outproductService;
	
	//转向输入年月的页面
	@RequestMapping("/cargo/outproduct/toedit.action")
	public String toEdit() {
		return "/cargo/outproduct/jOutproduct.jsp";
	}
	
	/**
	 * 
	 * @param inputDate	格式: yyyy/MM
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/cargo/outproduct/printNoTemplate.action")
	public void printNoTemplate(String inputDate, HttpServletResponse response) throws IOException {
		List<OutproductVO> dataList = outproductService.find(inputDate);
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		
		int rowNo = 0;//行号
		int colNo = 1;//列号
		
		//声明样式对象和字体对象
		CellStyle nStyle = wb.createCellStyle();
		Font nFont = wb.createFont();
		
		sheet.setColumnWidth(0, 2*300);//列宽
		sheet.setColumnWidth(1, 26*300);//列宽, 乘256时, 有bug, API底层设置不够精确
		sheet.setColumnWidth(2, 12*300);
		sheet.setColumnWidth(3, 29*300);
		sheet.setColumnWidth(4, 10*300);
		sheet.setColumnWidth(5, 12*300);
		sheet.setColumnWidth(6, 8*300);
		sheet.setColumnWidth(7, 10*300);
		sheet.setColumnWidth(8, 10*300);
		sheet.setColumnWidth(9, 8*300);
		
		//大标题, 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 9));
		//合并单元格的内容是写在合并前的第一个单元格中
		Row nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(36);//行高
		Cell nCell = nRow.createCell(colNo++);
		nCell.setCellValue(inputDate.replaceFirst("/0", "/").replaceFirst("/", "年")+"月份出货表");
		bigTitle(nStyle, nFont);
		nCell.setCellStyle(nStyle);
		
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints((float) 26.25);

		nCell = nRow.createCell(colNo++);
		String[] titles = new String[]{"客户", "订单号", "货号", "数量", "工厂", "附件", "工厂交期", "船期", 
				"贸易条款"};

		//初始化
		nStyle = wb.createCellStyle();
		nFont = wb.createFont();
		for (int i = 0; i < titles.length; i++) {
			nCell = nRow.createCell(i+1);
			nCell.setCellValue(titles[i]);
			title(nStyle, nFont);
			nCell.setCellStyle(nStyle);
		}
		
		//初始化
		nStyle = wb.createCellStyle();
		nFont = wb.createFont();
		for (int i = 0; i < dataList.size(); i++) {
			colNo = 1;//列号初始化

			/*
			 * 处理数据
			 * nCell设置了这么多次值, 为什么没有覆盖掉
			 * 	因为每次都新创建了一个Cell
			 */
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			nCell = nRow.createCell(colNo++);
			OutproductVO outproductVO = dataList.get(i);//获取出货表对象
			nCell.setCellValue(outproductVO.getCustomName());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getContractNo());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getProductNo());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getCnumber());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getFactoryName());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getExts());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getDeliveryPeriod());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getShipTime());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getTradeTerms());
			text(nStyle, nFont);
			nCell.setCellStyle(nStyle);
		}

//		OutputStream stream = new FileOutputStream(new File("D://outproduct.xls"));
//		wb.write(stream);
//		stream.flush();
//		stream.close();
		
		//下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		wb.write(stream);
		/**
		 * 在Controller里, 怎么拿到response, 声明, 直接声明, 就有
		 */
		du.download(stream, response, "出货表.xls");
	}
	
	/**
	 * 模板开发
	 * @param inputDate
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/cargo/outproduct/printHSSF.action")
	public void printHSSF(String inputDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<OutproductVO> dataList = outproductService.find(inputDate);
		
		/*
		 * linux下jdk1.8方法获取时, 不会拼接自己写的目录 /make/xlsprint 
		 */
		String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xls"));
		Workbook wb = new HSSFWorkbook(is);//打开一个模板文件, 工作薄
		Sheet sheet = wb.getSheetAt(0);//获取到第一个工作表
		
		int rowNo = 0;//行号
		int colNo = 1;//列号
		
		//处理大标题
		Row nRow = sheet.getRow(rowNo++);//获取一个行对象
		Cell nCell = nRow.getCell(colNo++);
		nCell.setCellValue(inputDate.replaceFirst("/0", "/").replaceFirst("/", "年")+"月份出货表");
		
		rowNo++;//跳过静态表格头
		
		//获取模板上的单元格样式
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();
		nCell = nRow.getCell(6);
		CellStyle dateStyle = nCell.getCellStyle();
		nCell = nRow.getCell(8);
		CellStyle tradeStyle = nCell.getCellStyle();
		//处理内容
		for (int i = 0; i < dataList.size(); i++) {
			colNo = 1;//列号初始化

			/*
			 * 处理数据
			 * nCell设置了这么多次值, 为什么没有覆盖掉
			 * 	因为每次都新创建了一个Cell
			 */
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			nCell = nRow.createCell(colNo++);
			OutproductVO outproductVO = dataList.get(i);//获取出货表对象
			nCell.setCellValue(outproductVO.getCustomName());
			nCell.setCellStyle(customStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getContractNo());
			nCell.setCellStyle(contractNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getProductNo());
			nCell.setCellStyle(productNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getCnumber());
			nCell.setCellStyle(numStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getFactoryName());
			nCell.setCellStyle(factoryStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getShipTime());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getTradeTerms());
			nCell.setCellStyle(tradeStyle);
		}

//		OutputStream stream = new FileOutputStream(new File("D://outproduct.xls"));
//		wb.write(stream);
//		stream.flush();
//		stream.close();
		
		//下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		wb.write(stream);
		/**
		 * 在Controller里, 怎么拿到response, 声明, 直接声明, 就有
		 */
		du.download(stream, response, "出货表.xls");
	}
	
	/**
	 * 模版开发 XSSF
	 * @param inputDate
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/cargo/outproduct/print.action")
	public void print(String inputDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<OutproductVO> dataList = outproductService.find(inputDate);
		
		/*
		 * linux下jdk1.8方法获取时, 不会拼接自己写的目录 /make/xlsprint 
		 */
		String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xlsx"));
		Workbook wb = new XSSFWorkbook(is);//打开一个模板文件, 工作薄 2007以上版本
		Sheet sheet = wb.getSheetAt(0);//获取到第一个工作表
		
		int rowNo = 0;//行号
		int colNo = 1;//列号
		
		//处理大标题
		Row nRow = sheet.getRow(rowNo++);//获取一个行对象
		Cell nCell = nRow.getCell(colNo++);
		nCell.setCellValue(inputDate.replaceFirst("/0", "/").replaceFirst("/", "年")+"月份出货表");
		
		rowNo++;//跳过静态表格头
		
		//获取模板上的单元格样式
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();
		nCell = nRow.getCell(6);
		CellStyle dateStyle = nCell.getCellStyle();
		nCell = nRow.getCell(8);
		CellStyle tradeStyle = nCell.getCellStyle();
		//处理内容
		for (int i = 0; i < dataList.size(); i++) {
			colNo = 1;//列号初始化

			/*
			 * 处理数据
			 * nCell设置了这么多次值, 为什么没有覆盖掉
			 * 	因为每次都新创建了一个Cell
			 */
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			nCell = nRow.createCell(colNo++);
			OutproductVO outproductVO = dataList.get(i);//获取出货表对象
			nCell.setCellValue(outproductVO.getCustomName());
			nCell.setCellStyle(customStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getContractNo());
			nCell.setCellStyle(contractNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getProductNo());
			nCell.setCellStyle(productNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getCnumber());
			nCell.setCellStyle(numStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getFactoryName());
			nCell.setCellStyle(factoryStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getShipTime());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(outproductVO.getTradeTerms());
			nCell.setCellStyle(tradeStyle);
		}

//		OutputStream stream = new FileOutputStream(new File("D://outproduct.xls"));
//		wb.write(stream);
//		stream.flush();
//		stream.close();
		
		//下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		wb.write(stream);
		/**
		 * 在Controller里, 怎么拿到response, 声明, 直接声明, 就有
		 */
		du.download(stream, response, "出货表.xlsx");
	}
	
	/**
	 * 大标题样式
	 * @param style
	 * @param font
	 * @return
	 */
	public void bigTitle(CellStyle style, Font font) {
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
		style.setAlignment(CellStyle.ALIGN_CENTER);//横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//纵向居中
		style.setFont(font);
	}
	
	/**
	 * 小标题样式
	 * @param style
	 * @param font
	 */
	public void title(CellStyle style, Font font) {
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		//表格线
		style.setBorderTop(CellStyle.BORDER_THIN);//细线
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);

		style.setFont(font);
	}
	
	/**
	 * 文字样式
	 * @param style
	 * @param font
	 */
	public void text(CellStyle style, Font font) {
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 10);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		
		style.setFont(font);
	}
}
