package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.Token;
import com.infoshare.lumato.logic.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Named
@RequestScoped
public class TokenDao extends CommonDAO {

    public String generateUserToken(User user) {
        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = true;
        String tokenString = RandomStringUtils.random(length, useLetters, useNumbers);
        Session session = getSession();
        Token tokenObject = new Token(user.getUserId(), tokenString);
        session.saveOrUpdate(tokenObject);
        executeAndCloseTransaction(session);
        return tokenString;
    }

    public String getUserToken(int userId) {
        Token token = null;
        Session session = getSession();
        try {
            String hQuery = "FROM Token T WHERE T.userId=:idParam";
            token = session.createQuery(hQuery, Token.class)
                    .setParameter("idParam", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("User with such id (" + userId + ") does not have a token!");
        }
        executeAndCloseTransaction(session);
        return token.getUserToken();
    }

}
