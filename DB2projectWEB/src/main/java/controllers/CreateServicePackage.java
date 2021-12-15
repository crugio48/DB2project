package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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

import entities.MobileAndFixedInternet;
import entities.MobilePhone;
import entities.OptionalProduct;
import entities.Service;
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
			session.setAttribute("errorMsg", "Invalid service package name");
			response.sendRedirect(homePagePath);
			return;
		}
		
		//control that service package with that name doesnt exist
		if (servicePackageService.isNameAlreadyPresent(name)) {
			session.setAttribute("errorMsg", "Service package with that name already exists, please choose another one");
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		
		
		//controls on checkboxes of services
		boolean mobilePhoneSelected, fixedPhoneSelected, mobileInternetSelected, fixedInternetSelected;
		try {
			mobilePhoneSelected = Boolean.valueOf(request.getParameter("mobile_phone"));
			fixedPhoneSelected = Boolean.valueOf(request.getParameter("fixed_phone"));
			mobileInternetSelected = Boolean.valueOf(request.getParameter("mobile_internet"));
			fixedInternetSelected = Boolean.valueOf(request.getParameter("fixed_internet"));
		} catch(NullPointerException e) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		Service mobilePhoneService = null;
		Service fixedPhoneService = null;
		Service fixedInternetService = null;
		Service mobileInternetService = null;
		
		
		int nMinutes, nSms, nGigaFix, nGigaMob;
		BigDecimal feeMinutes, feeSms, feeGigaFix, feeGigaMob;
		//all controls on parameters passed
		if (mobilePhoneSelected) {
			try {
				nMinutes = Integer.valueOf(request.getParameter("mobile_phone_minutes"));
				nSms = Integer.valueOf(request.getParameter("mobile_phone_sms"));
				feeMinutes = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_phone_fee_minutes")));
				feeSms = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_phone_fee_sms")));
				
			} catch(NumberFormatException | NullPointerException e) {
				session.setAttribute("errorMsg", "Invalid mobile phone parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			if(feeMinutes.floatValue() < 0 || feeSms.floatValue() < 0 || nMinutes < 0 || nSms < 0) {
				session.setAttribute("errorMsg", "Invalid mobile phone parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			
			mobilePhoneService = new Service("mobile phone");
			mobilePhoneService.setMobilePhone(new MobilePhone(nMinutes, nSms, feeMinutes, feeSms));
			
		}
		
		if (mobileInternetSelected) {
			try {
				nGigaMob = Integer.valueOf(request.getParameter("mobile_gigabytes"));
				feeGigaMob = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_fee_gigabytes")));
				
			} catch(NumberFormatException | NullPointerException e) {
				session.setAttribute("errorMsg", "Invalid mobile internet parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			if(feeGigaMob.floatValue() < 0 || nGigaMob < 0) {
				session.setAttribute("errorMsg", "Invalid mobile internet parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			mobileInternetService = new Service("mobile internet");
			mobileInternetService.setMobileAndFixedInternet(new MobileAndFixedInternet(nGigaMob, feeGigaMob));
		}
		
		if (fixedInternetSelected) {
			try {
				nGigaFix = Integer.valueOf(request.getParameter("fixed_gigabytes"));
				feeGigaFix = BigDecimal.valueOf(Double.parseDouble(request.getParameter("fixed_fee_gigabytes")));
				
			} catch(NumberFormatException | NullPointerException e) {
				session.setAttribute("errorMsg", "Invalid fixed internet parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			if(feeGigaFix.floatValue() < 0 || nGigaFix < 0) {
				session.setAttribute("errorMsg", "Invalid fixed internet parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			fixedInternetService = new Service("fixed internet");
			fixedInternetService.setMobileAndFixedInternet(new MobileAndFixedInternet(nGigaFix, feeGigaFix));
		}
		
		if(fixedPhoneSelected) {
			fixedPhoneService = new Service("fixed phone");
		}
		
		
		//now we need to check the selected optionals
		Map<Integer,Boolean> optionalsSelected = new HashMap<>();
		
		List<OptionalProduct> allOptionals = optionalProductService.getAllOptionalProducts();
		
		for(OptionalProduct p : allOptionals) {
			optionalsSelected.put(p.getOptional_product_id(), false);  //initialize all optional products to false
		}
		
		try {
			for(OptionalProduct p : allOptionals) {
				optionalsSelected.put(p.getOptional_product_id() , Boolean.valueOf(request.getParameter(String.valueOf(p.getOptional_product_id()))));
			}
		} catch(ClassCastException | IllegalArgumentException | NullPointerException e) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		
		
		//at this point all checks have been passed
		
		servicePackageService.createServicePackage(name, mobilePhoneService, fixedPhoneService,
				mobileInternetService, fixedInternetService, optionalsSelected, allOptionals);
		
		
		//creation successfull
		session.setAttribute("errorMsg", "Service package created successfully");
		response.sendRedirect(homePagePath);
		
	}

	
	public void destroy() {
	}
}
