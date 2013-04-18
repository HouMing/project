package name.hm.spring.mvc.m;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import name.hm.spring.data.jpa.Question;
import name.hm.spring.data.jpa.Statistic;
import name.hm.spring.mvc.tool.ConnectionFactory;
import name.hm.spring.mvc.tool.SQLtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidPooledConnection;

@Component
public class QuestionDAO
{
  @Autowired
    ConnectionFactory pool;

  @Autowired
    SQLtools tool;

  public Question oneByOid(Integer oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    PreparedStatement stmt;
    Question q = null;
    stmt = conn.prepareStatement(tool.parseSql("SELECT oid, survey_oid, description, itemA, itemB, itemC, itemD FROM question WHERE oid = ?;"));
    stmt.setInt(1, oid);
    ResultSet rst2 = stmt.executeQuery();
    rst2.next();
    Integer rst_oid = rst2.getInt("oid");
    Integer rst_survey_oid = rst2.getInt("survey_oid");
    String rst_decription = rst2.getString("description");
    String rst_itemA = rst2.getString("itemA");
    String rst_itemB = rst2.getString("itemB");
    String rst_itemC = rst2.getString("itemC");
    String rst_itemD = rst2.getString("itemD");
    q = new Question(rst_oid, rst_survey_oid, rst_decription, rst_itemA, rst_itemB, rst_itemC, rst_itemD);
    stmt.close();
    conn.recycle();
    return q;
  }

  public ArrayList<Integer> someProxiesBySurveyOid(Integer survey_oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Integer> proxies = new ArrayList<Integer>();
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT DISTINCT oid FROM question WHERE survey_oid = ?;"));
    stmt.setInt(1, survey_oid);
    stmt.executeQuery();
    ResultSet rst2 = stmt.getResultSet();
    while(rst2.next()) {
      Integer rst_oid = rst2.getInt("oid");
      proxies.add(rst_oid);
    }
    stmt.close();
    conn.recycle();
    return proxies;
  }

  public Integer deleteByProxy(Integer oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Integer> proxies = new ArrayList<Integer>();
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("DELETE FROM question WHERE oid = ?;"));
    stmt.setInt(1, oid);
    Integer ret = stmt.executeUpdate();
    stmt.close();
    conn.recycle();
    return ret;
  }

  public Integer update(Question one) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Integer> proxies = new ArrayList<Integer>();
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("UPDATE question SET description = ?, itemA = ?, itemB = ?, itemC = ?, itemD = ? WHERE oid = ?;"));
    stmt.setString(1, one.getDescription());
    stmt.setString(2, one.getItemA());
    stmt.setString(3, one.getItemB());
    stmt.setString(4, one.getItemC());
    stmt.setString(5, one.getItemD());
    stmt.setInt(6, one.getOid());
    Integer ret = stmt.executeUpdate();
    stmt.close();
    conn.recycle();
    return ret;
  }

  public Integer create(Question one) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Integer> proxies = new ArrayList<Integer>();
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("INSERT INTO question (survey_oid, description, itemA, itemB, itemC, itemD) VALUES (?, ?, ?, ?, ?, ?);"));
    stmt.setInt(1, one.getSurvey_oid());
    stmt.setString(2, one.getDescription());
    stmt.setString(3, one.getItemA());
    stmt.setString(4, one.getItemB());
    stmt.setString(5, one.getItemC());
    stmt.setString(6, one.getItemD());
    Integer ret = stmt.executeUpdate();
    stmt.close();
    conn.recycle();
    return ret;
  }

	public Statistic loadSttc(Question q) throws SQLException
	{
		  DruidPooledConnection conn = pool.getConnection();
	    PreparedStatement stmt;
	    stmt = conn.prepareStatement(tool.parseSql("SELECT  COUNT(oid) AS num FROM answer WHERE question_oid = ? AND item = 'itemA';"));
	    stmt.setInt(1, q.getOid());
	    ResultSet rst = stmt.executeQuery();
	    rst.next();
	    Integer num_itemA = rst.getInt("num");
	    stmt = conn.prepareStatement(tool.parseSql("SELECT  COUNT(oid) AS num FROM answer WHERE question_oid = ? AND item = 'itemB';"));
	    stmt.setInt(1, q.getOid());
	    rst = stmt.executeQuery();
	    rst.next();
	    Integer num_itemB = rst.getInt("num");
	    stmt = conn.prepareStatement(tool.parseSql("SELECT  COUNT(oid) AS num FROM answer WHERE question_oid = ? AND item = 'itemC';"));
	    stmt.setInt(1, q.getOid());
	    rst = stmt.executeQuery();
	    rst.next();
	    Integer num_itemC = rst.getInt("num");
	    stmt = conn.prepareStatement(tool.parseSql("SELECT  COUNT(oid) AS num FROM answer WHERE question_oid = ? AND item = 'itemD';"));
	    stmt.setInt(1, q.getOid());
	    rst = stmt.executeQuery();
	    rst.next();
	    Integer num_itemD = rst.getInt("num");
	    Integer num_sum = num_itemA + num_itemB + num_itemC + num_itemD;
	    Statistic ret = new Statistic(q.getOid(), num_itemA, num_itemB, num_itemC, num_itemD, num_sum);
	    stmt.close();
	    conn.recycle();
	    return ret;
	}

}
