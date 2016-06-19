package cn.zhaoyuening.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件操作处理者
 * 用来处理对应的文件操作
 * @author Zhao
 *
 */
public class FileHandler {
	//当前路径
	private File currentPath;
	private static FileHandler fileHandler;
	
	private FileHandler(){
		currentPath = new File("");
		currentPath = currentPath.getAbsoluteFile();
	}
	public static FileHandler get(){
		if(fileHandler==null){
			fileHandler = new FileHandler();
		}
		return fileHandler;
	}
	
	/**
	 * 显示路径下的所有文件和文件夹
	 * @param path 需要显示的路径
	 * @return 路径下的所有文件和文件名
	 */
	public String showFiles(String path){
		File filePath = new File(path);
		return showFiles(filePath);
	}
	
	public String showFiles(File filePath){
		File[] files = filePath.listFiles();
		StringBuffer buffer = new  StringBuffer();
		buffer.append(filePath.getPath()+"\n");
		for (File file : files) {
			if(file.isDirectory()){
				buffer.append("-- "+file.getName()+"\n");
			}
		}
		for (File file : files) {
			if(!file.isDirectory()){
				buffer.append(" "+file.getName()+"\n");
			}
		}
		return buffer.toString();
	}
	/**
	 * 回退父目录
	 */
	public void toParentPath(File filePath){
		currentPath = currentPath.getParentFile();
	}
	/**
	 * 进入文件夹
	 * @param filePath 当前文件路径
	 * @param folderName 进入当前路径下的子目录名
	 * @return 进入成功返回 true 失败返回 false
	 */
	public boolean toFolder(File filePath,String folderName){
		String newPath = filePath.getPath()+File.separatorChar+folderName;
		File file = new File(newPath);
		file = file.getAbsoluteFile();
		if(file.isDirectory()){
			//成功 将当前路径设置为 该子目录路径
			currentPath = file;
			return true;
		}else{
			//无该文件夹 失败
			System.err.println(folderName+" 不是一个文件夹");
			return false;
		}
	}

	public File getCurrentPath() {
		return currentPath;
	}
	
	private void setAbsoluteFile(File file,File newFile){
		newFile = newFile.getAbsoluteFile();
		System.out.println(newFile.getPath());
		file = newFile;
	}
	
	/**
	 * 是否是文件 不是文件返回null
	 */
	private File isFile(File path,String fileName){
		File filePath = new File(path.getPath()+File.separatorChar+fileName);
		if(filePath.isFile()){
			return filePath;
		}
		return null;
	}
	
	public String showContent(File path,String fileName) throws IOException{
		File filePath = isFile(path, fileName);
		if(filePath==null){
			return null;
		}
		InputStream in = new FileInputStream(filePath);
		String content = Stream2StringTools.getString(in);
		return content;
	}
}
