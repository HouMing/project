package name.hm.spring.mvc.m;

import java.sql.SQLException;
import java.util.ArrayList;

import name.hm.spring.data.jpa.Result;
import name.hm.spring.mvc.tool.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidPooledConnection;

@Component
public class ResultDAO 
{
   @Autowired
   QuestionDAO qdao;
   @Autowired
   AnswerDAO adao;
   @Autowired
   PersonDAO udao;
	
  public ArrayList<Result> someBySurveyOid(Integer survey_oid) throws SQLException
  {
  	ArrayList<Result> ret = new ArrayList<Result>();
  	
  	ArrayList<Integer> lq = qdao.someProxiesBySurveyOid(survey_oid);
  	ArrayList<Integer> user_oids = udao.someProxyBySurveyOid(survey_oid);

  	for (Integer user_oid : user_oids) {
  	 ArrayList<Integer> la = adao.someProxiesBySurveyOidAndUserOid(survey_oid, user_oid);
  
  	 ret.add(new Result(survey_oid, user_oid, la, lq));
  	}
    return ret;
  }

}

