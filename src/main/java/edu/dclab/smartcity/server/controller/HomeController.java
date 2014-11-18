package edu.dclab.smartcity.server.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.dclab.smartcity.server.dao.IBaseDao;
import edu.dclab.smartcity.server.entity.Restaurant;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	
	@Resource(name = "baseDao")
    private IBaseDao<Restaurant> baseDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage() {
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/home");
		
		mav.addObject("restaurants", baseDao.queryAll(Restaurant.class));
		
		return mav;
	}
}
