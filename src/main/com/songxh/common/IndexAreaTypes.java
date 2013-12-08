package com.songxh.common;

public class IndexAreaTypes {
	public enum ContentTypeEnums{
		
		IMAGE("1", "图片"),
		
		INFO("2", "文章"),
		
		PRODUCT("3", "产品");
		
		private String value;
		private String text;
		
		ContentTypeEnums(String value, String text){
			this.value = value;
			this.text = text;
		}
		
		public static String getText(String value){
			String text = "";
			for(ContentTypeEnums contentType : ContentTypeEnums.values()){
				if(contentType.getValue().equals(value)){
					text = contentType.getText();
					break;
				}
			}
			return text;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getText() {
			return text;
		}
	}
	
	public enum ViewTypeEnums{
		IMAGE_SWITCH("1", "图片切换"),
		TITLE_LIST("2", "标题列表"),
		IMAGE_LIST("3", "图片列表"),
		IMAGE_TITLE("4", "图片标题");
		private String value;
		private String text;
		ViewTypeEnums(String value, String text){
			this.value = value;
			this.text = text;
		}
		public static String getText(String value){
			String text = "";
			for(ViewTypeEnums viewType : ViewTypeEnums.values()){
				if(viewType.getValue().equals(value)){
					text = viewType.getText();
					break;
				}
			}
			return text;
		}
		public String getValue(){
			return value;
		}
		public String getText(){
			return text;
		}
	}
	
	public enum OrientationEnums{
		HORIZONTAL("1", "水平"),
		VERTICAL("2", "竖直");
		private String value;
		private String text;
		OrientationEnums(String value, String text){
			this.value = value;
			this.text = text;
		}
		public static String getText(String value){
			String text = "";
			for(OrientationEnums orientation : OrientationEnums.values()){
				if(orientation.getValue().equals(value)){
					text = orientation.getText();
					break;
				}
			}
			return text;
		}
		public String getValue(){
			return value;
		}
		public String getText(){
			return text;
		}
	}
}
