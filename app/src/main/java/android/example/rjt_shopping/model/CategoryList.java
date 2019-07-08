package android.example.rjt_shopping.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryList implements Serializable {

    @SerializedName("Coupon discount")
    private ArrayList<Coupon> coupons=new ArrayList<>();
    @SerializedName("Shipment track")
    private ArrayList<Shipment> shipment=new ArrayList<>();
    @SerializedName("Order history")
    private ArrayList<OrderConfirm> orderhistory=new ArrayList<>();
    @SerializedName("Order confirmed")
    private ArrayList<OrderConfirm> orderConfirms=new ArrayList<>();
    @SerializedName("subcategory")
    //Category sub=new Category("SUB");
    private ArrayList<SubCategory> submlist=new ArrayList<>();
    @SerializedName("category")
    private ArrayList<Category> mlist = new ArrayList<>();
    @SerializedName("products")
    private ArrayList<Product> plist=new ArrayList<>();

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    public ArrayList<Shipment> getShipment() {
        return shipment;
    }

    public void setShipment(ArrayList<Shipment> shipment) {
        this.shipment = shipment;
    }

    public ArrayList<OrderConfirm> getOrderhistory() {
        return orderhistory;
    }

    public void setOrderhistory(ArrayList<OrderConfirm> orderhistory) {
        this.orderhistory = orderhistory;
    }

    public ArrayList<OrderConfirm> getOrderConfirms() {
        return orderConfirms;
    }

    public void setOrderConfirms(ArrayList<OrderConfirm> orderConfirms) {
        this.orderConfirms = orderConfirms;
    }

    public ArrayList<Product> getPlist() {
        return plist;
    }

    public void setPlist(ArrayList<Product> plist) {
        this.plist = plist;
    }

    public CategoryList(){}

    public ArrayList<SubCategory> getSubmlist() {
        return submlist;
    }

    public void setSubmlist(ArrayList<SubCategory> submlist) {
        this.submlist = submlist;
    }

    public ArrayList<Category> getMlist() {
        return mlist;
    }

    public void setMlist(ArrayList<Category> mlist) {
        this.mlist = mlist;
    }
}
