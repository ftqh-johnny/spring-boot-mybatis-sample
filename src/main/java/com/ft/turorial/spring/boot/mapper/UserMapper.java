package com.ft.turorial.spring.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ft.turorial.spring.boot.domain.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user where id= #{id,jdbcType=INTEGER} ")
	User findOne(@Param("id")int id);
	
	@Select("select * from user where user_name= #{userName,jdbcType=VARCHAR} ")
	User findByUserName(@Param("userName")String userName);
	
}
