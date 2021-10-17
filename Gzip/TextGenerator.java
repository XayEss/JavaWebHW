package ua.itea;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class TextGenerator extends HttpServlet {

	
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter pw = resp.getWriter();
	String text;
	
		StringBuilder giantText = new StringBuilder();
		for (int i = 0; i < 3000000; i++) {
//			int length = (int) (Math.random()*15);
//			byte[] array = new byte[length]; 
//		    new Random().nextBytes(array);
//			giantText.append(new String(array, StandardCharsets.UTF_8));
			Random r = new Random();
			char c = (char)(r.nextInt(26) + 'a');
			giantText.append(c);
		}
		text = giantText.toString();
		pw.write(text);
		pw.close();
	}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
