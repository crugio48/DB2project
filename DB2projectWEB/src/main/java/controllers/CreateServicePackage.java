package controllers;

import java.io.IOException;
import java.math.BigDecimal;

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

import services.OptionalProductService;
import services.ServicePackageService;


@WebServlet("/CreateServicePackage")
public class CreateServicePackage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ServicePackageService")
	ServicePackageService servicePackageService;
	
	@EJB(name = "services/OptionalProductService")
	OptionalProductService optionalProductService;
    
    public CreateServicePackage() {
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
		String homePagePath = request.getServletContext().getContextPath() + "/GoToHomeEmployee";
		
		HttpSession session = request.getSession();
		
		//control of parameter of service package name
		String name = request.getParameter("service_package_name");		
		if (name == null || name.isEmpty()) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		//TODO control that service package with that name doesnt exist
		
		
		
		//controls on checkboxes of services
		boolean mobilePhoneSelected, fixedPhoneSelected, mobileInternetSelected, fixedInternetSelected;
		try {
			mobilePhoneSelected = Boolean.valueOf(request.getParameter("mobile_phone"));
			fixedPhoneSelected = Boolean.valueOf(request.getParameter("fixed_phone"));
			mobileInternetSelected = Boolean.valueOf(request.getParameter("mobile_phone_sms"));
			fixedInternetSelected = Boolean.valueOf(request.getParameter("mobile_phone_fee_minutes"));
		} catch(NullPointerException e) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		int nMinutes, nSms, nGigaFix, nGigaMob;
		BigDecimal feeMinutes, feeSms, feeGigaFix, feeGigaMob;
		//all controls on parameters passed
		if (mobilePhoneSelected) {
			try {
				nMinutes = Integer.valueOf(request.getParameter("mobile_phone_minutes"));
				nSms = Integer.valueOf(request.getParameter("mobile_phone"));
				feeMinutes = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_phone")));
				feeSms = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_phone")));
				
			} catch(NumberFormatException | NullPointerException e) {
				response.sendRedirect(homePagePath);
				return;
			}
			
			
			//TODO control
			
		}
		
		if (mobileInternetSelected) {
			try {
				nGigaMob = Integer.valueOf(request.getParameter("mobile_gigabytes"));
				feeGigaMob = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_fee_gigabytes")));
				
			} catch(NumberFormatException | NullPointerException e) {
				response.sendRedirect(homePagePath);
				return;
			}
			
			
			//TODO control
		}
		
		if (fixedInternetSelected) {
			try {
				nGigaFix = Integer.valueOf(request.getParameter("fixed_gigabytes"));
				feeGigaFix = BigDecimal.valueOf(Double.parseDouble(request.getParameter("fixed_fee_gigabytes")));
				
			} catch(NumberFormatException | NullPointerException e) {
				response.sendRedirect(homePagePath);
				return;
			}
			
			
			//TODO control
		}
		
		
		
		//TODO create services
		//TODO create service package
		
		
		
		
		String path = getServletContext().getContextPath() + "/GoToHomeEmployee";
		response.sendRedirect(path);
		
	}

	
	public void destroy() {
	}
}
