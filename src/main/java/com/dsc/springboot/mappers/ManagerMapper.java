package com.dsc.springboot.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dsc.springboot.models.Manager;

@Mapper
public interface ManagerMapper {
	@Select(value="select * from manager where loginName=#{loginName} and password=#{password}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="loginName",column="login_name"),
		@Result(property="username",column="user_name"),
		@Result(property="password",column="password"),
		@Result(property="roleId",column="role_id")
	})
	Manager login(@Param("loginName") String loginName,@Param("password") String password);
}
