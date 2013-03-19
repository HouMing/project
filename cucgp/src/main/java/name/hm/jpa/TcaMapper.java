package name.hm.jpa;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import name.hm.m.Tca;

public interface TcaMapper
{
	final String INSERT = "INSERT INTO cucgp.`tca` (tca_id, user_name, tca_name, introduction, requirement, numerator, applycount, status)"
			+ "VALUES (#{tcaId}, #{userName}, #{tcaName}, #{introduction}, #{requirement}, #{numerator}, #{applycount}, #{status})";

	final String SELECT_BY_ID = "SELECT * FROM cucgp.`tca` WHERE tca_id = #{param1}";
	final String SELECT_BY_USERNAME = "SELECT * FROM cucgp.`tca` WHERE user_name = #{param1}";

	final String UPDATE = "UPDATE cucgp.`tca` SET " +
			"user_name = #{userName}, tca_name = #{tcaName}, introduction = #{introduction}, " +
			"requirement = #{requirement}, numerator = #{numerator}, applycount = #{applycount}, status = #{status} " +
			"WHERE tca_id = #{tcaId}";

	final String DELETE = "DELETE FROM cucgp.`tca` WHERE tca_id = #{tcaId}";
// @Insert(INSERT)
// @SelectKey(before = false, keyProperty = "tcaId", resultType = Integer.class, statement = "SELECT MAX(tca_id) AS tca_id FROM cucgp.`tca`")
	public Integer insert(Tca tca);
// @Select(SELECT_BY_ID)
// @Results(value = { @Result(property = "tcaId", column = "tca_id"),
// @Result(property = "userName", column = "user_name"),
// @Result(property = "tcaName", column = "tca_name"),
// @Result(property = "introduction", column = "introduction"),
// @Result(property = "requirement", column = "requirement"),
// @Result(property = "numerator", column = "numerator"),
// @Result(property = "applycount", column = "applycount"),
// @Result(property = "status", column = "status") })
	public Tca selectByTcaId(Integer tcaId);
// @Select(SELECT_BY_USERNAME)
// @Results(value = { @Result(property = "tcaId", column = "tca_id"),
// @Result(property = "userName", column = "user_name"),
// @Result(property = "tcaName", column = "tca_name"),
// @Result(property = "introduction", column = "introduction"),
// @Result(property = "requirement", column = "requirement"),
// @Result(property = "numerator", column = "numerator"),
// @Result(property = "applycount", column = "applycount"),
// @Result(property = "status", column = "status") })
	public Tca selectByUserName(String userName);
// @Update(UPDATE)
	public Integer update(Tca tca);
// @Delete(DELETE)
	public Integer delete(Tca tca);

}
