package ua.itea;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class GZipServletFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  httpRequest  = (HttpServletRequest)  request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    if ( acceptsGZipEncoding(httpRequest) ) {
	      httpResponse.addHeader("Content-Encoding", "gzip");
	      GZipServletResponseWrapper gzipResponse =
	        new GZipServletResponseWrapper(httpResponse);
	      chain.doFilter(request, gzipResponse);
	      gzipResponse.close();
	    } else {
	      chain.doFilter(request, response);
	    }
		
	}
	
	private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
	      String acceptEncoding = 
	        httpRequest.getHeader("Accept-Encoding");

	      return acceptEncoding != null && 
	             acceptEncoding.indexOf("gzip") != -1;
	  }

}
