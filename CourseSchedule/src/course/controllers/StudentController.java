package course.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import course.business.Section;
import course.business.Student;
import course.data.StudentDB;
import java.io.PrintWriter;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentController extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
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
			String action = (String) ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("action"), "SafeString", 200, false);
			HashMap<Integer,Section> studentsections= new HashMap<Integer,Section>();
			StudentDB dao = new StudentDB();
			String errormsg=null;
			HttpSession session=null;
			if(action.equals("view"))
			{
				
				RequestDispatcher rd=request.getRequestDispatcher("viewschedulemain.jsp");  
				rd.forward(request, response);
			}
			
			else if(action.equals("Submit"))
			{
				session = request.getSession(true);
				int studentId = (int)session.getAttribute("id");
				String semseter = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("term"), "SafeString", 200,
						false);
				boolean b = dao.getScheduledDates(semseter);
				if(b)
				{
				 studentsections=dao.dropCourse(studentId,semseter);
				 if(studentsections.size() ==0)
			      {
			    	  errormsg = "You don't have any schedule classes to drop";
			    	  request.setAttribute("errormsg", errormsg);
			      }
				request.setAttribute("studentsectionlist", studentsections);
				}
				
				else
				{
					  errormsg = "You can't drop the courses for the selected semester, you can only view the "
					  		+ "list of courses";
			    	  request.setAttribute("errormsg", errormsg);
				}
				
				RequestDispatcher rd=request.getRequestDispatcher("dropCourse.jsp");  
				rd.forward(request, response);
			}
			else if(action.equals("student"))
			{
				request.setAttribute("loginuser", "student");
				HttpSession loginsession = request.getSession();
				loginsession.setAttribute("loginuser", "student");
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
				rd.forward(request, response);
			}
			else if(action.equals("admin"))
			{
				request.setAttribute("loginuser", "admin");
				HttpSession loginsession = request.getSession();
				loginsession.setAttribute("loginuser", "admin");
				RequestDispatcher rd=request.getRequestDispatcher("adminlogin.jsp");  
				rd.forward(request, response);
			}
			else if(action.equals("logout"))
			{
				session= request.getSession();
				session.invalidate();
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
				rd.forward(request, response);
				
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
	
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			String action = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("submit"), "SafeString", 200, false);
			StudentDB dao = new StudentDB();
			Student std= new Student();
			String invalidcredentials = null;
			String errormsg = null;
			HttpSession session=null;
			HashMap<Integer,Section> studentsections= new HashMap<Integer,Section>();
			HashMap<Integer,Section> sections = new HashMap<Integer,Section>();
			boolean exists=false;
			if(action.equals("Login"))
			{
				int id = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("id"), "SafeString", 200,
						false));
				String password= ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("password"), "SafeString", 200,
						false);
				String hashResult = "";
				
				try {
					hashResult = HashPassword.hashPassword(password);
				}
				catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					Student stu = dao.validateUser(id);
					
					if(hashResult.equals(stu.getPassword()))
					{
						session= request.getSession();
						session.setAttribute("id", id);
						session.setAttribute("usercontrol", "student");
						RequestDispatcher rd=request.getRequestDispatcher("student_details.jsp");  
						rd.forward(request, response);
					}
					
					else
					{
						invalidcredentials ="Incorrect UserName/Password";
						RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
						request.setAttribute("invalidcredentials", invalidcredentials);
						rd.forward(request, response);
					} 

			    } 
			else if (action.equals("Register")) 
			{
			int id = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("id"), "SafeString", 200, false));
			String firstName = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("firstname"), "SafeString", 200, false);
			String lastName = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("lastname"), "SafeString", 200, false);
			String email = request.getParameter("email");
			String password = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("password"), "SafeString", 200, false);
			String cnfrmpassword = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("cpassword"), "SafeString", 200, false);
			
			HttpSession loginsession = request.getSession();
			String user = (String) loginsession.getAttribute("loginuser");
			
			if(user.equals("student"))
			{
				exists = dao.checkStudentexists(id);
			}

			if(!exists)
			{
				request.setAttribute("errormsg", "The id you entered didn't match our records. Please enter a valid id ");
				request.setAttribute("email", email);
				request.setAttribute("firstname", firstName);
				request.setAttribute("lastname", lastName);
				request.setAttribute("id", id);
				
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");  
				  
				rd.forward(request, response);
			}
			else if(password.length()<6){
				request.setAttribute("errormsg", "Password length must be minimum 6 characters.");
				request.setAttribute("email", email);
				request.setAttribute("firstname", firstName);
				request.setAttribute("lastname", lastName);
				request.setAttribute("id", id);
				
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");  
				  
				rd.forward(request, response);
			}
			
			else if (!password.equals(cnfrmpassword))
			{
				request.setAttribute("email", email);
				request.setAttribute("firstname", firstName);
				request.setAttribute("lastname", lastName);
				request.setAttribute("id", id);
				request.setAttribute("errormsg", "Both the passwords should match");
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");  
				  
				rd.forward(request, response);			
			}		
			else
			{
			if(user.equals("student"))
			{
			
			boolean idexists = dao.checkStudent(id);
			
			boolean  emailexists = dao.checkStudentEmail(email);
			
			if(idexists)
			{
				request.setAttribute("errormsg", "A valid account exists with this id, please use login to login to your account");
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");  
				  
				rd.forward(request, response);
			}
			
			else if(emailexists)
			{
				request.setAttribute("errormsg", "A valid account exists with this email, please use different email");
				RequestDispatcher rd=request.getRequestDispatcher("register.jsp");  
				  
				rd.forward(request, response);
			}
			
			else
			{
				String hashResult  ;
				try {
					hashResult = HashPassword.hashPassword(password);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					hashResult = "error";
				}
				if(!hashResult.equals("error")){
					password = hashResult;
				}
			std = new Student(id,firstName,lastName,email,password);
			int result = dao.addStudent(std);
			if(result>=0)
			{
				session= request.getSession();
				session.setAttribute("id", id);
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
				  
				rd.forward(request, response);
			}
			}
			}
			}
			}
			else if(action.equals("Submit"))
			{
				String term = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("term"), "SafeString", 200,
						false);
				String dept = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("dept"), "SafeString", 200,
						false);
				session= request.getSession();
				session.setAttribute("term", term);
				session.setAttribute("dept", dept);
				session = request.getSession(true);
				int studentId = (int)session.getAttribute("id");
			
					studentsections=dao.checkStatus(studentId,term);
					request.setAttribute("studentsectionlist", studentsections);
					
					if(studentsections.size()==4)
					{
						errormsg= "You have already enrolled for 4 courses , you can only enroll for a"
								+ "new course if you drop a already registered course";
						request.setAttribute("errormsg", errormsg);
					}
					
					else
					{
						
					      sections = dao.getAllSections(dept, term);
					      System.out.println("size is"+sections.size());
					      if(sections.size() ==0)
					      {
					    	  errormsg = "There are no more sections avaibable in the selected department for the selected semester ";
					    	  request.setAttribute("errormsg", errormsg);
					      }
					      request.setAttribute("sectionlist", sections);
					      
					}
				RequestDispatcher rd=request.getRequestDispatcher("addCourse.jsp");  
				rd.forward(request, response);
			}
			
			else if(action.equals("Add"))
			{
				 HttpSession logsession = request.getSession(false);      
			        if (logsession == null || logsession.getAttribute("loginuser") == null) {
			            response.sendRedirect("index.jsp"); // No logged-in user found, so redirect to login page.
			        }
				session = request.getSession(true);
				int studentId = (int)session.getAttribute("id");
				String dept= (String) session.getAttribute("dept");
				String term =(String) session.getAttribute("term");
				int sectionId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("section"), "SafeString", 200,
						false));
				int count=0;
				String departmnet = dao.getDepartmnet(studentId);
				
				studentsections = dao.checktimeStatus(studentId,term);
			    Section s = dao.getSection(sectionId);
				boolean b = dao.checkForConflicts(studentsections,s);
				System.out.println("conflict occurs" + b);
				if(!departmnet.equals(dept))
				{
			     count = dao.getOtherdepartmentCount(studentId,departmnet,term);
				}
				if(count==1)
				{
					String resultMessage="You can enroll for only one course of other department per semester.It seems that"
							+ " you have already enrolled for one course from other department. Please check your schedule for"
							+ " other details  ";
			    	request.setAttribute("resultmessage", resultMessage);
			    	 request.setAttribute("sectionlist", sections);
			    	RequestDispatcher rd=request.getRequestDispatcher("addCourse.jsp");  
					rd.forward(request, response);
				}
				else if(b)
				{
					String resultMessage= "Conflict Error: Schedule conflict with the course you have enrolled before";
			    	request.setAttribute("resultmessage", resultMessage);
			    	request.setAttribute("sectionlist", sections);
			    	RequestDispatcher rd=request.getRequestDispatcher("addCourse.jsp");  
					rd.forward(request, response);
				}
				else
				{
			    int result=dao.addSection(studentId, sectionId);
			    
			    if(result==1)
			    {
			    	String resultMessage="You have successfully added the course to your schedule";
			    	request.setAttribute("resultmessage", resultMessage);
			    }
			    else
			    {
			    	String resultMessage="Error Occured while adding the course to your schedule. Please try again!!!";
			    	request.setAttribute("resultmessage", resultMessage);
			    }
			    
			    studentsections=dao.checkStatus(studentId,term);
				
				if(studentsections.size()==4)
				{
					errormsg= "You have enrolled for 4 courses , you can only enroll for a"
							+ "new course if you drop a already registered course";
					request.setAttribute("errormsg", errormsg);
				}
				
				else
				{
					
				      sections = dao.getAllSections(dept, term);
				      if(sections.size() ==0)
				      {
				    	  errormsg = "There are no more sections avaibable in the selected department for the selected semester ";
				    	  request.setAttribute("errormsg", errormsg);
				      }
				      request.setAttribute("sectionlist", sections);
				      
				}
			    RequestDispatcher rd=request.getRequestDispatcher("addCourse.jsp");  
				rd.forward(request, response);
				
				}
			}
			else if(action.equals("Drop"))
			{
				session = request.getSession(true);
				String dept= (String) session.getAttribute("dept");
				String term =(String) session.getAttribute("term");
				int studentId = (int)session.getAttribute("id");
				int sectionId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("section"), "SafeString", 200,
						false));
				studentsections=dao.checkStatus(studentId,term);
				if(studentsections.size() ==0)
			      {
			    	  errormsg = "You don't have any schedule classes to drop";
			    	  request.setAttribute("errormsg", errormsg);
			      }
				
				else
				{
				
				int result =dao.dropSection(studentId, sectionId);
				
				if(result==1)
				{
					String resultMessage="You have successfully dropped the course from your schedule";
			    	request.setAttribute("resultmessage", resultMessage);
				}
				
				else
				{
					String resultMessage="Error Occured while dropping the course from your schedule. Please try again!!!";
			    	request.setAttribute("resultmessage", resultMessage);
				}
				}
				
				studentsections=dao.checkStatus(studentId,term);
				request.setAttribute("studentsectionlist", studentsections);
				
				RequestDispatcher rd=request.getRequestDispatcher("dropCourse.jsp");  
				rd.forward(request, response);
				
				
			}
			
			else if(action.equals("View Schedule"))
			{
				session = request.getSession(true);
				int studentId = (int)session.getAttribute("id");
				String term = (String) ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("term"), "SafeString", 200,
						false);
				System.out.println("action" +studentId);
				ArrayList<Section> section=dao.getStudentSections(studentId,term);
				 if(section.size() ==0)
			      {
			    	  errormsg = "You don't have any schedule as you did not enroll for any class";
			    	  request.setAttribute("errormsg", errormsg);
			      }
				request.setAttribute("section", section);
				RequestDispatcher rd=request.getRequestDispatcher("viewschedule.jsp");  
				rd.forward(request, response);
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

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);      
        if (session == null || session.getAttribute("loginuser") == null) {
            response.sendRedirect("index.jsp"); // No logged-in user found, so redirect to login page.
        } else {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            chain.doFilter(req, res);  
        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		 

		
	}		
	
}