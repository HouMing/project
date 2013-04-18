package name.hm.spring.mvc.m;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import name.hm.spring.data.jpa.Survey;
import name.hm.spring.mvc.tool.ConnectionFactory;
import name.hm.spring.mvc.tool.SQLtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidPooledConnection;

@Component
public class SurveyDAO 
{
  @Autowired
    ConnectionFactory pool;

  @Autowired
    SQLtools tool;
  
  public ArrayList<Integer> allProxy() throws SQLException {
    DruidPooledConnection conn = pool.getConnection();
  	ArrayList<Integer> ret = new ArrayList<Integer>();
  	PreparedStatement stmt = conn.prepareStatement(tool.parseSql("SELECT DISTINCT oid FROM survey"));
  	ResultSet rst = stmt.executeQuery();
  	while (rst.next()) {
  		ret.add(rst.getInt("oid"));
  	}
  	stmt.close();
  	conn.recycle();
  	return ret;
  }
  
  public ArrayList<Survey> allLight() throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
  	ArrayList<Survey> ret = new ArrayList<Survey>(5);
  	PreparedStatement stmt = conn.prepareStatement(tool.parseSql("SELECT oid, name FROM survey"));
  	ResultSet rst = stmt.executeQuery();
  	while (rst.next()) {
  		Integer rst_oid = rst.getInt("oid");
  		String rst_name = rst.getString("name");
  		Survey tmp_survey = new Survey(rst_oid, rst_name);
  		ret.add(tmp_survey);
  	}
  	stmt.close();
  	conn.recycle();
    return ret;
  }

	public boolean deleteProxy(Integer oid) throws SQLException
	{
	  DruidPooledConnection conn = pool.getConnection();
  	ArrayList<Survey> ret = new ArrayList<Survey>();
  	PreparedStatement stmt = conn.prepareStatement(tool.parseSql("DELETE FROM survey WHERE oid = ?;"));
  	stmt.setInt(1,oid);
  	int rst = stmt.executeUpdate();
  	stmt.close();
  	conn.recycle();
  	if (rst == 1) {
  		return true;
  	} else {
  		return false;
  	}
	}

	public Integer create(Survey one) throws SQLException
	{
	  DruidPooledConnection conn = pool.getConnection();
  	PreparedStatement stmt = conn.prepareStatement(tool.parseSql("INSERT INTO survey (name) VALUES (?);"));
  	stmt.setString(1,one.getName());
  	int rst = stmt.executeUpdate();
  	stmt.close();
  	conn.recycle();
  	return rst;
	}

}

