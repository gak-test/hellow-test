package com.gak.controller.admin;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdHomeController {
	
	private static final Logger log = LoggerFactory.getLogger(AdHomeController.class);
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String home(Model model,  @ModelAttribute("msg") String msg) {
		model.addAttribute("isMemRegSuccess", msg);
		return "index";
	}

}
