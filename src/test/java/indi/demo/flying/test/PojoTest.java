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

import indi.demo.flying.pojo.Commodity;
import indi.demo.flying.service.CommodityService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class, databaseConnection = { "dataSource1", "dataSource2" })
@ContextConfiguration("classpath:spring-test.xml")
public class PojoTest {

	@Autowired
	private DataSource dataSource1;

	@Autowired
	private CommodityService commodityService;

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
}
