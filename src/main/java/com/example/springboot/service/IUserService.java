package com.example.springboot.service;


import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.api.UserService;
import com.example.springboot.vo.Users;
import com.example.springboot.dao.UserDao;
import com.example.springboot.vo.ResultVO;

@Service
public class IUserService implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Users> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public ResultVO exportUser(HttpServletResponse response) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		workbook.createSheet("student-info-list");
		Users users = new Users();
		users.setUuid("学号");
		users.setName("姓名");
		users.setAddress("地址");
		createUserWorkbook(workbook,users,0);
		
		List<Users> allUserInfo = userDao.findAllUsers();
		if(allUserInfo == null) {
			return new ResultVO("没有可导出的信息！", "200");
		}
		for(int i = 0; i < allUserInfo.size(); i++) {
			createUserWorkbook(workbook, allUserInfo.get(i), i+1);
		}
		OutputStream outputStream = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename=students.xls");
		response.setContentType("application/msexcel");
		workbook.write(outputStream);
		outputStream.close();
		return new ResultVO("success", "200");
	}
	
	public Workbook createUserWorkbook(Workbook workbook,Users users,int i) {
		Sheet sheet = workbook.getSheet("student-info-list");
		if(sheet != null) {
			sheet.setDefaultColumnWidth(20);
			Row row = sheet.createRow(i);
			CellStyle cellStyle = workbook.createCellStyle();
			
			org.apache.poi.ss.usermodel.Font font = workbook.createFont();
			if(font != null) {
				font.setFontName("黑体");
				font.setFontHeightInPoints((short) 12);
				cellStyle.setFont(font);
				cellStyle.setWrapText(true);//设置自动换行
			}
			if(i == 0) {
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(users.getUuid());
				cell0.setCellStyle(cellStyle);
				
				Cell cell1 = row.createCell(1);
				cell1.setCellValue(users.getName());
				cell1.setCellStyle(cellStyle);
				
				Cell cell2 = row.createCell(2);
				cell2.setCellValue(users.getAddress());
				cell2.setCellStyle(cellStyle);
			}else {
				Cell cell3 = row.createCell(0);
				cell3.setCellValue(users.getUuid());
				cell3.setCellStyle(cellStyle);
				
				Cell cell4 = row.createCell(1);
				cell4.setCellValue(users.getName());
				cell4.setCellStyle(cellStyle);
				
				Cell cell5 = row.createCell(2);
				cell5.setCellValue(users.getAddress());
				cell5.setCellStyle(cellStyle);
			}
		}
		return workbook;
	}
}
