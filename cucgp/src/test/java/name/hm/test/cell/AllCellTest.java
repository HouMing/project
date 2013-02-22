package name.hm.test.cell;

import name.hm.pojo.Workflow;
import name.hm.test.CellTest;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Categories.class)
@IncludeCategory(CellTest.class)
@SuiteClasses({ 
	EnvTest.class, 
	GroupCellTest.class, 
	UserCellTest.class,
	RoleCellTest.class,
	Workflow.class
	})
public class AllCellTest {
	
}
