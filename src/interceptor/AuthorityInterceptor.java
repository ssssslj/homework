package interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getRequestURI();//url
		ActionContext aContext = arg0.getInvocationContext();
		
		Map<String, Object> session = aContext.getSession();
		
		// »°≥ˆ Ù–‘
		String user = (String)session.get("user");
		
		if (!path.contains("creatImgCode") && user == null) {
			System.out.println("AuthorityInterceptor:nonono");
			return Action.LOGIN;
		}
		
		
		return arg0.invoke();
	}

}
