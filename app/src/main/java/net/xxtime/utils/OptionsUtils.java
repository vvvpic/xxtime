package net.xxtime.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import net.xxtime.R;

public class OptionsUtils {
    
    /**
     * 设置常用的设置项
     * @return
     */
    public static DisplayImageOptions getSimpleOptions(int round) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.mipmap.bg) //设置图片在下载期间显示的图片
        .showImageForEmptyUri(R.mipmap.bg)//设置图片Uri为空或是错误的时候显示的图片
        .showImageOnFail(R.mipmap.bg)  //设置图片加载/解码过程中错误时候显示的图片
        .cacheInMemory(true)//设置下载的图片是否缓存在内存中  
        .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中  
        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
        .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
        .displayer(new RoundedBitmapDisplayer(round))
        .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
        .build();
        return options;
    }

   

}
