package com.ft.turorial.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ft.turorial.spring.boot.domain.User;
import com.ft.turorial.spring.boot.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserMapper userMapper;
	@Override
	public User findOne(int id) {
		return userMapper.findOne(id);
	}
	@Override
	public User findByLoginName(String loginName) {
		return userMapper.findByUserName(loginName);
	}

}
