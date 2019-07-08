package android.example.rjt_shopping.model;

import java.io.Serializable;

public class OrderConfirm implements Serializable {

    private String orderid;
    private String orderstatus;
    private String name;
    private String billingadd;
    private String deliveryadd;
    private String mobile;
    private String email;
    private String itemid;
    private String itemname;
    private String itemquantity;
    private String totalprice;
    private String paidprice;
    private String placedon;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillingadd() {
        return billingadd;
    }

    public void setBillingadd(String billingadd) {
        this.billingadd = billingadd;
    }

    public String getDeliveryadd() {
        return deliveryadd;
    }

    public void setDeliveryadd(String deliveryadd) {
        this.deliveryadd = deliveryadd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(String itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getPaidprice() {
        return paidprice;
    }

    public void setPaidprice(String paidprice) {
        this.paidprice = paidprice;
    }

    public String getPlacedon() {
        return placedon;
    }

    public void setPlacedon(String placedon) {
        this.placedon = placedon;
    }
}
