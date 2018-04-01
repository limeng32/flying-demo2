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

import indi.demo.flying.pojo.Cart;
import indi.demo.flying.pojo.CartCommodity;
import indi.demo.flying.pojo.Commodity;
import indi.demo.flying.pojo.Person;
import indi.demo.flying.pojo.Role;
import indi.demo.flying.pojo.RoleEnum;
import indi.demo.flying.service.CartCommodityService;
import indi.demo.flying.service.CartService;
import indi.demo.flying.service.CommodityService;
import indi.demo.flying.service2.PersonService;
import indi.demo.flying.service2.Role_Service;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class, databaseConnection = { "dataSource1", "dataSource2" })
@ContextConfiguration("classpath:spring-test.xml")
public class PojoTest {

	@Autowired
	private DataSource dataSource1;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartCommodityService cartCommodityService;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private PersonService personService;

	@Autowired
	private Role_Service role_Service;

	@Test
	public void test() {
		Assert.assertNotNull(dataSource1);
		Assert.assertNotNull(commodityService);
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testCommodity.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testCommodity.result.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testCommodity.result.xml")
	public void testCommodity() {
		Commodity commodity = commodityService.mySelect("mmm");
		Assert.assertEquals("牙刷", commodity.getName());

		commodity.setPrice(1500);
		commodityService.myUpdate(commodity);

		Commodity commodity2 = commodityService.mySelect("nnn");
		Assert.assertEquals(2000, commodity2.getPrice().intValue());
		commodityService.myDelete(commodity2);
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testRole.xml", connection = "dataSource2")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testRole.result.xml", connection = "dataSource2", override = false)
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testRole.result.xml", connection = "dataSource2")
	public void testRole() {
		Role role = role_Service.mySelect("aaa");
		Assert.assertEquals("普通会员", role.getName());

		Role role2 = role_Service.mySelect("bbb");
		role2.setName("银牌会员");
		role2.setValue(RoleEnum.silver);
		role_Service.myUpdate(role2);
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testPerson.xml", connection = "dataSource2")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testPerson.result.xml", connection = "dataSource2", override = false)
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testPerson.result.xml", connection = "dataSource2")
	public void testPerson() {
		Person person = personService.mySelectWithoutCache("mmm");
		Assert.assertEquals("张三", person.getName());
		Assert.assertEquals(RoleEnum.normal, person.getRole().getValue());

		Person person2 = personService.mySelectWithoutCache("nnn");
		Role role = role_Service.mySelect("ccc");
		person2.setRole(role);
		personService.myUpdate(person2);
	}

	@Test
	@DatabaseSetups({
			@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testCart.dataSource.xml", connection = "dataSource1"),
			@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testCart.dataSource2.xml", connection = "dataSource2") })
	@ExpectedDatabases({
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testCart.dataSource.result.xml", connection = "dataSource1", override = false),
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testCart.dataSource2.result.xml", connection = "dataSource2", override = false) })
	@DatabaseTearDowns({
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testCart.dataSource.result.xml", connection = "dataSource1"),
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testCart.dataSource2.result.xml", connection = "dataSource2"), })
	public void testCart() {
		Cart cart = cartService.mySelect("aaa");
		Assert.assertEquals("张三", cart.getPerson().getName());
		Assert.assertEquals("普通会员", cart.getPerson().getRole().getName());

		Person lisi = personService.mySelect("nnn");
		cart.setPerson(lisi);
		cartService.myUpdate(cart);

		Person zhangsan = personService.mySelect("mmm");
		Role silver = role_Service.mySelect("bbb");
		zhangsan.setRole(silver);
		personService.myUpdate(zhangsan);
	}

	@Test
	@DatabaseSetups({
			@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testCartCommodity.dataSource.xml", connection = "dataSource1"),
			@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/pojoTest/testCartCommodity.dataSource2.xml", connection = "dataSource2") })
	@ExpectedDatabases({
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testCartCommodity.dataSource.result.xml", connection = "dataSource1", override = false),
			@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/indi/demo/flying/test/pojoTest/testCartCommodity.dataSource2.result.xml", connection = "dataSource2", override = false) })
	@DatabaseTearDowns({
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testCartCommodity.dataSource.result.xml", connection = "dataSource1"),
			@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/pojoTest/testCartCommodity.dataSource2.result.xml", connection = "dataSource2"), })
	public void testCartCommodity() {
		CartCommodity cartCommodity = cartCommodityService.mySelect(100000000000001L);
		Assert.assertEquals("普通会员", cartCommodity.getCart().getPerson().getRole().getName());
		Assert.assertEquals("牙刷", cartCommodity.getCommodity().getName());

		Cart cart = cartService.mySelect("bbb");
		Commodity commodity = commodityService.mySelect("mmm");
		CartCommodity cc = new CartCommodity();
		cc.setCart(cart);
		cc.setCommodity(commodity);
		cc.setAmount(3);
		cartCommodityService.myInsert(cc);
	}
}
