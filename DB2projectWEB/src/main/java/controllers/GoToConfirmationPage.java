package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
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
import entities.OptionalProduct;
import entities.ServicePackage;
import entities.ValidityPeriod;
import services.ServicePackageService;
import services.ValidityPeriodService;


@WebServlet("/GoToConfirmationPage")
public class GoToConfirmationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ServicePackageService")
	ServicePackageService servicePackageService;
	
	@EJB(name = "services/ValidityPeriodService")
	ValidityPeriodService validityPeriodService;
	
    
    public GoToConfirmationPage() {
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
		//String loginpath = request.getServletContext().getContextPath() + "/Logout";
		String homePagePath = request.getServletContext().getContextPath() + "/GoToHomeCustomer";
		
		HttpSession session = request.getSession();
		
		int servicePackageId;
		int validityPeriodId;
		
		try {
			servicePackageId = Integer.parseInt(request.getParameter("servicePackageId"));
			validityPeriodId = Integer.parseInt(request.getParameter("validityPeriodId"));
		} catch(NumberFormatException | NullPointerException e) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		ValidityPeriod validityPeriodSelected = null;
		
		if ((validityPeriodSelected = validityPeriodService.getValidityPeriod(validityPeriodId)) == null) {  //check if validity period exists
			response.sendRedirect(homePagePath);
			return;
		}
		
		ServicePackage servicePackageSelected = null;
		
		if ((servicePackageSelected = servicePackageService.getServicePackage(servicePackageId)) == null) {    //check if service package exists
			response.sendRedirect(homePagePath);
			return;
		}
		
		Map<Integer,Boolean> optionalsSelected = new HashMap<>();
		
		for (OptionalProduct p : servicePackageSelected.getOptionalProducts()) {
			optionalsSelected.put(p.getOptional_product_id(), false);  //initialize all optional products to false
			
			//System.out.println(p.getOptional_product_id());
		}
		
		try {
			for(OptionalProduct p : servicePackageSelected.getOptionalProducts()) {   //weird forEach working for the map collection
				optionalsSelected.put(p.getOptional_product_id() , Boolean.valueOf(request.getParameter(String.valueOf(p.getOptional_product_id()))));        //overwrite the false value with the real value
				
				//System.out.println(p.getOptional_product_id() + " " + request.getParameter(String.valueOf(p.getOptional_product_id())));
				//System.out.println(p.getOptional_product_id() + " " + Boolean.valueOf(request.getParameter(String.valueOf(p.getOptional_product_id()))));
			}
		} catch(ClassCastException | IllegalArgumentException | NullPointerException e) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		float totalAmount = 0;
		
		totalAmount += validityPeriodSelected.getDuration() * validityPeriodSelected.getMonthly_fee().floatValue();
		
	
		for (OptionalProduct p : servicePackageSelected.getOptionalProducts()) {
			if (optionalsSelected.get(p.getOptional_product_id())) {
				totalAmount += p.getMonthly_fee().floatValue() * validityPeriodSelected.getDuration();
			}
		}
		
		TempOrder tempOrder = new TempOrder(servicePackageId, validityPeriodId, optionalsSelected, totalAmount);
		
		session.setAttribute("tempOrder", tempOrder);  //setting the tempOrder in the session, if there was already another one it gets replaced
		
		
		String path = "/WEB-INF/customer/ConfirmationPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		
		ctx.setVariable("servicePackageSelected", servicePackageSelected);
		ctx.setVariable("validityPeriodSelected", validityPeriodSelected);
		ctx.setVariable("optionalsSelected", optionalsSelected);
		ctx.setVariable("totalAmount", totalAmount);
		
		
		templateEngine.process(path, ctx, response.getWriter());
		
		
	}

	public void destroy() {
	}
}
