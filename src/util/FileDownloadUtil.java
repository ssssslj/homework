package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadUtil {

	
	//��ͨjava�ļ����ط��������������п��    fileName������׺�� 
    public static String downloadFile(HttpServletResponse res,String fileName,String filePath) throws IOException {
        //��ȡ�ļ���Ŀ¼����ͬ��ܻ�ȡ�ķ�ʽ��һ�����������л�  
      //  String basePath = request.getSession().getServletContext().getRealPath("/upload");  
         //System.out.println(basePath);
        //��ȡ�ļ����ƣ������ļ���ʽ��  
       // String fileName = "qq�������ݴ��λ��.txt";  

        //��ϳ��������ļ�·��  
        String targetPath = filePath+File.separator+fileName;  

        
        //IO��ʵ�����صĹ���  
        res.setContentType("text/html; charset=UTF-8"); //���ñ����ַ�  
        res.setContentType("application/octet-stream"); //������������Ϊ��������  
        res.setHeader("Content-disposition", "attachment;filename="+fileName);//�������ص��ļ�����  
        OutputStream out = res.getOutputStream();   //����ҳ�淵�ط�ʽΪ����������Զ��������ؿ�   

 


   //IO�ַ������أ����ڴ��ļ�  
        System.out.println("�ַ���");  
        File file = new File(targetPath);  //�����ļ�  
        FileInputStream fis=new FileInputStream(file);  //�����ļ��ֽ�������  
        BufferedInputStream bis=new BufferedInputStream(fis); //�����ļ�����������  
        byte[] buffer = new byte[bis.available()];//���������ж�ȡ��������
        bis.read(buffer);//��ȡ�����ļ�
        bis.close();
        out.write(buffer);//��������ļ�
        out.flush();//�ͷŻ���
        out.close();//�ر������
  


        return null;
    }

   

}
