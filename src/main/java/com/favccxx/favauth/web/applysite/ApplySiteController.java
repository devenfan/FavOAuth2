package com.favccxx.favauth.web.applysite;

import java.util.List;
import java.util.UUID;

import com.favccxx.favauth.dto.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.favccxx.favauth.pojo.FavWebApp;
import com.favccxx.favauth.service.IFavWebAppService;

@Controller
@RequestMapping("applysite")
public class ApplySiteController {
	
	@Autowired
	IFavWebAppService favWebAppService;
	
	@RequestMapping("initapplysite")
	public ModelAndView initApplySite(){
		ModelAndView mav = new ModelAndView();
		String webKey = UUID.randomUUID().toString();
		mav.addObject("webKey", webKey);
		mav.setViewName("applysite/site_apply");
		
		return mav;
	}
	
	@RequestMapping("initapprovesite")
	public ModelAndView initApproveSite(){
		ModelAndView mav = new ModelAndView();
		List<FavWebApp> websiteList =  favWebAppService.findAll();
		mav.addObject("websiteList", websiteList);
		mav.setViewName("applysite/site_approval");
		
		return mav;
	}
	
	@RequestMapping("approvewebsite")
    @ResponseBody
	public ResultJson approveWebSite(String webId, String approveState){

        ResultJson resultJson = new ResultJson();
        FavWebApp favWebApp = favWebAppService.findOne(Long.valueOf(webId));

        if(favWebApp == null) {
            resultJson.setErrorCode("Invalid Parameters");
            resultJson.setErrorMsg("待审批网站不存在！");
            return resultJson;
        }

        favWebApp.setWebState(approveState);
        favWebAppService.updateFavWebApp(favWebApp);
        resultJson.setSuccess(true);
        return resultJson;
    }

	
	@RequestMapping("/addwebsite")
	@ResponseBody
	public ResultJson addWebSite(FavWebApp favWebApp){

        ResultJson resultJson = new ResultJson();

        if(StringUtils.isEmpty(favWebApp.getWebName())) {
            resultJson.setErrorCode("Invalid Parameters");
            resultJson.setErrorMsg("网站名称不可为空！");
            return resultJson;
        }

        if(StringUtils.isEmpty(favWebApp.getWebUrl())) {
            resultJson.setErrorCode("Invalid Parameters");
            resultJson.setErrorMsg("网站URL不可为空！");
            return resultJson;
        }

		ModelAndView mav = new ModelAndView();
		mav.setViewName("webapply/web_application");
		favWebAppService.createFavWebApp(favWebApp);
        resultJson.setSuccess(true);
		return resultJson;
	}
	
	
}
