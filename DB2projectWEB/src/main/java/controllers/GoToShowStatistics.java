package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import services.AaTablesService;


@WebServlet("/GoToShowStatistics")
public class GoToShowStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/AaTablesService")
	AaTablesService aaTablesService;
    
    public GoToShowStatistics() {
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
		
		
		
		String path = "/WEB-INF/employee/SalesReport.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		ctx.setVariable("totalPurchasesList", aaTablesService.getTotalPurchasesOfPackages());
		
		ctx.setVariable("totalPurchasesPerValList", aaTablesService.getTotalPurchasesOfPackagesPerVal());
		
		ctx.setVariable("totalRevenueWithOptionalsList", aaTablesService.getTotalRevenueWithOptionals());
		
		ctx.setVariable("averageOptionalsList", aaTablesService.getAverageOptionals());
		
		ctx.setVariable("insolventUsersList", aaTablesService.getInsolventUsers());
		
		ctx.setVariable("alertList", aaTablesService.getAlerts());
		
		ctx.setVariable("suspendedOrdersList", aaTablesService.getSuspendedOrders());
		
		ctx.setVariable("optionalsTotalRevenueBestSellersList", aaTablesService.getOptionalsTotalRevenueBestSellers());
		
		templateEngine.process(path, ctx, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
	}

}
