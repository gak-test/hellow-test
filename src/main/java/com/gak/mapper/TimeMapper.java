package com.gak.mapper;

import org.apache.ibatis.annotations.*;

public interface TimeMapper {

	@Select("SELECT sysdate FROM dual")
	public String getTime(); 	
	
}
