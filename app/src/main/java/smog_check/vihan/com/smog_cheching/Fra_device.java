package smog_check.vihan.com.smog_cheching;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ldoublem.ringPregressLibrary.OnSelectRing;
import com.ldoublem.ringPregressLibrary.Ring;
import com.ldoublem.ringPregressLibrary.RingProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * home1
 * @author andye
 */
public class Fra_device extends Fragment implements OnClickListener {

    RingProgress mRingProgress;
    private String[] data = new String[]{"厨房", "厕所", "卧室", "天台", "客厅","卧室"};
    Random random = new Random();                //产生随机数
    List<Ring> mlistRing = new ArrayList<>();    //随机数列表作为各个圈的数据显示

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fra_device,container, false);
        init(v);
        return v;
    }

    private void init(View v)
    {
        // headerIV = (ImageView) v.findViewById(R.id.person2_header_iv);\
        //RingProgressBar对象的获取
        mRingProgress = (RingProgress)v.findViewById(R.id.lv_ringp);
        mRingProgress.setOnSelectRing(new OnSelectRing() {
            @Override
            public void Selected(Ring r) {
                Toast.makeText(getActivity(), r.getName(), Toast.LENGTH_SHORT).show();  //对不同的环设置监听
            }
        });
        setData();
    }



    private void setData()
    {
        mlistRing.clear();
        for (int i = 0; i < data.length; i++)       //根据环的名称数组来确定有几环，在里边可以增加环数
        {

            Ring r = new Ring();                   //Ring在这里作为一个javabean，初始化数据后的某一个Ring作为 RingProgressBar 中某个Ring显示的数据的依据
            //多个Ring（javabean）被添加到  mlistRing 这样一个 ArrayList中，mlistRing作为整个RingProgressBar数据显示的依据
            //mlistRing的长度，就是我们RingProgressBar显示的环数， 环的颜色也在其中
            int p = random.nextInt(100);           //获得随机数作为显示的比值
            r.setProgress(p);                      //对某一环显示的真实比例进行设置
            r.setValue("");                //显示的数据进行设置

            r.setName(data[i]);                    //对某个ring的名字进行设置

//对某个环的颜色进行设置
            if (i == 0) {

                r.setStartColor(Color.rgb(235, 79, 56));
                r.setEndColor(Color.argb(100, 235, 79, 56));


            }
            if (i == 1) {
                r.setStartColor(Color.rgb(17, 205, 110));
                r.setEndColor(Color.argb(100, 17, 205, 110));

            }
            if (i == 2) {
                r.setStartColor(Color.rgb(234, 128, 16));
                r.setEndColor(Color.argb(100, 234, 128, 16));

            }
            if (i == 3) {
                r.setStartColor(Color.rgb(86, 171, 228));
                r.setEndColor(Color.argb(100, 86, 171, 228));

            }
            if (i == 4) {
                r.setStartColor(Color.rgb(157, 85, 184));
                r.setEndColor(Color.argb(100, 157, 85, 184));

            }
            if (i == 5) {
                r.setStartColor(Color.rgb(0, 0, 0));
                r.setEndColor(Color.argb(100, 0, 0, 0));
            }
            mlistRing.add(r);              //将每一环的显示数据依次添加到mlistRing ArrayList中进行总体封装
        }
        mRingProgress.setData(mlistRing, 500);   //最后对显示整个RingProgress的数据进行显示
        mRingProgress.setRingWidthScale(25/100f);     //宽度
        mRingProgress.setSweepAngle((int) (360f * (180 / 100f))); //位置旋转
        mRingProgress.setDrawBg(true, Color.rgb(168, 168, 168)); //背景
        mRingProgress.setSweepAngle((int) (360f));        //背景展示的角度大小
        mRingProgress.setCorner(true);                    //角的形状  ture为圆角   false为方
        mRingProgress.setDrawBgShadow(true, Color.argb(100, 235, 79, 56)); //辉光
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            // case R.id.person2_shopping: // 按钮操作
            // break;
            default:
                break;
        }
    }

}
