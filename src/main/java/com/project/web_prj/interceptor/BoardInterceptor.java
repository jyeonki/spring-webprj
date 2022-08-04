package com.project.web_prj.interceptor;

import com.project.web_prj.util.LoginUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.project.web_prj.util.LoginUtils.*;

// 인터셉터 : 컨트롤러에 요청이 들어가기 전, 후에 공통처리할 일들을 정의해놓는 클래스
@Configuration
@Log4j2
public class BoardInterceptor implements HandlerInterceptor {


    /*
        인터셉터의 전처리 메서드.
        리턴값이 true일 경우 컨트롤러 진입을 허용하고,
        false일 경우 진입을 허용하지 않는다.
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/member/sign-in.jsp");

        log.info("Board Interceptor preHandle() !");
        if (!isLogin(session)) {
            log.info("this request deny!! 집에 가");
//            dispatcher.forward(request, response);

            response.sendRedirect("/member/sign-in?message=no-login");
            // referer 때문에 RequestDispatcher 대신 sendRedirect 사용
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Board Interceptor postHandle() !");
    }
}
