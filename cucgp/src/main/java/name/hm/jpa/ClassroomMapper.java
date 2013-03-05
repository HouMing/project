package name.hm.jpa;

public interface ClassroomMapper
{
	final String INSERT = "INSERT INTO cucgp.`classroom` (classroom_name) VALUES (#{classroomName})";
	final String SELECT_ALL = "SELECT * FROM cucgp.`classroom`";
	final String UPDATE = "UPDATE cucgp.`classroom` SET classroom_name = #{classroomName} WHERE classroom_";
	
}
