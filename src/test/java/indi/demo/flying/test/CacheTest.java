package indi.demo.flying.test;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
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
import indi.demo.flying.entity2.RoleEnum;
import indi.demo.flying.service.CartService;
import indi.demo.flying.service2.PersonService;
import indi.demo.flying.service2.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class, databaseConnection = { "dataSource", "dataSource2" })
@ContextConfiguration("classpath:spring-test.xml")
public class CacheTest {

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
	@ExpectedDatabases({
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource.result.xml", connection = "dataSource", override = false),
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource2.result.xml", connection = "dataSource2", override = false) })
	@DatabaseTearDowns({
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource.result.xml", connection = "dataSource"),
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource2.result.xml", connection = "dataSource2"), })
	public void testCache() {

		Role r1 = new Role(), r2 = new Role(), r3 = new Role();
		r1.setName("normal");
		r1.setValue(RoleEnum.normal);
		roleService.myInsert(r1);

		r2.setName("silver");
		r2.setValue(RoleEnum.silver);
		roleService.myInsert(r2);

		r3.setName("gold");
		r3.setValue(RoleEnum.gold);
		roleService.myInsert(r3);

		Person p1 = new Person(), p2 = new Person();
		p1.setName("zhangsan");
		p1.setRole(r1);
		personService.myInsert(p1);

		p2.setName("lisi");
		p2.setRole(r2);
		personService.myInsert(p2);

		Cart c1 = new Cart();
		c1.setDeal(false);
		c1.setPerson(p1);
		cartService.myInsert(c1);
		
//		Person p_ = new Person();
//		p_.setId(p1.getId());
//		Cart cc = new Cart();
//		cc.setPerson(p_);
//		Cart c_ = cartService.mySelectOne(cc);

		Cart cart = cartService.mySelect(c1.getId());
		Assert.assertEquals("normal", cart.getPerson().getRole().getName());

		Map<String, Object> m = new HashMap<>();
		m.put("id", r1.getId());
		m.put("name", "normal1");
		roleService.updateDirectWithoutCache(m);

		cart = cartService.mySelect(c1.getId());
		Assert.assertEquals("normal", cart.getPerson().getRole().getName());

		Map<String, Object> m2 = new HashMap<>();
		m2.put("id", r1.getId());
		m2.put("name", "normal2");
		roleService.updateDirect(m2);

		cart = cartService.mySelect(c1.getId());
		Assert.assertEquals("normal2", cart.getPerson().getRole().getName());
	}
}
