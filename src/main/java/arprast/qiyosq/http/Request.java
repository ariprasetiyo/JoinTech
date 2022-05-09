package arprast.qiyosq.http;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request<T> {

    private T requestData;
    @NotBlank(message = "Request id can't be blank")
    private String requestId;
    private String id = UUID.randomUUID().toString();
    private String version;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
