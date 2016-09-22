package com.ft.turorial.spring.boot.service;

import com.ft.turorial.spring.boot.domain.User;

public interface UserService {
	User findOne(int id);
	User findByLoginName(String loginName);
}
