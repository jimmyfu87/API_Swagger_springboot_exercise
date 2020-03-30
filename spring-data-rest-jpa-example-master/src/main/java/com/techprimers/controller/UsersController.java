package com.techprimers.controller;

import com.techprimers.model.User;
import com.techprimers.repository.UserJpaRespository;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UsersController {

	/** The JPA repository */
    @Autowired
    private UserJpaRespository userJpaRespository;

	/**
	 * Used to fetch all the users from DB
	 * 
	 * @return list of {@link User}
	 */
    @ApiOperation(value = "查詢所有用户", httpMethod = "GET")
    @GetMapping(value = "/all")
    public List<User> findAll() {
        return userJpaRespository.findAll();
    }

    /**
	 * Used to find and return a user by name
	 * 
	 * @param name refers to the name of the user
	 * @return {@link User} object
	 */
    @ApiOperation(value = "根據name查詢用戶", httpMethod = "GET")
    @GetMapping(value = "/{name}")
    public User findByName(@PathVariable final String name){
        return userJpaRespository.findByName(name);
    }

    /**
	 * Used to create a User in the DB
	 * 
	 * @param users refers to the User needs to be saved
	 * @return the {@link User} created
	 */
    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @PostMapping(value = "/load")
    public User load(@RequestBody final User users) {
        userJpaRespository.save(users);
        return userJpaRespository.findByName(users.getName());
    }
    @ApiOperation(value = "刪除所有用户", httpMethod = "GET")
    @GetMapping(value = "/deleteall")
    public void deleteAll() {
    	userJpaRespository.deleteAll();
    }    
    @ApiOperation(value = "根據name刪除用戶", httpMethod = "GET")
    @Transactional   
    @GetMapping(value = "/delete/{name}")
    public void deleteByName(@PathVariable final String name){
         userJpaRespository.deleteByName(name);
    }
//    @GetMapping(value = "/delete/{id}")
//    public void deleteById() {
//     userJpaRespository.deleteById(1L);
//    }
}
