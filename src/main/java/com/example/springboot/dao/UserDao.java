package com.example.springboot.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springboot.vo.ResultVO;
import com.example.springboot.vo.Users;

@Mapper
public interface UserDao {

	public List<Users> findAllUsers();
	
}
