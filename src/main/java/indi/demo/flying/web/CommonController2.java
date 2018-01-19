package indi.demo.flying.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import indi.demo.flying.condition.RoleCondition;
import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.RoleEnum;
import indi.demo.flying.service.CartCommodityService;
import indi.demo.flying.service.CartService;
import indi.demo.flying.service.CommodityService;
import indi.demo.flying.service2.PersonService;
import indi.demo.flying.service2.RoleService;

@Controller
public class CommonController2 {
	
	public static final String UNIQUE_VIEW_NAME = "__unique_view_name";
	
	@Autowired
	private CartService cartService;

	@Autowired
	private CartCommodityService cartCommodityService;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = { RequestMethod.GET }, value = "/addCommodityToCart")
	public String addCommodityToCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "value") RoleEnum value, @RequestParam(value = "name") String name) {
		RoleCondition roleCondition = new RoleCondition();
		roleCondition.setValueOrPersonName(value, name);
		Person p = new Person();
		p.setRole(roleCondition);
		Collection<Person> personC = personService.mySelectAll(p);
		mm.addAttribute("_content", personC);
		return UNIQUE_VIEW_NAME;
	}

}
