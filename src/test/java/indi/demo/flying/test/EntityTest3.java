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
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DatabaseTearDowns;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader;

import indi.demo.flying.entity.Cart;
import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.Role;
import indi.demo.flying.service.CartService;
import indi.demo.flying.service2.PersonService;
import indi.demo.flying.service2.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class, databaseConnection = { "dataSource", "dataSource2" })
@ContextConfiguration("classpath:spring-test.xml")
public class EntityTest3 {

	@Autowired
	private DataSource dataSource2;

	@Autowired
	private CartService cartService;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Test
	public void testDataSource() {
		Assert.assertNotNull(dataSource2);
	}

	@Test
	@DatabaseSetups({
			@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/entityTest3/testPersonAndCart.dataSource.xml", connection = "dataSource"),
			@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/entityTest3/testPersonAndCart.dataSource2.xml", connection = "dataSource2") })
	@ExpectedDatabases({
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/entityTest3/testPersonAndCart.dataSource.result.xml", connection = "dataSource", override = false),
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/entityTest3/testPersonAndCart.dataSource2.result.xml", connection = "dataSource2", override = false) })
	@DatabaseTearDowns({
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/entityTest3/testPersonAndCart.dataSource.result.xml", connection = "dataSource"),
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/entityTest3/testPersonAndCart.dataSource2.result.xml", connection = "dataSource2"), })
	public void testPersonAndCart() {
		Cart cart = cartService.mySelect("aaa");
		Assert.assertEquals("��ͨ��Ա", cart.getPerson().getRole().getName());

		Role role = roleService.mySelect("bbb");
		Person person = personService.mySelect("mmm");
		person.setRole(role);
		personService.myUpdate(person);
		cart = cartService.mySelect("aaa");
		Assert.assertEquals("���ƻ�Ա", cart.getPerson().getRole().getName());

		Person p = new Person();
		p.setId("nnn");
		cart.setPerson(p);
		cartService.myUpdate(cart);
	}
}
