package cn.zhaoyuening.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 调度中心 指令数 调度相应程序执行
 * 
 * @author Zhao
 *
 */
public class Controller {
	// 文件操作处理者
	private static FileHandler fileHandler = FileHandler.get();

	public static void execute(int orderCount) throws IOException {
		switch (orderCount) {
		case 1:
			// ls
			ls();
			break;

		case -2:
			// cd
			System.err.println("未携带参数");
			break;

		case -3:
			// cat
			System.err.println("未携带参数");
			break;

		case 4:
			// exit
			break;

		case 5:
			// version
			version();
			break;

		case 6:
		case 7:
			// help
			help();
			break;

		case 8:
			// cd..
			cd_back();
			break;

		default:
			System.err.println("未实现");
			break;
		}
	}

	private static void version() throws IOException {
		//显示版本信息
		showExFile("ex_file","doc_version");
	}

	//帮助说明
	private static void help() throws IOException {
		//显示ex_file文件夹下的doc_help文档
		showExFile("ex_file","doc_help");
	}

	private static void showExFile(String folderName,String fileName) throws IOException {
		File file = new File("."+File.separatorChar+"src"+File.separatorChar+folderName);
		System.out.println(file.getPath());
		String content = fileHandler.showContent(file, fileName);
		if(content!=null){
			System.out.println(content);
		}
	}

	//cd..
	//回退到父目录
	private static void cd_back() {
		fileHandler.toParentPath(fileHandler.getCurrentPath());
	}

	//处理带参数的指令码
	public static void execute(int orderCount,List<String> params) throws IOException{
		switch(orderCount){
			case -2:
				//cd
				cd(params);
				break;	
			case -3:
				//cat
				cat(params);
				break;	
		}
	}

	//显示文本内容
	private static void cat(List<String> params) throws IOException {
		for (String param : params) {
			String fileName = param.trim();
			String content = fileHandler.showContent(fileHandler.getCurrentPath(), fileName);
			if(content!=null){
				System.out.println(fileName+" :");
				System.out.println();
				System.out.println(content);
				continue;
			}
			System.err.println("未发现文件 "+fileName);
		}
	}

	// cd order
	private static void cd(List<String> params) {
		//cd命令只能有一个参数
		if(params.size()>1){
			System.err.println("参数数量错误");
		}
		String param = params.get(0);
		if("..".equals(param)){
			System.err.println("请使用cd..命令");
			return ;
		}
		fileHandler.toFolder( fileHandler.getCurrentPath(),param);
	}

	// ls命令
	private static void ls() {
		String files = fileHandler.showFiles(fileHandler.getCurrentPath());
		System.out.println(files);
	}
}
