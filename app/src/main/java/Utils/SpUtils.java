package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.NoCopySpan.Concrete;
import android.view.View.OnClickListener;

/**
 * @author 周寒
 *
 */
public class SpUtils
{
	private static SharedPreferences sp;

	/**写入布尔类型的标识
	 * @param context  上下文环境
	 * @param key      存储节点名称
	 * @param value    存储节点的值
	 */
	public static  void putBoolean(Context context,String key,boolean value) 
	{
		if (sp == null)
		{
			sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key,value).commit();
	}
	
	/**读取布尔类型的标识
	 * @param context
	 * @param key       
	 * @param defvalue   此节点的默认值
	 * @return            
	 */
	public static boolean getboolean(Context context,String key,boolean defvalue) 
	{
		if (sp == null)
		{
			sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defvalue);
	}
	
	
	/**写入String类型的标识
	 * @param context  上下文环境
	 * @param key      存储节点名称
	 * @param value    存储节点的值
	 */
	public static  void putString(Context context,String key,String value) 
	{
		if (sp == null)
		{
			sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
		}
		sp.edit().putString(key,value).commit();
	}
	
	/**读取String类型的标识
	 * @param context
	 * @param key       
	 * @param defvalue   此节点的默认值
	 * @return            
	 */
	public static String getString(Context context,String key,String defvalue) 
	{
		if (sp == null)
		{
			sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
		}
		return sp.getString(key, defvalue);
	}

	/**
	 * 移除sp中的某个节点
	 * @param context
	 * @param key       节点key
	 */
	public static void remove(Context context,String key)  
	{
		if (sp == null)
		{
			sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
		}
		sp.edit().remove(key).commit();
	}


}
