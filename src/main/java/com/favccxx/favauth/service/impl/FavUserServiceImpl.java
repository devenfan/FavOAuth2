package com.favccxx.favauth.service.impl;

import com.favccxx.favauth.dao.IFavClientDao;
import com.favccxx.favauth.dao.IFavUserDao;
import com.favccxx.favauth.pojo.FavClient;
import com.favccxx.favauth.pojo.FavUser;
import com.favccxx.favauth.service.IFavClientService;
import com.favccxx.favauth.service.IFavUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("favUserService")
public class FavUserServiceImpl implements IFavUserService {

	@Autowired
	private IFavUserDao favUserDao;


    @Override
    public FavUser createFavUser(FavUser favUser) {
        return favUserDao.createFavUser(favUser);
    }

    @Override
    public FavUser updateFavUser(FavUser favUser) {
        return favUserDao.updateFavUser(favUser);
    }

    @Override
    public void deleteFavUser(Long userId) {
        favUserDao.deleteFavUser(userId);
    }

    @Override
	public FavUser findOne(Long clientId) {
		return favUserDao.findOne(clientId);
	}

	@Override
	public List<FavUser> findAll() {
		return favUserDao.findAll();
	}

    @Override
    public FavUser findByUserId(Long userId) {
        return favUserDao.findOne(userId);
    }

    @Override
    public FavUser findByUsername(String username) {
        return favUserDao.findByUsername(username);
    }

    @Override
    public FavUser findByUsernameAndPassword(String username, String password) {
        return favUserDao.findByUsernameAndPassword(username, password);
    }

}
