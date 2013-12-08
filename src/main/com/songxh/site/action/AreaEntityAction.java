package com.songxh.site.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.common.ContentTypes;
import com.songxh.common.IndexAreaTypes;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.product.entity.ProCategory;
import com.songxh.product.entity.Product;
import com.songxh.product.service.AreaEntityService;
import com.songxh.product.service.IndexAreaService;
import com.songxh.product.service.ProCategoryService;
import com.songxh.product.service.ProductService;
import com.songxh.site.entity.AreaEntity;
import com.songxh.site.entity.ImageDisplay;
import com.songxh.site.entity.IndexArea;
import com.songxh.site.entity.Info;
import com.songxh.site.service.ImageDisplayService;
import com.songxh.site.service.InfoService;
import com.songxh.tools.DateUtils;

public class AreaEntityAction extends BaseAction<AreaEntity> {

	@Autowired
	private AreaEntityService areaEntityService;
	@Autowired
	private IndexAreaService indexAreaService;
	@Autowired
	private ProductService productService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private ImageDisplayService imageDisplayService;
	@Autowired
	private ProCategoryService proCategoryService;
	
	public void prepareExecute(){}
	
	public String form(){
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			String areaId = request.getParameter("areaId");
			if(StringUtils.isNotBlank(areaId)){
				IndexArea area = this.indexAreaService.find(Long.parseLong(areaId));
				if(area != null){
					List<AreaEntity> list = this.areaEntityService.findByAreaId(area.getId());
					StringBuffer sb = new StringBuffer();
					if(list != null && !list.isEmpty()){
						for(AreaEntity entity : list){
							sb.append("," + entity.getEntityId());
						}
					}
					if(sb.length() > 0) sb.deleteCharAt(0);
					request.setAttribute("nodes", sb.toString());
					String url = "";
					JSONArray tree = new JSONArray();
					if(IndexAreaTypes.ContentTypeEnums.INFO.getValue().equals(area.getContentType())){
						JSONObject obj = null;
						obj = new JSONObject();
						obj.put("text", "公司新闻");
						obj.put("id", "area-info-news");
						obj.put("leaf", true);
						obj.put("url", "info.action?type=news");
						tree.add(obj);
						obj = new JSONObject();
						obj.put("text", "关于我们");
						obj.put("id", "area-info-about");
						obj.put("leaf", true);
						obj.put("url", "info.action?type=about");
						tree.add(obj);
						obj = new JSONObject();
						obj.put("text", "联系我们");
						obj.put("id", "area-info-contant");
						obj.put("leaf", true);
						obj.put("url", "info.action?type=contact");
						tree.add(obj);
					}else if(IndexAreaTypes.ContentTypeEnums.IMAGE.getValue().equals(area.getContentType())){
						JSONObject obj = null;
						obj = new JSONObject();
						obj.put("text", "公司照片");
						obj.put("id", "area-image-factory");
						obj.put("leaf", true);
						obj.put("url", "image-display.action?type=factory");
						tree.add(obj);
					}else if(IndexAreaTypes.ContentTypeEnums.PRODUCT.getValue().equals(area.getContentType())){
						JSONObject obj = null;
						List<ProCategory> cateList = this.proCategoryService.findAll(CommonConstraint.SORT);
						if(cateList != null && !cateList.isEmpty()){
							for(ProCategory cate : cateList){
								obj = new JSONObject();
								obj.put("id", "area-product-" + cate.getId());
								obj.put("text", cate.getCategoryName());
								obj.put("leaf", true);
								obj.put("url", "product.action?category=" + cate.getId());
								tree.add(obj);
							}
						}
						url = "product.action";
					}
					request.setAttribute("tree", tree.toString());
					request.setAttribute("url", url);
					return "form";
				}
			}
			return null;
		}else{
			addSave();
			return null;
		}
	}
	
	@Override
	protected void addSave() {}

	@Override
	protected void editSave() {}

	@Override
	protected BaseService<AreaEntity> getService() {
		return areaEntityService;
	}

	@Override
	protected void list() {
		Long areaId = this.getLongParam("areaId");
		JSONArray array = new JSONArray();
		if(areaId != null){
			IndexArea area = this.indexAreaService.find(areaId);
			if(area != null){
				List<AreaEntity> list = areaEntityService.findByAreaId(areaId);
				if(list != null && !list.isEmpty()){
					//查询关联实体分三种情况
					if(IndexAreaTypes.ContentTypeEnums.IMAGE.getValue().equals(area.getContentType())){
						//1.图片信息
						for(AreaEntity entity : list){
							ImageDisplay image = this.imageDisplayService.find(entity.getEntityId());
							if(image != null && image.getPublished()){
								JSONObject obj = new JSONObject();
								obj.put("id", entity.getId());
								obj.put("sort", entity.getSort());
								obj.put("title", image.getTitle());
								obj.put("memo", image.getMemo());
								obj.put("creater", image.getCreater());
								obj.put("insertTime", DateUtils.format(image.getInsertTime()));
								obj.put("path", image.getAttachment().getPath());
								obj.put("type", ContentTypes.ImageTypeEnums.getText(image.getTypeCode()));
								array.add(obj);
							}
						}
					}else if(IndexAreaTypes.ContentTypeEnums.INFO.getValue().equals(area.getContentType())){
						//2.文章信息
						for(AreaEntity entity : list){
							Info info = infoService.find(entity.getEntityId());
							if(info != null && info.getPublished()){
								JSONObject obj = new JSONObject();
								obj.put("id", entity.getId());
								obj.put("sort", entity.getSort());
								obj.put("title", info.getTitle());
								obj.put("creater", info.getCreater());
								obj.put("insertTime", info.getInsertTime());
								obj.put("memo", info.getMemo());
								obj.put("type", ContentTypes.InfoTypeEnums.getText(info.getTypeCode()));
								array.add(obj);
							}
						}
					}else if(IndexAreaTypes.ContentTypeEnums.PRODUCT.getValue().equals(area.getContentType())){
						//3.产品信息
						for(AreaEntity entity : list){
							Product product = productService.find(entity.getEntityId());
							if(product != null && product.getPublished()){
								JSONObject obj = new JSONObject();
								obj.put("id", entity.getId());
								obj.put("sort", entity.getSort());
								obj.put("creater", product.getCreater());
								obj.put("insertTime", product.getInsertTime());
								obj.put("title", product.getProductName());
								obj.put("memo", product.getMemo());
								obj.put("path", product.getImage());
								obj.put("type", product.getCategory().getCategoryName());
								array.add(obj);
							}
						}
					}
				}
			}
		}
		outJson(array.toJSONString());
	}

}
