package com.nibble.samples.is.interceptor;

import java.util.Map;
import com.softwareag.is.interceptor.HttpInterceptorException;

public class HttpInboundInterceptor implements com.softwareag.is.interceptor.HttpInterceptorIFC {

	@Override
	public void preProcess(String path, String method, String protocol, Map<String, String> headers, byte[] body, String uuid)
			throws HttpInterceptorException {
		System.out.println("HTTP Inbound Pre-Process >>>");
		try {
			System.out.println("  Method: " + method);
			System.out.println("  Path: " + path);
			System.out.println("  Protocol: " + protocol);
			System.out.println("  Headers:");
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				System.out.println("    " + entry.getKey() + ": " + entry.getValue());
			}
			System.out.println("  UUID: " + uuid);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		System.out.println("<<< HTTP Inbound Pre-Process");

	}

	@Override
	public void postProcess(int code, String text, Map<String, String> headers, byte[] body, String uuid) {
		System.out.println("HTTP Inbound Post-Process >>>");
		try {
			System.out.println("  Response code: " + code);
			System.out.println("  Response text: " + text);
			System.out.println("  Headers:");
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				System.out.println("    " + entry.getKey() + ": " + entry.getValue());
			}
			System.out.println("  UUID: " + uuid);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		System.out.println("<<< HTTP Inbound Post-Process");
	}
}
