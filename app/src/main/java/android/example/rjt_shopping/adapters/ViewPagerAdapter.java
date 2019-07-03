package android.example.rjt_shopping.adapters;

import android.example.rjt_shopping.activities.MainActivity;
import android.example.rjt_shopping.fragments.CategoryFragment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFragment=new ArrayList<>();
    ArrayList<String> mUrl=new ArrayList<>();
    private int custom_postion=0;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
       // if(custom_postion>mFragment.size()-1) custom_postion=0;
        //Log.i("MyTag","current:"+custom_postion);

        return mFragment.get(custom_postion++);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    public void addFragment(String url){
        mFragment.add(CategoryFragment.newInstance(url));
        mUrl.add(url);
    }
}
