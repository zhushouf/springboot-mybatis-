package com.example.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.api.UserService;
import com.example.springboot.service.IUserService;
import com.example.springboot.vo.ResultVO;
import com.example.springboot.vo.Users;
import com.mysql.cj.xdevapi.Result;

@RestController
@ComponentScan
@RequestMapping("/api")
public class userController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	String index() {
		System.out.println("login 准备跳转x。。。");
		return "login";
	}
	
	@RequestMapping(path = "/findAllUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Users>> findAllUser(){
		List<Users> resultVO = userService.findAllUsers();
		return new ResponseEntity<>(resultVO, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/exportUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ResultVO> downloadUsers(HttpServletResponse response) throws IOException{
		ResultVO resultVO = userService.exportUser(response);
		if(resultVO.getCode() != "200") {
			return new ResponseEntity<>(resultVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resultVO, HttpStatus.OK);
	}
	
}
