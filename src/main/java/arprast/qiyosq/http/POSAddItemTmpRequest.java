package arprast.qiyosq.http;

import javax.validation.constraints.NotNull;

public class POSAddItemTmpRequest {

    @NotNull
    String itemCode;
    @NotNull
    int qty;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
