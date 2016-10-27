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

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @ClassName: Q
 * @Description: StringUtils字符串工具类
 * @Author：Chengel_HaltuD
 * @Date：2015-8-29 下午2:24:27
 * @version V1.0
 *
 */
public class Q
{
	/**
	 * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
	 * 
	 * @param str 无逗号的数字
	 * <a href="http://home.51cto.com/index.php?s=/space/34010" target="_blank">@return</a> 加上逗号的数字
	 */
	public static String addComma(String str) {
		// 将传进数字反转
		String reverseStr = new StringBuilder(str).reverse().toString();
		String strTemp = "";
		for (int i=0; i<reverseStr.length(); i++) { 
			if (i*3+3 > reverseStr.length()){ 
				strTemp += reverseStr.substring(i*3,reverseStr.length()); 
				break; 
			} 
			strTemp += reverseStr.substring(i*3, i*3+3)+","; 
		}
		// 将[789,456,] 中最后一个[,]去除
		if (strTemp.endsWith(",")) { 
			strTemp = strTemp.substring(0, strTemp.length()-1); 
		}
		// 将数字重新反转
		String resultStr = new StringBuilder(strTemp).reverse().toString();        
		return resultStr;
	}
	/**
	 * 将每四个数字加上逗号处理（通常使用金额方面的编辑）
	 *
	 * @param str 无逗号的数字
	 * <a href="http://home.51cto.com/index.php?s=/space/34010" target="_blank">@return</a> 加上逗号的数字
	 */
	public static String subStringFour(String str) {
		// 将传进数字反转
		String reverseStr = new StringBuilder(str).reverse().toString();
		String strTemp = "";
		for (int i=0; i<reverseStr.length(); i++) {
			if (i*4+4 > reverseStr.length()){
				strTemp += reverseStr.substring(i*4,reverseStr.length());
				break;
			}
			strTemp += reverseStr.substring(i*4, i*4+4)+",";
		}
		// 将[789,456,] 中最后一个[,]去除
		if (strTemp.endsWith(",")) {
			strTemp = strTemp.substring(0, strTemp.length()-1);
		}
		// 将数字重新反转
		String resultStr = new StringBuilder(strTemp).reverse().toString();
		return resultStr;
	}

	/**
	 * 
	 * @Title: SeparateMobile 
	 * @Description: 电话号中间横杠分隔 
	 * @param mobile
	 * @return
	 * @return String
	 *
	 */
	public static String SeparateMobile(String mobile)
	{
		if (TextUtils.isEmpty(mobile)) {
			return mobile;
		}

		if (mobile.length() == 11) {
			return mobile.substring(0, 3) + "-" + mobile.substring(3, 7) + "-" + mobile.substring(7, mobile.length());
		}
		return mobile;
	}

	/**
	 * 
	 * @Title: hiddenMobile 
	 * @Description: 电话号隐藏中间四位 
	 * @param mobile
	 * @return
	 * @return String
	 *
	 */
	public static String hiddenMobile(String mobile)
	{
		if (TextUtils.isEmpty(mobile)) {
			return mobile;
		}

		if (mobile.length() == 11) {
			return mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
		}
		return mobile;
	}

	/**
	 * 
	 * @Title: isText 
	 * @Description: 判断是数字，字母，还是汉字 
	 * @param text
	 * @return
	 * @return String
	 *
	 */
	public static String isText(String text)
	{
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(text);
		if (m.matches())
		{
			return "SHUZI";
		}
		p = Pattern.compile("[a-zA-Z]");
		m = p.matcher(text);
		if (m.matches())
		{
			return "ZIMU";
		}
		p = Pattern.compile("[\u4e00-\u9fa5]");
		m = p.matcher(text);
		if (m.matches())
		{
			return "HANZI";
		}
		return "";
	}

	/**
	 * 
	 * @Title: isAllText 
	 * @Description: 是否全是字符串 
	 * @param text
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isAllText(String text) {
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			boolean isValid = ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (
					(c >= '0') && (
							c <= '9'));
			if (!isValid)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: isAllChinese 
	 * @Description: 是否全是汉字 
	 * @param text
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isAllChinese(String text)
	{
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

		for (int i = 0; i < text.length(); i++) {
			Matcher m = p.matcher(String.valueOf(text.charAt(i)));
			if (!m.matches()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 尾数去零
	 * @param obj
	 * @return
	 * @return boolean
	 *
	 */
	public static String subZeroAndDot(String s)
	{
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");
			s = s.replaceAll("[.]$", "");
		}
		return s;
	}

	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 比较多个字符串
	 * @param obj
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isEquals(String[] agrs)
	{
		String last = null;
		for (int i = 0; i < agrs.length; i++) {
			String str = agrs[i];
			if (isEmpty(str)) {
				return false;
			}
			if ((last != null) && (!str.equalsIgnoreCase(last))) {
				return false;
			}
			last = str;
		}
		return true;
	}

	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 判断字符串是否为空 
	 * @param obj
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isEmpty(String value)
	{
		if ((value != null) && (!"".equalsIgnoreCase(value.trim())) && (!"null".equalsIgnoreCase(value.trim()))) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 多个字符串是否为空 
	 * @param obj
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isEmpty(String[] value)
	{
		int i = 0; if (i < value.length)
		{
			if ((value != null) && (!"".equalsIgnoreCase(value[i].trim())) && (!"null".equalsIgnoreCase(value[i].trim()))) {
				return false;
			}
			return true;
		}

		return true;
	}

	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 判断对象是否为空 
	 * @param obj
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isEmpty(Object obj)
	{
		if (obj == null)
			return true;
		if ((obj instanceof CharSequence)) {
			CharSequence str = (CharSequence)obj;
			if ((str == null) || ("".equals(str)) || ("null".equals(str)))
				return true;
		}
		else if ((obj instanceof TextView)) {
			TextView txt = (TextView)obj;
			if ((txt == null) || ("".equals(txt.getText()))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @Title: isEmpty 
	 * @Description: 多个对象是否有空 
	 * @param obj
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isEmpty(Object[] obj)
	{
		for (int i = 0; i < obj.length; i++)
		{
			if (obj[i] == null)
				return true;
			if ((obj[i] instanceof CharSequence)) {
				CharSequence str = (CharSequence)obj[i];
				if ((str == null) || ("".equals(str)) || ("null".equals(str)))
					return true;
			}
			else if ((obj[i] instanceof TextView)) {
				TextView txt = (TextView)obj[i];
				if ((txt == null) || ("".equals(txt.getText()))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 返回一个高亮spannable
	 * @param content 文本内容
	 * @param color   高亮颜色
	 * @param start   起始位置
	 * @param end     结束位置
	 * @return 高亮spannable
	 */
	public static CharSequence getHighLightText(String content, int color, int start, int end) {
		if (TextUtils.isEmpty(content)) {
			return "";
		}
		start = start >= 0 ? start : 0;
		end = end <= content.length() ? end : content.length();
		SpannableString spannable = new SpannableString(content);
		CharacterStyle span = new ForegroundColorSpan(color);
		spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannable;
	}

	/**
	 * 获取链接样式的字符串，即字符串下面有下划线
	 * @param resId 文字资源
	 * @return 返回链接样式的字符串
	 */
	public static Spanned getHtmlStyleString(Context context, int resId) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"\"><u><b>").append(context.getResources().getString(resId)).append(" </b></u></a>");
		return Html.fromHtml(sb.toString());
	}

	/** 格式化文件大小，不保留末尾的0 */
	public static String formatFileSize(long len) {
		return formatFileSize(len, false);
	}

	/** 格式化文件大小，保留末尾的0，达到长度一致 */
	public static String formatFileSize(long len, boolean keepZero) {
		String size;
		DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
		DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
		if (len < 1024) {
			size = String.valueOf(len + "B");
		} else if (len < 10 * 1024) {
			// [0, 10KB)，保留两位小数
			size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
		} else if (len < 100 * 1024) {
			// [10KB, 100KB)，保留一位小数
			size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
		} else if (len < 1024 * 1024) {
			// [100KB, 1MB)，个位四舍五入
			size = String.valueOf(len / 1024) + "KB";
		} else if (len < 10 * 1024 * 1024) {
			// [1MB, 10MB)，保留两位小数
			if (keepZero) {
				size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float) 100)) + "MB";
			} else {
				size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
			}
		} else if (len < 100 * 1024 * 1024) {
			// [10MB, 100MB)，保留一位小数
			if (keepZero) {
				size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float) 10)) + "MB";
			} else {
				size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
			}
		} else if (len < 1024 * 1024 * 1024) {
			// [100MB, 1GB)，个位四舍五入
			size = String.valueOf(len / 1024 / 1024) + "MB";
		} else {
			// [1GB, ...)，保留两位小数
			size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
		}
		return size;
	}
}
