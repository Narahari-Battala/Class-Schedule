package course.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import course.business.Admin;
import course.business.Course;
import course.business.Department;
import course.business.Room;
import course.business.Section;
import course.business.Student;
import course.data.AdminDB;
import course.data.StudentDB;
import java.io.PrintWriter;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminServlet")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
			String action = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("submit"), "HTTPParameterValue", 200,
					false);
			AdminDB admin = new AdminDB();
			if(action.equals("Add Department"))
			{
				request.setAttribute("action", "Add Department");
				RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp"); 
			    rd1.forward(request, response);
			}
			else if(action.equals("View Departments"))
			{
				ArrayList<Department> departments = admin.getAllDepartments();
				request.setAttribute("departments", departments);
				request.setAttribute("action", "View Departments");
				RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp"); 
			    rd1.forward(request, response);
			}
			else if(action.equals("Delete Department"))
			{
				ArrayList<Department> departments = admin.getAllDepartments();
				request.setAttribute("departments", departments);
				request.setAttribute("action", "Delete Department");
				RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp"); 
			    rd1.forward(request, response);
			}
			
			else if(action.equals("Add Section"))
			{
				ArrayList<Course> courses = admin.getAllCourses();
				ArrayList<Section> semesters = admin.getSemesters();
				ArrayList<Section> buildings = admin.getBuildings();
				ArrayList<Room> rooms = admin.getRooms();
				ArrayList<Department> departments = admin.getAllDepartments();
				request.setAttribute("departments", departments);
				request.setAttribute("action", "Add Section");
				request.setAttribute("courses", courses);
				request.setAttribute("semesters", semesters);
				request.setAttribute("buildings", buildings);
				request.setAttribute("rooms", rooms);
				RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp"); 
			    rd1.forward(request, response);
			}
			else if(action.equals("Delete Section"))
			{
				ArrayList<Section> sections = new ArrayList<Section>();
				sections=admin.getAllSections();
				if(sections.size()==0)
				{
			    request.setAttribute("errormsg", "No Sections exists");
				}
			    request.setAttribute("section", sections);
			    request.setAttribute("action", "delete section");
			    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
			    rd1.forward(request, response);
			}
			else if(action.equals("Add Student"))
			{
				ArrayList<Department> departments = admin.getAllDepartments();
				request.setAttribute("departments", departments);
				request.setAttribute("action", "add student");
			    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
			    rd1.forward(request, response);
			}
			else if(action.equals("View Students"))
			{
				ArrayList<Student> students = new ArrayList<Student>();
				students=admin.getStudents();
				request.setAttribute("students", students);
				request.setAttribute("action", "View Students");
			    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
			    rd1.forward(request, response);
			}
			else if(action.equals("Delete Student"))
			{
				ArrayList<Student> students = new ArrayList<Student>();
				students=admin.getStudents();
				request.setAttribute("students", students);
				request.setAttribute("action", "Delete Student");
			    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
			    rd1.forward(request, response);
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
			// TODO Auto-generated method stub
			String action = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("submit"), "SafeString", 200, false);
			AdminDB dao = new AdminDB();
			Admin admin= new Admin();
			String invalidcredentials = null;
			String errormsg = null;
			HttpSession session=null;
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

						Admin stu = dao.validateUser(id);
						
						if(hashResult.equals(stu.getPassword()))
						{
							session= request.getSession();
							session.setAttribute("id", id);
							session.setAttribute("usercontrol", "admin");
							RequestDispatcher rd=request.getRequestDispatcher("adminlandingpage.jsp");  
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
			String email = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("email"), "Email", 200, false);
			String password = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("password"), "SafeString", 200, false);
			String cnfrmpassword = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("cpassword"), "SafeString", 200, false);
			
			HttpSession loginsession = request.getSession();
			String user = (String) loginsession.getAttribute("loginuser");
			
			if(user.equals("admin"))
			{
				exists = dao.checkAdminexists(id);
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
			if(user.equals("admin"))
			{
			
			boolean idexists = dao.checkAdmin(id);
			
			boolean  emailexists = dao.checkAdminEmail(email);
			
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
			admin = new Admin(id,firstName,lastName,email,password);
			int result = dao.addAdmin(admin);
			if(result>=0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
				  
				rd.forward(request, response);
			}
			}
			}
			}
			}
			
			else if(action.equals("Add Course"))
			{
				ArrayList<Department> departments = dao.getAllDepartments();
				request.setAttribute("departments", departments);
				request.setAttribute("action", "Add Course");
				RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
				  
				rd.forward(request, response);
			}
			
			else if(action.equals("AddCourse"))
			{
				String courseId = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("courseid"), "SafeString", 200,
						false);
				String courseName = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("coursetitle"), "SafeString", 200,
						false);
				String department = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("dept"), "SafeString", 200,
						false);
				
				boolean courseExists = dao.courseExists(courseId);
				if(courseExists)
				{
					request.setAttribute("errormsg", " Course with this course id already exists , please select a "
							+ "different CourseId");
					ArrayList<Department> departments = dao.getAllDepartments();
					request.setAttribute("departments", departments);
					request.setAttribute("action", "Add Course");
					request.setAttribute("cid", courseId);
					request.setAttribute("cname", courseName);
					RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
					  
					rd.forward(request, response);
				}
				
				else
				{
					int result=dao.addCourse(courseId,courseName);
					int course = dao.getCourseId(courseId);
					System.out.println("new course is " + course);
					int result1 = dao.addcoursetoDepartment(course,department);
					request.setAttribute("action", "Add Course");
			      	request.setAttribute("successmsg", "You have Sucessfully added the course"); 
			      	RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
				    rd.forward(request, response);
				}
			}
			
			else if(action.equals("Delete Course"))
			{
				ArrayList<Course> courses = dao.getAllCourses();
				request.setAttribute("courses", courses);
				request.setAttribute("action", "Delete Course");
				RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
				  
				rd.forward(request, response);
				
			}
			else if(action.equals("DeleteCourse"))
			{
				int courseId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("course"), "SafeString", 200,
						false));
				dao.deletecourse(courseId);
				request.setAttribute("successmsg", "You have Sucessfully deleted the course");
				ArrayList<Course> courses = dao.getAllCourses();
				request.setAttribute("courses", courses);
				request.setAttribute("action", "Delete Course");
				RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
				  
				rd.forward(request, response);
			}
			else if(action.equals("View Courses"))
			{
				ArrayList<Course> courses = dao.getAllCourses();
				request.setAttribute("courses", courses);
				request.setAttribute("action", "View Courses");
				RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
				  
				rd.forward(request, response);
			}
			else if(action.equals("AddDepartment"))
			{
				String deptId = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("departmentid"), "SafeString", 200,
						false);
				String deptName = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("departmenttitle"), "SafeString", 200,
						false);
				
				boolean deptExists = dao.departmentExists(deptId);
				if(deptExists)
				{
					request.setAttribute("errormsg", " Departmnet with this id already exists , please select a "
							+ "different Id");
					request.setAttribute("action", "Add Department");
					request.setAttribute("did", deptId);
					request.setAttribute("dname", deptName);
					RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
					  
					rd.forward(request, response);
				}
				
				else
				{
					int result=dao.addDepartment(deptId,deptName);
			      	request.setAttribute("successmsg", "You have Sucessfully added the Department"); 
			      	request.setAttribute("action", "Add Department");
			      	RequestDispatcher rd=request.getRequestDispatcher("coursechange.jsp");  
					  
					rd.forward(request, response);
				}
			}
				else if(action.equals("DeleteDepartment"))
				{
					String departmentId = (ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("department"), "SafeString", 200,
							false));
					dao.deletedepartment(departmentId);
					request.setAttribute("errormsg", "You have Sucessfully deleted the department"); 
					ArrayList<Department> departments = dao.getAllDepartments();
					request.setAttribute("departments", departments);
					request.setAttribute("action", "Delete Department");
					RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");
					rd1.forward(request, response);
				}
				else if(action.equals("AddSection")) 
				{
				    int sectionnum = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("number"), "SafeString", 200,
							false));
				    int seclimit = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("limit"), "SafeString", 200,
							false));
				    int courseId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("course"), "SafeString", 200,
							false));
				    String semester = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("semester"), "SafeString", 200,
							false);
				    int roomId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("room"), "SafeString", 200,
							false));
				    dao.addSection(sectionnum,seclimit,2018,courseId,semester,roomId,2);
				    request.setAttribute("successmsg", "You have Sucessfully added the section");
				    ArrayList<Course> courses = dao.getAllCourses();
					ArrayList<Section> semesters = dao.getSemesters();
					ArrayList<Section> buildings = dao.getBuildings();
					ArrayList<Room> rooms = dao.getRooms();
					ArrayList<Department> departments = dao.getAllDepartments();
					request.setAttribute("departments", departments);
					request.setAttribute("action", "Add Section");
					request.setAttribute("courses", courses);
					request.setAttribute("semesters", semesters);
					request.setAttribute("buildings", buildings);
					request.setAttribute("rooms", rooms);
					RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp"); 
				    rd1.forward(request, response);
				}
				
				else if(action.equals("View Sections")) 
				{
					ArrayList<Section> sections = new ArrayList<Section>();
					sections=dao.getAllSections();
					if(sections.size()==0)
					{
				    request.setAttribute("errormsg", "No Sections exists");
					}
				    request.setAttribute("section", sections);
				    request.setAttribute("action", "view sections");
				    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
				    rd1.forward(request, response);
				}
				else if(action.equals("DeleteSection")) 
				{
					int sectionId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("section"), "SafeString", 200,
							false));
					dao.deletestudentsections(sectionId);
					dao.deletesection(sectionId);
					request.setAttribute("successmsg", "Section deleted successfully");
					ArrayList<Section> sections = new ArrayList<Section>();
					sections=dao.getAllSections();
					if(sections.size()==0)
					{
				    request.setAttribute("errormsg", "No Sections exists");
					}
				    request.setAttribute("section", sections);
				    request.setAttribute("action", "delete section");
				    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
				    rd1.forward(request, response);
					
				}
				else if(action.equals("AddStudent")) 
				{
					int studentId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("studentid"), "SafeString", 200,
							false));
					String department = ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("dept"), "SafeString", 200,
							false);
					exists = dao.studentExists(studentId);
					if(exists)
					{
						ArrayList<Department> departments = dao.getAllDepartments();
						request.setAttribute("departments", departments);
						 request.setAttribute("errormsg", "Student with this id already exists");
						 request.setAttribute("action", "add student");
						 request.setAttribute("sid", studentId);
						    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
						    rd1.forward(request, response);
					}
					else
					{
					     dao.addStudent(studentId,department);
					     ArrayList<Department> departments = dao.getAllDepartments();
							request.setAttribute("departments", departments);
					    request.setAttribute("action", "add student");
					    request.setAttribute("successmsg", "Student added successfully");
					    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
					    rd1.forward(request, response);
					}
				}
				else if(action.equals("DeleteStudent")) 
				{
				     int studentId = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", request.getParameter("student"), "SafeString", 200,
							false));
				       dao.deleteStudentdetails(studentId);
				       dao.deletestudentfromsection(studentId);
				       dao.deletestudent(studentId);
				        ArrayList<Student> students = new ArrayList<Student>();
						students=dao.getStudents();
						request.setAttribute("students", students);
						 request.setAttribute("successmsg", "Student deleted successfully");
						request.setAttribute("action", "Delete Student");
					    RequestDispatcher rd1=request.getRequestDispatcher("coursechange.jsp");  
					    rd1.forward(request, response);
				     
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
	}


