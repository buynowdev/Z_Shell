package cn.zhaoyuening;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.zhaoyuening.util.Controller;
import cn.zhaoyuening.util.FileHandler;
import cn.zhaoyuening.util.OrderTable;

public class HomeThread extends Thread {
	
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public void run() {
		super.run();
		//是否继续运行
		boolean isRun = true;
		while (isRun) {
			//循环获取用户输入命令
			try {
				isRun = handleOrder();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Thanks");
	}
	
	//处理指令
	private boolean handleOrder() throws IOException{
		//获取指令
		System.out.print(FileHandler.get().getCurrentPath().getAbsolutePath()+" >");
		String order = sc.nextLine().trim(); 
		//退出指令
		if("exit".equals(order)){
			return false;			
		}
		//处理命令 是否为带参数指令
		String[] orderAndParam = order.split(" ");
		if(orderAndParam.length>1){
			String order_ = orderAndParam[0].trim();
			int orderCount = OrderTable.getOrderCount(order_);
			//参数列表
			List<String> params = new ArrayList<String>();
			//将参数循环添加到参数列表
			for(int i=1;i<orderAndParam.length;i++){
				String param = orderAndParam[i].trim();
				params.add(param);
			}
			Controller.execute(orderCount,params);
			return true;
		}
		
		//获取命令指令
		int orderCount = OrderTable.getOrderCount(order);
		//0为退出指令码
		if(orderCount== 0){
			//不存在该命令
			System.err.println("not found the "+order);			
		}else{
			//交由控制器处理该命令
			Controller.execute(orderCount);
		}
		
		return true;
	}
}
