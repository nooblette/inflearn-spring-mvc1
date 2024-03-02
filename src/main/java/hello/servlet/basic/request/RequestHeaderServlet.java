package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		printStartLine(request);
		printHeaders(request);
		printHeaderUtils(request);
		printEtc(request);
	}

	// HTTP 요청 메시지의 가장 첫 라인(start line) 출력
	private static void printStartLine(HttpServletRequest request) {
		System.out.println("--- REQUEST-LINE - start ---");

		// 요청 uri : http://localhost:8080/request-header?hello
		System.out.println("request.getMethod() = " + request.getMethod()); //GET
		System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
		System.out.println("request.getScheme() = " + request.getScheme()); //http
		System.out.println("request.getRequestURL() = " + request.getRequestURL()); // http://localhost:8080/request-header
		System.out.println("request.getRequestURI() = " + request.getRequestURI()); // /request-header
		System.out.println("request.getQueryString() = " + request.getQueryString()); // ?hello
		System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
		System.out.println();

		System.out.println("--- REQUEST-LINE - end ---");
	}

	// Headers 모든 정보 출력
	private static void printHeaders(HttpServletRequest request){
		System.out.println("--- Headers - start ---");

		// 방법 1) getHeaderNames() 메서드 사용(예전 방식) : HTTP 메서드의 모든 헤더 정보를 가져와서 출력한다.
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headerName = headerNames.nextElement();
			System.out.println(headerName + ": " + headerName);
		}
		System.out.println();

		// 방법 2) getHeaderNames().asIterator() 메서드 사용(요즘 방식) : 간결한 문법 사용
		request.getHeaderNames().asIterator()
			.forEachRemaining(headerName -> System.out.println(headerName + ": " + headerName));

		System.out.println("--- Headers - end ---");
	}

	// Headers 유용한 정보만 출력
	private static void printHeaderUtils(HttpServletRequest request){
		System.out.println("--- Header Utils - start ---");
		System.out.println("[HOST 편의 조회]");

		// getServerName(), getServerPort() : 브라우저 Request Headers의 Host 헤더의 값들을 조회한다. (e.g. Host: localhost:8080)
		System.out.println("request.getServerName() = " + request.getServerName()); // host name 조회
		System.out.println("request.getServerPort() = " + request.getServerPort()); // port 조회
		System.out.println();

		System.out.println("[Accept-Language(콘텐츠 협상 헤더) 편의 조회]");
		// getLocales() : 브라우저 Request Headers의 Accept-Language 헤더의 모든 값들을 조회한다.
		// (e.g. Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7 (언어를 세팅하는 웹 브라우저가 선호하는 언어 우선순위 순서))
		request.getLocales().asIterator()
				.forEachRemaining(locale -> System.out.println("locale = " + locale));
		System.out.println("request.getLocale() = " + request.getLocale()); // getLocale() : 웹 브라우저가 가장 선호하는 언어를 조회
		System.out.println();

		System.out.println("[cookie 편의 조회]");
		// getCookies() : 쿠키 정보 조회, 쿠키도 HTTP 요청 헤더에 담긴다.
		if(request.getCookies() != null){
			for (Cookie cookie : request.getCookies()) {
				// 개발자가 원하는 쿠키값을 출력
				System.out.println(cookie.getName() + ": " + cookie.getValue());
			}
		}
		System.out.println();

		System.out.println("[Content 편의 조회]");
		// getContentXx() : Content 헤더와 관련된 정보 조회
		System.out.println("request.getContentType() = " + request.getContentType()); // HTTP 본문(body) 담겨진 정보들을 조회(Get 방식일때는 null 로 조회된다.)
		System.out.println("request.getContentLength() = " + request.getContentLength()); // contentLength와 관련된 정보는 웹 브라우저(혹은 postman 등)가 만들어준다.
		System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
		System.out.println();

		// 특정 헤더 정보(e.g. host, pord etc)만 조회
		System.out.println("[특정 헤더 정보 조회]");
		String host = request.getHeader("host");
		System.out.println("host = " + host);
		System.out.println("--- Header Utils - end ---");
	}

	// Headers 기타 정보 조회
	private static void printEtc(HttpServletRequest request){
		System.out.println("--- 기타 조회 start ---");

		// Remote : 서버가 받은 요청에 대한 정보(네트워크 커넥션과 관련된 정보를 확인)
		System.out.println("[Remote 정보]");
		System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
		System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
		System.out.println("request.getRemotePort() = " + request.getRemotePort());

		// Local : 서버에 대한 정보
		System.out.println("[Local 정보]");
		System.out.println("request.getLocalName() = " + request.getLocalName());
		System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
		System.out.println("request.getLocalPort() = " + request.getLocalPort());

		System.out.println("--- 기타 조회 end ---");
		System.out.println();
	}
}
