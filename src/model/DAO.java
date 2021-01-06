package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public abstract class DAO {
	public static String JsonSearch(String sql) {
		Connection con;
		ResultSet rs;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e){
			System.out.print(e);
		} 
		try
		{
			String uri="jdbc:mysql://" + System.getenv("DB_HOST") +"/localdb";
			con=DriverManager.getConnection(uri,"root","DtZBAxrN57Mx");
			PreparedStatement ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			JSONArray array = new JSONArray(); 
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {  
		        JSONObject jsonObj = new JSONObject();
		        for (int i = 1; i <= columnCount; i++) {  
		            String columnName =metaData.getColumnLabel(i);  
		            String value = rs.getString(columnName);  
		            jsonObj.put(columnName, value);  
		        }   
		        array.put(jsonObj);   
		    }  
			con.close();
			return array.toString();
		}
		catch(SQLException | JSONException e1){
			System.out.print(e1);
		}
		return null;
	}
	
	public static boolean Update(String sql) {
		Connection con;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e){
			System.out.print(e);
		} 
		try
		{
			String uri="jdbc:mysql://" + System.getenv("DB_HOST") ;
			con=DriverManager.getConnection(uri,"root","DtZBAxrN57Mx");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate(sql);
			con.close();
			return true;
		}
		catch(SQLException e1){
			System.out.print(e1);
		}
		return false;
	}
}
