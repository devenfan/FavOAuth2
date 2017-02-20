package com.favccxx.favauth.web.oauth2;

import java.util.List;

import com.favccxx.favauth.dto.ResultJson;
import com.favccxx.favauth.pojo.FavUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.favccxx.favauth.pojo.FavWebApp;
import com.favccxx.favauth.service.IFavWebAppService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("oauth2")
public class OAuth2LoginController {
	
	@Autowired
	IFavWebAppService favWebAppService;
	
	@RequestMapping(value={"getoAuth2loginapp"})
	public ModelAndView getOAuth2LoginApp(){
		ModelAndView mav = new ModelAndView();
		List<FavWebApp> websiteList =  favWebAppService.findAll();
		mav.addObject("websiteList", websiteList);
		mav.setViewName("oauth2/oAuth2_login_app");
		return mav;
	}
	
	
	@RequestMapping(value={"oauth2login"})
	public ModelAndView oauth2Login(HttpSession session, String webId){
		ModelAndView mav = new ModelAndView();
		if(!StringUtils.isEmpty(webId)){
			FavWebApp favWebApp = favWebAppService.findOne(Long.valueOf(webId));
            FavUser favUser = (FavUser) session.getAttribute("favUser");
			mav.addObject("favWebApp", favWebApp);
            mav.addObject("favUser", favUser);
		}		
		mav.setViewName("oauth2/oAuth2Login");
		return mav;
	}


	@RequestMapping("userAuthorize")
	@ResponseBody
    public ResultJson userAuthorize() {
        return null;
    }

}
