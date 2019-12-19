package com.dsc.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dsc.springboot.model.User;

@Mapper
public interface UserMapper {
	
	@Select(value="select * from user limit #{page},#{limit}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="username",column="username"),
		@Result(property="password",column="password"),
		@Result(property="sex",column="sex")
	})
	List<User> getUsers(@Param("page") int page, @Param("limit") int limit);
	
	@Select(value="select * from user where id=#{id}")
	User getUserById(@Param("id") Integer id);
	
	@Insert(value="insert into user(username,password,sex) values(#{username},#{password},#{sex})")
	@ResultType(value=Integer.class)
	int addUser(User user);
	
	@Update(value="update user set username=#{username},password=#{password},sex=#{sex} where id=#{id}")
	@ResultType(value=Integer.class)
	int updateUser(User user);
	
	@Delete(value="delete from user where id=#{id}")
	@ResultType(value=Integer.class)
	int deleteUser(@Param("id") Integer id);
	
	@Select(value="select count(*) from user")
	@ResultType(value=Integer.class)
	int getUserCount();
	
}
