package myService;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import Utils.ConstantValue;
import Utils.SpUtils;
import smog_check.vihan.com.smog_cheching.Activity_Danger;

public class checkingservice extends Service
{
    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO Auto-generated method stub
        return null;
    }

    // 服务第一次创建的时候被调用
    @Override
    public void onCreate()
    {
        Toast.makeText(getApplicationContext(), "检测服务开启", Toast.LENGTH_SHORT).show();
        SpUtils.putBoolean(this, ConstantValue.ISSERVICE, true); // 告诉系统服务开启了
        // Intent intent2 = new Intent(getBaseContext(), BootActivity.class);
        // intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //广播接受者中不能开启Activity
        // getApplication().startActivity(intent2);
        // super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        System.out.println("onStartCommand");
        Toast.makeText(getApplicationContext(), "onStartCommand", Toast.LENGTH_SHORT).show();
        System.out.println("检测正在进行");


        //监听线程
        new Thread()
        {
            private int code;
            private String current_value;
            private boolean point = true;

            public void run()
            {
                while (SpUtils.getboolean(getApplicationContext(),
                        ConstantValue.iSCHECKING, false))
                {
                    try
                    {

                        String path = "http://api.heclouds.com/devices/20242388/datastreams/vihan";
                        URL url;
                        http:
                        // api.heclouds.com/devices/20242388/datastreams/vihan
                        url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url
                                .openConnection();
                        conn.setRequestMethod("GET");
                        conn.setReadTimeout(5000);
                        conn.setRequestProperty("api-key",
                                "bUwXEK8i85YlBx2PdqWztcrLZVo=");
                        code = conn.getResponseCode();
                        InputStream in = conn.getInputStream();
                        if (code == 200)
                        {
                            try
                            {

                                StringBuffer out = new StringBuffer();
                                byte[] b = new byte[4096];
                                for (int n; (n = in.read(b)) != -1; )
                                {
                                    out.append(new String(b, 0, n));
                                }
                                System.out.println(out.toString());

                                JSONObject root = new JSONObject(out.toString());
                                JSONObject two = root.getJSONObject("data");
                                current_value = two.getString("current_value");
                                System.out.println("数据"
                                        + two.getString("current_value"));
                            }
                            catch (Exception e)
                            {
                                System.out.println("————————————————————————————————————————————异常");
                                e.printStackTrace();
                            }

                        }
                        else
                        {
                            System.out.println("————————————————————————————————————————————网络异常");
                        }

                        sleep(5000);// 睡眠100毫秒
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }
                    double current_value_00 = Double.valueOf(current_value).doubleValue();
                    if (current_value_00 > 200)
                    {
                        SpUtils.putBoolean(getApplicationContext(), ConstantValue.iSCHECKING, false);  //如果出现煤气泄漏，先关闭检测，进行处理
                        Intent intent2 = new Intent(getApplicationContext(), Activity_Danger.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                      //广播接受者中不能开启Activity
                        getApplication().startActivity(intent2);
                    }

                }//;while 结束
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
    }

    // 服务销毁的时候调用
    @Override
    public void onDestroy()
    {
        Toast.makeText(getApplicationContext(), "检测服务销毁", Toast.LENGTH_SHORT).show();
        SpUtils.putBoolean(this, ConstantValue.ISSERVICE, false); // 告诉系统服务关闭了
        System.out.println("onDestroy");
        super.onDestroy();
    }
}
