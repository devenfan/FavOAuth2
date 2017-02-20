package com.favccxx.favauth.web.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.favccxx.favauth.service.IFavUserService;
import com.favccxx.favauth.service.impl.FavUserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.favccxx.favauth.pojo.FavUser;

@Controller
public class LoginController {

    @Resource
    private IFavUserService favUserService;
	
	@RequestMapping(value={"login","/"})
	public String login(){
		return "login/login";
	}
	
	
	@RequestMapping("loginForm")
	public ModelAndView loginForm(HttpSession session,HttpServletRequest request,String username, String password, Model model){

        String errorMsg = "";
        ModelAndView mav = new ModelAndView();
        System.out.println("Username/Password: " + username + " / " + password);

		request.setAttribute("username", username);
		request.setAttribute("password", password);

		if(StringUtils.isEmpty(username)) {
            errorMsg = "Username cannot be empty!";
            mav.addObject("errorMsg", errorMsg);
			mav.setViewName("login/login");
			return mav;
		}

        FavUser favUser = null;

        favUser = favUserService.findByUsername(username);
        if(favUser == null) {
            errorMsg = "Username not exist!";
            mav.addObject("errorMsg", errorMsg);
            mav.setViewName("login/login");
            return mav;
        }

        favUser = favUserService.findByUsernameAndPassword(username, password);
        if(favUser == null) {
            errorMsg = "Username/Password not match!";
            mav.addObject("errorMsg", errorMsg);
            mav.setViewName("login/login");
            return mav;
        }

        session.setAttribute("favUser", favUser);
        mav.addObject("favUser", favUser);
        mav.setViewName("forward:/admin");
		return mav;
	}
	
//	@RequestMapping("oauth2login")
//	public ModelAndView oauth2Login(){
//		ModelAndView mav = new ModelAndView();
//		return mav;
//	}

}
