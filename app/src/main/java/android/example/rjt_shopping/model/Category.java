package android.example.rjt_shopping.model;

import com.google.gson.annotations.SerializedName;

public class Category {


    private String cid;
    private String cname;
    private String cdiscription;
    private String cimagerl;

    public Category(){}
    public Category(String cin, String cname, String cdiscription, String cimagerl) {
        this.cid = cin;
        this.cname = cname;
        this.cdiscription = cdiscription;
        this.cimagerl = cimagerl;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdiscription() {
        return cdiscription;
    }

    public void setCdiscription(String cdiscription) {
        this.cdiscription = cdiscription;
    }

    public String getCimagerl() {
        return cimagerl;
    }

    public void setCimagerl(String cimagerl) {
        this.cimagerl = cimagerl;
    }
}
