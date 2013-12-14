
package com.songxh.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseController;
import com.songxh.core.Page;
import com.songxh.cust.service.MessageService;
import com.songxh.product.entity.ProCategory;
import com.songxh.product.entity.Product;
import com.songxh.product.service.ProCategoryService;
import com.songxh.product.service.ProImageService;
import com.songxh.product.service.ProductService;
import com.songxh.site.entity.RotateImage;
import com.songxh.site.entity.SiteProperty;
import com.songxh.site.service.ImageDisplayService;
import com.songxh.site.service.InfoService;
import com.songxh.site.service.RotateImageService;
import com.songxh.site.service.SitePropertyService;

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
	@Autowired
	private ProductService productService;
	@Autowired
	private ProCategoryService proCategoryService;
	@Autowired
	private ProImageService proImageService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ImageDisplayService imageDisplayService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private RotateImageService rotateImageService;
	@Autowired
	private SitePropertyService sitePropertyService;
	private static final String TITLE = "title";
	@RequestMapping("/index")
	public String index(){
		request.setAttribute(TITLE, "home");
		request.setAttribute("type", "index");
		common();
		return "site.index";
	}
	
	@RequestMapping("/product/p{pageNo}")
	public String proCatePage(@PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", "product");
		request.setAttribute("title", "Products");
		common();
		if(pageNo == null) pageNo = 1;
		Page<ProCategory> page = new Page<ProCategory>(pageNo, 4);
		page = proCategoryService.findList(page, new HashMap<String, Object>());
		for(ProCategory category : page.getResult()){
			List<Product> products = productService.findList(0, 4, "where category.id=? order by sort desc", category.getId());
			category.setProducts(products);
			if(products != null && !products.isEmpty()){
				category.setProCount(products.size());
			}
		}
		request.setAttribute("page", page);
		return "site.procate";
	}

	@RequestMapping("/product/{cate}/p{pageNo}")
	public String proPage(@PathVariable("cate") Long cate, @PathVariable("pageNo") Integer pageNo){
		if(cate == null){
			List<ProCategory> clist = proCategoryService.findList(0, 1, "");
			if(clist != null && !clist.isEmpty()){
				cate = clist.get(0).getId();
			}
		}
		if(pageNo == null) pageNo = 1;
		request.setAttribute("title", "Products");
		request.setAttribute("type", "product");
		common();
		Page<Product> page = new Page<Product>(pageNo, 10);
		page = productService.findList(page, new HashMap<String, Object>());
		request.setAttribute("page", page);
		ProCategory category = proCategoryService.find(cate);
		request.setAttribute("category", category);
		return "site.product";
	}
	
	@RequestMapping("/product/info/{id}")
	public String proinfo(@PathVariable("id") Long id){
		request.setAttribute("type", "product");
		common();
		return "site.proinfo";
	}
	
	@RequestMapping("/info/{type}")
	public String info(@PathVariable("type") String type){
		request.setAttribute("type", type);
		common();
		return "site.info";
	}
	
	@RequestMapping("/news/p{pageNo}")
	public String news(@PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", "news");
		common();
		return "site.news";
	}
	
	@RequestMapping("/news/info/{id}")
	public String newsinfo(@PathVariable("id") Long id){
		request.setAttribute("type", "news");
		common();
		return "site.newsinfo";
	}
	
	@RequestMapping("/photo/{type}")
	public String photo(@PathVariable("type") String type){
		request.setAttribute("type", type);
		common();
		return "site.photo";
	}
	
	@RequestMapping("/message")
	public String message(){
		request.setAttribute("type", "message");
		common();
		return "site.message";
	}
	
	@RequestMapping("/message/post")
	public String postmsg(){
		return null;
	}
	
	private void common(){
		List<SiteProperty> properties = sitePropertyService.findAll("");
		if(properties != null && !properties.isEmpty()){
			for(SiteProperty property : properties){
				request.setAttribute(property.getPropertyCode(), property.getPropertyValue());
			}
		}
		//左侧产品分类
		List<ProCategory> cates = proCategoryService.findAll(CommonConstraint.SORT);
		if(cates != null && !cates.isEmpty()){
			for(ProCategory category : cates){
				category.setProCount(productService.countByCategory(category.getId()));
			}
		}
		//banner
		List<RotateImage> banners = rotateImageService.findAll(CommonConstraint.SORT);
		request.setAttribute("banners", banners);
	}
}

	