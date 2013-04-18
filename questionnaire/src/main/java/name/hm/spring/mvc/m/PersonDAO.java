package name.hm.spring.mvc.m;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import name.hm.spring.data.jpa.Person;
import name.hm.spring.mvc.tool.ConnectionFactory;
import name.hm.spring.mvc.tool.SQLtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidPooledConnection;

@Component
public class PersonDAO
{
  @Autowired
    ConnectionFactory pool;

  @Autowired
    SQLtools tool;

  public Integer oneProxyByUsername(String username) throws SQLException {
    DruidPooledConnection conn = pool.getConnection();
    PreparedStatement stmt = conn.prepareStatement(tool.parseSql("SELECT oid FROM user WHERE username = ?;"));
    stmt.setString(1, username);
    ResultSet rst = stmt.executeQuery();
    rst.next();
    Integer oid = rst.getInt("oid");
    stmt.close();
    conn.recycle();
    return oid;
  }

  public Person oneByProxy(Integer person_oid) throws SQLException {
    DruidPooledConnection conn = pool.getConnection();
    PreparedStatement stmt = conn.prepareStatement(tool.parseSql("SELECT oid, username, password, `right` FROM user WHERE oid = ?;"));
    stmt.setInt(1, person_oid);
    ResultSet rst =  stmt.executeQuery();
    rst.next();
    Integer oid = rst.getInt("oid");
    String username = rst.getString("username");
    String password = rst.getString("password");
    Integer right = rst.getInt("right");
    Person p =  new Person(oid, username, password, right);
    stmt.close();
    conn.recycle();
    return p;
  }

	public ArrayList<Integer> someProxyBySurveyOid(Integer survey_oid) throws SQLException
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
    DruidPooledConnection conn = pool.getConnection();
    PreparedStatement stmt = conn.prepareStatement(tool.parseSql("SELECT DISTINCT user_oid FROM answer WHERE survey_oid = ?;"));
    stmt.setInt(1, survey_oid);
    ResultSet rst = stmt.executeQuery();
    while(rst.next()){
      ret.add(rst.getInt("user_oid"));
    }
    stmt.close();
    conn.recycle();
    return ret;
 }

	public void create(Person p) throws SQLException
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
    DruidPooledConnection conn = pool.getConnection();
    PreparedStatement stmt = conn.prepareStatement(tool.parseSql("INSERT INTO user (username, password, `right`) VALUES (?, ?, ?);"));
    stmt.setString(1, p.getUsername());
    stmt.setString(2, p.getPassword());
    stmt.setInt(3, p.getRight());
    Integer rst = stmt.executeUpdate();
    stmt.close();
    conn.recycle();
	}
}
