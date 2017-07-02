package cn.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 
 * @ClassName:  FileUtils   
 * @Description:文件工具类 
 * @author: shengte lee
 * @date:   2017年7月1日 下午5:18:13   
 *     
 * @Copyright: 2017  Inc. All rights reserved. 
 * 注意：
 */
public class FileUtils {
	
	/**
	 * 根据路径和名称查找文件
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getFileByPathAndName(String path,String fileName) {
		try {
			File fs = new File(path);
			if(!fs.exists()){
				System.err.println("文件路径不存在");
				return null;
			}
			File[] fileArray = fs.listFiles();
			for(File file : fileArray){
				if(file.getName().equals(fileName)){
					return file;
				}
			}
			System.err.println("文件不存在");
			return null;
		} catch (Exception e) {
			System.err.println("获取文件异常"+e.getMessage());
			return null;
		}

	}
	
	/**
	 * 根据路径和名称查找文件并删除
	 * @param path
	 * @param fileName
	 * @return boolean
	 */
	public static boolean deleteFileByPathAndName(String path,String fileName) {
		File f = getFileByPathAndName(path, fileName);
		if(f == null){
			System.out.println("删除文件: "+fileName+" 失败,文件不存在");
			return false;
		}
		try {
			boolean b = f.delete();
			if(b == true){
				System.out.println("删除文件: "+f.getName()+" 成功");
				return true;
			}
			System.out.println("删除文件: "+f.getName()+" 失败");
			return false;
		} catch (Exception e) {
			System.err.println("删除文件: "+f.getName()+" 异常,"+e.getMessage());
			return false;
		}
						
	}
	
	/**
	 * 保存文件到指定目录
	 * @param path
	 * @param file
	 * @return
	 */
	public static boolean saveByPath(String path,File file) {
		File tempPath = new File(path);
		if(!tempPath.exists()){
			tempPath.mkdirs();
		}
		String saveFile = path+"/"+file.getName();
		try {		
			return saveFile(saveFile, file);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 保存文件到指定目录（重命名）
	 * @param path
	 * @param rename
	 * @param file
	 * @return
	 */
	public static boolean saveByPath(String path,String rename,File file) {
		File tempPath = new File(path);
		if(!tempPath.exists()){
			tempPath.mkdirs();
		}
		String saveFile = path+"/"+rename;	
		try {		
			return saveFile(saveFile, file);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 保存文件
	 * @param saveFile
	 * @param file
	 * @return
	 */
	public static boolean saveFile(String saveFile,File file) {
		try {		
			FileInputStream in = new FileInputStream(file);
			FileOutputStream ou = new FileOutputStream(new File(saveFile));
			byte[] b = new byte[1024];
			while(in.read(b)!=-1){
				ou.write(b);
				ou.flush();
			}
			in.close();
			ou.close();
			System.out.println("文件保存完毕："+saveFile);
			return true;
		} catch (Exception e) {
			System.err.println("文件保存错误:"+e.getMessage());
			return false;
		}
	}
	
	
	public static String getPostfix(String fileName) {
		int i = fileName.lastIndexOf(".");
		if(i<0) return null;
		if(fileName.length()<=0) return null;
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}

}
