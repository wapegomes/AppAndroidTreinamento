package br.com.wpgomes.treinamento.appcasttreinamento.ui.adapters;

import android.content.Context;
import android.os.Parcelable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.wpgomes.treinamento.appcasttreinamento.R;

/**
 * Created by wgomes on 22/09/16.
 */
public class CustomPagerAdapter extends PagerAdapter {

    Context context;


    private Integer[] Imgid = {
            R.raw.img1, R.raw.img2, R.raw.img3, R.raw.img4
    };

    public int getCount() {
        return Imgid.length;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {


        ImageView img = new ImageView(collection.getContext());
        img.setImageResource(Imgid[position]);
        ((ViewPager) collection).addView(img, 0);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }


    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    public Parcelable saveState() {
        return null;
    }


}

