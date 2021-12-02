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
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import services.ServicePackageService;


@WebServlet("/GoToBuyService")
public class GoToBuyService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ServicePackageService")
	ServicePackageService servicePackageService;
    
    public GoToBuyService() {
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginpath = request.getServletContext().getContextPath() + "/Logout";
		String homePagePath = request.getServletContext().getContextPath() + "/GoToHomeCustomer";
		
		HttpSession session = request.getSession();
		
		int servicePackageId;
		try {
			servicePackageId = Integer.parseInt(request.getParameter("servicePackageId"));
			
		} catch(NumberFormatException | NullPointerException e) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		if (!servicePackageService.doesServicePackageExist(servicePackageId)) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
	}

}
