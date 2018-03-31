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

import indi.demo.flying.pojo.Account22;
import indi.demo.flying.pojo.Account2_;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.Detail2_;
import indi.demo.flying.pojo.Detail_;
import indi.demo.flying.pojo.LoginLogSource2;
import indi.demo.flying.pojo.LoginLog_;
import indi.demo.flying.pojo.Product;
import indi.demo.flying.pojo.Role2_;
import indi.demo.flying.pojo.Role_;
import indi.demo.flying.service.Account22Service;
import indi.demo.flying.service.AccountService;
import indi.demo.flying.service.DetailService;
import indi.demo.flying.service.LoginLogService;
import indi.demo.flying.service.ProductService;
import indi.demo.flying.service.RoleService;
import indi.demo.flying.service2.Account2Service;
import indi.demo.flying.service2.Detail2Service;
import indi.demo.flying.service2.LoginLogSource2Service;
import indi.demo.flying.service2.Role2Service;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class, databaseConnection = { "dataSource1", "dataSource2" })
@ContextConfiguration("classpath:spring-test.xml")
public class AccountTest {

	@Autowired
	private DataSource dataSource1;

	@Autowired
	private AccountService accountService;

	@Autowired
	private Account22Service account22Service;

	@Autowired
	private Account2Service account2Service;

	@Autowired
	private DetailService detailService;

	@Autowired
	private Detail2Service detail2Service;

	@Autowired
	private LoginLogService loginLogService;

	@Autowired
	private LoginLogSource2Service loginLogSource2Service;

	@Autowired
	private ProductService productService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private Role2Service role2Service;

	@Test
	public void test() {
		Assert.assertNotNull(dataSource1);
		Assert.assertNotNull(accountService);
		Assert.assertNotNull(account2Service);
	}

	@Test
	@DatabaseSetups({
			@DatabaseSetup(connection = "dataSource1", type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/accountTest/test1.datasource1.xml"),
			@DatabaseSetup(connection = "dataSource2", type = DatabaseOperation.CLEAN_INSERT, value = "/indi/demo/flying/test/accountTest/test1.datasource2.xml") })
	@ExpectedDatabases({
			@ExpectedDatabase(connection = "dataSource1", override = false, assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/indi/demo/flying/test/accountTest/test1.datasource1.result.xml"),
			@ExpectedDatabase(connection = "dataSource2", override = false, assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/indi/demo/flying/test/accountTest/test1.datasource2.result.xml") })
	@DatabaseTearDowns({
			@DatabaseTearDown(connection = "dataSource1", type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/accountTest/test1.datasource1.result.xml"),
			@DatabaseTearDown(connection = "dataSource2", type = DatabaseOperation.DELETE_ALL, value = "/indi/demo/flying/test/accountTest/test1.datasource2.result.xml") })
	public void test1() {
		Account_ account_ = accountService.select(1);
		Assert.assertEquals("ann", account_.getName());

		Account22 account22_ = account22Service.select(11);
		Assert.assertEquals("ann11", account22_.getName());

		Account2_ account2_ = account2Service.select(1);
		Assert.assertEquals("nick", account2_.getNickname());

		Detail_ detail_ = detailService.select(21);
		Assert.assertEquals("cool", detail_.getName());

		Detail2_ detail2_ = detail2Service.select(11);
		Assert.assertEquals("dd", detail2_.getName());

		LoginLog_ loginLog_ = loginLogService.select(31);
		Assert.assertEquals("a", loginLog_.getLoginIP());

		LoginLogSource2 login2_ = loginLogSource2Service.select(21);
		Assert.assertEquals("ll", login2_.getLoginIP());
		Assert.assertEquals("ann", login2_.getAccount().getName());

		Product product = productService.select(41);
		Assert.assertEquals("p", product.getName());

		Role_ role_ = roleService.select(51);
		Assert.assertEquals("r", role_.getName());

		Role2_ role2_ = role2Service.select(31);
		Assert.assertEquals("rr", role2_.getName());
	}
}
