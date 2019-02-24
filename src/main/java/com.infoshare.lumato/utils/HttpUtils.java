package com.infoshare.lumato.utils;

import com.infoshare.lumato.models.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HttpUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
    }


    public static void redirect(String address) {
        try {
            getResponse().sendRedirect(address);
        } catch (IOException e) {
            System.out.println("Error while redirecting");
        }
    }

    private static Cookie[] getCookies() {
        return HttpUtils.getRequest().getCookies();
    }

    public static String getCookieValueByCookieName(String cookieKey) {
        Cookie[] kookeys = getCookies();
        if (kookeys == null) {
            return "";
        }
        for (int i = 0; i < kookeys.length; i++) {
            if (kookeys[i].getName().equals(cookieKey)) {
                return kookeys[i].getValue();
            }
        }
        return "";
    }

    public static User getCurrentUserFromSession(){
        return (User) HttpUtils.getSession().getAttribute("currentUser");
    }
}