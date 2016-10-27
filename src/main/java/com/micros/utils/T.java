/* * Copyright (C) 2013 Chengel_HaltuD
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
 * */


package com.micros.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 *
 * @ClassName: T
 * @Description: T是Toast自定义Toast
 * @Author：Chengel_HaltuD
 * @Date：2015-5-30 下午3:03:04
 * @version V1.0
 **/


public class T {

	private Context context;

	public T(Context context) {
		this.context = context;
	}

/**
	 * 默认效果*/


	public void D(String string) {
		D(string, context);
	}

	public void D(String string,Context context) {
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}

/*
*
	 * 自定义显示位置效果
*/


	public void P(String string) {
		P(string, 0, 0, context);
	}

	public void P(String string,int xOffSet,int yOffSet) {
		P(string, xOffSet, yOffSet, context);
	}

	public void P(String string,int xOffSet,int yOffSet,Context context) {
		Toast toast = Toast.makeText(context,"自定义位置Toast", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, xOffSet, yOffSet);
		toast.show();
	}
/*
*
	 * 内嵌图片效果*/



	public void I(String string,int resId) {
		I(string, resId, 0, 0, context);
	}

	public void I(String string,int resId,int xOffSet,int yOffSet) {
		I(string, resId, xOffSet, yOffSet, context);
	}

	public void I(String string,int resId ,int xOffSet,int yOffSet,Context context) {
		Toast toast = Toast.makeText(context,string, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, xOffSet, yOffSet);
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(context);
		imageCodeProject.setImageResource(resId);
		toastView.addView(imageCodeProject, 0);
		toast.show();
	}

	/**
	 * 外带图片消息
	 * @param string
	 * @param resId
	 */
	public void O(String string,int resId) {
		O(string, resId, 0, 0, context);
	}



	public void O(String string,int resId,int xOffSet,int yOffSet) {
		O(string, resId, xOffSet, yOffSet, context);
	}

	public void O(String string,int resId,int xOffSet,int yOffSet,Context context) {
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, xOffSet, yOffSet);
		View toastView = toast.getView();
		ImageView img = new ImageView(context);
		img.setImageResource(resId);
		LinearLayout ll = new LinearLayout(context);
		ll.addView(img);
		ll.addView(toastView);
		toast.setView(ll);
		toast.show();
	}

/*
*
	 * 自定义View的Toast
	 *   
*/



	public void V(String string,int resId){
		V(string, resId, null);
	}

	public void V(String string,int resId,ViewGroup root){
		//参数：R.layout.toast_xml,root
		//注释：inflate 从资源toast_xml 中 填充 部分布局
		View layout = LayoutInflater.from(context).inflate(resId,root);
		//TextView text = (TextView) layout.findViewById(R.id.text);
		//ImageView mImageView = (ImageView) layout.findViewById(R.id.iv);
		//mImageView.setBackgroundResource(R.drawable.ic_launcher);
		//text.setText(string);
		//text.setTextColor(Color.RED);
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}
/*

*
	 * 其他线程
*/


	public void Thread(String string){
		Toast.makeText(context.getApplicationContext(), string, Toast.LENGTH_SHORT).show(); ////
	}
}
