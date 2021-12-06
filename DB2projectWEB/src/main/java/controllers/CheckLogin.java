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
import entities.Employee;
import services.CustomerService;
import services.EmployeeService;


@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "services/EmployeeService")
	private EmployeeService employeeService;
	@EJB(name = "services/CustomerService")
	private CustomerService customerService;
	
	
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
			session.setAttribute("errorMessage", "Missing or empty credential value");
			response.sendRedirect(loginpath);
			return;
		}
		
		Employee employee = null;
		Customer customer = null;
		
		employee = employeeService.checkCredentials(usrn, pwd);
		
		customer = customerService.checkCredentials(usrn, pwd);
			
			
		
		
		
		String path;
		if (employee == null && customer == null) {
			session.setAttribute("errorMessage", "Missing or empty credential value");
			response.sendRedirect(loginpath);
			return;
		}
		else if (employee != null && customer == null) {
			session.setAttribute("employee", employee);
			path = getServletContext().getContextPath() + "/GoToHomeEmployee";
			response.sendRedirect(path);
		}
		else if (employee == null && customer != null) {
			
			session.setAttribute("customer", customer);
			
			if (session.getAttribute("tempOrder") != null) {
				path = getServletContext().getContextPath() + "/GoToConfirmationAfterLogin";
				response.sendRedirect(path);
				return;
			}
			
			path = getServletContext().getContextPath() + "/GoToHomeCustomer";
			response.sendRedirect(path);
			
		}
		else {
			//should never get here, this means that there are one customer and one employee
			//with the same username and password
			session.setAttribute("errorMessage", "Database is inconsistent, fix needed");
			response.sendRedirect(loginpath);
			return;
		}
		
	}
	
	public void destroy() {
	}

}
