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

import java.util.Hashtable;
import java.util.Locale;

import android.util.Log;

/**
 * 
 * @ClassName: L
 * @Description: L是The class for print log
 * @Author：Chengel_HaltuD
 * @Date：2015-5-30 下午3:00:51
 * @version V1.0
 *
 */

public class L
{
  public static boolean LOGFLAG = true;
  public static String TAG = "log";
  public static int logLevel = 2;
  public static Hashtable<String, L> sLoggerTable = new Hashtable();
  public static String CLASSNAME = "Chengel_HaltuD";
  public static L jlog;
  public static L klog;
  public static String JAMES = "@james@ ";
  public static String KESEN = "@kesen@ ";

  public L(String name)
  {
    CLASSNAME = name;
  }

  public static L kLog()
  {
    if (klog == null)
    {
      klog = new L(KESEN);
    }
    return klog;
  }

  public static L jLog()
  {
    if (jlog == null)
    {
      jlog = new L(JAMES);
    }
    return jlog;
  }

  public static L getLog(String className)
  {
    L classLogger = (L)sLoggerTable.get(className);
    if (classLogger == null)
    {
      classLogger = new L(className);
      sLoggerTable.put(className, classLogger);
    }
    return classLogger;
  }

  private static String getFunctionName()
  {
    StackTraceElement[] sts = Thread.currentThread().getStackTrace();
    if (sts == null)
    {
      return null;
    }
    StackTraceElement[] arrayOfStackTraceElement1 = sts; int j = sts.length; for (int i = 0; i < j; i++) { StackTraceElement st = arrayOfStackTraceElement1[i];

      if (!st.isNativeMethod())
      {
        if (!st.getClassName().equals(Thread.class.getName()))
        {
          if (!st.getClassName().equals(st.getClass().getName()))
          {
            return CLASSNAME; } 
        }
      } } return null;
  }

  public static void V(Object string)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 2)
      {
        String name = getFunctionName();
        if (name != null)
        {
          Log.v(TAG, name + " - " + string);
        }
        else
        {
          Log.v(TAG, string.toString());
        }
      }
    }
  }

  public static void D(Object string)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 3)
      {
        String name = getFunctionName();
        if (name != null)
        {
          Log.d(TAG, name + " - " + string);
        }
        else
        {
          Log.d(TAG, string.toString());
        }
      }
    }
  }

  public static void D(String format, Object[] args)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 3)
      {
        String string = buildMessage(format, args);
        String name = getFunctionName();

        if (name != null)
        {
          Log.d(TAG, name + " - " + string);
        }
        else
        {
          Log.d(TAG, string.toString());
        }
      }
    }
  }

  public static void I(Object string)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 4)
      {
        String name = getFunctionName();
        if (name != null)
        {
          Log.i(TAG, name + " - " + string);
        }
        else
        {
          Log.i(TAG, string.toString());
        }
      }
    }
  }

  public static void W(Object string)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 5)
      {
        String name = getFunctionName();
        if (name != null)
        {
          Log.w(TAG, name + " - " + string);
        }
        else
        {
          Log.w(TAG, string.toString());
        }
      }
    }
  }

  public static void E(Object string)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 6)
      {
        String name = getFunctionName();
        if (name != null)
        {
          Log.e(TAG, name + " - " + string);
        }
        else
        {
          Log.e(TAG, string.toString());
        }
      }
    }
  }

  public static void E(Exception exception)
  {
    if (LOGFLAG)
    {
      if (logLevel <= 6)
      {
        Log.e(TAG, "error", exception);
      }
    }
  }

  public static void E(String string, Throwable throwable)
  {
    if (LOGFLAG)
    {
      String line = getFunctionName();
      Log.e(TAG, "{Thread:" + Thread.currentThread().getName() + "}" + 
        "[" + CLASSNAME + line + ":] " + string + "\n", throwable);
    }
  }

  private static String buildMessage(String format, Object[] args)
  {
    String msg = args == null ? format : String.format(Locale.US, format, args);
    StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
    String caller = "<unknown>";
    for (int i = 2; i < trace.length; i++) {
      Class clazz = trace[i].getClass();
      if (!clazz.equals(L.class)) {
        String callingClass = trace[i].getClassName();
        callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
        callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
        caller = callingClass + "." + trace[i].getMethodName();
        break;
      }
    }
    return String.format(Locale.US, "[%d] %s: %s", new Object[] { 
      Long.valueOf(Thread.currentThread().getId()), caller, msg });
  }
}