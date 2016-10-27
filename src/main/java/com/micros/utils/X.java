/*
 * Copyright (C) 2012 Chengel_HaltuD
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

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: X
 * @Description: VerifyCheck 描述：验证类
 * @Author：Chengel_HaltuD
 * @Date：2015-8-29 下午2:26:17
 * @version V1.0
 *
 */
public class X
{

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
		String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles)) return false;
		else return mobiles.matches(telRegex);
	}

	private static int[] idsArray = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/**
	 * 
	 * @Title: isCarBrand 
	 * @Description: 验证车牌号 
	 * @param carBrand
	 * @return
	 * @return boolean
	 *
	 */
	public static boolean isCarBrand(String carBrand)
	{
		if ((carBrand == null) || ("".equals(carBrand.trim()))) {
			return false;
		}
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$");
		Matcher m = p.matcher(carBrand);
		return m.matches();
	}

	/**
	 * 账号是否验证通过
	 * 支持2-16位英文�?中文、数字�?下划线�?�?
	 * @return
	 */
	public static boolean isAccountVerify(String account){
		
		if(account == null || "".equals(account.trim())) {
			return false;
		}else{
			String accountTrim = account.trim();
			Pattern patternAccount = Pattern.compile("^([a-zA-Z0-9_.\u4e00-\u9fa5]{2,16})+$");
			Matcher matcherAccount = patternAccount.matcher(accountTrim);
			if (!matcherAccount.matches()) {
				return false;
			}else{
				return true;
			}
		}
	}

	/**
	 * 验证手机号码的格式是否正�?
	 * @param mobileString
	 * @return
	 */
	public static  boolean isMobilePhoneVerify(String mobileString){
		if (mobileString == null || "".equals(mobileString.trim())) {//如果是null或""直接返回
			return false;
		}else{
			String mobileTrim = mobileString.trim();//去空格
			Pattern patternMobile = Pattern.compile("^1[3|5|4|7|8][0-9]{9}$");//正则表达式
			Matcher matcherMobile = patternMobile.matcher(mobileTrim);
			if(!matcherMobile.matches()){//true means match 
				return false;
			}else{
				return true;
			}
		}
	}

	/**
	 * 验证手机号码的格式是否正�?
	 * @param mobileString
	 * @return
	 */
	public static boolean isMobileNo(String mobile)
	{
		if (TextUtils.isEmpty(mobile)) {
			return false;
		}
		Pattern p = Pattern.compile("1[3|4|5|6|8|9]\\d{9}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 验证邮箱格式是否正确
	 * @param emailString
	 * @return
	 */
	public static boolean isEmailVerify(String emailString){
		if(emailString == null || "".equals(emailString.trim())){
			return false;
		}else{
			String emailTrim = emailString.trim();
			Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$");
			Matcher matcherEmail = patternEmail.matcher(emailTrim);
			if (!matcherEmail.matches()) {
				return false;
			}else{
				return true;
			}
		}
	}

	/**
	 * 验证真是姓名的格式是否正确，是否是中�?
	 * @param realitynameString
	 * @return
	 */
	public static boolean isRealnameVerify(String realitynameString){
		if (realitynameString == null || "".equals(realitynameString.trim())) {
			return false;
		}else{
			String realitynameTrim = realitynameString.trim();
			Pattern patternRealityname = Pattern.compile("[\u4e00-\u9fa5]{2,10}");// 2~10个中文汉�?
			Matcher matcherRealityname = patternRealityname.matcher(realitynameTrim);
			if (!matcherRealityname.matches()) {
				return false;
			}else{
				return true;
			}
		}
	}

	/**
	 * 验证身份证的号码是否是格式正确的
	 * @param idCardNumber
	 * @return
	 */
	public static boolean isIDCardVerify(String idCardNumber){
		if(null == idCardNumber){
			return false;
		}
		
		if("".equals(idCardNumber.trim())){
			return false;
		}
		
		String idCardNumberTrim = idCardNumber.trim();
		String idPattern = "^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X|x)?$";
		Pattern patternId = Pattern.compile(idPattern);//身份证号�?
		Matcher matcherId = patternId.matcher(idCardNumberTrim);
		if (!matcherId.matches()) {
			return false;
		}
		
		//下面是验�?8位身份证的最后一位字�?数字是否正确
		int temp  = 0;
		if(idCardNumberTrim.length() == 18){
			char[] idArray = idCardNumberTrim.toCharArray();
			
			for (int i = 0; i < idArray.length - 1; i++) {
				String valueOf = String.valueOf(idArray[i]);
				int parseInt = Integer.parseInt(valueOf);
				temp += parseInt * idsArray[i];
			}
			int temp2 = temp % 11;
			String lastChar  = "";
			switch (temp2) {
			case 0:
				lastChar = "1";
				break;
			case 1:
				lastChar = "0";
				break;
			case 2:
				lastChar = "X";
				break;
			case 3:
				lastChar = "9";
				break;
			case 4:
				lastChar = "8";
				break;
			case 5:
				lastChar = "7";
				break;
			case 6:
				lastChar = "6";
				break;
			case 7:
				lastChar = "5";
				break;
			case 8:
				lastChar = "4";
				break;
			case 9:
				lastChar = "3";
				break;
			case 10:
				lastChar = "2";
				break;
			}
			char charAtLast = idCardNumberTrim.charAt(17);
			if(!(""+charAtLast).equalsIgnoreCase(lastChar)){
				return false;
			}
		}
		//以上条件都不符合才会返回true
		return true;
	}

	/**
	 * 验证密码的强度
	 *  1.如果密码少于5位,那么就认为这是一个弱密码.
	 *	2.如果密码只由数字、小写字母、大写字母或其它特殊符号当中的一种组成,则认为这是一个弱密码.
	 *	3.如果密码由数字、小写字母、大写字母或其它特殊符号当中的两种组成,则认为这是一个中度安全的密码.
	 *	4.如果密码由数字、小写字母、大写字母或其它特殊符号当中的三种以上组成,则认为这是一个比较安全的密码.
	 * @return
	 */
	public static String judgePassLevel(String string){
		String str1 = "弱";
		String str2 = "中";
		String str3 = "强";
		if(string.length()<5){
			return str1;
		}

		int num = judgePassNum(string);
		if(num <= 0||num == 1){
			return str1;
		}else if(num == 2){
			return str2;
		}else{
			return str3;
		}

	}

	public static int judgePassNum(String string){
		String str1 = "^[0-9]$";
		String str2 = "^[a-z]$";
		String str3 = "^[A-Z]$";
		String str4 = "^[^0-9a-zA-Z]$";
		Pattern pattern1 = Pattern.compile(str1);
		Pattern pattern2 = Pattern.compile(str2);
		Pattern pattern3 = Pattern.compile(str3);
		Pattern pattern4 = Pattern.compile(str4);

		List<Integer> list = new ArrayList<Integer>();

		String[] split = string.split("");
		for (int i = 1; i < split.length; i++) {
			if(pattern1.matcher(split[i]).matches()){
				if(!list.contains(1)){
					list.add(1);
				}
			}
			if(pattern2.matcher(split[i]).matches()){
				if(!list.contains(2)){
					list.add(2);
				}
			}
			if(pattern3.matcher(split[i]).matches()){
				if(!list.contains(3)){
					list.add(3);
				}
			}
			if(pattern4.matcher(split[i]).matches()){
				if(!list.contains(4)){
					list.add(4);
				}
			}
		}
		return list.size();
	}
}