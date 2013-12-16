
package com.songxh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseController;
import com.songxh.core.Page;
import com.songxh.cust.entity.Message;
import com.songxh.cust.service.MessageService;
import com.songxh.product.entity.ProCategory;
import com.songxh.product.entity.Product;
import com.songxh.product.service.ProCategoryService;
import com.songxh.product.service.ProImageService;
import com.songxh.product.service.ProductService;
import com.songxh.site.entity.ImageDisplay;
import com.songxh.site.entity.Info;
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
	private static final String[] acceptAttacheType = {".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".jpg", ".jpeg", ".png", ".gif", ".bmp"};
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
		Page<Product> page = new Page<Product>(pageNo, 12);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		param.put("category.id", cate);
		page = productService.findList(page, param);
		request.setAttribute("page", page);
		ProCategory category = proCategoryService.find(cate);
		request.setAttribute("category", category);
		return "site.product";
	}
	
	@RequestMapping("/product/info/{id}")
	public String proinfo(@PathVariable("id") Long id){
		if(id != null){
			Product product = productService.find(id);
			request.setAttribute("product", product);
			String tags = product.getTags();
			if(StringUtils.isNotBlank(tags)){
				request.setAttribute("tags", tags.split(","));
			}
			request.setAttribute("relates", productService.findByTags(tags));
		}
		request.setAttribute("type", "product");
		common();
		return "site.proinfo";
	}
	
	@RequestMapping("/info/{type}")
	public String info(@PathVariable("type") String type){
		request.setAttribute("type", type);
		if("about".equals(type)){
			request.setAttribute("title", "Company Info");
		}else if("contact".equals(type)){
			request.setAttribute("title", "Contact Us");
		}
		List<Info> list = infoService.findByType(type, 0, 1);
		if(list != null && !list.isEmpty()){
			request.setAttribute("info", list.get(0));
		}
		common();
		return "site.info";
	}
	
	@RequestMapping("/news/p{pageNo}")
	public String news(@PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", "news");
		request.setAttribute("title", "news");
		common();
		if(pageNo == null) pageNo = 0;
		Page<Info> page = new Page<Info>(pageNo, 10);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		param.put("typeCode", "news");
		page = infoService.findList(page, param, CommonConstraint.SORT);
		request.setAttribute("page", page);
		return "site.news";
	}
	
	@RequestMapping("/news/info/{id}")
	public String newsinfo(@PathVariable("id") Long id){
		request.setAttribute("type", "news");
		request.setAttribute("title", "news");
		if(id != null){
			Info news = infoService.find(id);
			request.setAttribute("news", news);
			if(news != null)
				request.setAttribute("title", news.getTitle());
		}
		common();
		return "site.newsinfo";
	}
	
	@RequestMapping("/photo/{type}/p{pageNo}")
	public String photo(@PathVariable("type") String type, @PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", type);
		if("factory".equals(type)){
			request.setAttribute("title", "Factory Display");
		}
		if(pageNo == null) pageNo = 1;
		Page<ImageDisplay> page = new Page<ImageDisplay>(pageNo, 12);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		param.put("typeCode", type);
		page = imageDisplayService.findList(page, param, CommonConstraint.SORT);
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
	public String postmsg(Message message, HttpServletResponse response){
		this.response = response;
		boolean success = true;
		String msg = "";
		List<MultipartFile> files = null;
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			files = multipartRequest.getFiles("file");
			if(files != null && !files.isEmpty()){
				for(MultipartFile file : files){
					for(String accept : acceptAttacheType){
						file.getOriginalFilename().endsWith(accept);
						success = false;
						msg = file.getOriginalFilename() + " is not accepted!";
						break;
					}
					if(!success) break;
				}
			}
		}catch(ClassCastException e){}
		messageService.saveMessage(message, files);
		JSONObject obj = new JSONObject();
		obj.put("success", success);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
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
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("published", true);
			for(ProCategory category : cates){
				param.put("category.id", category.getId());
				category.setProCount(productService.count(param));
			}
		}
		request.setAttribute("gcates", cates);
		
		//banner
		Map<String, Object> bannerMap = new HashMap<String, Object>();
		bannerMap.put("published", true);
		List<RotateImage> banners = rotateImageService.findList(bannerMap, CommonConstraint.SORT);
		request.setAttribute("banners", banners);
	}
}

	