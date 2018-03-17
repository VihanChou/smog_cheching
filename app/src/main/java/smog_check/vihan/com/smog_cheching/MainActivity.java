package smog_check.vihan.com.smog_cheching;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import Utils.ExitAPPUtils;
import toasty.Toasty;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{


    private long timeSpace;
    private LinearLayout mTab_item_container;
    private FragmentManager mFM = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                    //添加主界面
        ExitAPPUtils.getInstance().addActivity(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Fragment f = new Fra_Main();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();


        //悬浮按钮以及其监听事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "数据更新成功！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                main();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    /**
     * 切换到安全联系人fragement
     */
    private void changeSafenumber()
    {
        Fragment f = new Fra_SafePhone();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    /**
     * 切换到报警记录fragement
     */
    private void recorder()
    {
        Fragment f = new Fra_Recorder();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    /**
     * 切换到安全助手fragement
     */
    private void helper()
    {
        Fragment f = new Fra_helper();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    /**
     * 切换到设备查看fragement
     */
    private void device()
    {
        Fragment f = new Fra_device();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    /**
     * 切换到应用信息fragement
     */
    private void about()
    {
        Fragment f = new Fra_about();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    /**
     * 切换到应用主界面fragement
     */
    private void main()
    {
        Fragment f = new Fra_Main();
        if (null == mFM)
            mFM = getSupportFragmentManager();
        FragmentTransaction ft = mFM.beginTransaction();
        ft.replace(R.id.content_container, f);
        ft.commit();
    }

    //返回键的监听函数
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) //如果导航页面是开启的，那么关闭
        {
            drawer.closeDrawer(GravityCompat.START);

        }
        else
        {
            super.onBackPressed();
        }
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);    //菜单展示
        return true;
    }


    //点击菜单按钮，执行该方法
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //导航栏的点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout)
        {
            // Handle the camera action
            finish();
            Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                      //广播接受者中不能开启Activity
            getApplication().startActivity(intent2);
        }
        else if (id == R.id.nav_safe_number)
        {
            changeSafenumber();
        }
        else if (id == R.id.nav_recording)
        {
            recorder();
        }
        else if (id == R.id.nav_helper)
        {
            helper();

        }
        else if (id == R.id.nav_myd_evice)
        {
            Toasty.success(MainActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();
            device();
        }
        else
        {
            about();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //双击返回
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (System.currentTimeMillis() - timeSpace > 2000)
        {
            timeSpace = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "再次点击退出App，检测仍然进行", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ExitAPPUtils.getInstance().exit();
        }
        return true;
    }


}
