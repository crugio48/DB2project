package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/EmployeeLogCheck")
public class EmployeeLogCheck implements Filter {

    
    public EmployeeLogCheck() {
    }

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("EmployeeLogCheck filter executing ...");
		
		//cast to httpServlet
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//the path for any type of error
		String loginpath = req.getServletContext().getContextPath() + "/Logout";
		
		HttpSession session = req.getSession();
		
		//check if employee has logged in
		if (session.isNew() || session.getAttribute("employee") == null) {
			session.setAttribute("errorMessage", "Employee not logged in to access this functionality");
			res.sendRedirect(loginpath);
			return;
		}
		
		
		chain.doFilter(request, response);
		//do not do anything after the execution of the servlets
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
