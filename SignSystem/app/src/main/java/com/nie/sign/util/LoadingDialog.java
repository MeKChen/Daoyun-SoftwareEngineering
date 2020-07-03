package com.nie.sign.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Toast;


import com.nie.sign.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadingDialog {

	public static Dialog dialog;
	public static Date data;
	public static SimpleDateFormat format;
	
	public static void showToast(Context context, String msg){
		Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void showDialog(Context context){
		dialog=new Dialog(context, R.style.dialog);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.loading_dialog);
		dialog.setCanceledOnTouchOutside(false);

		dialog.show();
	}

	public static void disDialog(){
		dialog.dismiss();
	}
	
	public static String getTime(){
		data=new Date(System.currentTimeMillis());
		format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string=format.format(data);
		return string;
	}
}
