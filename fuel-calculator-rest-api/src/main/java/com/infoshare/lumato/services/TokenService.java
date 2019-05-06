package com.infoshare.lumato.services;

import com.infoshare.lumato.logic.dao.TokenDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TokenService {

    @Inject
    TokenDao tokenDao;

    public boolean isTokenValid(int userId, String userToken) {
        String tokenInDatabase = tokenDao.getUserToken(userId);
        return tokenInDatabase.equals(userToken);
    }


}
