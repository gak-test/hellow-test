package com.gak.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gak.dao.user.*;
import com.gak.dto.user.*;

@Service
public class CodeServiceImpl implements CodeService {

  
   @Autowired
   CodeDao dao;
   
   @Override
   public Code[] getCodeList(String category) {
      return dao.getCodeList(category);
   }

}