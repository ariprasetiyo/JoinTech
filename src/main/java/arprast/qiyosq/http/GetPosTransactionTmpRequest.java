package arprast.qiyosq.http;

import javax.validation.constraints.NotBlank;

public class GetPosTransactionTmpRequest {

    @NotBlank(message = "customer id can't blank")
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }
}
