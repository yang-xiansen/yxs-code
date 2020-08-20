package org.yxs.test.service;


import org.yxs.test.entity.User;

public interface UserService extends IService<User>{
	User findByName(String userName);
	
	void saveUser(User user);
}
