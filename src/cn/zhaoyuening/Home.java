package cn.zhaoyuening;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Z_shell 模拟系统 主页
 * @author Zhao
 *
 */
public class Home {
	
	public static void main(String[] args) {
		//welcome word
		System.out.println("welcome to z_shell");
		System.out.println("input --help or -h get helper");
		System.out.println("author:赵         date:16/6/20");
		Thread homeThread = new HomeThread();
		Executor ex = Executors.newCachedThreadPool();
		ex.execute(homeThread);
	}
}
