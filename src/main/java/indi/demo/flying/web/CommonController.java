package indi.demo.flying.web;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import indi.demo.flying.condition.CartCommodityCondition;
import indi.demo.flying.entity.Cart;
import indi.demo.flying.entity.CartCommodity;
import indi.demo.flying.entity.Commodity;
import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.Role;
import indi.demo.flying.service.CartCommodityService;
import indi.demo.flying.service.CartService;
import indi.demo.flying.service.CommodityService;
import indi.demo.flying.service2.PersonService;
import indi.demo.flying.service2.RoleService;

@Controller
public class CommonController {

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

	@RequestMapping(method = { RequestMethod.GET }, value = "/getCart")
	public String getCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Cart cart = cartService.mySelect(id);
		mm.addAttribute("_content", cart);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getCommodity")
	public String getCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Commodity commodity = commodityService.mySelect(id);
		mm.addAttribute("_content", commodity);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/addCommodity")
	public String AddCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "name", required = false) String name) {
		Commodity commodity = new Commodity();
		commodity.setName(name);
		commodity.setPrice(price);
		commodityService.myInsert(commodity);
		mm.addAttribute("_content", commodity);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/updateCommodity")
	public String updateCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "name", required = false) String name) {
		Commodity commodity = commodityService.mySelect(id);
		if (commodity != null) {
			if (name != null) {
				commodity.setName(name);
			}
			if (price != null) {
				commodity.setPrice(price);
			}
			commodityService.myUpdate(commodity);
		}
		mm.addAttribute("_content", commodity);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getCommodityByCart")
	public String getCommodityByCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String cartId) {
		Cart cartCondition = new Cart();
		cartCondition.setId(cartId);
		CartCommodityCondition cartCommodityCondition = new CartCommodityCondition();
		cartCommodityCondition.setCart(cartCondition);
		Collection<CartCommodity> cartCommodityC = cartCommodityService.mySelectAll(cartCommodityCondition);
		mm.addAttribute("_content", cartCommodityC);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/dealCart")
	public String dealCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String cartId) {
		Cart cart = cartService.mySelect(cartId);
		if (cart != null) {
			cart.setDeal(true);
			cart.setDealTime(Calendar.getInstance().getTime());
			cartService.myUpdate(cart);

			CartCommodity cartCommodityCondition = new CartCommodity();
			cartCommodityCondition.setCart(cart);
			Collection<CartCommodity> cartCommodityC = cartCommodityService.mySelectAll(cartCommodityCondition);
			mm.addAttribute("_content", cartCommodityC);
		}
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/undealCart")
	public String unDealCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String cartId) {
		Cart cart = cartService.mySelect(cartId);
		if (cart != null) {
			cart.setDeal(false);
			cart.setDealTime(null);
			cartService.myUpdatePersistent(cart);

			CartCommodity cartCommodityCondition = new CartCommodity();
			cartCommodityCondition.setCart(cart);
			Collection<CartCommodity> cartCommodityC = cartCommodityService.mySelectAll(cartCommodityCondition);
			mm.addAttribute("_content", cartCommodityC);
		}
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getPerson")
	public String getPerson(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Person person = personService.mySelect(id);
		mm.addAttribute("_content", person);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getCartCommodity")
	public String getCartCommodity(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		CartCommodity c = cartCommodityService.mySelect(id);
		mm.addAttribute("_content", c);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getRole")
	public String getRole(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Role role = roleService.mySelect(id);
		mm.addAttribute("_content", role);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/updateRoleDirectly")
	public String updateRoleDirectly(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		if (id != null) {
			Map<String, Object> m = new HashMap<>();
			m.put("id", id);
			m.put("name", name);
			roleService.updateDirect(m);
		}
		Role role = roleService.mySelect(id);
		mm.addAttribute("_content", role);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/updateRoleDirectlyWithoutCache")
	public String updateRoleDirectlyWithoutCache(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		if (id != null) {
			Map<String, Object> m = new HashMap<>();
			m.put("id", id);
			m.put("name", name);
			roleService.updateDirectWithoutCache(m);
		}
		Role role = roleService.mySelect(id);
		mm.addAttribute("_content", role);
		return UNIQUE_VIEW_NAME;
	}
}
