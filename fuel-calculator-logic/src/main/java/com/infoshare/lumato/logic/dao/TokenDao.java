package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.Token;
import com.infoshare.lumato.logic.model.User;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TokenDao extends CommonDAO {

    public void generateUserToken(User user) {
        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = true;
        String tokenString = RandomStringUtils.random(length, useLetters, useNumbers);
        Session session = getSession();
        Token tokenObject = new Token(user.getUserId(), tokenString);
        session.saveOrUpdate(tokenObject);
        executeAndCloseTransaction(session);

    }

    public String getUserToken() {
        throw new NotImplementedException("method 'getUserToken' was not implemented yet!");
    }

}
