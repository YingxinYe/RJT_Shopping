package android.example.rjt_shopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    static String category;
    ImageView imageView;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String url){
        //Category=url;
        CategoryFragment categoryFragment=new CategoryFragment();
        Bundle bundle=new Bundle();
        bundle.putString("CAT_URL",url);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        imageView=view.findViewById(R.id.view_pager_fragment_image_container);
        Bundle bundle=getArguments();
        category=bundle.getString("CAT_URL");
        Glide.with(getActivity()).load(category).into(imageView);
        return view;
    }

}
