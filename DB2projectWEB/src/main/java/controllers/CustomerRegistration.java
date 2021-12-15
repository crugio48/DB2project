package controllers;

import java.io.IOException;

import javax.ejb.EJB;
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

import entities.Customer;
import services.CustomerService;



@WebServlet("/CustomerRegistration")
public class CustomerRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "services/CustomerService")
	private CustomerService customerService;
	
	
    public CustomerRegistration() {
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
		String email = request.getParameter("email");
		
		if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty() || email == null || email.isEmpty()) {
			session.setAttribute("errorMsg", "Missing or empty credential value");
			response.sendRedirect(loginpath);
			return;
		}
		
		
		Customer customer = null;
		
		customer = customerService.createCustomer(usrn, pwd, email);
		
		String path = null;
		if (customer == null) {
			session.setAttribute("errorMsg", "Invalid credentials, username already present");
			response.sendRedirect(loginpath);
			return;
		}
		else {
			session.setAttribute("customer", customer);
			
			if (session.getAttribute("tempOrder") != null) {
				path = getServletContext().getContextPath() + "/GoToConfirmationAfterLogin";
				response.sendRedirect(path);
				return;
			}
			
			path = getServletContext().getContextPath() + "/GoToHomeCustomer";
			session.setAttribute("errorMsg", "Registration successfull");
			response.sendRedirect(path);
		}
		
	}
	
	public void destroy() {
	}

}