package controllers;

import java.io.IOException;
import java.util.Date;

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

import beans.TempOrder;
import entities.Customer;
import services.OrderService;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "services/OrderService")
	private OrderService orderService;
    
    public CreateOrder() {
        super();
        // TODO Auto-generated constructor stub
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
		
		TempOrder tempOrder = (TempOrder) session.getAttribute("tempOrder");
		Customer customer = (Customer) session.getAttribute("customer");
		
		if(customer == null  || tempOrder == null ) {
			response.sendRedirect(homePagePath);
			return;
		}
		
		
		
		String outcome = null;
		if (randomPaymentSuccessfull()) {
			outcome = "payed";
		}
		else {
			outcome = "rejected";
		}
		
		Date date = new Date(System.currentTimeMillis());
		
		orderService.createOrder(tempOrder.getServicePackageId(), tempOrder.getValidityPeriodId(), tempOrder.getOptionalsSelected(), tempOrder.getTotalAmount(), tempOrder.getStartDate(), customer.getUsername(), date , outcome);
		
		response.sendRedirect(homePagePath);	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
	}
	
	public void destroy() {
		
	}
	
	//the method returns randomically true or false
	private boolean randomPaymentSuccessfull() {
		if (Math.random() >= 0.5) {
			return true;
		}
		else {
			return false;
		}
	}
}
