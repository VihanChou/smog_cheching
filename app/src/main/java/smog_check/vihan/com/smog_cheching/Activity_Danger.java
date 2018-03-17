package smog_check.vihan.com.smog_cheching;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.DataBaseActions;
import Utils.ConstantValue;
import Utils.MySqlOpenHelper;
import Utils.SpUtils;
import myService.checkingservice;
import toasty.Toasty;

public class Activity_Danger extends AppCompatActivity
{

    private MediaPlayer mediaPlayer;        // MediaPlayer对象
    private File file;                      // 要播放的文件
    private Vibrator vibrator = null;
    private DataBaseActions Dbtool = new DataBaseActions();
    private MySqlOpenHelper myOpenHelper;
    String Alert = null;
    String Abuse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__danger);
        myOpenHelper = new MySqlOpenHelper(getApplicationContext(),
                "GHData.db",
                "create table Recorder(alert varchar(20),abuse varchar(20))");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //TODO: 2017/12/7   开始报警的记录
        Alert = date;
        mediaPlayer = new MediaPlayer();     //MediaPlayer对象
        shack();
        play();
    }

    //震动逻辑
    private void shack()
    {
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        long[] pattern = {1000, 2000};
        vibrator.vibrate(pattern, 0);   // 启动震动，并持续指定的时间
    }

    //点击取消的逻辑
    public void ok(View view)
    {
        //TODO: 2017/12/7   报警解除的记录
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Abuse = date;
//        Intent in = new Intent(getApplication(), checkingservice.class);
//        startService(in);                                            //再次开启检测服务
        vibrator.cancel();                                           // 关闭震动
        mediaPlayer.stop();
        SpUtils.putBoolean(getApplicationContext(), ConstantValue.iSCHECKING, false); //返回后打开检测
        finish();
    }

    //一键报警逻辑
    public void call_police(View view)
    {
        //TODO: 2017/12/7   报警解除的记录
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Abuse = date;
        vibrator.cancel();                                           // 关闭震动
        mediaPlayer.stop();
        String number = "119";//et_number.getText().toString().trim();
        //行拨打电话事件,利用intent
        Intent intent = new Intent(); // 创建一个intent对象
        intent.setAction(intent.ACTION_CALL); // 设置一个intent中的动作，此处为拨打
        intent.setData(Uri.parse("tel:" + number)); // 设置拨打电话的号码数据
        startActivity(intent); // 【6】：开启我们设定的intent事件,即拨打电话
    }

    //一键求助逻辑
    public void call_peple(View view)
    {
        //TODO: 2017/12/7   报警解除的记录
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Abuse = date;
        vibrator.cancel();                                           // 关闭震动
        mediaPlayer.stop();
        String number = SpUtils.getString(this, ConstantValue.NUMBER_HELPER, "").toString().trim();//et_number.getText().toString().trim(); // 【4】获取edittext的文本输入内容
        if ("".equals(number))
        {
            Toasty.info(this, "求助号码未设置", Toast.LENGTH_SHORT).show();
            // 为文本内容为空的时候，设置一个文本框的提示，（利用toast类，土司）
            return;
        }
        // 【5】在验证电话号码不为空的时候进行拨打电话事件,利用intent
        Intent intent = new Intent(); // 创建一个intent对象
        intent.setAction(intent.ACTION_CALL); // 设置一个intent中的动作，此处为拨打
        intent.setData(Uri.parse("tel:" + number)); // 设置拨打电话的号码数据
        startActivity(intent); // 【6】：开启我们设定的intent事件,即拨打电话
    }

    //一键物业逻辑
    public void call_property(View view)
    {
        //TODO: 2017/12/7   报警解除的记录
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Abuse = date;
        vibrator.cancel();                                           // 关闭震动
        mediaPlayer.stop();
        String number = SpUtils.getString(this, ConstantValue.NUMBER_PROPERTY, "").toString().trim();//et_number.getText().toString().trim(); // 【4】获取edittext的文本输入内容
        if ("".equals(number))
        {
            Toasty.info(this, "物业号码未设置", Toast.LENGTH_SHORT).show();
            // 为文本内容为空的时候，设置一个文本框的提示，（利用toast类，土司）
            return;
        }
        // 【5】在验证电话号码不为空的时候进行拨打电话事件,利用intent
        Intent intent = new Intent(); // 创建一个intent对象
        intent.setAction(intent.ACTION_CALL); // 设置一个intent中的动作，此处为拨打
        intent.setData(Uri.parse("tel:" + number)); // 设置拨打电话的号码数据
        startActivity(intent); // 【6】：开启我们设定的intent事件,即拨打电话
    }

    public void Add_Data_To_DataBAse()
    {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Dbtool.addToDb(myOpenHelper, Alert + "", Abuse + "");

    }

    // 播放音乐的方法
    private void play()
    {
        try
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.dange_soundr);
            mediaPlayer.start();// 播放音乐
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void finish()
    {
        super.finish();
        Add_Data_To_DataBAse();
    }
}
