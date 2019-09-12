package com.person.test;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import common.person.PersonDto;

public class JUnitTest {
	
	FileOutputStream fos = null;
	
	String sCurTime = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());
	
	PersonDto perDto;

	public void TestmakeExcelImageAndData(String[] filename, List<PersonDto> person) {
		// for xls varible
		// 헤더 정보 구성
		// 행 생성
		HSSFRow row = null;

		// 셀 생성
		HSSFCell cell = null;

		// simple test!!
		File file = new File("C:\\sw\\" + sCurTime + " Image&Datas.xls");
		// 워크북
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 워크시트 생성
		HSSFSheet sheet = workbook.createSheet("Images");
		HSSFSheet sheet2 = workbook.createSheet("Datas");

		InputStream inputStream = null;
		Drawing drawing = null;
		CreationHelper helper = null;
		ClientAnchor anchor = null;
		byte[] bytes = null;
		int pictureIdx = 0;
		Picture pic = null;

		int specialSize = 0;

		try {
			for (int i = 0; i < filename.length; i++) {
				inputStream = new FileInputStream("C:\\Users\\hbee\\Downloads\\" + filename[i]);

				bytes = IOUtils.toByteArray(inputStream);

				pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

				inputStream.close();

				helper = workbook.getCreationHelper();

				drawing = sheet.createDrawingPatriarch();

				anchor = helper.createClientAnchor();

				anchor.setCol1(1);
				anchor.setCol2(20);
				anchor.setRow1(5 + specialSize);
				anchor.setRow2(20);

				pic = drawing.createPicture(anchor, pictureIdx);

				pic.resize();
				specialSize = 20;
			}

			row = sheet2.createRow(0);
			cell = row.createCell(0);

			// 셀 생성
			cell = row.createCell(0);
			cell.setCellValue("ID");

			cell = row.createCell(1);
			cell.setCellValue("NAME");

			cell = row.createCell(2);
			cell.setCellValue("AGE");

			cell = row.createCell(3);
			cell.setCellValue("SEX");

			for (int rowlistcount = 0; rowlistcount < person.size(); rowlistcount++) {
				perDto = person.get(rowlistcount);
				// 행 생성
				row = sheet2.createRow(rowlistcount + 1);

				cell = row.createCell(0);
				cell.setCellValue(perDto.getId());

				cell = row.createCell(1);
				cell.setCellValue(perDto.getName());

				cell = row.createCell(2);
				cell.setCellValue(perDto.getAge());

				cell = row.createCell(3);
				cell.setCellValue(perDto.getSex());
			}

			fos = new FileOutputStream(file);
			workbook.write(fos);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void TestmakeXlsxWriter(List<PersonDto> person) {
		// Basic xlsx files.

		File file = new File("C:\\sw\\" + sCurTime + ".xlsx");
		// for xlsx varible
		// 워크북 생성
		XSSFWorkbook workbook_xss = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet_xss = workbook_xss.createSheet();

		// 행 생성
		XSSFRow row_xss = sheet_xss.createRow(0);
		// 셀 생성
		XSSFCell cell_xss = null;
		// 헤더 정보 구성
		cell_xss = row_xss.createCell(0);
		cell_xss.setCellValue("id");
		cell_xss = row_xss.createCell(1);
		cell_xss.setCellValue("name");

		cell_xss = row_xss.createCell(2);
		cell_xss.setCellValue("age");
		cell_xss = row_xss.createCell(3);
		cell_xss.setCellValue("sex");
		// 리스트의 size 만큼 row를 생성
		PersonDto perDto;
		for (int rowlistcount = 0; rowlistcount < person.size(); rowlistcount++) {
			perDto = person.get(rowlistcount);
			// 행 생성
			row_xss = sheet_xss.createRow(rowlistcount + 1);

			cell_xss = row_xss.createCell(0);
			cell_xss.setCellValue(perDto.getId());
			cell_xss = row_xss.createCell(1);
			cell_xss.setCellValue(perDto.getName());
			cell_xss = row_xss.createCell(2);
			cell_xss.setCellValue(perDto.getAge());
			cell_xss = row_xss.createCell(3);
			cell_xss.setCellValue(perDto.getSex());
		}

		try {
			fos = new FileOutputStream(file);
			workbook_xss.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook_xss != null)
					workbook_xss.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

