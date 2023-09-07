package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.Map;

public class MyView {
    
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    
    public void render(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    public void render(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws ServletException, IOException {

        modelToRequestAttribute(request, model);
        MapKeyValue(model);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);

    }

    private static void MapKeyValue(Map<String, Object> model) {
        model.forEach((key,value)-> System.out.println("key : "+key +"value : "+ value ));
    }

    private static void modelToRequestAttribute(HttpServletRequest request, Map<String, Object> model) {
        model.forEach((key, value)-> request.setAttribute(key,value));
    }
}
