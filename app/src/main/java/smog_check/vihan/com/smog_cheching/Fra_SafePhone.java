package smog_check.vihan.com.smog_cheching;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import Utils.ConstantValue;
import Utils.SpUtils;
import toasty.Toasty;

public class Fra_SafePhone extends Fragment
{

    private TextView mTv_property;
    private TextView mTv_people;
    private View mV_toot;
    private LinearLayout mProperty;
    private LinearLayout mHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mV_toot = inflater.inflate(R.layout.fra_safephone, container, false);
        init(mV_toot);
        Input();
        return mV_toot;
    }

    //初始化数据以及UI
    private void init(View v)
    {

        // headerIV = (ImageView) v.findViewById(R.id.person2_header_iv);
        mTv_people = (TextView) v.findViewById(R.id.tv_people);
        mTv_property = (TextView) v.findViewById(R.id.tv_property);
        String number = SpUtils.getString(getActivity(), ConstantValue.NUMBER_HELPER, "").toString().trim(); // 【4】获取edittext的文本输入内容
        if ("".equals(number))
        {
            mTv_people.setText("号码未设置");
        }
        else
        {
            mTv_people.setText(number);
        }
        number = SpUtils.getString(getActivity(), ConstantValue.NUMBER_PROPERTY, "").toString().trim(); // 【4】获取edittext的文本输入内容
        if ("".equals(number))
        {
            mTv_property.setText("号码未设置");
        }
        else
        {
            mTv_property.setText(number);
        }
    }


    //号码编辑逻辑
    public void Input()
    {
        mHelper = (LinearLayout) mV_toot.findViewById(R.id.helper);
        mProperty = (LinearLayout) mV_toot.findViewById(R.id.property);
        mHelper.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showSetPsdDiolog(ConstantValue.NUMBER_HELPER);
            }
        });
        mProperty.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showSetPsdDiolog(ConstantValue.NUMBER_PROPERTY);

            }
        });
    }


    /**
     * 设置密码的对话框
     */
    private void showSetPsdDiolog(final String location)
    {
        //自定义对话框的展示样式 需要成一个dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        final View view = View.inflate(getActivity(), R.layout.dialog_set_flow, null);
        //这里的view通过view.inflat去加载一个布局文件，作为对话框的样式
        Window window = dialog.getWindow();
//        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        //     window.setWindowAnimations(R.style.dialog_style);  //添加动画
        dialog.setView(view);
        dialog.show();
        EditText et_confirm_psd = (EditText) view.findViewById(R.id.et_confirm_psd);
        et_confirm_psd.setText(SpUtils.getString(getActivity(), location, "")); //显示上一次的设置
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);

        //提交按钮
        bt_submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //找到两个控件中的内容进比对
                EditText et_confirm_psd = (EditText) view.findViewById(R.id.et_confirm_psd);
                String confirm_psd = et_confirm_psd.getText().toString();

                if (!TextUtils.isEmpty(confirm_psd))
                {
                    SpUtils.putString(getActivity(), location, confirm_psd);
                    Toasty.success(getActivity(), "号码设置成功", 0, true).show();
                    dialog.dismiss();
                }
                else
                {
                    Toasty.error(getActivity(), "请输入您要设置的号码", 0, true).show();
                }


                //更新UI
                String number = SpUtils.getString(getActivity(), ConstantValue.NUMBER_HELPER, "").toString().trim(); // 【4】获取edittext的文本输入内容
                if ("".equals(number))
                {
                    mTv_people.setText("号码未设置");
                }
                else
                {
                    mTv_people.setText(number);
                }
                number = SpUtils.getString(getActivity(), ConstantValue.NUMBER_PROPERTY, "").toString().trim(); // 【4】获取edittext的文本输入内容
                if ("".equals(number))
                {
                    mTv_property.setText("号码未设置");
                }
                else
                {
                    mTv_property.setText(number);
                }
            }


        });

        //取消按钮
        bt_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();

            }
        });
    }
}
