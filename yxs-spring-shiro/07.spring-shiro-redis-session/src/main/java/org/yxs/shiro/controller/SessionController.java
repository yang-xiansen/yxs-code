package org.yxs.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxs.shiro.core.Result;
import org.yxs.shiro.entity.UserOnline;
import org.yxs.shiro.service.SessionService;

import java.util.List;


@Controller
@RequestMapping("/online")
public class SessionController {

	@Autowired
	SessionService sessionService;

	@RequestMapping("index")
	public String online() {
		return "online";
	}

	@ResponseBody
	@RequestMapping("list")
	public List<UserOnline> list() {
		return sessionService.list();
	}

	@ResponseBody
	@RequestMapping("forceLogout")
	public Result forceLogout(String id) {
		try {
			sessionService.forceLogout(id);
			return Result.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("踢出用户失败");
		}

	}
}
