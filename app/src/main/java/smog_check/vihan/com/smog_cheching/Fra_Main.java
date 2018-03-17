package smog_check.vihan.com.smog_cheching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import myService.checkingservice;
import Utils.ConstantValue;
import Utils.SpUtils;

/**
 * home1
 *
 * @author andye
 */
public class Fra_Main extends Fragment implements OnClickListener {

    private TextView tv;                          //文字浓度描述
    private ToggleButton Tb_main;                 //检测按钮

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fra_main, container, false);
        init(v);
        tv.setText("烟雾浓度" + SpUtils.getString(getActivity(), ConstantValue.CURRENT_VALUE, "空"));
        return v;
    }


    @Override
    public void onStart()
    {
        System.out.println("++++++++++++onStart" + SpUtils.getString(getActivity(), ConstantValue.CURRENT_VALUE, "空"));
        super.onStart();
    }

    @Override
    public void onAttach(final Context context) {
        System.out.println("++++++++++++onAttach");

        new Thread() {

            private int code;
            private String current_value;
            private boolean point = true;

            public void run() {
//                while (SpUtils.getboolean(getApplicationContext(),
//                        ConstantValue.iSCHECKING, false)) {
                try {
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
                    if (code == 200) {
                        try {

                            StringBuffer out = new StringBuffer();
                            byte[] b = new byte[4096];
                            for (int n; (n = in.read(b)) != -1; ) {
                                out.append(new String(b, 0, n));
                            }
                            System.out.println("json文件"+out.toString());
                            JSONObject root = new JSONObject(out.toString());
                            JSONObject two = root.getJSONObject("data");
                            current_value = two.getString("current_value");
                            System.out.println("数据" + two.getString("current_value"));
                            SpUtils.putString(context, ConstantValue.CURRENT_VALUE, current_value);
                        } catch (Exception e)
                        {
                            System.out.println("————————————————————————————————————————————异常");
                            e.printStackTrace();
                        }
                    } else
                    {
                        System.out.println("———————————————————————————————————————————网络异常");
                    }
                    sleep(5000);// 睡眠100毫秒
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }.start();


        super.onAttach(context);
    }


    private void init(View v) {
        // headerIV = (ImageView) v.findViewById(R.id.person2_header_iv);
        tv = (TextView) v.findViewById(R.id.tv);
        Tb_main = (ToggleButton) v.findViewById(R.id.TB_main);
        Tb_main.setChecked(SpUtils.getboolean(getActivity(), ConstantValue.iSCHECKING, false)); //每次打开该界面的时候，加载UI对上一次的状态进行显示
        Tb_main.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TB_main: // 按钮操作

                Intent in = new Intent(getActivity(), checkingservice.class);
                boolean isOPen = Tb_main.isChecked();
                if (isOPen)              //绑定按键状态和检测状态
                {
                    SpUtils.putBoolean(getActivity(), ConstantValue.iSCHECKING, true);
                    getActivity().startService(in);
                }
                else
                {
                    SpUtils.putBoolean(getActivity(), ConstantValue.iSCHECKING, false);
                }
                break;
            default:
                break;
        }
    }

}
