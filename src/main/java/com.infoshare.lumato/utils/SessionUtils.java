package com.infoshare.lumato.utils;

import com.infoshare.lumato.models.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse(){
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        return response;
    }


    public static void redirect(String address) {
        try {
            getResponse().sendRedirect(address);
        } catch (IOException e) {
            System.out.println("Error while redirecting");
        }
    }
}