package name.hm.test.unit;

import name.hm.pojo.Tca;
import name.hm.test.BaseTestCase;

// TODO IMPL UNIT
//TODO 12 UNIT, Upgrade - task : #0310
public class TcaUnitTest extends BaseTestCase
{
	public static Integer TCA_ID = null;
	public static String USER_NAME = UserUnitTest.USER_NAME;
	public static final String TCA_NAME = "公务员招聘0";
	
	public static Integer TCA_ID1 = null;
	public static String USER_NAME1 = UserUnitTest.USER_NAME;
	public static String TCA_NAME1 = "公务员招聘1";
	
	public static Integer TCA_ID2 = null;
	public static String USER_NAME2 = UserUnitTest.USER_NAME;
	public static String TCA_NAME2 = "公务员招聘2";
	
	public static final String INTRODUCTION = "这是一个公务员的项目";
	public static final String REQUIREMENT = "需要会喝茶，会聊天，会打麻将，会泡妞，就是不能会码字";
	public static final Integer NUMERATOR = 1;
	public static final Integer APPLYCOUNT = 1000;
	
	Tca.Status S1 = Tca.S1;
	Tca.Status S2 = Tca.S2;
}
