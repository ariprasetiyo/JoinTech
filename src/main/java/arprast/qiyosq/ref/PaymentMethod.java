package arprast.qiyosq.ref;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentMethod {

    @JsonProperty("bank_transfer")
    BANK_TRANSFER("bank_transfer", "bank transfer"),
    @JsonProperty("qris")
    QRIS("qris", "qris"),
    @JsonProperty("cash")
    CASH("cash", "cash");

    public final String id;
    public final String desc;

    PaymentMethod(final String id, final String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static PaymentMethod valueOfName(final String methodId) {

        if (methodId == null) {
            return null;
        }

        switch (methodId) {
            case "bank_transfer":
                return BANK_TRANSFER;
            case "qris":
                return QRIS;
            case "cash":
            default:
                return CASH;
        }
    }

}
