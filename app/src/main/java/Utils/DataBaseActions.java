package Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseActions
{
    String Table_Name = "Recorder";

    //记住密码增加
    public void addToDb(MySqlOpenHelper myOpenHelper, String id, String pwd)    //增加一行
    {
                /*db.insert(table, nullColumnHack, values)
                 *table:         要插入的表的名字
				 *nullColumnHack：
				 *values        ：内部封装了一个map   key；对应表的列名如_name或者phone，，value对应的插入值
				 *返回值为插入的新行的id，对应的第一列的值recno
				 */

        SQLiteDatabase db = myOpenHelper.getWritableDatabase();      //获取数据库对象db
        ContentValues values = new ContentValues();                     //构造insert需要的values参数
        values.put("alert", id);
        values.put("abuse", pwd);
        Cursor cursor = db.query(Table_Name, new String[]{"abuse"}, "alert=?", new String[]{id}, null, null, null);
        //在表格的id列中找到其值为id（）的那一行，并且返回其pwd列中的数据到cursor中，可返回多列
        if (cursor != null)      //id存在(更新)
        {
            db.delete(Table_Name, "alert=?", new String[]{id});
            db.insert(Table_Name, null, values);
        }
        else                   //id不存在（添加）
        {
            long insert = db.insert(Table_Name, null, values);
        }

      db.close();                                                  //关闭数据库
    }

    //删除
    public void DeleteFromDB(MySqlOpenHelper myOpenHelper, String id)
    {

				/* db.delete(table,whereClause, whereArgs)
                 * 将whereClause列值为whereArg的所在行删除
				 * 返回值，代表影响的行数，将索引_name的值为的vihan的那些行删除
				 */
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        int delete = db.delete(Table_Name, "alert=?", new String[]{id});
        db.close();
    }


}
