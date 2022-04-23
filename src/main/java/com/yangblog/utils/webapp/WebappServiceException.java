package com.yangblog.utils.webapp;


import lombok.Getter;

@Getter
public class WebappServiceException extends RuntimeException {

    private static final long serialVersionUID = 3094987278865971168L;

    private WebappRetCode code;
    private boolean log = true;

    public WebappServiceException(WebappRetCode code) {
        super();
        this.code = code;
    }

    public WebappServiceException(WebappRetCode code, String message) {
        super(message);
        this.code = code;
    }

    public WebappServiceException(WebappRetCode code, String message, boolean log) {
        super(message);
        this.code = code;
        this.log = log;
    }

    public WebappServiceException(WebappRetCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
