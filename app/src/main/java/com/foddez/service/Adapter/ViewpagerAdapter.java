package com.foddez.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import com.foddez.service.Model.SliderUtils;
import com.foddez.service.Util.CustomVolleyRequest;
import com.foddez.service.Model.SliderUtils;
import com.foddez.service.R;

import java.util.List;

public class ViewpagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<SliderUtils> sliderImg;
    private ImageLoader imageLoader;
    //private int[] image={R.drawable.janta1,R.drawable.janta2,R.drawable.janta3};

    public ViewpagerAdapter(List<SliderUtils> sliderImg, Context context) {
        this.sliderImg=sliderImg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slider_layout,null);

        SliderUtils utils=sliderImg.get(position);

        ImageView imageView=view.findViewById(R.id.imageSliderView);
        //imageView.setImageResource(image[position]);

        imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(utils.getSliderImageUrl(), ImageLoader.getImageListener(imageView,R.drawable.sample_slider, android.R.drawable.ic_dialog_alert));

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp= (ViewPager) container;
        View view= (View) object;
        vp.removeView(view);
    }
}
