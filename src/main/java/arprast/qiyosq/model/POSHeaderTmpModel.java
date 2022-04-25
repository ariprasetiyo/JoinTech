package arprast.qiyosq.model;

import arprast.qiyosq.ref.PaymentMethod;

import javax.persistence.ColumnResult;
import java.util.Date;

public class POSHeaderTmpModel {
    private String requestId;
    private String customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private PaymentMethod paymentMethod;
    private float totalTrxAmount;
    private float totalDiscountAmount;
    private float totalPaidAmount;
    private String username;
    private Date createdTime;

    public POSHeaderTmpModel(){

    }

    public POSHeaderTmpModel(final String requestId,
                             final  String customerName,
                             final  String customerId,
                             final String phoneNumber,
                             final String address,
                             final String paymentMethod,
                             final float totalTrxAmount,
                             final float totalDiscountAmount,
                             final float totalPaidAmount,
                             final String username,
                             final Date createdTime) {
        this.requestId = requestId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.paymentMethod = PaymentMethod.valueOfName(paymentMethod);
        this.totalTrxAmount = totalTrxAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.username = username;
        this.createdTime = createdTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
