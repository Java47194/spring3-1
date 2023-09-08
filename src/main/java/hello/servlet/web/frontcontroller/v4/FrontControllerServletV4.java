package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4 ",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String,ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form",new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save",new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();// uri 가져오기
        ControllerV4 controller = controllerV4Map.get(requestURI);

        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        Map<String,Object>model = new HashMap<>(); // model 생성
        System.out.println("model1 = " + model); // 빈값


        String viewName = controller.process(paramMap, model); // 빈 모델 값을 넘김
        System.out.println("viewName = " + viewName);

        MyView view= new MyView("/WEB-INF/views/" + viewName + ".jsp");
        System.out.println("model2 = " + model); //MemberSaveControllerV4에 값을 받음
        model.forEach((key,value)-> System.out.println("key2 = " + key +" value2 = "+ value));

        view.render(request,response,model);


    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String>paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
