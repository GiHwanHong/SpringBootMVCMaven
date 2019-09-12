package common.handler;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.person.PersonDto;

public class CustomExcelHandler {

	// Common varible
	FileOutputStream fos = null;
	String sCurTime = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());

	public static File makeExcelImages(File[] Images, String savePath, int separateCols, String sheetName) throws Exception{
		File file;
		FileOutputStream fos = null;
		// 워크북 생성
		XSSFWorkbook workbookXss = null;
		
		XSSFSheet sheetXss = null;
		workbookXss = new XSSFWorkbook();
		
		int rowNum = 1;
		int colNum = 1;
		int preColumn=0;
		file = new File(savePath);
		
		try {
			// initalize sheet
			 sheetXss = workbookXss.createSheet(sheetName);
			 sheetXss.setColumnWidth(0, 800);
			 XSSFCreationHelper helper = workbookXss.getCreationHelper();
			 XSSFDrawing drawing = sheetXss.createDrawingPatriarch();
			 XSSFRow rowXss = sheetXss.createRow(rowNum); // 행 생성
			 
			 for (int idx = 0; idx < Images.length; idx++) {
				if(separateCols >=6) {
					separateCols = 5;
				}
				
				BufferedImage img = ImageIO.read(Images[idx]);
				InputStream resource = new BufferedInputStream(new FileInputStream(Images[idx]));
				
				int pictureIdx = workbookXss.addPicture(resource, XSSFWorkbook.PICTURE_TYPE_PNG);
				
				XSSFClientAnchor anchor = helper.createClientAnchor();
				XSSFCell cellXss = rowXss.createCell(colNum);

				anchor.setRow1(rowNum);
				sheetXss.setColumnWidth(colNum, img.getWidth() * 32);
				anchor.setCol1(colNum);
				preColumn = colNum;
				colNum = colNum + 4; 
				
				for (int colMini = preColumn + 1; colMini < colNum; colMini++) {
					sheetXss.setColumnWidth(colMini, 800);	// 이미지가 뿌려지지 않는 곳의 컬럼을 축소한다
				}
				cellXss.getRow().setHeight((short) (img.getHeight() * 15)); // 그림사이즈에 맞게
				
				XSSFPicture pic = drawing.createPicture(anchor, pictureIdx);
				pic.resize(1.0); // 이미지 사이즈 비율 설정

				if ((idx+1) % separateCols == 0) {
					colNum = 1;
					rowNum = rowNum + 4;
					rowXss = sheetXss.createRow(rowNum); // 행 생성
				}
				
			}
			fos = new FileOutputStream(file);
			workbookXss.write(fos);
		}finally {
			if (workbookXss != null)
				workbookXss.close();
			if (fos != null)
				fos.close();	
		}
			
		return file;
	}

	public static void ReadCsvToXlsx(String[] filenames) {
		// using library
		String csvFileAddress = "";
		String xlsxFileAddress = "";
		FileOutputStream fileOutputStream = null;
		String[] fileNameW = null;
		XSSFWorkbook workbook_xss = null;
		XSSFSheet sheet_xss = null;
		XSSFRow row_xss = null;
		
		try {
			for (int i = 0; i < filenames.length; i++) {
				csvFileAddress = "E:\\workspace\\files\\csvfiles\\" + filenames[i]; // csv file address

				fileNameW = filenames[i].split("\\.");
				xlsxFileAddress = "E:\\workspace\\files\\savefiles\\LWriter_" + fileNameW[0] + ".xlsx"; // xlsx file address
				workbook_xss = new XSSFWorkbook();
				sheet_xss = workbook_xss.createSheet(fileNameW[0].toString());
				String currentLine = null;
				int RowNum = -1;

				BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
				while ((currentLine = br.readLine()) != null) {
					String str[] = currentLine.split(",");
					RowNum++;
					row_xss = sheet_xss.createRow(RowNum);
					for (int j = 0; j < str.length; j++) {
						row_xss.createCell(j).setCellValue(str[j]);
					}
					if (RowNum > 100000)
						// 5만, 9만건 , 10만건 - 정상
						// 20만건 - CPU를 너무 잡아먹음
						break;
				}

				fileOutputStream = new FileOutputStream(xlsxFileAddress);
				workbook_xss.write(fileOutputStream);
				fileOutputStream.close();
				System.out.println("사용된 메모리 : " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + " Exception in try Again");
		}
	}
	/*
	 * 	private static byte[] BufferToImage(BufferedImage img) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage originalImg = img;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImg, "png", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
		}
	 */
}