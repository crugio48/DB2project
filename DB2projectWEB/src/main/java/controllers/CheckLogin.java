package controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	//TODO add ejb service
	
	
    public CheckLogin() {
        super();
    }
    
    public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//the path for any type of error
		String loginpath = request.getServletContext().getContextPath() + "/Logout";
		
		HttpSession session = request.getSession();
		
		//obtain and escape params
		String usrn = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty()) {
			session.setAttribute("errorMessage", "Please input valid username and password");
			response.sendRedirect(loginpath);
			return;
		}
		
		//TODO check user and password using bean and service
		
		
		
		//TODO all following
		//if in the db we didn't find the user --> the user is null so we show a message saying incorrect values in the form
		/*
		if (user == null) {
			
			session.setAttribute("errorMessage", "Username or password not valid");
			response.sendRedirect(loginpath);
			return;
			
		} else {
			
			//TODO send to customer or employee servlet 
			
		}
		*/
		
		
	}
	
	public void destroy() {
	}

}
