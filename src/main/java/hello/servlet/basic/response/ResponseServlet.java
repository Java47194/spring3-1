package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseServlet",urlPatterns = "/response-header")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //status-line
      // response.setStatus(HttpServletResponse.SC_OK);  //Http 응답코드
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //HTTP 400응답

      //response-headers
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("cache-Control","mo-cache,no-store,must-revalidate"); //캐시를 무효화 하겠다
        response.setHeader("Pragma","no-cache");//과거 버전 캐시 무효화
        response.setHeader("my-header","hello");

      //[header 편의 메서드]
        // content(response);
        //cookie(response);
        //redirect(response);

        PrintWriter writer = response.getWriter();
        writer.write("ok");


    }

    private void content(HttpServletResponse response){
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    //쿠키 편의 메서드
    private void cookie(HttpServletResponse response){
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600);//600초
        response.addCookie(cookie);

    }


    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        // response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");

        response.sendRedirect("basic/hello-form.html");
    }


}
