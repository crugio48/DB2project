package controllers;

import java.io.IOException;
import java.util.Map;

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

import beans.TempOrder;
import entities.ServicePackage;
import entities.ValidityPeriod;
import services.ServicePackageService;
import services.ValidityPeriodService;


@WebServlet("/GoToConfirmationAfterLogin")
public class GoToConfirmationAfterLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ServicePackageService")
	ServicePackageService servicePackageService;
	
	@EJB(name = "services/ValidityPeriodService")
	ValidityPeriodService validityPeriodService;
       
    public GoToConfirmationAfterLogin() {
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
		
		//the path for any type of error
		String loginpath = request.getServletContext().getContextPath() + "/Logout";
		String homePagePath = request.getServletContext().getContextPath() + "/GoToHomeCustomer";
		
		HttpSession session = request.getSession();
		
		TempOrder tempOrder = null;
		
		if ((tempOrder = (TempOrder) session.getAttribute("tempOrder")) == null) {
			session.setAttribute("errorMessage", "Don't hack please");
			response.sendRedirect(loginpath);
			return;
		}
		
		
		ValidityPeriod validityPeriodSelected = null;
		
		if ((validityPeriodSelected = validityPeriodService.getValidityPeriod(tempOrder.getValidityPeriodId())) == null) {  //check if validity period exists
			response.sendRedirect(homePagePath);
			return;
		}
		
		ServicePackage servicePackageSelected = null;
		
		if ((servicePackageSelected = servicePackageService.getServicePackage(tempOrder.getServicePackageId())) == null) {    //check if service package exists
			response.sendRedirect(homePagePath);
			return;
		}
		
		String path = "/WEB-INF/customer/ConfirmationPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		
		ctx.setVariable("servicePackageSelected", servicePackageSelected);
		ctx.setVariable("validityPeriodSelected", validityPeriodSelected);
		ctx.setVariable("optionalsSelected", tempOrder.getOptionalsSelected());
		ctx.setVariable("totalAmount", tempOrder.getTotalAmount());
		
		
		templateEngine.process(path, ctx, response.getWriter());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
	}

}
