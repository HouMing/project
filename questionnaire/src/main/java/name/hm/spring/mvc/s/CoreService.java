package name.hm.spring.mvc.s;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import name.hm.spring.data.jpa.Answer;
import name.hm.spring.data.jpa.Person;
import name.hm.spring.data.jpa.Question;
import name.hm.spring.data.jpa.Statistic;
import name.hm.spring.data.jpa.Survey;
import name.hm.spring.mvc.m.AnswerDAO;
import name.hm.spring.mvc.m.PersonDAO;
import name.hm.spring.mvc.m.QuestionDAO;
import name.hm.spring.mvc.m.ResultDAO;
import name.hm.spring.mvc.m.SurveyDAO;
import name.hm.spring.mvc.tool.ConnectionFactory;
import name.hm.spring.mvc.tool.SQLtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;

@Service
public class CoreService
{
	@Autowired
	AnswerDAO adao;
	@Autowired
	QuestionDAO qdao;
	@Autowired
	SurveyDAO sdao;
	@Autowired
	ResultDAO rdao;
	@Autowired
	PersonDAO pdao;

	public Person getUser(String username, String password)
	{
		if (username == null || password == null) {
			return null;
		}
		try {
		  Integer poid;
			poid = pdao.oneProxyByUsername(username);
			if (poid == null) {
				return null;
			}
			Person p = pdao.oneByProxy(poid);
			if (p.getPassword().equals(password)) {
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Survey> allLightSurvey()
	{
		try {
			ArrayList<Survey> ret = sdao.allLight();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Survey> allHeavySurvey()
	{
		try {
			ArrayList<Survey> ret = allLightSurvey();
			for (Survey tmp : ret) {
				tmp.setLr(rdao.someBySurveyOid(tmp.getOid()));
				tmp.setLq(qdao.someProxiesBySurveyOid(tmp.getOid()));
			}
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteSurvey(String oid)
	{
		boolean ret = false;
		try {
			ret = sdao.deleteProxy(Integer.parseInt(oid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (ret) {
			return "删除成功";
		} else {
			return "删除失败";
		}
	}

	public ArrayList<Question> someQuestionsOfSurvey(String oid)
	{
		ArrayList<Question> ret = new ArrayList<Question>();
		if (oid == null) {
			return null;
		}
		try {
			ArrayList<Integer> pQuestions = qdao.someProxiesBySurveyOid(Integer.parseInt(oid));
			for (Integer proxy: pQuestions) {
				ret.add(qdao.oneByOid(proxy));
			}
			return ret;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteQuestion(String oid)
	{
		if (oid == null) {
			return "无QuestionOid";
		}
		Integer ret = 0;
		try {
			ret = qdao.deleteByProxy(Integer.parseInt(oid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (ret == 1) {
			return "删除成功";
		} else if (ret == 0){
			return "删除失败";
		} else {
			return "未知情况";
		}
	}

	public String updateQuestion(String oid, String description, String itemA, String itemB, String itemC, String itemD)
	{
		Question one = null;
		Integer status = 0;
		try {
			one = qdao.oneByOid(Integer.parseInt(oid));
			if (one == null) {
				return "问题『"+description+"』不存在！";
			}
			one.setDescription(description);
			one.setItemA(itemA);
			one.setItemB(itemB);
			one.setItemC(itemC);
			one.setItemD(itemD);
			status = qdao.update(one);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		switch (status) {
		case 0:
			return "保存失败";
		case 1:
			return "保存成功";
		default:
			return "未知状态";
		}
	}

	public String createQuestion(String survey_oid, String description, String itemA, String itemB,
			String itemC, String itemD)
	{
		Question one = null;
		Integer status = 0;
		try {
			one = new Question(Integer.parseInt(survey_oid), description, itemA, itemB, itemC, itemD);
			if (one == null) {
				status = 0;
			}
			status = qdao.create(one);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		switch (status) {
		case 0:
			return "保存失败";
		case 1:
			return "保存成功";
		default:
			return "未知状态";
		}
	}

	public String createSurvey(String name)
	{
		Survey one = null;
		Integer status = 0;
		try {
			one = new Survey(name);
			if (one == null) {
				status = 0;
			}
			status = sdao.create(one);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		switch (status) {
		case 0:
			return "保存失败";
		case 1:
			return "保存成功";
		default:
			return "未知状态";
		}
	}

	public void updateAnswer(Answer a)
	{
		try {
			int ret = adao.updateOrCreate(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void registerUser(String username, String password)
	{
		Person p = new Person(null, username, password, 2);
		try {
			pdao.create(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statistic getStatistic(Question q)
	{
		Statistic ret = null;
		try {
			ret = qdao.loadSttc(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
