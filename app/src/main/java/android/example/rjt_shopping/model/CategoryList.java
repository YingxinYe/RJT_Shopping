package android.example.rjt_shopping.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CategoryList {

    @SerializedName("subcategory")
    //Category sub=new Category("SUB");
    private ArrayList<SubCategory> submlist=new ArrayList<>();
    @SerializedName("category")
    private ArrayList<Category> mlist = new ArrayList<>();
    @SerializedName("products")
    private ArrayList<Product> plist=new ArrayList<>();

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
