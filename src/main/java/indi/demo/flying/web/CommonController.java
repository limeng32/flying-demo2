package indi.demo.flying.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.LoginLogSource2;
import indi.demo.flying.pojo.LoginLog_;
import indi.demo.flying.pojo.Role_;
import indi.demo.flying.service.AccountService;
import indi.demo.flying.service.LoginLogService;
import indi.demo.flying.service.RoleService;
import indi.demo.flying.service2.LoginLogSource2Service;

@Controller
public class CommonController {

	public static final String UNIQUE_VIEW_NAME = "__unique_view_name";

	@Autowired
	private AccountService accountService;

	@Autowired
	private LoginLogService loginLogService;

	@Autowired
	private LoginLogSource2Service loginLog2Service;

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getAccount")
	public String getAccount(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Account_ a = accountService.select(id);
		mm.addAttribute("_content", a);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getLoginLog2")
	public String getLoginLog2(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		LoginLogSource2 l = loginLog2Service.select(id);
		mm.addAttribute("_content", l);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getLoginLog")
	public String getLoginLog(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		LoginLog_ l = loginLogService.select(id);
		mm.addAttribute("_content", l);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/updateAccount")
	public String updateAccount(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id, @RequestParam("name") String name) {
		Account_ a = accountService.select(id);
		a.setName(name);
		accountService.update(a);
		mm.addAttribute("_content", a);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/updateAccountWithoutCache")
	public String updateAccountWithoutCache(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id, @RequestParam("name") String name) {
		Account_ a = accountService.select(id);
		a.setName(name);
		accountService.updateWithoutCache(a);
		mm.addAttribute("_content", a);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/selectAccountWithoutCache")
	public String getAccountWithoutCache(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Account_ a = accountService.selectWithoutCache(id);
		mm.addAttribute("_content", a);
		return UNIQUE_VIEW_NAME;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getRole")
	public String getRole(HttpServletRequest request, HttpServletResponse response, ModelMap mm,
			@RequestParam("id") String id) {
		Role_ r = roleService.selectWithoutCache(id);
		mm.addAttribute("_content", r);
		return UNIQUE_VIEW_NAME;
	}
}
