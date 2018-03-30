package indi.demo.flying.test;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader;

import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.Role;
import indi.demo.flying.entity2.RoleEnum;
import indi.demo.flying.service2.PersonService;
import indi.demo.flying.service2.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class, databaseConnection = { "dataSource2" })
@ContextConfiguration("classpath:spring-test.xml")
public class EntityTest2 {

	@Autowired
	private DataSource dataSource2;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Test
	public void testDataSource() {
		Assert.assertNotNull(dataSource2);
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/entityTest2/testRole.xml", connection = "dataSource2")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/entityTest2/testRole.result.xml", connection = "dataSource2", override = false)
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/entityTest2/testRole.result.xml", connection = "dataSource2")
	public void testRole() {
		Role role = roleService.mySelect("aaa");
		Assert.assertEquals("普通会员", role.getName());

		Role role2 = roleService.mySelect("bbb");
		role2.setValue(RoleEnum.silver);
		role2.setName("银牌会员");
		roleService.myUpdate(role2);
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/entityTest2/testPerson.xml", connection = "dataSource2")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/entityTest2/testPerson.result.xml", connection = "dataSource2", override = false)
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/entityTest2/testPerson.result.xml", connection = "dataSource2")
	public void testPerson() {
		Person person = personService.mySelect("mmm");
		Assert.assertEquals("张三", person.getName());
		Assert.assertEquals(RoleEnum.normal, person.getRole().getValue());

		Person person2 = personService.mySelect("nnn");
		Role role = roleService.mySelect("ccc");
		person2.setRole(role);
		personService.myUpdate(person2);
	}
}
