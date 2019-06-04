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
		this.setInputStream(rdnu.getImage()); // ȡ�ô�������ַ�����ͼƬ
		HttpSession session = ServletActionContext.getRequest().getSession();
		/*
		 * ActionContext.getContext().getSession() .put("imgCode",
		 * rdnu.getString());
		 */
		System.out.println("????");
		// ȡ������ַ�������HttpSession
		session.setAttribute("imgCode", rdnu.getString());
		//���õ�ǰsession����Чʱ��Ϊ10*60��
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
