package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.CloseableHttpResponse; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair; 
import org.apache.http.util.EntityUtils;

import com.opensymphony.xwork2.ActionContext;

import vo.Simg;

public class SimgAction extends BaseAction{

	private String number;
    private String imgstr;
    
    
    
    public String getImgstr() {
		return imgstr;
	}
	public void setImgstr(String imgstr) {
		this.imgstr = imgstr;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	private ActionContext actionContext;
	public SimgAction() {
		
	}
	public ActionContext getActionContext() {
		return actionContext;
	}
	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}
    
	public String login() {
		System.out.println(this.number);
		String img1 = iManager.getimgbynumber(this.number);
		System.out.println(img1);
		// ������ַ����ʶ��API�ӿ�
		String url = "https://api-cn.faceplusplus.com/facepp/v3/compare"; 
		
		List<BasicNameValuePair> formparams = new ArrayList<>();  
		formparams.add(new BasicNameValuePair("api_key", "isIQ1kyKleznuoj86camJ83FAFCA7_ns"));  
		formparams.add(new BasicNameValuePair("api_secret", "2TPDtcjOI_AHQUCI_EwXnAzU9bS5gyF8")); 
		formparams.add(new BasicNameValuePair("image_base64_1",imgstr));  
		formparams.add(new BasicNameValuePair("image_base64_2",img1));  
		
		
		return post(formparams,url);
	}
	
    public static String post(List<BasicNameValuePair> formparams,String url) {  
           
        CloseableHttpClient httpclient = HttpClients.createDefault();  
            
        HttpPost httppost = new HttpPost(url);  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {
                	String str=EntityUtils.toString(entity, "UTF-8");
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + str);  
                    //com.alibaba.fastjson.JSONObject object =(com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.parse(EntityUtils.toString(entity, "UTF-8"));
                    //System.out.println(object.getJSONObject("testsetTestcaseExecute").get("confidence"));
                    int index1 = str.indexOf("confidence");
                    if (index1==-1) {
						return INPUT;
					}
                    System.out.println(index1);
                    String con = str.substring(index1+13,index1+19);
                    if(con.charAt(5)==',') {
                    	con=con.substring(0,5);
                    }
                    float f =Float.parseFloat(con);
                    System.out.println("匹配率："+str.substring(index1+13,index1+19));
                    System.out.println("--------------------------------------");  
                    if (f>=75.000) {
                    	System.out.println("匹配成功");
						return SUCCESS;
					}else {
						System.out.println("匹配失败");
						return INPUT;
					}
                    
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // �ر�����,�ͷ���Դ    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
   
	return ERROR;
    }
	
}
