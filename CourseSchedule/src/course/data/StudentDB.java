package course.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import course.business.Section;
import course.business.Student;
import java.io.PrintWriter;


public class StudentDB {
	
	HashMap<Integer,Section> studentsections ;
	HashMap<Integer,Section> sections;
	public int addStudent(Student s)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "insert into form.student values(?,?,?,?,?)";
		PreparedStatement ps=null;
		int result;
		
		try {
			    ps=connection.prepareStatement(query);
			    ps.setInt(1, s.getId());
			    ps.setString(2, s.getFirstName());
			    ps.setString(3, s.getLastName());
			    ps.setString(4, s.getEmail());
			    ps.setString(5, s.getPassword());
			    result = ps.executeUpdate();
			    
		} catch (SQLException e) {
            System.err.println(e);
            return 0;
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	    
	    return result;
		
	}
	
	public Student validateUser(int id)
	{
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			Student s = new Student();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from form.student where id =? ";
			try {
				    ps=connection.prepareStatement(query);
				    ps.setInt(1,id);
				    rs = ps.executeQuery();
				    while(rs.next())
				    {
				    	String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200,
								false);
				    	String lname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString",
								200, false);
				    	String email = rs.getString(4);
				    	String password = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(5), "SafeString",
								200, false);
				    	
				    	s = new Student(id,fname,lname,email,password);
				    }
				    
				    }
			  
			 catch (SQLException e) {
			        System.err.println(e);
			    } finally {
			        DBUtil.closeResultSet(rs);
			        DBUtil.closePreparedStatement(ps);
			        pool.freeConnection(connection);
			    }
			return s;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
	}
	
	public HashMap<Integer,Section> checkStatus(int studentid, String term)

	{
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			studentsections = new HashMap<Integer,Section>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query ="select s.section_id,s.section_num,s.section_limit,s.year,s.semester,s.room, c.course_name,dc.department from form.section s inner join form.course c on "
					+ " c.course_id = s.course inner join form.departmentcourse dc on c.course_id = dc.course where s.semester=? and section_id in (select section from form.studentsection where student= ?)";
			try
			{
			ps=connection.prepareStatement(query);
			ps.setString(1, term);
			ps.setInt(2, studentid);
			rs = ps.executeQuery();
			while(rs.next())
			{
				int sectionId = rs.getInt(1);
				int sectionNum= rs.getInt(2);
				int sectionLimit = rs.getInt(3);
				int year = rs.getInt(4);
				String semseter = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(5), "SafeString", 200, false);
				int room = rs.getInt(6);
				String courseName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(7), "SafeString", 200,
						false);
				String department = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(8), "SafeString", 200,
						false);
				Section sec= new Section(sectionId,sectionNum,sectionLimit,year,semseter,room,courseName,department);
				studentsections.put(sectionId,sec);
				
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closeResultSet(rs);
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return studentsections;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
	}
	
	public HashMap<Integer,Section> getAllSections(String department, String term)
	{
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			sections = new HashMap<Integer,Section>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query= "select (s.section_id),s.section_num,s.year,s.semester,r.roomnum,b.buildingname,d.dept_id,l.lecturetype,"
					+ "ts.starttime,ts.endtime, ds.dayvalue,tsec.fname,tsec.lname,s.section_limit,c.course_name "
					+ "from form.section s inner join form.course c on s.course=c.course_id inner join form.departmentcourse dc "
					+ "on c.course_id=dc.course inner join form.department d on dc.department = d.dept_id inner join form.lecture_type l "
					+ "on l.id = s.lecture_type inner join form.room r on s.room=r.id inner join form.building b "
					+ "on b.id=r.building inner join form.sectiondaytime sd on s.section_id = sd.section inner join form.timeschedule ts "
					+ "on sd.timeid = ts.id inner join form.dayschedule ds "
					+ "on sd.dayid = ds.id inner join form.teachersection t on t.section = s.section_id inner join form.teacher tsec on t.teacher = tsec.teacher_id where d.dept_id= ? and s.semester=?";
			try
			{
			ps=connection.prepareStatement(query);
			ps.setString(1, department);
			ps.setString(2, term);
			rs= ps.executeQuery();
			while(rs.next())
			{
				int sectionId = rs.getInt(1);
				int sectionNum= rs.getInt(2);
				int year =rs.getInt(3);
				String semester = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(4), "SafeString", 200, false);
				int roomno = rs.getInt(5);
				String building = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(6), "SafeString", 200,
						false);
				String departmentId = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(7), "SafeString", 200,
						false);
				String lectureType = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(8), "SafeString", 200,
						false);
				String starttime = rs.getString(9);
				String endtime =rs.getString(10);
				String day =ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(11), "SafeString", 200,
						false);
				String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(12), "SafeString", 200,
						false);
				String lname =ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(13), "SafeString", 200,
						false);
				int sectionLimit = rs.getInt(14);
				String courseName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(15), "SafeString", 200,
						false);
				for(Map.Entry<Integer, Section> en:studentsections.entrySet())
				{
					System.out.println("section is"+en.getKey() + " " + en.getValue().getSectionNum());
				}
				if(!(studentsections.containsKey(sectionId)))
				{
					System.out.println("not conatians0" + sectionId);
				Section sec= new Section(sectionId,sectionNum,sectionLimit,year,courseName,day,semester,roomno,building,departmentId,lectureType,starttime,endtime,fname+" " + lname);
				sections.put(sectionId,sec);
				}
			}
			}
			
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closeResultSet(rs);
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return sections;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
		
	    }
	
	public int addSection(int studentId, int sectionId)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
	    String query= "insert into form.studentsection(student,section) values(?,?) ";
	    int result;
	    try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setInt(1, studentId);
	    ps.setInt(2, sectionId);
	    result=ps.executeUpdate();
	    }
	    catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return result;
	    
	}
	
	public int dropSection(int studentId, int sectionId)
	{
	
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
	    String query= "delete from form.studentsection where student=? and section=? ";
	    int result;
	    try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setInt(1, studentId);
	    ps.setInt(2, sectionId);
	    result=ps.executeUpdate();
	    }
	    catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return result;
	}
	
	 public ArrayList<Section> getStudentSections(int studentId, String term)
	 {
		    
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			ResultSet rs =null;
			ArrayList<Section> section= new ArrayList<Section>();
			System.out.println("inside getstudents");
			String query= "select (s.section_id),s.section_num,s.year,s.semester,r.roomnum,b.buildingname,d.dept_id,l.lecturetype,"
		    		+ "ts.starttime,ts.endtime, ds.dayvalue,tsec.fname,tsec.lname,c.course_name, s.section_limit "
		    		+ "from form.section s inner join form.course c on s.course=c.course_id inner join form.departmentcourse dc "
		    		+ "on c.course_id=dc.course inner join form.department d on dc.department = d.dept_id inner join form.lecture_type l "
		    		+ "on l.id = s.lecture_type inner join form.room r on s.room=r.id inner join form.building b "
		    		+ "on b.id=r.building inner join form.sectiondaytime sd on s.section_id = sd.section inner join form.timeschedule ts "
		    		+ "on sd.timeid = ts.id inner join form.dayschedule ds "
		    		+ "on sd.dayid = ds.id inner join form.teachersection t on t.section = s.section_id inner join form.teacher "
		    		+ "tsec on t.teacher = tsec.teacher_id inner join form.studentsection ss on "
		    		+ "ss.section = s.section_id where ss.student= ? and s.semester=?";
			try
		    {
		    ps=connection.prepareStatement(query);
		    ps.setInt(1, studentId);
		    ps.setString(2, term);
		    rs= ps.executeQuery();
		    while(rs.next())
		    {
		    	int sectionId = rs.getInt(1);
				int sectionNum= rs.getInt(2);
				int year =rs.getInt(3);
				String semester = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(4), "SafeString", 200,
						false);
				int roomno = rs.getInt(5);
				String building = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(6), "SafeString", 200,
						false);
				String departmentId = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(7), "SafeString", 200,
						false);
				String lectureType = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(8), "SafeString", 200,
						false);
				String starttime = rs.getString(9);
				String endtime = rs.getString(10);
				String day =ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(11), "SafeString", 200,
						false);
				String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(12), "SafeString", 200,
						false);
				String lname =ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(13), "SafeString", 200,
						false);
				String courseName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(14), "SafeString", 200,
						false);
				int sectionlimit = rs.getInt(15);
				System.out.println(sectionId + " " + sectionNum  + " " +  semester +" "+ roomno + " " + building + " " +departmentId + " " + lectureType + starttime + endtime + day + fname + lname+ courseName );
				Section sec= new Section(sectionId,sectionNum,sectionlimit,year,courseName,day,semester,roomno,building,departmentId,lectureType,starttime,endtime,fname+" " + lname);
				section.add(sec);
		    }
		    }
		    catch (SQLException e) {
	            System.err.println(e);
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return section;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
			
			
	 }

	public boolean checkStudent(int id) {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			String query= "select * from form.student where id=?";
			ResultSet rs;
			Student s= null;
			try
			{
			ps=connection.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int stuid = rs.getInt(1);
				String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200, false);
				String lname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString", 200,
						false);
				String email = rs.getString(4);
				String password = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(5), "SafeString", 200,
						false);
				
				s = new Student(stuid,fname,lname,email,password);
				
				return true;
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return false;
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
			
			
			return false;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return false;
		}	
	    
	}

	public boolean checkStudentEmail(String email) {
		
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			String query= "select * from form.student where email=?";
			ResultSet rs;
			Student s= null;
			try
			{
			ps=connection.prepareStatement(query);
			ps.setString(1, email);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200, false);
				String lname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString", 200,
						false);
				String stemail = rs.getString(4);
				String password = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(5), "SafeString", 200,
						false);
				
				s = new Student(id,fname,lname,stemail,password);
				return true;
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return false;
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
			
			
			return false;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return false;
		}

}
	
	public HashMap<Integer,Section> dropCourse(int studentid, String semester)

	{
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			studentsections = new HashMap<Integer,Section>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query ="select s.section_id,s.section_num,s.section_limit,s.year,s.semester,s.room, c.course_name,dc.department from form.section s inner join form.course c on "
					+ " c.course_id = s.course inner join form.departmentcourse dc on c.course_id = dc.course where section_id in (select section from form.studentsection where student= ? and semester=?)";
			try
			{
			ps=connection.prepareStatement(query);
			ps.setInt(1, studentid);
			ps.setString(2, semester);
			rs = ps.executeQuery();
			while(rs.next())
			{
				int sectionId = rs.getInt(1);
				int sectionNum= rs.getInt(2);
				int sectionLimit = rs.getInt(3);
				int year = rs.getInt(4);
				String semseter = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(5), "SafeString", 200, false);
				int room = rs.getInt(6);
				String courseName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(7), "SafeString", 200,
						false);
				String department = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(8), "SafeString", 200,
						false);
				Section sec= new Section(sectionId,sectionNum,sectionLimit,year,semseter,room,courseName,department);
				studentsections.put(sectionId,sec);
				
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closeResultSet(rs);
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return studentsections;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
	}
	
	public Boolean getScheduledDates(String term)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
        Date start=null;
        Date end=null;
	    String query= "select start_date,end_date from form.semester where id=?";
	    ResultSet rs=null;
	    Student s= null;
	    try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setString(1, term);
	    rs=ps.executeQuery();
	    while(rs.next())
	    {
	            start = rs.getDate(1);
	            end = rs.getDate(2);
	    }
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    if(end.before(date))
	    {
	    	return false;
	    }
	}
	    catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	    
	    return true;
	}

	public String getDepartmnet(int id) {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			String query= "select department from form.studentdetails where id=?";
			ResultSet rs=null;
			try
			{
			ps=connection.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next())
			{
				String department = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(1), "SafeString", 200, false);
				return department;
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closeResultSet(rs);
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return null;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
	}

public boolean checkStudentexists(int id) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
	    String query= "select * from form.studentdetails where id=?";
	    ResultSet rs=null;
	    try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setInt(1, id);
	    rs=ps.executeQuery();
	    while(rs.next())
	    {
	    	int studentId = rs.getInt(1);
	    	if(studentId == id)
	    	{
	    		return true;
	    	}
	    }
	    }
	    catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	    
		return false;
	}

	public int getOtherdepartmentCount(int studentId, String department,String term) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
        int count=0;
	    String query= "select s.section_id from form.studentsection ss inner join form.section s "
	    		+ "on ss.section = s.section_id inner join form.departmentcourse dc on"
	    		+ " s.course = dc.course where ss.student=? and dc.department != ? and s.semester=? ";
	    ResultSet rs=null;
	    try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setInt(1, studentId);
	    ps.setString(2, department);
	    ps.setString(3, term);
	    rs=ps.executeQuery();
	    while(rs.next())
	    {
	    	count++;
	    }
	    }
	    catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return count;
	}

	public boolean checkForConflicts(HashMap<Integer, Section> studentsections2, Section s) {

         for(Map.Entry<Integer, Section> entry:studentsections2.entrySet())
         {
        	 if((entry.getValue().getDay().equals(s.getDay())) && (entry.getValue().getEndTime().equals(s.getEndTime())) 
        			 && (entry.getValue().getStarttime().equals(s.getStarttime())) && (entry.getValue().getYear()==(s.getYear()))
        			&& (entry.getValue().getSemseter().equals(s.getSemseter())))
        	 {
        		return true; 
        	 }
         }
		return false;
	}

	public Section getSection(int sectionId) {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			String query= "select s.section_id,s.year,s.semester,ds.dayvalue,ts.starttime,ts.endtime from form.section s inner join "
					+ "form.sectiondaytime sd on"
					+ " s.section_id = sd.section inner join form.dayschedule ds "
					+ " on sd.dayid = ds.id inner join form.timeschedule ts on "
					+ "ts.id = sd.timeid  where s.section_id=?";
			ResultSet rs=null;
			try
			{
			ps=connection.prepareStatement(query);
			ps.setInt(1, sectionId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int secId = rs.getInt(1);
				int year = rs.getInt(2);
				String semester = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString", 200, false);
				String day = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(4), "SafeString", 200,
						false);
				String starttime =rs.getString(5);
				String endtime = rs.getString(6);
				Section s = new Section(secId,year,semester,day,starttime,endtime);
				return s;
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closeResultSet(rs);
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return null;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
	}

	public HashMap<Integer, Section> checktimeStatus(int studentId, String term) {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			sections = new HashMap<Integer,Section>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			String query= "select s.section_id,s.year,s.semester,ds.dayvalue,ts.starttime,ts.endtime from "
					+ "form.section s inner join form.studentsection ss on s.section_id = ss.section inner join"
					+ " form.sectiondaytime sd on"
					+ " s.section_id = sd.section inner join form.dayschedule ds "
					+ " on sd.dayid = ds.id inner join form.timeschedule ts on "
					+ "ts.id = sd.timeid  where ss.student=? and s.semester = ?";
			ResultSet rs=null;
			try
			{
			ps=connection.prepareStatement(query);
			ps.setInt(1, studentId);
			ps.setString(2, term);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int secId = rs.getInt(1);
				int year = rs.getInt(2);
				String semester = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString", 200, false);
				String day = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(4), "SafeString", 200,
						false);
				String starttime =rs.getString(5);
				String endtime = rs.getString(6);
				
				Section s = new Section(secId,year,semester,day,starttime,endtime);
				sections.put(secId, s);
				
			}
			}
			catch (SQLException e) {
			    System.err.println(e);
			} finally {
			    DBUtil.closeResultSet(rs);
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return sections;
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
			
			
			return null;
		} catch (IntrusionException e) {
			/* This catch block will be executed when advanced 
			 * intrusion behavior is detected in ESIDE's try block statement. */ 
			
			return null;
		}
	}
}

