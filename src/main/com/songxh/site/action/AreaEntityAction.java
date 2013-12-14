package com.songxh.site.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
					String target = "";
					List<Map<String, Object>> tree = new LinkedList<Map<String, Object>>();
					if(IndexAreaTypes.ContentTypeEnums.INFO.getValue().equals(area.getContentType())){
						Map<String, Object> obj = null;
						obj = new HashMap<String, Object>();
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
						target = "add-info";
					}else if(IndexAreaTypes.ContentTypeEnums.IMAGE.getValue().equals(area.getContentType())){
						Map<String, Object> obj = null;
						obj = new HashMap<String, Object>();
						obj.put("text", "公司照片");
						obj.put("id", "area-image-factory");
						obj.put("leaf", true);
						obj.put("url", "image-display.action?type=factory");
						tree.add(obj);
						target = "add-image";
					}else if(IndexAreaTypes.ContentTypeEnums.PRODUCT.getValue().equals(area.getContentType())){
						Map<String, Object> obj = null;
						List<ProCategory> cateList = this.proCategoryService.findAll(CommonConstraint.SORT);
						if(cateList != null && !cateList.isEmpty()){
							for(ProCategory cate : cateList){
								obj = new HashMap<String, Object>();
								obj.put("id", cate.getId());
								obj.put("text", cate.getCategoryName());
								obj.put("leaf", true);
								obj.put("url", "product.action?category=" + cate.getId());
								tree.add(obj);
							}
						}
						target = "add-product";
					}
					request.setAttribute("tree", tree);
					return target;
				}
			}
		}
		return null;
	}
	
	@Override
	public String add() {
		boolean success = false;
		String msg = "";
		String type = request.getParameter("ctype");
		if(id == null){
			msg = "请先选择要添加的记录";
		}else if(StringUtils.isBlank(type)){
			msg = "非法操作";
		}else{
			IndexArea area = indexAreaService.findByContentType(type);
			if(area == null){
				msg = "没有这个分类";
			}else{
				List<AreaEntity> list = areaEntityService.findByAreaIdAndEntityId(area.getId(), id);
				if(list != null && !list.isEmpty()){
					msg = "该记录已在首页显示";
				}else{
					AreaEntity entity = new AreaEntity();
					entity.setArea(area.getId());
					entity.setEntityId(id);
					areaEntityService.insert(entity);
					success = true;
					msg = "操作成功";
				}
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("success", success);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
		return null;
	}

	@Override
	public String edit() {
		boolean success = false;
		String msg = "";
		String areaIdStr = request.getParameter("areaId");
		if(StringUtils.isBlank(areaIdStr)){
			msg = "请先选择首页区域";
		}else if(id == null){
			msg = "请先选择需要移除的记录";
		}else{
			List<AreaEntity> list = areaEntityService.findByAreaIdAndEntityId(Long.parseLong(areaIdStr), id);
			if(list == null || list.isEmpty()){
				msg = "不存在该记录";
			}else{
				areaEntityService.delete(list.get(0).getId());
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("success", success);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
		return null;
	}

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

	@Override
	protected void addSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void editSave() {
		// TODO Auto-generated method stub
		
	}

}
