package arprast.qiyosq.http;

import arprast.qiyosq.ref.StatusCode;

public class Response {

    private static final long serialVersionUID = -191184234025791265L;
    private StatusCode statusCode;
    private String statusMessage;
    private String responseId;
    private String username;
    private Object responseData;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        this.statusMessage = statusCode.desc;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
