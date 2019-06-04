package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadUtil {

	
	//普通java文件下载方法，适用于所有框架    fileName包括后缀名 
    public static String downloadFile(HttpServletResponse res,String fileName,String filePath) throws IOException {
        //获取文件根目录，不同框架获取的方式不一样，可自由切换  
      //  String basePath = request.getSession().getServletContext().getRealPath("/upload");  
         //System.out.println(basePath);
        //获取文件名称（包括文件格式）  
       // String fileName = "qq下载内容存的位置.txt";  

        //组合成完整的文件路径  
        String targetPath = filePath+File.separator+fileName;  

        
        //IO流实现下载的功能  
        res.setContentType("text/html; charset=UTF-8"); //设置编码字符  
        res.setContentType("application/octet-stream"); //设置内容类型为下载类型  
        res.setHeader("Content-disposition", "attachment;filename="+fileName);//设置下载的文件名称  
        OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框   

 


   //IO字符流下载，用于大文件  
        System.out.println("字符流");  
        File file = new File(targetPath);  //创建文件  
        FileInputStream fis=new FileInputStream(file);  //创建文件字节输入流  
        BufferedInputStream bis=new BufferedInputStream(fis); //创建文件缓冲输入流  
        byte[] buffer = new byte[bis.available()];//从输入流中读取不受阻塞
        bis.read(buffer);//读取数据文件
        bis.close();
        out.write(buffer);//输出数据文件
        out.flush();//释放缓存
        out.close();//关闭输出流
  


        return null;
    }

   

}
