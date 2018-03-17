package smog_check.vihan.com.smog_cheching;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Utils.Alert_and_Abuse;
import Utils.DataBaseActions;
import Utils.MySqlOpenHelper;

/**
 * @author vihan
 */
public class Fra_Recorder extends Fragment implements OnClickListener
{

    private ScrollView mSv_recorder;
    private LinearLayout mLl_recordercontainer;
    private MySqlOpenHelper myOpenHelper;
    private List<Alert_and_Abuse> data;
    private DataBaseActions Dbtool = new DataBaseActions();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fra_recorder, container, false);
        InitDatabasde();
        data = init_Recorder_data();

        init(v);

        return v;
    }

    private void InitDatabasde()
    {
        // 用户报警记录的数据库中的表
        // 数据库初始化（如果数据库还未创建）
        myOpenHelper = new MySqlOpenHelper(getActivity(),
                "GHData.db",
                "create table Recorder(alert varchar(20),abuse varchar(20))");


    }

    // 用户报警记录的数据初始化，返回报警记录数据
    public List<Alert_and_Abuse> init_Recorder_data()
    {
        String sql = "select * from Recorder";
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        List<Alert_and_Abuse> pointList = new ArrayList<Alert_and_Abuse>();
        Alert_and_Abuse point = null;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext())
        {
            point = new Alert_and_Abuse();
            point.setAlert(cursor.getString(cursor.getColumnIndex("alert")));
            point.setAbuse(cursor.getString(cursor.getColumnIndex("abuse")));
            pointList.add(point);
        }
        return pointList;
    }

    private void init(View v)
    {

        mSv_recorder = (ScrollView) v.findViewById(R.id.sv_recorder);
        mLl_recordercontainer = (LinearLayout) v.findViewById(R.id.ll_recordercontainer);
////        view.getLayoutParams() == null;
        TextView tv_recorder = null;
        View view = null;
        ImageView iv_head = null;
        int i = 0;
        // for (i = 0; i < 20; i++)
        {
//            view = getActivity().getLayoutInflater().inflate(R.layout.item_recoder, null, false);
//            tv_recorder = (TextView) view.findViewById(R.id.tv_recorder);
//            iv_head = (ImageView) view.findViewById(R.id.iv_head);
//            tv_recorder.setText("========" + i + "===========");
//            mLl_recordercontainer.addView(view);

            for (i = 0; i < data.size(); i++)
            {
                Alert_and_Abuse n = data.get(i);
                view = getActivity().getLayoutInflater().inflate(R.layout.item_recoder, null, false);
                tv_recorder = (TextView) view.findViewById(R.id.tv_recorder);
                tv_recorder.setText("煤气预警: " + n.getAlert());
                mLl_recordercontainer.addView(view);


                view = getActivity().getLayoutInflater().inflate(R.layout.item_recoder, null, false);
                tv_recorder = (TextView) view.findViewById(R.id.tv_recorder);
                tv_recorder.setText("报警解除: " + n.getAbuse());
                mLl_recordercontainer.addView(view);


            }
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // case R.id.person2_shopping: // 按钮操作
            // break;

            default:
                break;
        }
    }

}
