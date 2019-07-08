package android.example.rjt_shopping.database;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.rjt_shopping.model.Product;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "shopping cart";
    private static final String TABLE_NAME = "table_cart";

    private static final String COLUMN_ID = "item_id";
    private static final String COLUMN_NAME = "item_names";
    private static final String COLUMN_QUANTITY = "item_quantity";
    private static final String COLUMN_PRICE = "item_price";
    private static final String COLUMN_IMAGE = "item_iamge";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY ,"
            + COLUMN_NAME + " VARCHAR ,"
            + COLUMN_QUANTITY + " INTEGER, "
            + COLUMN_PRICE + " INTEGER ,"
            + COLUMN_IMAGE + " VARCHAR );";

    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private boolean isProductExist(Product product) {
        String query = "select* from " + TABLE_NAME + " where " + COLUMN_ID + "=?";
        String[] args = new String[]{product.getId()};
        Cursor cursor = db.rawQuery(query, args);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    public void insertProduct(Product product) {
        if (isProductExist(product)) {
            Log.i("MyTag", "exists");
            updateProduct(product, 1);
        } else {
            Log.i("MyTag", "not exists, insert now");
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, product.getId());
            values.put(COLUMN_NAME, product.getPname());
            values.put(COLUMN_PRICE, product.getPrize());
            values.put(COLUMN_QUANTITY, 1);
            values.put(COLUMN_IMAGE, product.getImage());
            db.insert(TABLE_NAME, null, values);
        }
    }

    public void minusProduct(Product product) {
        updateProduct(product, -1);
    }

    public void addProduct(Product product) {
        updateProduct(product, 1);
    }

    public void updateProduct(Product p, int change) {
        int new_quantity=0;
        String id = p.getId();
        ArrayList<Product> products=readAlldata();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)) {
                new_quantity=Integer.parseInt(products.get(i).getQuantity())+change;
            }
        }
        if(new_quantity<1){
            deleteProduct(p);
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUANTITY,new_quantity);
            db.update(TABLE_NAME,values,COLUMN_ID+"=?",new String[]{id});
        }

//        String query="select * from "+TABLE_NAME+" where "+COLUMN_ID+" =?";
//        String[] args=new String[]{id};
//        Cursor cursor=db.rawQuery(query,args);
//        cursor.moveToFirst();
//        if(cursor!=null && cursor.moveToFirst()){
//            new_quantity=cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))+change;
//        }
//        cursor.close();
//        if(new_quantity<1){
//            deleteProduct(p);
//        }
//        else{
//            values.put(COLUMN_QUANTITY,new_quantity);
//            db.update(TABLE_NAME,values,COLUMN_ID+"=?",new String[]{id});
//        }
    }

    public void deleteProduct(Product product) {
        String id = product.getId();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{id});
    }

    public void deleteAllProduct() {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Product> readAlldata() { //may wrong on type
        ArrayList<Product> mlist = new ArrayList<>();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_QUANTITY, COLUMN_PRICE, COLUMN_IMAGE};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))));
                product.setPname(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                product.setQuantity(String.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY))));
                product.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                product.setPrize(String.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))));
                mlist.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mlist;
    }

}
