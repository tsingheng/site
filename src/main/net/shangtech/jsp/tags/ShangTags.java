package net.shangtech.jsp.tags;

public class ShangTags {
	public static String replace(String source, String... regs){
		if(regs != null && regs.length > 0 && regs.length%2 == 0){
			for(int i = 0; i < regs.length; i+=2){
				source = source.replaceAll(regs[i], regs[i+1]);
			}
		}
		return source;
	}
}
