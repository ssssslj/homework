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
	//��ͨjava�ļ����ط��������������п��  
    public static InputStream zipDownload(Exam exam,String zipName) throws IOException {
       
    	

	/**
	 * @param source
	 *            Դ�ļ�
	 * @param basePath
	 * @param zos
	 */
	
          //����1��IO��ʵ�����صĹ���  
//        res.setContentType("text/html; charset=UTF-8"); //���ñ����ַ�  
//        res.setContentType("application/octet-stream"); //������������Ϊ��������  
//        res.setHeader("Content-disposition", "attachment;filename="+zipName);//�������ص��ļ�����  
//        OutputStream out = res.getOutputStream();   //����ҳ�淵�ط�ʽΪ����������Զ��������ؿ�   

  

        //�������ж���ļ�����ѹ����������������ļ�  
        
        //String zipFilePath = zipBasePath+File.separator+zipName;  
       String zipBasePath=FileUtil.getExamAnswerPath(exam);
       String zipFilePath=zipBasePath+File.separator+zipName;
     //������Ҫ���ص��ļ�·���ļ���
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

        //ѹ���ļ�
       //ѹ���ļ�
       File zip = new File(zipFilePath);  
       if (!zip.exists()){     
           zip.createNewFile();     
       }
       //����zip�ļ������  
       ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
       zipFile(zipBasePath,zipName, zipFilePath,fileNameList,zos);
       zos.close();
      // res.setHeader("Content-disposition", "attachment;filename="+zipName);//�������ص�ѹ���ļ�����

       //���������ļ�д���ͻ��ˣ�����ķ���ͬ�ϣ�ʹ�û��������  
       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));  
       return bis;
//       byte[] buff = new byte[bis.available()];  
//       bis.read(buff);
//       bis.close();
//       out.write(buff);//��������ļ�
//       out.flush();//�ͷŻ���
//       out.close();//�ر������
//
//       return null;
    }
    
    /**
     * ��ȡĳ���ļ����µ������ļ�
     *
     * @param fileNameList ����ļ����Ƶ�list
     * @param path �ļ��е�·��
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
        		System.out.println("��     ����" + tempList[i]);
                fileNameList.add(tempList[i].toString());
                //fileNameList.add(tempList[i].getName());
             }
        	if (tempList[i].isDirectory())
        	{ 
        	      //System.out.println("�ļ��У�" + tempList[i]);
                getAllFileName(tempList[i].getAbsolutePath(),fileNameList);
             }
        }
      
      }
    
    /**
     * ѹ���ļ�
     * @param zipBasePath ��ʱѹ���ļ�����·��
     * @param zipName ��ʱѹ���ļ�����
     * @param zipFilePath ��ʱѹ���ļ�����·��
     * @param filePaths ��Ҫѹ�����ļ�·������
     * @throws IOException
     */
    private static String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths,ZipOutputStream zos) throws IOException 
    {
      //System.out.println("--------3"+filePaths.get(0));
      // System.out.println("--------3"+filePaths.get(1));
        //ѭ����ȡ�ļ�·�����ϣ���ȡÿһ���ļ���·��  
        //for(String filePath : filePaths){  
    	for(int i=0;i<filePaths.size();i++) {
    		String filePath=filePaths.get(i);
        	System.out.println("-------"+filePath);
            File inputFile = new File(filePath);  //�����ļ�·�������ļ�  
            if(inputFile.exists()) { //�ж��ļ��Ƿ���� 
            	System.out.println("------2-"+filePath);
                if (inputFile.isFile()) {  //�ж��Ƿ������ļ��������ļ���  
                    //������������ȡ�ļ�  
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));  

                    //���ļ�д��zip�ڣ������ļ����д��  
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));  

                    //д���ļ��ķ�����ͬ��                  
                    int size = 0;  
                    byte[] buffer = new byte[1024];  //���ö�ȡ���ݻ����С
                    while ((size = bis.read(buffer)) > 0) {  
                        zos.write(buffer, 0, size);  
                    }  
                    //�ر����������  
                    zos.closeEntry();  
                    bis.close(); 
                    return null;
                } else {  //������ļ��У���ʹ����ٵķ�����ȡ�ļ���д��zip  
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
