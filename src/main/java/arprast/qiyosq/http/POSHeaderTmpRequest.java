package arprast.qiyosq.http;

import arprast.qiyosq.ref.PaymentMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class POSHeaderTmpRequest {

    @NotBlank(message =  "Customer name can't blank")
    private String customerName;
    private String phoneNumber;
    private String address;
    @NotNull(message =  "Payment method cant't blank")
    private PaymentMethod paymentMethod;
    private Date createdTime = new Date();
    @NotNull(message =  "Item cant't blank")
    private POSAddItemTmpRequest items;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public POSAddItemTmpRequest getItems() {
        return items;
    }

    public void setItems(POSAddItemTmpRequest items) {
        this.items = items;
    }
}
