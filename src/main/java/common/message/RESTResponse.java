 package common.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP REST Response
 *
 * @author EXEM Inc
 * @since 1.0
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RESTResponse {
	
	public final static int STATUS_OK = 200;
	public final static int STATUS_ERR = 900;
  
    private int status = STATUS_OK;
    
    private String error;
    
    private String exception;

    private String message;

    private String stacktrace;

    private List list;

	private Map map;

    private Object data;
    

    public boolean isSuccess() {
        return (status == STATUS_OK);
    }

    public void setSuccess(boolean success) {
    	if (success) status = STATUS_OK;
    	else status = STATUS_ERR;
    }
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}


    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public List newAndGetList() {
        if (list == null) {
            list = new ArrayList();
        }
        return list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map newAndGetMap() {
        if (map == null) {
            map = new HashMap();
        }
        return map;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object object) {
        this.data = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   
}
