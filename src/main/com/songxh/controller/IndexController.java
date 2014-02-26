
package com.songxh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
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
import com.songxh.common.ContentTypes;
import com.songxh.common.IndexAreaTypes.ContentTypeEnums;
import com.songxh.core.BaseController;
import com.songxh.core.JspToHtml;
import com.songxh.core.Page;
import com.songxh.cust.entity.Message;
import com.songxh.cust.service.MessageService;
import com.songxh.product.entity.ProCategory;
import com.songxh.product.entity.Product;
import com.songxh.product.service.ProCategoryService;
import com.songxh.product.service.ProImageService;
import com.songxh.product.service.ProductService;
import com.songxh.site.entity.AreaEntity;
import com.songxh.site.entity.ImageDisplay;
import com.songxh.site.entity.IndexArea;
import com.songxh.site.entity.Info;
import com.songxh.site.entity.RotateImage;
import com.songxh.site.entity.SiteProperty;
import com.songxh.site.service.AreaEntityService;
import com.songxh.site.service.ImageDisplayService;
import com.songxh.site.service.IndexAreaService;
import com.songxh.site.service.InfoService;
import com.songxh.site.service.RotateImageService;
import com.songxh.site.service.SitePropertyService;
import com.songxh.system.service.SiteLogService;
import com.songxh.tools.MailUtils;

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
	//privatePort请求不生成html,publicPort请求需要生成html
	private static String publicPort = "";
	static{
		Properties props = new Properties();
		try {
			props.load(IndexController.class.getClassLoader().getResourceAsStream("port.properties"));
			publicPort = props.getProperty("public");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
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
	@Autowired
	private AreaEntityService areaEntityService;
	@Autowired
	private IndexAreaService indexAreaService;
	@Autowired
	private SiteLogService siteLogService;
	private static final String[] acceptAttacheType = {".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".jpg", ".jpeg", ".png", ".gif", ".bmp"};
	private static final String TITLE = "mi_title";
	@RequestMapping("/index")
	public String index(){
		request.setAttribute("type", "index");
		List<IndexArea> areas = indexAreaService.findAll("");
		if(areas != null && !areas.isEmpty()){
			for(IndexArea area : areas){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("area", area.getId());
				List<AreaEntity> entities = areaEntityService.findList(map, CommonConstraint.SORT);
				if(entities != null && !entities.isEmpty()){
					List<Object> list = new LinkedList<Object>();
					if(ContentTypeEnums.INFO.getValue().equals(area.getContentType())){
						for(AreaEntity entity : entities){
							Info info = infoService.find(entity.getEntityId());
							if(info != null && info.getPublished()){
								list.add(info);
							}
						}
					}else{
						for(AreaEntity entity : entities){
							Product product = productService.find(entity.getEntityId());
							if(product != null && product.getPublished()){
								list.add(product);
							}
						}
						request.setAttribute(area.getAreaCode(), list);
					}
				}
			}
		}
		List<Info> infos = infoService.findByType(ContentTypes.InfoTypeEnums.ABOUT.getCode(), 0, 1);
		if(infos != null && !infos.isEmpty()){
			Info about = infos.get(0);
			if(about.getContent() != null){
				String content = about.getContent().replaceAll("<([^>]*)>", "");
				content = content.replaceAll("&nbsp;", " ");
				request.setAttribute("about", content);
			}
		}
		List<Info> newslist = infoService.findByType(ContentTypes.InfoTypeEnums.NEWS.getCode(), 0, 3);
		request.setAttribute("newslist", newslist);
		List<ImageDisplay> images = imageDisplayService.findList(0, 1, "where typeCode=? order by sort desc", "factory");
		if(images != null && !images.isEmpty()){
			request.setAttribute("image", images.get(0));
		}
		common();
		return "site.index";
	}
	
	@RequestMapping("/{a}-product/p{pageNo}")
	public String proCatePage(@PathVariable("a") String a, @PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", "product");
		request.setAttribute("title", "Products");
		request.setAttribute(TITLE, "All products");
		common();
		if(pageNo == null) pageNo = 1;
		Page<Product> page = new Page<Product>(pageNo, 12);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		page = productService.findList(page, param);
		for(Product product : page.getResult()){
			String description = product.getDescription();
			if(description != null){
				description = description.replaceAll("<([^>]*)>", "");
				description = description.replaceAll(" ", "");
				description = description.replaceAll("\t", "");
				description = description.replaceAll("&nbsp;", " ");
				if(description.length() > 150){
					description = description.substring(0, 150) + "...";
				}
			}
			product.setDesc(description);
		}
		request.setAttribute("page", page);
		request.setAttribute("cateName", "All Products");
		return "site.product";
	}

	@RequestMapping("/{a}-product/{b}-{cate}/p{pageNo}")
	public String proPage(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("cate") Long cate, @PathVariable("pageNo") Integer pageNo){
		if(cate == null){
			List<ProCategory> clist = proCategoryService.findList(0, 1, "");
			if(clist != null && !clist.isEmpty()){
				cate = clist.get(0).getId();
				request.setAttribute(TITLE, clist.get(0).getCategoryName());
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
		for(Product product : page.getResult()){
			String description = product.getDescription();
			if(description != null){
				description = description.replaceAll("<([^>]*)>", "");
				description = description.replaceAll(" ", "");
				description = description.replaceAll("\t", "");
				description = description.replaceAll("&nbsp;", " ");
				if(description.length() > 150){
					description = description.substring(0, 150) + "...";
				}
			}
			product.setDesc(description);
		}
		request.setAttribute("page", page);
		ProCategory category = proCategoryService.find(cate);
		request.setAttribute("category", category);
		request.setAttribute("cateName", category.getCategoryName());
		return "site.product";
	}
	
	@RequestMapping("/{a}-product/info/{b}-{id}")
	public String proinfo(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("id") Long id){
		if(id != null){
			Product product = productService.find(id);
			request.setAttribute("product", product);
			request.setAttribute(TITLE, product.getProductName());
			String tags = product.getTags();
			if(StringUtils.isNotBlank(tags)){
				request.setAttribute("tags", tags.split(","));
			}
			request.setAttribute("relates", productService.findByTags(tags));
			request.setAttribute("category", product.getCategory());
			request.setAttribute("cateName", product.getCategory().getCategoryName());
		}
		request.setAttribute("type", "product");
		common();
		return "site.proinfo";
	}
	
	@RequestMapping("/{a}-info/{type}-{b}")
	public String info(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("type") String type){
		request.setAttribute("type", type);
		request.setAttribute(TITLE, type);
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
	
	@RequestMapping("/{a}-news/p{pageNo}")
	public String news(@PathVariable("a") String a, @PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", "news");
		request.setAttribute("title", "news");
		request.setAttribute(TITLE, "Windmoke news");
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
	
	@RequestMapping("/{a}-news/info/{b}-{id}")
	public String newsinfo(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("id") Long id){
		request.setAttribute("type", "news");
		request.setAttribute("title", "news");
		if(id != null){
			Info news = infoService.find(id);
			request.setAttribute("news", news);
			if(news != null){
				request.setAttribute("title", news.getTitle());
				request.setAttribute(TITLE, news.getTitle());
			}
		}
		common();
		return "site.newsinfo";
	}
	
	@RequestMapping("/{a}-photo/{type}-{b}/p{pageNo}")
	public String photo(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("type") String type, @PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", type);
		if("factory".equals(type)){
			request.setAttribute("title", "Factory Display");
			request.setAttribute(TITLE, "Factory Display");
		}
		if(pageNo == null) pageNo = 1;
		Page<ImageDisplay> page = new Page<ImageDisplay>(pageNo, 12);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		param.put("typeCode", type);
		page = imageDisplayService.findList(page, param, CommonConstraint.SORT);
		request.setAttribute("page", page);
		common();
		return "site.photo";
	}
	
	@RequestMapping("/message")
	public String message(){
		request.setAttribute("type", "message");
		request.setAttribute(TITLE, "Send message to Windmoke");
		common();
		return "site.message";
	}
	
	@RequestMapping("/message/post")
	public String postmsg(Message message, HttpServletResponse response, HttpServletRequest request){
		this.response = response;
		boolean success = true;
		String msg = "";
		List<MultipartFile> files = null;
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			files = multipartRequest.getFiles("file");
			if(files != null && !files.isEmpty()){
				for(MultipartFile file : files){
					if(file == null || StringUtils.isBlank(file.getOriginalFilename()))
						continue;
					boolean accepted = false;
					for(String accept : acceptAttacheType){
						if(file.getOriginalFilename().endsWith(accept)){
							accepted = true;
							break;
						}
					}
					if(!accepted){
						success = false;
						msg += "The type of file " + file.getOriginalFilename() + " is not accepted!";
						break;
					}
				}
			}
		}catch(ClassCastException e){}
		if(success){
			message.setIp(request.getRemoteAddr());
			messageService.saveMessage(message, files);
			final Message _message = message;
			new Thread(){
				@Override
				public void run(){
					StringBuffer content = new StringBuffer();
					content.append("email:").append(_message.getEmail() + System.getProperty("line.separator"));
					content.append("content:").append(_message.getMsgContent() + System.getProperty("line.separator"));
					content.append("详情请<a href=\"http://www.winsmoke.com:8180/admin\">点此<a>登录系统");
					MailUtils.sendEmail("winsmoke.com留言[" + _message.getSubject() + "]", content.toString());
				}
			}.start();
		}
		JSONObject obj = new JSONObject();
		obj.put("success", success);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
		return null;
	}
	
	private void common(){
		if(StringUtils.isNotBlank(publicPort) && publicPort.equals(String.valueOf(request.getServerPort()))){
			
			//生成静态页面
			JspToHtml jspToHtml = new JspToHtml(request.getRequestURI());
			new Thread(jspToHtml).start();
		}
		List<SiteProperty> properties = sitePropertyService.findAll("");
		if(properties != null && !properties.isEmpty()){
			for(SiteProperty property : properties){
				request.setAttribute(property.getPropertyCode(), property.getPropertyValue());
			}
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent.id", 0);
		//左侧产品分类
		List<ProCategory> cates = proCategoryService.findList(param, CommonConstraint.SORT);
		if(cates != null && !cates.isEmpty()){
			for(ProCategory category : cates){
				param.put("parent.id", category.getId());
				category.setChildren(proCategoryService.findList(param, CommonConstraint.SORT));
				if(category.getChildren().isEmpty()){
					category.setProCount(productService.countByCategory(category.getId()));
				}else{
					for(ProCategory child : category.getChildren()){
						child.setProCount(productService.countByCategory(child.getId()));
					}
				}
			}
		}
		request.setAttribute("gcates", cates);
		
		//banner
		Map<String, Object> bannerMap = new HashMap<String, Object>();
		bannerMap.put("published", true);
		List<RotateImage> banners = rotateImageService.findList(bannerMap, CommonConstraint.SORT);
		request.setAttribute("banners", banners);
	}
	
	@RequestMapping("/product/p{pageNo}")
	public String proCatePage(@PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", "product");
		request.setAttribute("title", "Products");
		request.setAttribute(TITLE, "All products");
		common();
		if(pageNo == null) pageNo = 1;
		Page<Product> page = new Page<Product>(pageNo, 12);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		page = productService.findList(page, param);
		for(Product product : page.getResult()){
			String description = product.getDescription();
			if(description != null){
				description = description.replaceAll("<([^>]*)>", "");
				description = description.replaceAll(" ", "");
				description = description.replaceAll("\t", "");
				description = description.replaceAll("&nbsp;", " ");
				if(description.length() > 150){
					description = description.substring(0, 150) + "...";
				}
			}
			product.setDesc(description);
		}
		request.setAttribute("page", page);
		request.setAttribute("cateName", "All Products");
		return "site.product";
	}

	@RequestMapping("/product/{cate}/p{pageNo}")
	public String proPage(@PathVariable("cate") Long cate, @PathVariable("pageNo") Integer pageNo){
		if(cate == null){
			List<ProCategory> clist = proCategoryService.findList(0, 1, "");
			if(clist != null && !clist.isEmpty()){
				cate = clist.get(0).getId();
				request.setAttribute(TITLE, clist.get(0).getCategoryName());
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
		for(Product product : page.getResult()){
			String description = product.getDescription();
			if(description != null){
				description = description.replaceAll("<([^>]*)>", "");
				description = description.replaceAll(" ", "");
				description = description.replaceAll("\t", "");
				description = description.replaceAll("&nbsp;", " ");
				if(description.length() > 150){
					description = description.substring(0, 150) + "...";
				}
			}
			product.setDesc(description);
		}
		request.setAttribute("page", page);
		ProCategory category = proCategoryService.find(cate);
		request.setAttribute("category", category);
		request.setAttribute("cateName", category.getCategoryName());
		return "site.product";
	}
	
	@RequestMapping("/product/info/{id}")
	public String proinfo(@PathVariable("id") Long id){
		if(id != null){
			Product product = productService.find(id);
			request.setAttribute("product", product);
			request.setAttribute(TITLE, product.getProductName());
			String tags = product.getTags();
			if(StringUtils.isNotBlank(tags)){
				request.setAttribute("tags", tags.split(","));
			}
			request.setAttribute("relates", productService.findByTags(tags));
			request.setAttribute("category", product.getCategory());
			request.setAttribute("cateName", product.getCategory().getCategoryName());
		}
		request.setAttribute("type", "product");
		common();
		return "site.proinfo";
	}
	
	@RequestMapping("/info/{type}")
	public String info(@PathVariable("type") String type){
		request.setAttribute("type", type);
		request.setAttribute(TITLE, type);
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
		request.setAttribute(TITLE, "Windmoke news");
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
			if(news != null){
				request.setAttribute("title", news.getTitle());
				request.setAttribute(TITLE, news.getTitle());
			}
		}
		common();
		return "site.newsinfo";
	}
	
	@RequestMapping("/photo/{type}/p{pageNo}")
	public String photo(@PathVariable("type") String type, @PathVariable("pageNo") Integer pageNo){
		request.setAttribute("type", type);
		if("factory".equals(type)){
			request.setAttribute("title", "Factory Display");
			request.setAttribute(TITLE, "Factory Display");
		}
		if(pageNo == null) pageNo = 1;
		Page<ImageDisplay> page = new Page<ImageDisplay>(pageNo, 12);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("published", true);
		param.put("typeCode", type);
		page = imageDisplayService.findList(page, param, CommonConstraint.SORT);
		request.setAttribute("page", page);
		common();
		return "site.photo";
	}
}

	