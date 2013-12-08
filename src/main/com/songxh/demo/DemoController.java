
package com.songxh.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.songxh.core.BaseController;

/**
 * 文件名： DemoController.java
 * 作者： 宋相恒
 * 版本： 2013-6-23 上午11:38:00 v1.0
 * 日期： 2013-6-23
 * 描述：
 */
@Controller
@RequestMapping("/")
public class DemoController extends BaseController {
	@RequestMapping("demo")
	public String demo(){
		request.setAttribute("info", "springmvc");
		return "demo";
	}
}

	