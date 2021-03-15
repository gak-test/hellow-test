package com.gak.dao;

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
public class MybatisTest {
   private static final Logger log = LoggerFactory.getLogger(MybatisTest.class);
   
   @Autowired
   private SqlSessionFactory sqlSessionFactory;
   
   @Test
   public void testConnection() {
      try {
         
    	  SqlSession sess = sqlSessionFactory.openSession();
    	  Connection conn = sess.getConnection();
    	  
    	  log.info(sess.toString());
          log.info(conn.toString());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}