package com.gak.mapper;

import java.sql.*;

import org.apache.ibatis.session.*;
import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BoardMapperTest {
   
   private static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);
	
//	@Autowired
//	private BoardMapper boardMapper;
//	
//	@Test
//	public void testGetTime() {
//		boardMapper.getList();
//	}
	
}