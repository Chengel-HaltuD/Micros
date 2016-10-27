/*
 * Copyright (C) 2013 Chengel_HaltuD
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.micros.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @ClassName: S
 * @Description: S是SharedPreferences存储工具类
 * @Author：Chengel_HaltuD
 * @Date：2015-5-30 下午3:02:52
 * @version V1.0
 *
 */

public class S
{
  public static String FILE_NAME = "share_data";

  public static void put(Context context, String key, Object object)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, 0);
    SharedPreferences.Editor editor = sp.edit();

    if ((object instanceof String))
    {
      editor.putString(key, (String)object);
    } else if ((object instanceof Integer))
    {
      editor.putInt(key, ((Integer)object).intValue());
    } else if ((object instanceof Boolean))
    {
      editor.putBoolean(key, ((Boolean)object).booleanValue());
    } else if ((object instanceof Float))
    {
      editor.putFloat(key, ((Float)object).floatValue());
    } else if ((object instanceof Long))
    {
      editor.putLong(key, ((Long)object).longValue());
    }
    else {
      editor.putString(key, object.toString());
    }

    SharedPreferencesCompat.apply(editor);
  }

  public static Object get(Context context, String key, Object defaultObject)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, 0);

    if ((defaultObject instanceof String))
    {
      return sp.getString(key, (String)defaultObject);
    }if ((defaultObject instanceof Integer))
    {
      return Integer.valueOf(sp.getInt(key, ((Integer)defaultObject).intValue()));
    }if ((defaultObject instanceof Boolean))
    {
      return Boolean.valueOf(sp.getBoolean(key, ((Boolean)defaultObject).booleanValue()));
    }if ((defaultObject instanceof Float))
    {
      return Float.valueOf(sp.getFloat(key, ((Float)defaultObject).floatValue()));
    }if ((defaultObject instanceof Long))
    {
      return Long.valueOf(sp.getLong(key, ((Long)defaultObject).longValue()));
    }

    return null;
  }

  public static void remove(Context context, String key)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, 0);
    SharedPreferences.Editor editor = sp.edit();
    editor.remove(key);
    SharedPreferencesCompat.apply(editor);
  }

  public static void clear(Context context)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, 0);
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    SharedPreferencesCompat.apply(editor);
  }

  public static boolean contains(Context context, String key)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, 0);
    return sp.contains(key);
  }

  public static Map<String, ?> getAll(Context context)
  {
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, 0);
    return sp.getAll();
  }

  private static class SharedPreferencesCompat
  {
    private static final Method sApplyMethod = findApplyMethod();

    private static Method findApplyMethod()
    {
      try
      {
        Class clz = SharedPreferences.Editor.class;
        return clz.getMethod("apply", new Class[0]);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
      return null;
    }

    public static void apply(SharedPreferences.Editor editor)
    {
      try
      {
        if (sApplyMethod != null) {
          sApplyMethod.invoke(editor, new Object[0]);
          return;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException) {
      }
      catch (IllegalAccessException localIllegalAccessException) {
      }
      catch (InvocationTargetException localInvocationTargetException) {
      }
      editor.commit();
    }
  }
}
