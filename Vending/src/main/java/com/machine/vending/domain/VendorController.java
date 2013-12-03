package com.machine.vending.domain;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.machine.vending.service.VendorService;

@Controller("vendorController")
public class VendorController {

	@Resource(name = "vendorService")
	private VendorService vendorService;
	
	public VendorController() {
		super();
	}
	
	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

//  Have a Spring configuration issue somewhere otherwise I would use EL/JSTL for the initial render of the JSP
//	@RequestMapping("vendor.jsp")
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView modelView = new ModelAndView("vendor");
//		modelView.addObject("inventory", vendorService.getAllVendorItems());
//		modelView.addObject("userBalance", request.getSession().getAttribute(USER_BALANCE));
//		return modelView;
//	}
	
	@RequestMapping(value = "/getItems", method = RequestMethod.GET)
	public @ResponseBody InventoryResponse getVendorItems() {
		InventoryResponse inventory = new InventoryResponse();
		inventory.setInventory(vendorService.getAllVendorItems());
		inventory.setUserBalance(vendorService.getUserBalance());
		return inventory;
	}
	
	@RequestMapping(value = "/verifyAccount", method = RequestMethod.POST)
	public @ResponseBody ResultResponse verifyAccountInfo(@RequestParam(value = "username") String username, 
			@RequestParam(value = "password") String password, HttpServletResponse response) {
		response.setContentType("text/html");
		return vendorService.verifyAccountInfo(username, password);
	}
	
	@RequestMapping(value = "/restockItem", method = RequestMethod.POST)
	public @ResponseBody ResultResponse restockItem(@RequestParam(value = "itemId") Integer itemId) {
		return vendorService.restockItem(itemId);
	}
	
	@RequestMapping(value = "/insertCoin", method = RequestMethod.POST)
	public @ResponseBody ResultResponse insertCoin(@RequestParam(value = "amount") BigDecimal amount) {
		return vendorService.insertCoin(amount);
	}
	
	@RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
	public @ResponseBody ResultResponse purchaseItem(@RequestParam(value = "itemId") Integer itemId) {
		return vendorService.purchaseItem(itemId);
	}
	
	@RequestMapping(value = "/returnCoin", method = RequestMethod.POST)
	public @ResponseBody CoinReturnResponse returnCoin() {
		return vendorService.returnCoin();
	}
	
}
