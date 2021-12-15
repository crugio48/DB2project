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

import beans.TempOrder;
import entities.OptionalProduct;
import entities.Customer;
import entities.Order;
import services.OrderService;


@WebServlet("/GoToConfirmationWithOldOrder")
public class GoToConfirmationWithOldOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/OrderService")
	private OrderService orderService;
    
    public GoToConfirmationWithOldOrder() {
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
		
		String homePagePath = request.getServletContext().getContextPath() + "/GoToHomeCustomer";
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("customer");
		
		if (customer == null) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		int orderId = 0;
		try {
			orderId = Integer.parseInt(request.getParameter("orderId"));
		} catch(NumberFormatException | NullPointerException e) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		Order order = orderService.getOrder(orderId);
		
		if (order == null || !order.getCustomer().getUsername().equals(customer.getUsername())
				|| !order.getStatus().equals("rejected")) {
			session.setAttribute("errorMsg", "Don't hack please");
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		TempOrder tempOrder = new TempOrder(orderId);
		
		session.setAttribute("tempOrder", tempOrder);
		
		
		String path = "/WEB-INF/customer/ConfirmationPage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		
		ctx.setVariable("servicePackageSelected", order.getServicePackage());
		ctx.setVariable("validityPeriodSelected", order.getValidityPeriod());
		ctx.setVariable("optionalsSelected", order.getOptionalProducts());
		ctx.setVariable("totalAmount", order.getTotal_value());
		ctx.setVariable("startDate", order.getStart_date());
		
		
		templateEngine.process(path, ctx, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
			
	}

}
