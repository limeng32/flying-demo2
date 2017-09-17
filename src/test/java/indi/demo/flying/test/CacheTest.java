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
	@IfProfileValue(name = "CACHE", value = "true")
	public void testDataSource() {
		Assert.assertNotNull(dataSource2);
	}

	@Test
	@IfProfileValue(name = "CACHE", value = "true")
	@ExpectedDatabases({
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource.result.xml", connection = "dataSource", override = false),
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource2.result.xml", connection = "dataSource2", override = false) })
	@DatabaseTearDowns({
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource.result.xml", connection = "dataSource"),
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/cacheTest/testCache.dataSource2.result.xml", connection = "dataSource2"), })
	public void testCache() {

		Role r1 = new Role(), r2 = new Role(), r3 = new Role();
		r1.setName("普通会员");
		r1.setValue(RoleEnum.normal);
		roleService.myInsert(r1);

		r2.setName("银牌会员");
		r2.setValue(RoleEnum.silver);
		roleService.myInsert(r2);

		r3.setName("金牌会员");
		r3.setValue(RoleEnum.gold);
		roleService.myInsert(r3);

		Person p1 = new Person(), p2 = new Person();
		p1.setName("张三");
		p1.setRole(r1);
		personService.myInsert(p1);

		p2.setName("李四");
		p2.setRole(r2);
		personService.myInsert(p2);

		Cart c1 = new Cart();
		c1.setDeal(false);
		c1.setPersonId(p1.getId());
		cartService.myInsert(c1);

		Cart cart = cartService.mySelect(c1.getId());
		Assert.assertEquals("普通会员", cart.getPerson().getRole().getName());

		Map<String, Object> m = new HashMap<>();
		m.put("id", r1.getId());
		m.put("name", "普通会员1");
		roleService.updateDirectWithoutCache(m);

		cart = cartService.mySelect(c1.getId());
		Assert.assertEquals("普通会员", cart.getPerson().getRole().getName());

		Map<String, Object> m2 = new HashMap<>();
		m2.put("id", r1.getId());
		m2.put("name", "普通会员2");
		roleService.updateDirect(m2);

		cart = cartService.mySelect(c1.getId());
		Assert.assertEquals("普通会员2", cart.getPerson().getRole().getName());
	}
}
