package course.data;

import java.sql.*;
import java.io.PrintWriter;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

public class SQLUtil {

    public static String getHtmlRows(ResultSet results)
            throws SQLException {
        
				/* NOTE: ESIDE has created the following try catch blocks.
				 * If the generated input validation code detects a problem
				 * (e.g., malicious characters entered by user) an exception is thrown.
				 * Doing so will skip the rest of the try block code and go directly to
				 * one of the generated catch blocks below.
				 * */
				try {
					StringBuilder htmlRows = new StringBuilder();
					ResultSetMetaData metaData = results.getMetaData();
					int columnCount = metaData.getColumnCount();
					htmlRows.append("<tr>");
					String cell;
					for (int i = 1; i <= columnCount; i++) {
					    cell = "<th>" + metaData.getColumnName(i) + "</th>";
					    htmlRows.append(cell);
					}
					htmlRows.append("</tr>");
					while (results.next()) {
					    htmlRows.append("<tr>");
					    for (int i = 1; i <= columnCount; i++) {
					        cell = "<td>" + ESAPI.validator().getValidInput("replace ME with validation context", results.getString(i), "SafeString", 200,
									false) + "</td>";
					        htmlRows.append(cell);
					    }
					}
					htmlRows.append("</tr>");
					return htmlRows.toString();
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

    public static String encode(String s) {
        if (s == null) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (ch == 39) {  // 39 is the ASCII code for an apostrophe
                sb.insert(i++, "'");
            }
        }
        return sb.toString();
    }
}