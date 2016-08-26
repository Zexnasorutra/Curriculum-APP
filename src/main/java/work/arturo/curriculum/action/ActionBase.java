package work.arturo.curriculum.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Base class for struts actions
 * 
 * @author Arturo
 * 
 */
public class ActionBase extends ActionSupport {

	private static final long serialVersionUID = 1L;

	protected Logger logger;

	public ActionBase() {
		logger = LogManager.getLogger();
		// Add host to logger
		ThreadContext.put("host", ServletActionContext.getRequest()
				.getRemoteHost());

	}

}
