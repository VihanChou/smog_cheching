package Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlOpenHelper extends SQLiteOpenHelper
{
    /*
     * super(context, name, factory, version);
     * context：上下文
     * name   ：数据库的名称
     * factory：创建的cursor对象。游标，结果集
     * version：数据库的版本，从1开始
     */
    private String sql;

    public MySqlOpenHelper(Context context, String dbname, String sql) //构造器
    {
        super(context, dbname, null, 1);
        //super(context,"vihan.db",null,1);
        this.sql = sql;
    }

    /*
     * //Called when the database is created for the first time.
     * 当数据库初次创建的时候会自动调用方法
     * //特别适合做表结构初始化，创建表就是写sql语句
     * //id一般以下划线开头
     * //数据库底层都以String来存数据，以下语句在数据表中创建了两列分别是_id和_name，成功对该数据库进行了初始化
     */
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sql);
        //db.execSQL("create table info(_id integer primary key autoincrement,_name varchar(20),phone varchar(20))");//id设定自动增长1，2，3，4，5
    }


    /*Called when the database needs to be upgraded.
     * 当数据库需要升级的时候会自动调用
     * 表结构的更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //db.execSQL("alter table info add phone varchar(20)");
    }

}
