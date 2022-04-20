package arprast.qiyosq.http;

import arprast.qiyosq.ref.PaymentMethod;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class POSHeaderTmpRequest {

    @NotNull
    private String customerName;
    private String phoneNumber;
    private String address;
    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull
    private float totalTrxAmount;
    @NotNull
    private float totalDiscountAmount;
    @NotNull
    private float totalPaidAmount;
    private Date createdTime = new Date();
    @NotNull
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

    public float getTotalTrxAmount() {
        return totalTrxAmount;
    }

    public void setTotalTrxAmount(float totalTrxAmount) {
        this.totalTrxAmount = totalTrxAmount;
    }

    public float getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(float totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public float getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(float totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
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
