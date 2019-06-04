package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * MD5通用类
 * 
 * @author 浩令天下
 * @since 2017.04.15
 * @version 1.0.0_1
 * 
 */
public class EncryptUtil {
    
	
	public static String Encoder(String str) {
		
		return getSHA256(str);
	}
	
	public static String EncoderByMD5(String str) {
		String newStr = null;
		try {
			MessageDigest md5;
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64Encoder = new BASE64Encoder();
			 try {
				newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return newStr;
	}
	
	public static boolean checkPassword(String password,String md5Pass) {
		if (getSHA256(password).equals(md5Pass)) {
			return true;
		}
		
		return false;
	}
	
	  /**
	    * 利用java原生的类实现SHA256加密
	    * @param str 加密后的报文
	    * @return
	    */
	    public static String getSHA256(String str){
	    	str = str+"_" + str;
	    	str = EncoderByMD5(str);
	      MessageDigest messageDigest;
	     String encodestr = "";
	     try {
	      messageDigest = MessageDigest.getInstance("SHA-256");
	      messageDigest.update(str.getBytes("UTF-8"));
	      encodestr = byte2Hex(messageDigest.digest());
	     } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	     } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	     }
	     return encodestr;
	    }
	    /**
	    * 将byte转为16进制
	    * @param bytes
	    * @return
	    */
	    private static String byte2Hex(byte[] bytes){
	     StringBuffer stringBuffer = new StringBuffer();
	     String temp = null;
	     for (int i=0;i<bytes.length;i++){
	      temp = Integer.toHexString(bytes[i] & 0xFF);
	      if (temp.length()==1){
	      //1得到一位的进行补0操作
	      stringBuffer.append("0");
	      }
	      stringBuffer.append(temp);
	     }
	     return stringBuffer.toString();
	    }
	
	
}