package cn.zhaoyuening.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令表
 * @author Zhao
 *
 */
public class OrderTable {
	private static Map<String,Integer> orderMap;
	static {
		orderMap = new HashMap<String,Integer>();
		//需要带参数的指令 指令码为负数
		//0为退出shell指令码
		orderMap.put("ls", 1);
		orderMap.put("cd", -2);
		orderMap.put("cat", -3);
		orderMap.put("exit", 4);
		orderMap.put("version", 5);
		orderMap.put("--help", 6);
		orderMap.put("-h", 7);
		orderMap.put("cd..", 8);
	}
	
	/**
	 * 获得指令码
	 * @param order
	 * @return 存在该指令 返回对应的指令码 不存在返回 0
	 */
	public static int getOrderCount(String order){
		if(orderMap.get(order)==null){
			return 0;
		}
		return orderMap.get(order);
	}
}
