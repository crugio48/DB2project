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
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import entities.OptionalProduct;
import services.OptionalProductService;


@WebServlet("/CreateOptionalProduct")
public class CreateOptionalProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "services/OptionalProductService")
	private OptionalProductService optionalProductService;
	
	
    public CreateOptionalProduct() {
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
		
		String homeEmployeePath = request.getServletContext().getContextPath() + "/GoToHomeEmployee";
		HttpSession session = request.getSession();
		
		String optProdName = request.getParameter("optional_product_name");
		String optProdCost = request.getParameter("optional_product_cost");
		
		if (optProdName == null || optProdCost == null || optProdName.isEmpty() || optProdCost.isEmpty()) {
			session.setAttribute("errorMsg", "Incorrect or missing param values");
			response.sendRedirect(homeEmployeePath);
			return;
		}
		BigDecimal costBigDecimal = null;
		try {
			costBigDecimal = BigDecimal.valueOf(Double.parseDouble(optProdCost));
		}catch (NumberFormatException | NullPointerException e) {
			session.setAttribute("errorMsg", "Incorrect or missing param values");
			response.sendRedirect(homeEmployeePath);
			return;
		}
		
		if(costBigDecimal.floatValue() < 0) {
			session.setAttribute("errorMsg", "Incorrect or missing param values");
			response.sendRedirect(homeEmployeePath);
			return;
		}
		
		
		//check if there is already an opt prod with that name
		
		OptionalProduct optionalProduct = null;
		
		if (optionalProductService.isOptionalProductAlreadyPresent(optProdName)) {
			session.setAttribute("errorMsg", "An optional product with the same name already exists, please choose a new one");
			response.sendRedirect(homeEmployeePath);
			return;
		}
		else {
			//put the opt prod in the db
			optionalProductService.addNewOptionalProduct(optProdName, costBigDecimal);
			session.setAttribute("errorMsg", "Optional product created successfully");
			response.sendRedirect(homeEmployeePath);
			return;
		}
		
				

		
	}
	
	public void destroy(){
		
	}

}
