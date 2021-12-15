package controllers;

import java.io.IOException;
import java.util.List;

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

import entities.ServicePackage;
import entities.ValidityPeriod;
import services.ServicePackageService;
import services.ValidityPeriodService;



@WebServlet("/GoToHomeCustomer")
public class GoToHomeCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
       
	@EJB(name = "services/ValidityPeriodService")
	ValidityPeriodService validityPeriodService;
	
	@EJB(name = "services/ServicePackageService")
	ServicePackageService servicePackageService;
	
    public GoToHomeCustomer() {
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
		
		HttpSession session = request.getSession();
		
		
		List<ServicePackage> packagesList = null;
		packagesList = servicePackageService.getAllAvailableServicePackages();
		
		List<ValidityPeriod> validityPeriodList = null;
		validityPeriodList = validityPeriodService.getAllValidityPeriods();
		
		
		String path = "/WEB-INF/customer/CustomerHome.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
				
		ctx.setVariable("validityPeriods", validityPeriodList);
		ctx.setVariable("servicePackages", packagesList);
		
		if(session.getAttribute("errorMsg") != null) {
			ctx.setVariable("errorMsg", session.getAttribute("errorMsg"));
			session.removeAttribute("errorMsg");
		}
		
		templateEngine.process(path, ctx, response.getWriter());
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
	}

}
