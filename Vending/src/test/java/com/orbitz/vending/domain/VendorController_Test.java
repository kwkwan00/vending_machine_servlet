package com.orbitz.vending.domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.orbitz.vending.service.VendorService;

public class VendorController_Test {

	private VendorController vendorController;
	
	private VendorService vendorService = mock(VendorService.class);
	
	@Before
	public void setUp() {
		vendorController = new VendorController(vendorService);
	}
	
	@Test
	public void vendorItemsMapping() {
		InventoryResponse response;
		
		when(vendorService.getAllVendorItems()).thenReturn(null);
		when(vendorService.getUserBalance()).thenReturn(null);
		response = vendorController.getVendorItems();
		assertNull(response.getInventory());
		assertNull(response.getUserBalance());
		
		when(vendorService.getAllVendorItems()).thenReturn(new ArrayList<VendorItem>());
		when(vendorService.getUserBalance()).thenReturn(BigDecimal.ONE);
		response = vendorController.getVendorItems();
		assertEquals(new ArrayList<VendorItem>(), response.getInventory());
		assertEquals(BigDecimal.ONE, response.getUserBalance());
	}
	
	@Test
	public void verifyAccountInfoMapping() {
		when(vendorService.verifyAccountInfo(null, null)).thenReturn(null);
		assertNull(vendorController.verifyAccountInfo(null, null, new MyMockServletResponse()));
		
		when(vendorService.verifyAccountInfo(null, null)).thenReturn(new ResultResponse());
		assertEquals(new ResultResponse(), vendorController.verifyAccountInfo(null, null, new MyMockServletResponse()));
	}
	
	@Test
	public void restockItemMapping() {
		when(vendorService.restockItem(null)).thenReturn(null);
		assertNull(vendorController.restockItem(null));
		
		when(vendorService.restockItem(0)).thenReturn(new ResultResponse());
		assertEquals(new ResultResponse(), vendorController.restockItem(0));
	}
	
	@Test
	public void insertCoinMapping() {
		when(vendorService.insertCoin(null)).thenReturn(null);
		assertNull(vendorController.insertCoin(null));
		
		when(vendorService.insertCoin(BigDecimal.ONE)).thenReturn(new ResultResponse());
		assertEquals(new ResultResponse(), vendorController.insertCoin(BigDecimal.ONE));
	}
	
	@Test
	public void purchaseProductMapping() {
		when(vendorService.purchaseItem(null)).thenReturn(null);
		assertNull(vendorController.purchaseItem(null));
		
		when(vendorService.purchaseItem(0)).thenReturn(new ResultResponse());
		assertEquals(new ResultResponse(), vendorController.purchaseItem(0));
	}
	
	@Test
	public void returnCoinMapping() {
		when(vendorService.returnCoin()).thenReturn(null);
		assertNull(vendorController.returnCoin());
		
		when(vendorService.returnCoin()).thenReturn(new CoinReturnResponse());
		assertEquals(new CoinReturnResponse(), vendorController.returnCoin());
	}
	
	private class MyMockServletResponse implements HttpServletResponse {

		@Override
		public void flushBuffer() throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getBufferSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isCommitted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetBuffer() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBufferSize(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setCharacterEncoding(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setContentLength(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setContentType(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setLocale(Locale arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addCookie(Cookie arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addDateHeader(String arg0, long arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addHeader(String arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addIntHeader(String arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean containsHeader(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String encodeRedirectURL(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeRedirectUrl(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeURL(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeUrl(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getHeader(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<String> getHeaderNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<String> getHeaders(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getStatus() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void sendError(int arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendError(int arg0, String arg1) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendRedirect(String arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setDateHeader(String arg0, long arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHeader(String arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setIntHeader(String arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setStatus(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setStatus(int arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}