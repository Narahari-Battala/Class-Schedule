package course.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import course.business.Admin;
import course.business.Course;
import course.business.Department;
import course.business.Room;
import course.business.Section;
import course.business.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintWriter;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

public class AdminDB {

	public Admin validateUser(int id) {
	
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			Admin s = new Admin();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from form.admindetails where id =? ";
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
				    	
				    	s = new Admin(id,fname,lname,email,password);
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

	public boolean checkAdminexists(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
	    String query= "select * from form.admin where id=?";
	    ResultSet rs=null;
	    try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setInt(1, id);
	    rs=ps.executeQuery();
	    while(rs.next())
	    {
	    	int adminId = rs.getInt(1);
	    	if(adminId == id)
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
	
public boolean checkAdmin(int id) {
		
		
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
		String query= "select * from form.admindetails where id=?";
		ResultSet rs;
		Admin s= null;
		try
	    {
	    ps=connection.prepareStatement(query);
	    ps.setInt(1, id);
	    rs=ps.executeQuery();
	    while(rs.next())
	    {
	    	int stuid = rs.getInt(1);
	    	String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200, false);
	    	String lname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString", 200, false);
	    	String email = rs.getString(4);
	    	String password = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(5), "SafeString", 200, false);
	    	
	    	s = new Admin(stuid,fname,lname,email,password);
	    	
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

	public boolean checkAdminEmail(String email) {
		
		
		
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
			String query= "select * from form.admindetails where email=?";
			ResultSet rs;
			Admin s= null;
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
				
				s = new Admin(id,fname,lname,stemail,password);
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

	public int addAdmin(Admin s) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "insert into form.admindetails values(?,?,?,?,?)";
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

	public ArrayList<Department> getAllDepartments() {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Department> departments = new ArrayList<Department>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			String query = "Select dept_id,dept_name from form.department";
			Department d;
			PreparedStatement ps=null;
			ResultSet rs =null;
			int result;
			try {
				  ps=connection.prepareStatement(query);
				 rs=ps.executeQuery();
				 while(rs.next())
				 {
					 
				     String departmentId = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(1), "SafeString", 200,
							false);
				     String departmentName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200,
							false);
				     d = new Department(departmentId,departmentName);
				     departments.add(d);
				 }
				 }
			catch (SQLException e) {
			    System.err.println(e);
			} 
			
			finally {
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return departments;
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

	public boolean courseExists(String courseId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Select course_name from form.course where course_number=?";
		Department d;
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setString(1, courseId);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 return true;
			 }
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return false;
	}

	public int addCourse(String courseId, String courseName) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Insert into form.course(course_number,course_name) values(?,?)";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setString(1, courseId);
			  ps.setString(2, courseName);
			  result=ps.executeUpdate();
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
return result;
	}

	public int getCourseId(String courseId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "select course_id from form.course where course_number=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setString(1, courseId);
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				  result = rs.getInt(1);
			  }
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
return result;
	}

	public int addcoursetoDepartment(int course, String department) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Insert into form.departmentcourse values(?,?)";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, course);
			  ps.setString(2, department);
			  result=ps.executeUpdate();
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
return result;
	}

	public ArrayList<Course> getAllCourses() {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Course> departments = new ArrayList<Course>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			String query = "Select * from form.course";
			Course d;
			PreparedStatement ps=null;
			ResultSet rs =null;
			int result;
			try {
				  ps=connection.prepareStatement(query);
				 rs=ps.executeQuery();
				 while(rs.next())
				 {
					 
				     int cid = rs.getInt(1);
				     int cnumber = rs.getInt(2);
				     String courseName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(3), "SafeString", 200,
							false);
				     d = new Course(cid,cnumber,courseName);
				     departments.add(d);
				 }
				 }
			catch (SQLException e) {
			    System.err.println(e);
			} 
			
			finally {
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return departments;
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

	public void deletecourse(int courseId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.course where course_id=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, courseId);
			  result = ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		
	}

	public boolean departmentExists(String deptId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Select dept_name from form.department where dept_id=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setString(1, deptId);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 return true;
			 }
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return false;
	}

	public int addDepartment(String deptId, String deptName) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Insert into form.department values(?,?)";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setString(1, deptId);
			  ps.setString(2, deptName);
			  result=ps.executeUpdate();
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
return result;
	}

	public void deletedepartment(String departmentId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.department where dept_id=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setString(1, departmentId);
			  result = ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		
	}

	public ArrayList<Section> getSemesters() {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Section> sections = new ArrayList<Section>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			String query = "select id,semsester from form.semester";
			PreparedStatement ps=null;
			ResultSet rs =null;
			int result=0;
			try {
				  ps=connection.prepareStatement(query);
			      rs= ps.executeQuery();
			      while(rs.next())
			      {
			    	 String id = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(1), "SafeString", 200,
							false);
			    	 String name = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200,
							false);
			    	 Section s = new Section(name,id);
			    	 sections.add(s);
			      }
			      }
			catch (SQLException e) {
			    System.err.println(e);
			} 
			
			finally {
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

	public ArrayList<Section> getBuildings() {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Section> sections = new ArrayList<Section>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			String query = "select * from form.building";
			PreparedStatement ps=null;
			ResultSet rs =null;
			int result=0;
			try {
				  ps=connection.prepareStatement(query);
			      rs= ps.executeQuery();
			      while(rs.next())
			      {
			    	 int id = rs.getInt(1);
			    	 String name = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200,
							false);
			    	 Section s = new Section(name,id);
			    	 sections.add(s);
			      }
			      }
			catch (SQLException e) {
			    System.err.println(e);
			} 
			
			finally {
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

	public ArrayList<Room> getRooms() {
		
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Room> rooms = new ArrayList<Room>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			String query = "select * from form.room";
			PreparedStatement ps=null;
			ResultSet rs =null;
			int result=0;
			try {
				  ps=connection.prepareStatement(query);
			      rs= ps.executeQuery();
			      while(rs.next())
			      {
			    	 int id = rs.getInt(1);
			    	 int roomnum = rs.getInt(2);
			    	 int capacity = rs.getInt(3);
			    	 int building = Integer.parseInt(ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(4), "SafeString", 200,
							false));
			    	 Room s = new Room(id,roomnum,capacity,building);
			    	 rooms.add(s);
			      }
			      }
			catch (SQLException e) {
			    System.err.println(e);
			} 
			
			finally {
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return rooms;
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

	public void addSection(int sectionnum, int seclimit, int i, int courseId, String semester, int roomId, int j) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "insert into form.section(section_num,section_limit,year,course,semester,room,lecture_type) "
				+ "values(?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, sectionnum);
			  ps.setInt(2, seclimit);
			  ps.setInt(3, i);
			  ps.setInt(4, courseId);
			  ps.setString(5, semester);
			  ps.setInt(6, roomId);
			  ps.setInt(7, j);
			  
			  
			  result =ps.executeUpdate();

		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		
	}

	public ArrayList<Section> getAllSections() {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Section> sections = new ArrayList<Section>();
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
					+ "on sd.dayid = ds.id inner join form.teachersection t on t.section = s.section_id inner join form.teacher tsec on t.teacher = tsec.teacher_id";
			try
			{
			ps=connection.prepareStatement(query);
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
				String endtime = rs.getString(10);
				String day =ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(11), "SafeString", 200,
						false);
				String fname = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(12), "SafeString", 200,
						false);
				String lname =ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(13), "SafeString", 200,
						false);
				int sectionLimit = rs.getInt(14);
				String courseName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(15), "SafeString", 200,
						false);
				
				Section sec= new Section(sectionId,sectionNum,sectionLimit,year,courseName,day,semester,roomno,building,departmentId,lectureType,starttime,endtime,fname+" " + lname);
				sections.add(sec);
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

	public void deletestudentsections(int sectionId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.studentsection where section=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, sectionId);
			  result = ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		

		
	}

	public void deletesection(int sectionId) {

		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.section where section_id=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, sectionId);
			  result = ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		


		
	}

	public boolean studentExists(int studentId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Select department from form.studentdetails where id=?";
		Department d;
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, studentId );
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 return true;
			 }
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		return false;
	}

	public int addStudent(int studentId, String department) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "Insert into form.studentdetails values(?,?)";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, studentId);
			  ps.setString(2, department);
			  result=ps.executeUpdate();
			 }
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
return result;
		
	}

	public ArrayList<Student> getStudents() {
		
		/* NOTE: ESIDE has created the following try catch blocks.
		 * If the generated input validation code detects a problem
		 * (e.g., malicious characters entered by user) an exception is thrown.
		 * Doing so will skip the rest of the try block code and go directly to
		 * one of the generated catch blocks below.
		 * */
		try {
			ArrayList<Student> students = new ArrayList<Student>();
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			String query = "Select * from form.studentdetails";
			Student d;
			PreparedStatement ps=null;
			ResultSet rs =null;
			int result;
			try {
				  ps=connection.prepareStatement(query);
				 rs=ps.executeQuery();
				 while(rs.next())
				 {
					 
				     int departmentId = rs.getInt(1);
				     String departmentName = ESAPI.validator().getValidInput("replace ME with validation context", rs.getString(2), "SafeString", 200,
							false);
				     d = new Student(departmentId,departmentName);
				     students.add(d);
				 }
				 }
			catch (SQLException e) {
			    System.err.println(e);
			} 
			
			finally {
			    DBUtil.closePreparedStatement(ps);
			    pool.freeConnection(connection);
			}
			return students;
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

	public void deleteStudentdetails(int studentId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.studentdetails where id=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, studentId);
			  ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	}

	public void deletestudentfromsection(int studentId) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.studentsection where student=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, studentId);
			  result = ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
		

	}

	public void deletestudent(int studentId) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
		String query = "delete from form.student where id=?";
		PreparedStatement ps=null;
		ResultSet rs =null;
		int result=0;
		
		try {
			  ps=connection.prepareStatement(query);
			  ps.setInt(1, studentId);
			  ps.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println(e);
        } 
		
		finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
	}
		
	}
		



