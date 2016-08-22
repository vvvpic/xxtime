package net.xxtime.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import net.xxtime.base.activity.XxtimeApplication;

import java.util.ArrayList;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class CommentView extends View {

    Paint redpaint,yellowpaint,graypaint,paint,dgraypaint,whitepaint;

    private int red=50,yellow=30,gary=20;

    public CommentView(Context context) {
        this(context, null);
    }

    public void setProgess(int red,int yellow,int gary){
        this.red=red;
        this.yellow=yellow;
        this.gary=gary;
        postInvalidate();
    }

    public CommentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        redpaint = new Paint();
        redpaint.setColor(Color.parseColor("#FF4081"));
        redpaint.setAntiAlias(true);

        yellowpaint = new Paint();
        yellowpaint.setColor(Color.parseColor("#f49d2b"));
        yellowpaint.setAntiAlias(true);

        graypaint = new Paint();
        graypaint.setColor(Color.parseColor("#999999"));
        graypaint.setAntiAlias(true);

        dgraypaint = new Paint();
        dgraypaint.setColor(Color.parseColor("#eeeeee"));
        dgraypaint.setAntiAlias(true);

        paint = new Paint();
        paint.setColor(Color.parseColor("#444444"));
        paint.setTextSize(XxtimeApplication.width/30);
        paint.setAntiAlias(true);

        whitepaint = new Paint();
        whitepaint.setColor(Color.parseColor("#ffffff"));
        whitepaint.setTextSize(XxtimeApplication.width/30);
        whitepaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h=getHeight();
        int jian4=h/4;
        int jian7=h/7;
        int w=getWidth();

        int pw=w-XxtimeApplication.width/25-XxtimeApplication.width/12;

        canvas.drawText("好评",XxtimeApplication.width/50 , jian4, paint);
        canvas.drawText("中评",XxtimeApplication.width/50 , jian4*2, paint);
        canvas.drawText("差评",XxtimeApplication.width/50 , jian4*3, paint);

        canvas.drawRect(XxtimeApplication.width/50+XxtimeApplication.width/12 ,jian7,w-XxtimeApplication.width/50,jian4+h/50,dgraypaint);
        canvas.drawRect(XxtimeApplication.width/50+XxtimeApplication.width/12 ,jian7,XxtimeApplication.width/50+XxtimeApplication.width/12+(red*pw/100),jian4+h/50,redpaint);
        canvas.drawText(red+"%",XxtimeApplication.width/50+(red*pw/100) , jian4, whitepaint);

        canvas.drawRect(XxtimeApplication.width/50+XxtimeApplication.width/12 ,jian7+jian4,w-XxtimeApplication.width/50,2*jian4+h/50,dgraypaint);
        canvas.drawRect(XxtimeApplication.width/50+XxtimeApplication.width/12 ,jian7+jian4,XxtimeApplication.width/50+XxtimeApplication.width/12+(yellow*pw/100),2*jian4+h/50,yellowpaint);
        canvas.drawText(yellow+"%",XxtimeApplication.width/50+(yellow*pw/100) , jian4*2, whitepaint);

        canvas.drawRect(XxtimeApplication.width/50+XxtimeApplication.width/12 ,jian7+2*jian4,w-XxtimeApplication.width/50,3*jian4+h/50,dgraypaint);
        canvas.drawRect(XxtimeApplication.width/50+XxtimeApplication.width/12 ,jian7+2*jian4,XxtimeApplication.width/50+XxtimeApplication.width/12+(gary*pw/100),3*jian4+h/50,graypaint);
        canvas.drawText(gary+"%",XxtimeApplication.width/50+(gary*pw/100) , jian4*3, whitepaint);

    }
}
