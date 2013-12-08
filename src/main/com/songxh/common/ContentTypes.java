package com.songxh.common;

public class ContentTypes {
	public enum InfoTypeEnums{
		ABOUT("about", "关于我们"),
		
		CONTACT("contact", "联系我们"),
		
		NEWS("news", "公司新闻");
		private String code;
		private String text;
		InfoTypeEnums(String code, String text){
			this.code = code;
			this.text = text;
		}
		public static String getText(String code){
			for(InfoTypeEnums infoType : InfoTypeEnums.values()){
				if(infoType.getCode().equals(code)){
					return infoType.getText();
				}
			}
			return "";
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	public enum ImageTypeEnums{
		FACTORY("factory", "工厂照片");
		private String code;
		private String text;
		ImageTypeEnums(String code, String text){
			this.code = code;
			this.text = text;
		}
		public static String getText(String code){
			for(InfoTypeEnums infoType : InfoTypeEnums.values()){
				if(infoType.getCode().equals(code)){
					return infoType.getText();
				}
			}
			return "";
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
}
