package course.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
			String url="/index.jsp";
			String action=ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"), "SafeString", 200, false);
			if(action.equals("logout")){
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			url="/index.jsp";
			}
		} catch (ValidationException e) {
			/* This catch block is executed when ESIDE finds input that did
			 * not match validation rules (e.g., bad user input). */
			
			/* This is a sample Exception Handling code that might need to be modified by the developers based on the code*/
			
			/* PrintWriter errorout = response.getWriter();
			   String referer;
			   try {
			 	    referer = ESAPI.validator().getValidInput("replace ME with validation context",request.getHeader("Referer"), "URL", 200, false);
			      errorout.print("<h1>ESIDE default exception handling</h1>");
			      errorout.print("<b>The input contains invalid characters or in wrong format! <a href="+ ESAPI.encoder().encodeForHTML(referer));
			      errorout.print(">GoBack</a> and try again!</b>");
			   }catch (ValidationException e1){ }
			*/
			
			
			return;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
