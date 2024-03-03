package hello.servlet.basic.response;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * status-line
		 * */
		// setStatus() : HTTP 응답 코드 세팅
		// 이떄 응답 코드를 상수로 정의한다.
		// - 200을 직접 적으면 magic number(어떤 의미인지 알 수 없는 값)가 아니라 의미 있는 값(SC_OK)이 되므로 일반적으로 상수를 활용한다.
		response.setStatus(HttpServletResponse.SC_OK); // 응답 코드를 상수로 정의

		/**
		 * response-headers
		 * */
		// response.setHeader("Content-Type", "text/plain;charset=utf-8"); // charset=utf-8 : 한글 지원

		// 캐시 무효화
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");

		// 개발자가 임의의 헤더 입력
		response.setHeader("my-header", "hello");

		/**
		 * Header 편의 메서드
		 * */
		content(response); // 표현 헤더(content 헤더) 관련 편의 메서드
		cookie(response); // 쿠키 관련 편의 메서드
		redirect(response); // redirect 관련 편의 메서드

		/**
		 * 응답 message body
		 * */
		response.getWriter().write("ok");
	}


	private void content(HttpServletResponse response){
		// Content-Type 헤더 값을 개발자가 직접 세팅하지 않고 setter 메서드로 지정할 수 있다.

		// 개발자가 직접 세팅
		// response.setHeader("Content-Type", "text/plain;charset=utf-8");

		// setter 편의 기능 사용 - 개발자가 직접 세팅하는 경우와 동일하게 동작한다.
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		//response.setContentLength(100); // 생략시 자동으로 byte 크기를 계산해서 생성된다.
	}

	private void cookie(HttpServletResponse response){
		// 헤더의 들어가는 Cookie 와 관련된 값을 개발자가 직접 세팅하지 않고 Cookie 객체와 메서드를 사용하여 편리하게 지정할 수 있다.

		// 개발자가 직접 세팅
		response.setHeader("Set-Cookie", "myCookie=good; Max-age=600");

		// Cookie 객체와 addCookie() 메서드 사용 - 개발자가 직접 세팅하는 경우와 동일하게 동작한다.
		Cookie cookie = new Cookie("myCookie", "good");
		cookie.setMaxAge(600); // 쿠키 유효 시간 : 600초
		response.addCookie(cookie); // HTTP 응답 헤더에 쿠키 세팅
	}

	private void redirect(HttpServletResponse response) throws IOException {
		// Ststus code : 302
		// Redircet Location : /basic/hello-form.html

		// 개발자가 직접 세팅
		// response.setStatus(HttpServletResponse.SC_FOUND);
		// response.setHeader("Location", "/basic/hello-form.html");

		// sendRequest() 메서드 사용 - 개발자가 직접 세팅하는 경우와 동일하게 동작한다.
		response.sendRedirect("/basic/hello-form.html");
	}
}
