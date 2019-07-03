package com.example.springboot.api;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.springboot.vo.ResultVO;
import com.example.springboot.vo.Users;

public interface UserService {

	public List<Users> findAllUsers();
	
	public ResultVO exportUser(HttpServletResponse response) throws IOException;
}
