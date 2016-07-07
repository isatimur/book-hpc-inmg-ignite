package com.blu.imdg.wsession.controller;

import com.blu.imdg.wsession.bean.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
//@Scope("session")
@SessionAttributes("personObj")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model, HttpServletRequest request) {
		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        //model.addAttribute("personObj", new Person());
		request.getSession().setAttribute("TEST", new Person("Test"));
		return "welcome";
	}

	@RequestMapping(value="/helloagain", method = RequestMethod.GET)
	public String sayHelloAgain(ModelMap model, HttpServletRequest request) {
        // set session
		model.addAttribute("greeting", request.getSession().getAttribute("TEST"));
		return "welcome";
	}

}
