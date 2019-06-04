package action;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import util.ImgCodeUtil;

public class ImgCodeAction {
	private static final long serialVersionUID = 1L;
	private ByteArrayInputStream inputStream;

	public String execute() throws Exception {
		ImgCodeUtil rdnu = ImgCodeUtil.Instance();
		this.setInputStream(rdnu.getImage()); // 取得带有随机字符串的图片
		HttpSession session = ServletActionContext.getRequest().getSession();
		/*
		 * ActionContext.getContext().getSession() .put("imgCode",
		 * rdnu.getString());
		 */
		System.out.println("????");
		// 取得随机字符串放入HttpSession
		session.setAttribute("imgCode", rdnu.getString());
		//设置当前session的有效时间为10*60秒
		session.setMaxInactiveInterval(600);
		return "success";
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

}
