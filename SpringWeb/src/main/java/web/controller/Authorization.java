package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import web.database.DAOFactory;
import web.database.DAOSelection;
import web.database.DataBaseDAOInterface;
import web.database.UserService;

@Controller
@RequestMapping("/authorization")
public class Authorization {
	private boolean showForm = true;
	private static final String AUTHORIZED = "authorized";
	private final UserService userService;

	public Authorization(UserService userService) {
		super();
		this.userService = userService;
	}

//	@GetMapping(value= {"/{logout}"})
//	public String checkAuthorization(@PathVariable(value="logout") String logout, ModelMap modelMap) throws ServletException, IOException {
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attr.getRequest().getSession();
//		if (logout != null) {
//			session.setAttribute(AUTHORIZED, null);
//			showForm = true;
//		}
//		modelMap.addAttribute("showForm", showForm);
//		return "authorization";
//	}


	@GetMapping()
	public String logout(ModelMap modelMap, @RequestParam(required = false) String logout) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		if (logout != null) {
			session.setAttribute(AUTHORIZED, null);
			showForm = true;
		}
		modelMap.addAttribute("showForm", showForm);
		return "authorization";
	}

	@PostMapping
	public String authorizate(@RequestParam("login") String login, @RequestParam("password") String password,
			ModelMap modelMap) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		String loginFromSession = (String) session.getAttribute(AUTHORIZED);
		if (loginFromSession != null) {
			showForm = false;
		} else {
			String userName = userService.checkAuthorization(login, password);
			if (userName != null) {
				showForm = false;
				session.setAttribute(AUTHORIZED, userName);
				// modelMap.addAttribute(AUTHORIZED, userName);
				// out.write("<center><font color='green'>Access granted</font>");
			}
			modelMap.addAttribute("showForm", showForm);
		}
		return "authorization";
	}

}