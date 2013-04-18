package name.hm.spring.mvc.tool;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;

@Component
public class SQLtools
{
  public String parseSql(String sql){
  	// parser得到AST
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
		List<SQLStatement> stmtList = parser.parseStatementList(); //
		// 将AST通过visitor输出
		StringBuilder out = new StringBuilder();
		SQLASTOutputVisitor visitor = SQLUtils.createFormatOutputVisitor(out,	stmtList, JdbcUtils.MYSQL);
		for (SQLStatement stmt : stmtList) {
			stmt.accept(visitor);
		}
		return out.toString();
  }
}
