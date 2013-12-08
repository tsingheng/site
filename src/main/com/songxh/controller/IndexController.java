
package com.songxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.songxh.core.BaseController;

/**
 * 文件名： IndexController.java
 * 作者： 宋相恒
 * 版本： 2013-8-5 下午10:21:26 v1.0
 * 日期： 2013-8-5
 * 描述：
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	@RequestMapping("index")
	public String index(){
		
		return "site.index";
	}
}

	