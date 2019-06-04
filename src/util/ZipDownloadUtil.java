package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import javax.servlet.http.HttpServletResponse;

import vo.Exam;

public class ZipDownloadUtil {
	//普通java文件下载方法，适用于所有框架  
    public static InputStream zipDownload(Exam exam,String zipName) throws IOException {
       
    	

	/**
	 * @param source
	 *            源文件
	 * @param basePath
	 * @param zos
	 */
	
          //方法1：IO流实现下载的功能  
//        res.setContentType("text/html; charset=UTF-8"); //设置编码字符  
//        res.setContentType("application/octet-stream"); //设置内容类型为下载类型  
//        res.setHeader("Content-disposition", "attachment;filename="+zipName);//设置下载的文件名称  
//        OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框   

  

        //将附件中多个文件进行压缩，批量打包下载文件  
        
        //String zipFilePath = zipBasePath+File.separator+zipName;  
       String zipBasePath=FileUtil.getExamAnswerPath(exam);
       String zipFilePath=zipBasePath+File.separator+zipName;
     //创建需要下载的文件路径的集合
       //List<String> filePaths = new ArrayList<String>();  
       List<String> fileNameList=new ArrayList<String>();
       getAllFileName(zipBasePath,fileNameList);
       System.out.println("fileNameList: " + fileNameList.size());
       for(int i=0;i<fileNameList.size();i++)
      {
//       	String total_fileName=zipBasePath+File.separator+fileNameList.get(i);
//       	//System.out.println(total_fileName);
//       	//System.out.println("\n");
//       	filePaths.add(total_fileName);
       System.out.println(fileNameList.get(i));
     }

        //压缩文件
       //压缩文件
       File zip = new File(zipFilePath);  
       if (!zip.exists()){     
           zip.createNewFile();     
       }
       //创建zip文件输出流  
       ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
       zipFile(zipBasePath,zipName, zipFilePath,fileNameList,zos);
       zos.close();
      // res.setHeader("Content-disposition", "attachment;filename="+zipName);//设置下载的压缩文件名称

       //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出  
       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));  
       return bis;
//       byte[] buff = new byte[bis.available()];  
//       bis.read(buff);
//       bis.close();
//       out.write(buff);//输出数据文件
//       out.flush();//释放缓存
//       out.close();//关闭输出流
//
//       return null;
    }
    
    /**
     * 获取某个文件夹下的所有文件
     *
     * @param fileNameList 存放文件名称的list
     * @param path 文件夹的路径
     * @return
     */
    public static void getAllFileName(String path,List<String> fileNameList)
    { 
    	
        boolean flag = false;
        File file = new File(path); 
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++)
        {
        	if (tempList[i].isFile()) 
        	{ 
        		System.out.println("文     件：" + tempList[i]);
                fileNameList.add(tempList[i].toString());
                //fileNameList.add(tempList[i].getName());
             }
        	if (tempList[i].isDirectory())
        	{ 
        	      //System.out.println("文件夹：" + tempList[i]);
                getAllFileName(tempList[i].getAbsolutePath(),fileNameList);
             }
        }
      
      }
    
    /**
     * 压缩文件
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName 临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private static String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths,ZipOutputStream zos) throws IOException 
    {
      //System.out.println("--------3"+filePaths.get(0));
      // System.out.println("--------3"+filePaths.get(1));
        //循环读取文件路径集合，获取每一个文件的路径  
        //for(String filePath : filePaths){  
    	for(int i=0;i<filePaths.size();i++) {
    		String filePath=filePaths.get(i);
        	System.out.println("-------"+filePath);
            File inputFile = new File(filePath);  //根据文件路径创建文件  
            if(inputFile.exists()) { //判断文件是否存在 
            	System.out.println("------2-"+filePath);
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹  
                    //创建输入流读取文件  
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));  

                    //将文件写入zip内，即将文件进行打包  
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));  

                    //写入文件的方法，同上                  
                    int size = 0;  
                    byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {  
                        zos.write(buffer, 0, size);  
                    }  
                    //关闭输入输出流  
                    zos.closeEntry();  
                    bis.close(); 
                    return null;
                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip  
                    try {  
                        File[] files = inputFile.listFiles();  
                        List<String> filePathsTem = new ArrayList<String>();  
                        for (File fileTem:files) {  
                            filePathsTem.add(fileTem.toString());
                        }  
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTem,zos);
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        } 
        return null;
    } 
 

    


}
