package name.hm.test;

//{{{

public class BaseTestCase implements ITestCase, ILogger
{
	/*
  public RoleMapper roleMapper = null;
  public GroupMapper groupMapper = null;
  public UserMapper userMapper = null;
  public ActionMapper actionMapper = null;

  static protected String CONF_FILE_NAME = "appContext-MyBATIS.xml";
  static protected String LOGGER_APPENDER = "dev";
  static protected Logger logger = Logger.getLogger(LOGGER_APPENDER); 
  static protected SqlSessionFactory sqlSessionFactory = null;
  static protected SqlSession se = null;

     protected WorkflowMapper workflowMapper = null;
     protected DepartmentMapper departmentMapper = null;
     protected TitleMapper titleMapper = null;
     protected ClassroomMapper classroomMapper = null;
     protected TeacherMapper teacherMapper = null;
     protected StudentMapper studentMapper = null;
     protected StuaMapper stuaMapper = null;
     protected TcaMapper tcaMapper = null;

  static {
    String resource = CONF_FILE_NAME;
    Reader reader;
    try {
      reader = Resources.getResourceAsReader(resource);
      if (sqlSessionFactory == null) {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void openTestSession() throws Exception
  {
    se = sqlSessionFactory.openSession();
    Assert.notNull(se,"openTestSession failed!");
    Class c = BaseTestCase.class;
    Field[] fields = c.getFields();
    for(Field field : fields) {
      if(field.getType().toString().matches("^.*Mapper$")){
        field.set(this, se.getMapper(field.getType()));
      }
    }
  }

  public void closeTestSession()
  {
    Class c = BaseTestCase.class;
    Field[] fields = c.getFields();
    for(Field field : fields) {
      if(field.getName().matches("^.*Mapper$")){
        try {
          field.set(this, null);
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    if (se != null) {
      se.close();
      se = null;
    }
  }
*/
}
