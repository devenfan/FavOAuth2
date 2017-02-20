package com.favccxx.favauth.service;

import com.favccxx.favauth.pojo.FavUser;

import java.util.List;

public interface IFavUserService {

	public FavUser createFavUser(FavUser favUser);

    public FavUser updateFavUser(FavUser favUser);

    public void deleteFavUser(Long userId);

    FavUser findOne(Long userId);

    List<FavUser> findAll();

    FavUser findByUserId(Long userId);

    FavUser findByUsername(String username);

    FavUser findByUsernameAndPassword(String username, String password);
}
