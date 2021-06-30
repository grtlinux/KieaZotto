package org.tain.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IpPrint {

	public static void print() {
		if (Boolean.TRUE) {
			System.out.println(">>>>> Client IP: " + get());
		}
	}
	
	public static String get() {
		String ip = "";
		
		if (Boolean.TRUE) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();
		}
		
		return ip;
	}
}
