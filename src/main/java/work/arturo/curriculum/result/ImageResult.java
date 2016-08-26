package work.arturo.curriculum.result;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import work.arturo.curriculum.action.IImageAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;


public class ImageResult implements Result {

	private static final long serialVersionUID = 1L;

	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		IImageAction imageAction = (IImageAction) invocation.getAction();

		// Add the image data from action to response
		response.setContentType(imageAction.getContentType());
		response.getOutputStream().write(imageAction.getImageBytes());
		response.getOutputStream().flush();

	}

}
