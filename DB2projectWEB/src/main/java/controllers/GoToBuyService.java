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


@WebServlet("/GoToBuyService")
public class GoToBuyService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ServicePackageService")
	ServicePackageService servicePackageService;
	
	@EJB(name = "services/ValidityPeriodService")
	ValidityPeriodService validityPeriodService;
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
		
		//String loginpath = request.getServletContext().getContextPath() + "/Logout";
		String homePagePath = request.getServletContext().getContextPath() + "/GoToHomeCustomer";
		
		HttpSession session = request.getSession();
		
		int servicePackageId;
		try {
			servicePackageId = Integer.parseInt(request.getParameter("servicePackageId"));
			
		} catch(NumberFormatException | NullPointerException e) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		ServicePackage servicePackage = null;
		
		
		if ((servicePackage =  servicePackageService.getServicePackage(servicePackageId)) == null) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		List<ServicePackage> packagesList = null;
		packagesList = servicePackageService.getAllAvailableServicePackages();
		
		List<ValidityPeriod> validityPeriodList = null;
		validityPeriodList = validityPeriodService.getAllValidityPeriods();
		
		String path = "/WEB-INF/customer/BuyAServicePage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		ctx.setVariable("servicePackageSelected", servicePackage);
		ctx.setVariable("validityPeriods", validityPeriodList);
		ctx.setVariable("servicePackages", packagesList);
		
		
		templateEngine.process(path, ctx, response.getWriter());
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
	}

}
