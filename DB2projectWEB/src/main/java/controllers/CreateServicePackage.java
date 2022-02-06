package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import entities.ValidityPeriod;
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
		int mobilePhoneSelected, fixedPhoneSelected, mobileInternetSelected, fixedInternetSelected;
		try {
			mobilePhoneSelected = Integer.valueOf(request.getParameter("mobile_phones"));
			fixedPhoneSelected = Integer.valueOf(request.getParameter("fixed_phones"));
			mobileInternetSelected = Integer.valueOf(request.getParameter("mobile_internets"));
			fixedInternetSelected = Integer.valueOf(request.getParameter("fixed_internets"));
		} catch(NumberFormatException | NullPointerException e) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		//no service can be selected as negative number
		if (mobilePhoneSelected < 0 || fixedPhoneSelected < 0 || mobileInternetSelected < 0 || fixedInternetSelected < 0) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		//at least one service has to be included
		if ((mobilePhoneSelected + fixedPhoneSelected + mobileInternetSelected + fixedInternetSelected) == 0) {
			session.setAttribute("errorMsg", "Service packages must have at least one service");
			response.sendRedirect(homePagePath);
			return;
		}
		
		List<Service> allServices = new ArrayList();
		
		Service generalService = null;
		
		
		int nMinutes, nSms, nGigaFix, nGigaMob;
		BigDecimal feeMinutes, feeSms, feeGigaFix, feeGigaMob;
		//all controls on parameters passed
		for (int i = 1; i <= mobilePhoneSelected; i++) {
			try {
				nMinutes = Integer.valueOf(request.getParameter("mobile_phone_minutes" + String.valueOf(i)));
				nSms = Integer.valueOf(request.getParameter("mobile_phone_sms" + String.valueOf(i)));
				feeMinutes = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_phone_fee_minutes" + String.valueOf(i))));
				feeSms = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_phone_fee_sms" + String.valueOf(i))));
				
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
			
			
			generalService = new Service("mobile phone");
			generalService.setMobilePhone(new MobilePhone(nMinutes, nSms, feeMinutes, feeSms));
			
			allServices.add(generalService);
			
		}
		
		
		
		for (int i = 1; i <= mobileInternetSelected; i++) {
			try {
				nGigaMob = Integer.valueOf(request.getParameter("mobile_gigabytes" + String.valueOf(i)));
				feeGigaMob = BigDecimal.valueOf(Double.parseDouble(request.getParameter("mobile_fee_gigabytes" + String.valueOf(i))));
				
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
			
			generalService = new Service("mobile internet");
			generalService.setMobileAndFixedInternet(new MobileAndFixedInternet(nGigaMob, feeGigaMob));
			
			allServices.add(generalService);
		}
		
		for (int i = 1; i <= fixedInternetSelected; i++) {
			try {
				nGigaFix = Integer.valueOf(request.getParameter("fixed_gigabytes" + String.valueOf(i)));
				feeGigaFix = BigDecimal.valueOf(Double.parseDouble(request.getParameter("fixed_fee_gigabytes" + String.valueOf(i))));
				
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
			
			generalService = new Service("fixed internet");
			generalService.setMobileAndFixedInternet(new MobileAndFixedInternet(nGigaFix, feeGigaFix));
			
			allServices.add(generalService);
		}
		
		for (int i = 1; i <= fixedPhoneSelected; i++) {
			generalService = new Service("fixed phone");
			
			allServices.add(generalService);
		}
		
		
		//now we need to check the selected optionals
		Map<Integer,Boolean> optionalsSelected = new HashMap<>();
		
		List<OptionalProduct> allOptionals = optionalProductService.getAllOptionalProducts();
		
		if (allOptionals != null) {
			
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
		}
		
		
		
		
		List<ValidityPeriod> validityPeriods = new ArrayList();
		
		int validityCounter = 0;
		try {
			validityCounter = Integer.parseInt(request.getParameter("validity_periods"));
		} catch(NumberFormatException | NullPointerException e) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		//check of at least one validity period selected
		if (validityCounter < 1) {
			session.setAttribute("errorMsg", "Select at least one validity period");
			response.sendRedirect(homePagePath);
			return;
		}
		
		int duration;
		BigDecimal cost;
		
		for (int i = 1; i <= validityCounter; i++) {
			try {
				duration = Integer.parseInt(request.getParameter("validity_period_duration" + String.valueOf(i)));
				cost = BigDecimal.valueOf(Double.parseDouble(request.getParameter("validity_period_cost" + String.valueOf(i))));
			} catch(NumberFormatException | NullPointerException e) {
				session.setAttribute("errorMsg", "Invalid Validity period parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			if(cost.floatValue() < 0 || duration < 0) {
				session.setAttribute("errorMsg", "Invalid Validity period parameters");
				response.sendRedirect(homePagePath);
				return;
			}
			
			validityPeriods.add(new ValidityPeriod(cost, duration));
			
		}
		
		
		
		
		//at this point all checks have been passed
		
		servicePackageService.createServicePackage(name, allServices, validityPeriods, optionalsSelected, allOptionals);
		
		
		//creation successfull
		session.setAttribute("errorMsg", "Service package created successfully");
		response.sendRedirect(homePagePath);
		
	}

	
	public void destroy() {
	}
}
