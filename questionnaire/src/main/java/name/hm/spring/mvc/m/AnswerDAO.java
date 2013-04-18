package name.hm.spring.mvc.m;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import name.hm.spring.data.jpa.Answer;
import name.hm.spring.mvc.tool.ConnectionFactory;
import name.hm.spring.mvc.tool.SQLtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidPooledConnection;

@Component
public class AnswerDAO
{
  @Autowired
    ConnectionFactory pool;

  @Autowired
    SQLtools tool;

  public ArrayList<Integer> someProxiesBySurveyOid(Integer survey_oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Integer> proxies = new ArrayList<Integer>();
    // select all related user_oid;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT oid FROM answer WHERE survey_oid = ?;"));
    stmt.setInt(1, survey_oid);
    stmt.execute();
    ResultSet rst = stmt.getResultSet();
    while (rst.next()) {
      proxies.add(rst.getInt("oid"));
    }
    stmt.close();
    conn.recycle();
    return proxies;
  }

  public ArrayList<Integer> someProxiesBySurveyOidAndQuestionOid(Integer survey_oid, Integer question_oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Integer> proxies = new ArrayList<Integer>();
    // select all related user_oid;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT DISTINCT oid FROM answer WHERE survey_oid = ? AND question_oid = ?;"));
    stmt.setInt(1, survey_oid);
    stmt.setInt(2, question_oid);
    ResultSet rst = stmt.getResultSet();
    while (rst.next()) {
      int tmp_oid = rst.getInt("oid");
      proxies.add(tmp_oid);
    }
    stmt.close();
    conn.recycle();
    return proxies;
  }

  public ArrayList<Integer> someProxiesBySurveyOidAndUserOid(Integer survey_oid, Integer user_oid) throws SQLException
  {
  	ArrayList<Integer> proxies = new ArrayList<Integer>();
  	
    DruidPooledConnection conn = pool.getConnection();
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT DISTINCT oid FROM answer WHERE survey_oid = ? AND user_oid = ?;"));
    stmt.setInt(1, survey_oid);
    stmt.setInt(2, user_oid);
    ResultSet rst = stmt.executeQuery();
    while (rst.next()) {
      int tmp_oid = rst.getInt("oid");
      proxies.add(tmp_oid);
    }
    stmt.close();
    conn.recycle();
    return proxies;
  }

  public Integer oneProxyByIndex(Integer survey_oid, Integer question_oid, Integer user_oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    Integer proxy = null;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT oid FROM answer WHERE survey_oid = ? AND question_oid = ? AND user_oid = ?;"));
    stmt.setInt(1, survey_oid);
    stmt.setInt(2, question_oid);
    stmt.setInt(3, user_oid);
    ResultSet rst = stmt.getResultSet();
    rst.next();
    proxy = rst.getInt("oid");
    stmt.close();
    conn.recycle();
    return proxy;
  }

  public ArrayList<Answer> someByProxies(ArrayList<Integer> oids) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    ArrayList<Answer> objects = new ArrayList<Answer>();
    // select all related user_oid;
    PreparedStatement stmt;
    for (Integer oid : oids) {
      stmt = conn.prepareStatement(tool.parseSql("SELECT oid, user_oid, question_oid, survey_oid, item FROM answer WHERE oid = ?;"));
      stmt.setInt(1, oid);
      ResultSet rst = stmt.getResultSet();
      while (rst.next()) {
        Integer tmp_oid = rst.getInt("oid");
        Integer user_oid = rst.getInt("uesr_oid");
        Integer question_oid = rst.getInt("question_oid");
        Integer survey_oid = rst.getInt("survey_oid");
        String item = rst.getString("item");
        Answer tmp_object = new Answer(tmp_oid, user_oid, question_oid, survey_oid, item);
        objects.add(tmp_object);
      }
      stmt.close();
    }
    conn.recycle();
    return objects;
  }

  public Answer oneByProxy(Integer oid) throws SQLException
  {
    DruidPooledConnection conn = pool.getConnection();
    Answer object = null; 
    // select all related user_oid;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT oid, user_oid, question_oid, survey_oid, item FROM answer WHERE oid = ?;"));
    stmt.setInt(1, oid);
    ResultSet rst = stmt.getResultSet();
    rst.next();
    Integer tmp_oid = rst.getInt("oid");
    Integer user_oid = rst.getInt("uesr_oid");
    Integer question_oid = rst.getInt("question_oid");
    Integer survey_oid = rst.getInt("survey_oid");
    String item = rst.getString("item");
    object = new Answer(tmp_oid, user_oid, question_oid, survey_oid, item);
    stmt.close();
    conn.recycle();
    return object;
  }

  public int update(Answer a) throws SQLException
	{
    DruidPooledConnection conn = pool.getConnection();
    // select all related user_oid;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("UPDATE answer SET item = ? WHERE oid = ?;"));
    stmt.setString(1, a.getItem());
    stmt.setInt(2, a.getOid());
    int rst = stmt.executeUpdate();
    stmt.close();
    conn.recycle();
    return rst;
	}
  
  public int create(Answer a) throws SQLException
	{
    DruidPooledConnection conn = pool.getConnection();
    // select all related user_oid;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("INSERT INTO answer (user_oid, question_oid, survey_oid, item) VALUES (?, ?, ?, ?);"));
    stmt.setInt(1, a.getPerson_oid());
    stmt.setInt(2, a.getQuestion_oid());
    stmt.setInt(3, a.getSurvey_oid());
    stmt.setString(4, a.getItem());
    int rst = stmt.executeUpdate();
    stmt.close();
    conn.recycle();
		return rst;
	}
  
	public int updateOrCreate(Answer a) throws SQLException
	{
    DruidPooledConnection conn = pool.getConnection();
    // select all related user_oid;
    PreparedStatement stmt;
    stmt = conn.prepareStatement(tool.parseSql("SELECT oid, user_oid, question_oid, survey_oid, item FROM answer WHERE user_oid = ? AND question_oid = ? AND survey_oid = ?;"));
    stmt.setInt(1, a.getPerson_oid());
    stmt.setInt(2, a.getQuestion_oid());
    stmt.setInt(3, a.getSurvey_oid());
    ResultSet rst = stmt.executeQuery();
    rst.next();
    int row = rst.getRow();
    if (row == 1) {
    Integer tmp_oid = rst.getInt("oid");
    a.setOid(tmp_oid);
    stmt.close();
    conn.recycle();
    return update(a);
    } else {
    stmt.close();
    conn.recycle();
    return create(a);
    }
	}
}

