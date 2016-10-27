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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;



// TODO: Auto-generated Javadoc
/**
 *
 * @ClassName: A
 * @Description: A是AbAppUtil应用工具类
 * @Author：Chengel_HaltuD
 * @Date：2015-5-30 下午2:56:47
 * @version V1.0
 *
 */


public class A
{
	public static List<String[]> mProcessList = null;

	/**
	 * 获取App是否后台运行
	 * @return String
	 *
	 */
	public static boolean isBackground(Context context)
	{
		ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(context.getPackageName())) {
				if (appProcess.importance == 400) {
					L.I("后台" + appProcess.processName);
					return true;
				}
				L.I("前台" + appProcess.processName);
				return false;
			}
		}

		return false;
	}

	/**
	 * 获取App是否后台运行
	 * @return String
	 *
	 */
	public static boolean isApplicationBroughtToBackground(Context context)
	{
		ActivityManager am = (ActivityManager)context.getSystemService("activity");
		List tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = ((ActivityManager.RunningTaskInfo)tasks.get(0)).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取App版本
	 * @return String
	 *
	 */
	public static String getAppVersionName(Context context)
	{
		String versionName = "";
		try
		{
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if ((versionName == null) || (versionName.length() <= 0))
				return "";
		}
		catch (Exception e) {
			L.E(e);
		}
		return versionName;
	}

	/**
	 * 描述：打开并安装文件.
	 *
	 * @param context the context
	 * @param file apk文件路径
	 */
	public static void installApk(Context context, File file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 描述：卸载程序.
	 *
	 * @param context the context
	 * @param packageName 包名
	 */
	public static void uninstallApk(Context context,String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri packageURI = Uri.parse("package:" + packageName);
		intent.setData(packageURI);
		context.startActivity(intent);
	}



	/**
	 * 用来判断服务是否运行.
	 *
	 * @param context the context
	 * @param className 判断的服务名字 "com.xxx.xx..XXXService"
	 * @return true 在运行 false 不在运行
	 */
	public static boolean isServiceRunning(Context context, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
		Iterator<RunningServiceInfo> l = servicesList.iterator();
		while (l.hasNext()) {
			RunningServiceInfo si = (RunningServiceInfo) l.next();
			if (className.equals(si.service.getClassName())) {
				isRunning = true;
			}
		}
		return isRunning;
	}

	/**
	 * 停止服务.
	 *
	 * @param context the context
	 * @param className the class name
	 * @return true, if successful
	 */
	public static boolean stopRunningService(Context context, String className) {
		Intent intent_service = null;
		boolean ret = false;
		try {
			intent_service = new Intent(context, Class.forName(className));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (intent_service != null) {
			ret = context.stopService(intent_service);
		}
		return ret;
	}

	/**
	 * Gets the number of cores available in this device, across all processors.
	 * Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
	 * @return The number of cores, or 1 if failed to get result
	 */
	public static int getNumCores() {
		try {
			//Get directory containing CPU info
			File dir = new File("/sys/devices/system/cpu/");
			//Filter to only list the devices we care about
			File[] files = dir.listFiles(new FileFilter(){

				@Override
				public boolean accept(File pathname) {
					//Check if filename is "cpu", followed by a single digit number
					if(Pattern.matches("cpu[0-9]", pathname.getName())) {
					   return true;
				    }
				    return false;
				}

			});
			//Return the number of cores (virtual CPU devices)
			return files.length;
		} catch(Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * 描述：判断网络是否有效.
	 *
	 * @param context the context
	 * @return true, if is network available
	 */
	public static boolean isNetworkAvailable(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Gps是否打开
	 * 需要<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />权限
	 *
	 * @param context the context
	 * @return true, if is gps enabled
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	    return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}


	/**
	 * 判断当前网络是否是移动数据网络.
	 *
	 * @param context the context
	 * @return boolean
	 */
	public static boolean isMobile(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/**
	 * 导入数据库.
	 *
	 * @param context the context
	 * @param dbName the db name
	 * @param rawRes the raw res
	 * @return true, if successful
	 */
    public static boolean importDatabase(Context context,String dbName,int rawRes) {
		int buffer_size = 1024;
		InputStream is = null;
		FileOutputStream fos = null;
		boolean flag = false;

		try {
			String dbPath = "/data/data/"+context.getPackageName()+"/databases/"+dbName;
			File dbfile = new File(dbPath);
			//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
			if (!dbfile.exists()) {
				//欲导入的数据库
				if(!dbfile.getParentFile().exists()){
					dbfile.getParentFile().mkdirs();
				}
				dbfile.createNewFile();
				is = context.getResources().openRawResource(rawRes);
				fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[buffer_size];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
				   fos.write(buffer, 0, count);
				}
				fos.flush();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}
		return flag;
	}

	 /**
     * 获取屏幕尺寸与密度.
     *
     * @param context the context
     * @return mDisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        Resources mResources;
        if (context == null){
            mResources = Resources.getSystem();

        }else{
            mResources = context.getResources();
        }
        //DisplayMetrics{density=1.5, width=480, height=854, scaledDensity=1.5, xdpi=160.421, ydpi=159.497}
        //DisplayMetrics{density=2.0, width=720, height=1280, scaledDensity=2.0, xdpi=160.42105, ydpi=160.15764}
        DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
        return mDisplayMetrics;
    }

	public static void showSoftInput(Context context)
	{
		InputMethodManager inputMethodManager = (InputMethodManager)context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(0, 2);
	}

	public static void closeSoftInput(Context context)
	{
		InputMethodManager inputMethodManager = (InputMethodManager)context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if ((inputMethodManager != null) && (((Activity)context).getCurrentFocus() != null))
			inputMethodManager.hideSoftInputFromWindow(((Activity)context).getCurrentFocus()
					.getWindowToken(), 2);
	}

	public static void openKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager)mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, 2);
		imm.toggleSoftInput(2,
				1);
	}

	public static void closeKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager)mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}



	/**
     * 获取包信息.
     *
     * @param context the context
     */
    public static PackageInfo getPackageInfo(Context context) {
    	PackageInfo info = null;
	    try {
	        String packageName = context.getPackageName();
	        info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return info;
    }

	/**
     *
     * 描述：根据进程名返回应用程序.
     * @param context
     * @param processName
     * @return
     */
    public static ApplicationInfo getApplicationInfo(Context context,String processName) {
        if (processName == null) {
            return null;
        }

        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<ApplicationInfo> appList = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (ApplicationInfo appInfo : appList) {
        	if (processName.equals(appInfo.processName)) {
                return appInfo;
            }
        }
        return null;
    }

	/**
     *
     * 描述：kill进程.
     * @param context
     * @param pid
     */
    public static void killProcesses(Context context,int pid,String processName) {
    	/*String cmd = "kill -9 "+pid;
    	Process process = null;
	    DataOutputStream os = null;
    	try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	AbLogUtil.d(AbAppUtil.class, "#kill -9 "+pid);*/

    	ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    	String packageName = null;
    	try {
    		if(processName.indexOf(":")==-1){
    			packageName = processName;
		    }else{
		    	packageName = processName.split(":")[0];
		    }

			activityManager.killBackgroundProcesses(packageName);

			//
			Method forceStopPackage = activityManager.getClass().getDeclaredMethod("forceStopPackage", String.class);
			forceStopPackage.setAccessible(true);
			forceStopPackage.invoke(activityManager, packageName);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }


	/**
	 *
	 * 描述：执行命令.
	 * @param command
	 * @param workdirectory
	 * @return
	 */
	public static String runCommand(String[] command, String workdirectory){
		String result = "";
		L.I("#"+command);
		try {
			ProcessBuilder builder = new ProcessBuilder(command);
			// set working directory
			if (workdirectory != null){
				builder.directory(new File(workdirectory));
			}
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream in = process.getInputStream();
			byte[] buffer = new byte[1024];
			while(in.read(buffer)!=-1){
				String str = new String(buffer);
				result = result + str;
			}
			in.close();
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return result;
	}

	/**
	 *
	 * 描述：运行脚本.
	 * @param script
	 * @return
	 */
	public static String runScript(String script){
		String sRet = "";
		try {
			final Process m_process = Runtime.getRuntime().exec(script);
			final StringBuilder sbread = new StringBuilder();
			Thread tout = new Thread(new Runnable() {
				public void run() {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(m_process.getInputStream()),
							8192);
					String ls_1 = null;
					try {
						while ((ls_1 = bufferedReader.readLine()) != null) {
							sbread.append(ls_1).append("\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							bufferedReader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			tout.start();

			final StringBuilder sberr = new StringBuilder();
			Thread terr = new Thread(new Runnable() {
				public void run() {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(m_process.getErrorStream()),
							8192);
					String ls_1 = null;
					try {
						while ((ls_1 = bufferedReader.readLine()) != null) {
							sberr.append(ls_1).append("\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							bufferedReader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			terr.start();

			int retvalue = m_process.waitFor();
			while (tout.isAlive()) {
				Thread.sleep(50);
			}
			if (terr.isAlive())
				terr.interrupt();
			String stdout = sbread.toString();
			String stderr = sberr.toString();
			sRet = stdout + stderr;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sRet;
	}

	/*protected InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return null;
	}

	protected InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/**
	 * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
	 * @return 应用程序是/否获取Root权限
	 */
	public static boolean getRootPermission(Context context) {
		String packageCodePath = context.getPackageCodePath();
	    Process process = null;
	    DataOutputStream os = null;
	    try {
	        String cmd="chmod 777 " + packageCodePath;
	        //切换到root帐号
	        process = Runtime.getRuntime().exec("su");
	        os = new DataOutputStream(process.getOutputStream());
	        os.writeBytes(cmd + "\n");
	        os.writeBytes("exit\n");
	        os.flush();
	        process.waitFor();
	    } catch (Exception e) {
	        return false;
	    } finally {
	        try {
	            if (os != null) {
	                os.close();
	            }
	            process.destroy();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
	    return true;
	}

	/**
	 *
	 * 描述：获取进程运行的信息.
	 * @return
	 */
	public static List<String[]> getProcessRunningInfo() {
		List<String[]> processList = null;
		try {
			String result = runCommandTopN1();
			processList = parseProcessRunningInfo(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processList;
    }

	/**
	 *
	 * 描述：top -n 1.
	 * @return
	 */
	public static String runCommandTopN1() {
		String result = null;
		try {
			String[] args = {"/system/bin/top", "-n", "1"};
			result = runCommand(args, "/system/bin/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }


	/**
	 *
	 * 描述：解析数据.
	 * @param info
	 * User 39%, System 17%, IOW 3%, IRQ 0%
	 * PID    PR CPU% S   #THR     VSS     RSS    PCY    UID        Name
     * 31587  0  39%  S    14    542288K  42272K  fg   u0_a162  cn.amsoft.process
     * 313    1  17%  S    12    68620K   11328K  fg   system   /system/bin/surfaceflinger
     * 32076  1   2%  R     1    1304K    604K    bg   u0_a162  /system/bin/top
	 * @return
	 */
	public static List<String[]> parseProcessRunningInfo(String info) {
		 List<String[]> processList = new ArrayList<String[]>();
		 int Length_ProcStat = 10;
		 String tempString = "";
		 boolean bIsProcInfo = false;
		 String[] rows = null;
		 String[] columns = null;
		 rows = info.split("[\n]+");
		 // 使用正则表达式分割字符串
		 for (int i = 0; i < rows.length; i++) {
			 tempString = rows[i];
			 //AbLogUtil.d(AbAppUtil.class, tempString);
			 if (tempString.indexOf("PID") == -1) {
				 if (bIsProcInfo == true) {
					 tempString = tempString.trim();
					 columns = tempString.split("[ ]+");
					 if (columns.length == Length_ProcStat) {
						 //把/system/bin/的去掉
						 if(columns[9].startsWith("/system/bin/")){
							continue;
						 }
						 //AbLogUtil.d(AbAppUtil.class, "#"+columns[9]+",PID:"+columns[0]);
						 processList.add(columns);
					 }
				 }
			 } else {
				bIsProcInfo = true;
			 }
		}
		return processList;
	}


	/**
     *
     * 描述：获取可用内存.
     * @param context
     * @return
     */
	public static long getAvailMemory(Context context){
        //获取android当前可用内存大小
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        //当前系统可用内存 ,将获得的内存大小规格化
        return memoryInfo.availMem;
    }

	/**
	 *
	 * 描述：总内存.
	 * @param context
	 * @return
	 */
	public static long getTotalMemory(Context context){
		//系统内存信息文件
		String file = "/proc/meminfo";
		String memInfo;
		String[] strs;
		long memory = 0;

		try{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader,8192);
			//读取meminfo第一行，系统内存大小
			memInfo = bufferedReader.readLine();
			strs = memInfo.split("\\s+");
			for(String str:strs){
				L.I(str+"\t");
			}
			//获得系统总内存，单位KB
			memory = Integer.valueOf(strs[1]).intValue()*1024;
			bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Byte转位KB或MB
		return memory;
	}

	/**
	 * 获取设备UUID
	 * @param context
	 * @return
	 */
	public static String getMyUUID(Context context){
//		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		final String tmDevice, tmSerial, tmPhone, androidId;
//		tmDevice = "" + tm.getDeviceId();
//		tmSerial = "" + tm.getSimSerialNumber();
//		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
//		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
//		String uniqueId = deviceUuid.toString();
//		Log.d("debug", "uuid=" + uniqueId);

		UUID uuid = UUID.randomUUID();
		String uniqueId = uuid.toString();
		if (!Q.isEmpty(uniqueId)){
			uniqueId = uniqueId.replaceAll("-", "");
		}

		return uniqueId;
	}

	/**
	 * 获取设备DeviceId
	 * @param context
	 * @return
	 */
	public static String getMyDeviceID(Context context) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, tmPhone, androidId;
		return   "" + tm.getDeviceId();
	}
}
