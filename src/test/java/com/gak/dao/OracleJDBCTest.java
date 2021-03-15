package com.gak.dao;

import java.sql.*;

import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OracleJDBCTest {
   private static final Logger log = LoggerFactory.getLogger(OracleJDBCTest.class);
   
   static {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
   }
   
   @Test
   public void testConnection() {
      try {
         Connection conn = DriverManager.getConnection(
                                             "jdbc:oracle:thin:@192.168.10.49:1521:gak",
                                             "gak",
                                             "q1231235");
         log.info(conn.toString());
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}