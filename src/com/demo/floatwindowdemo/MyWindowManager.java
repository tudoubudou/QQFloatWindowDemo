package com.demo.floatwindowdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class MyWindowManager {

	/**
	 * 灏忔偓娴獥View鐨勫疄渚�	 */
	private static FloatWindowSmallView smallWindow;

	/**
	 * 澶ф偓娴獥View鐨勫疄渚�	 */
	private static FloatWindowBigView bigWindow;

	/**
	 * 鐏鍙戝皠鍙扮殑瀹炰緥
	 */
	private static RocketLauncher rocketLauncher;

	/**
	 * 灏忔偓娴獥View鐨勫弬鏁�	 */
	private static LayoutParams smallWindowParams;

	/**
	 * 澶ф偓娴獥View鐨勫弬鏁�	 */
	private static LayoutParams bigWindowParams;

	/**
	 * 鐏鍙戝皠鍙扮殑鍙傛暟
	 */
	private static LayoutParams launcherParams;

	/**
	 * 鐢ㄤ簬鎺у埗鍦ㄥ睆骞曚笂娣诲姞鎴栫Щ闄ゆ偓娴獥
	 */
	private static WindowManager mWindowManager;

	/**
	 * 鐢ㄤ簬鑾峰彇鎵嬫満鍙敤鍐呭瓨
	 */
	private static ActivityManager mActivityManager;

	/**
	 * 鍒涘缓涓�釜灏忔偓娴獥銆傚垵濮嬩綅缃负灞忓箷鐨勫彸閮ㄤ腑闂翠綅缃�
	 */
	public static void createSmallWindow(Context context) {
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (smallWindow == null) {
			smallWindow = new FloatWindowSmallView(context);
			if (smallWindowParams == null) {
				smallWindowParams = new LayoutParams();
				smallWindowParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
				smallWindowParams.format = PixelFormat.RGBA_8888;
				smallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				smallWindowParams.width = FloatWindowSmallView.windowViewWidth;
				smallWindowParams.height = FloatWindowSmallView.windowViewHeight;
				smallWindowParams.x = screenWidth;
				smallWindowParams.y = screenHeight / 2;
			}
			smallWindow.setParams(smallWindowParams);
			windowManager.addView(smallWindow, smallWindowParams);
		}
	}

	/**
	 * 灏嗗皬鎮诞绐椾粠灞忓箷涓婄Щ闄ゃ�
	 */
	public static void removeSmallWindow(Context context) {
		if (smallWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(smallWindow);
			smallWindow = null;
		}
	}

	/**
	 * 鍒涘缓涓�釜澶ф偓娴獥銆備綅缃负灞忓箷姝ｄ腑闂淬�
	 */
	public static void createBigWindow(Context context) {
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (bigWindow == null) {
			bigWindow = new FloatWindowBigView(context);
			if (bigWindowParams == null) {
				bigWindowParams = new LayoutParams();
				bigWindowParams.x = screenWidth / 2
						- FloatWindowBigView.viewWidth / 2;
				bigWindowParams.y = screenHeight / 2
						- FloatWindowBigView.viewHeight / 2;
				bigWindowParams.type = LayoutParams.TYPE_PHONE;
				bigWindowParams.format = PixelFormat.RGBA_8888;
				bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				bigWindowParams.width = FloatWindowBigView.viewWidth;
				bigWindowParams.height = FloatWindowBigView.viewHeight;
			}
			windowManager.addView(bigWindow, bigWindowParams);
		}
	}

	/**
	 * 灏嗗ぇ鎮诞绐椾粠灞忓箷涓婄Щ闄ゃ�
	 */
	public static void removeBigWindow(Context context) {
		if (bigWindow != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(bigWindow);
			bigWindow = null;
		}
	}

	/**
	 * 鍒涘缓涓�釜鐏鍙戝皠鍙帮紝浣嶇疆涓哄睆骞曞簳閮ㄣ�
	 */
	public static void createLauncher(Context context) {
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (rocketLauncher == null) {
			rocketLauncher = new RocketLauncher(context);
			if (launcherParams == null) {
				launcherParams = new LayoutParams();
				launcherParams.x = screenWidth / 2 - RocketLauncher.width / 2;
				launcherParams.y = screenHeight - RocketLauncher.height;
				launcherParams.type = LayoutParams.TYPE_PHONE;
				launcherParams.format = PixelFormat.RGBA_8888;
				launcherParams.gravity = Gravity.LEFT | Gravity.TOP;
				launcherParams.width = RocketLauncher.width;
				launcherParams.height = RocketLauncher.height;
			}
			windowManager.addView(rocketLauncher, launcherParams);
		}
	}

	/**
	 * 灏嗙伀绠彂灏勫彴浠庡睆骞曚笂绉婚櫎銆�	 */
	public static void removeLauncher(Context context) {
		if (rocketLauncher != null) {
			WindowManager windowManager = getWindowManager(context);
			windowManager.removeView(rocketLauncher);
			rocketLauncher = null;
		}
	}

	/**
	 * 鏇存柊鐏鍙戝皠鍙扮殑鏄剧ず鐘舵�銆�	 */
	public static void updateLauncher() {
		if (rocketLauncher != null) {
			rocketLauncher.updateLauncherStatus(isReadyToLaunch());
		}
	}

	/**
	 * 鏇存柊灏忔偓娴獥鐨凾extView涓婄殑鏁版嵁锛屾樉绀哄唴瀛樹娇鐢ㄧ殑鐧惧垎姣斻�
	 * 
	 * @param context
	 *            鍙紶鍏ュ簲鐢ㄧ▼搴忎笂涓嬫枃銆�	 */
	public static void updateUsedPercent(Context context) {
		if (smallWindow != null) {
			TextView percentView = (TextView) smallWindow
					.findViewById(R.id.percent);
			percentView.setText(getUsedPercentValue(context));
		}
	}

	/**
	 * 鏄惁鏈夋偓娴獥(鍖呮嫭灏忔偓娴獥鍜屽ぇ鎮诞绐�鏄剧ず鍦ㄥ睆骞曚笂銆�	 * 
	 * @return 鏈夋偓娴獥鏄剧ず鍦ㄦ闈笂杩斿洖true锛屾病鏈夌殑璇濊繑鍥瀎alse銆�	 */
	public static boolean isWindowShowing() {
		return smallWindow != null || bigWindow != null;
	}

	/**
	 * 鍒ゆ柇灏忕伀绠槸鍚﹀噯澶囧ソ鍙戝皠浜嗐�
	 * 
	 * @return 褰撶伀绠鍙戝埌鍙戝皠鍙颁笂杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�	 */
	public static boolean isReadyToLaunch() {
		if ((smallWindowParams.x > launcherParams.x && smallWindowParams.x
				+ smallWindowParams.width < launcherParams.x
				+ launcherParams.width)
				&& (smallWindowParams.y + smallWindowParams.height > launcherParams.y)) {
			return true;
		}
		return false;
	}

	/**
	 * 濡傛灉WindowManager杩樻湭鍒涘缓锛屽垯鍒涘缓涓�釜鏂扮殑WindowManager杩斿洖銆傚惁鍒欒繑鍥炲綋鍓嶅凡鍒涘缓鐨刉indowManager銆�	 * 
	 * @param context
	 *            蹇呴』涓哄簲鐢ㄧ▼搴忕殑Context.
	 * @return WindowManager鐨勫疄渚嬶紝鐢ㄤ簬鎺у埗鍦ㄥ睆骞曚笂娣诲姞鎴栫Щ闄ゆ偓娴獥銆�	 */
	private static WindowManager getWindowManager(Context context) {
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
		}
		return mWindowManager;
	}

	/**
	 * 濡傛灉ActivityManager杩樻湭鍒涘缓锛屽垯鍒涘缓涓�釜鏂扮殑ActivityManager杩斿洖銆傚惁鍒欒繑鍥炲綋鍓嶅凡鍒涘缓鐨凙ctivityManager銆�	 * 
	 * @param context
	 *            鍙紶鍏ュ簲鐢ㄧ▼搴忎笂涓嬫枃銆�	 * @return ActivityManager鐨勫疄渚嬶紝鐢ㄤ簬鑾峰彇鎵嬫満鍙敤鍐呭瓨銆�	 */
	private static ActivityManager getActivityManager(Context context) {
		if (mActivityManager == null) {
			mActivityManager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
		}
		return mActivityManager;
	}

	/**
	 * 璁＄畻宸蹭娇鐢ㄥ唴瀛樼殑鐧惧垎姣旓紝骞惰繑鍥炪�
	 * 
	 * @param context
	 *            鍙紶鍏ュ簲鐢ㄧ▼搴忎笂涓嬫枃銆�	 * @return 宸蹭娇鐢ㄥ唴瀛樼殑鐧惧垎姣旓紝浠ュ瓧绗︿覆褰㈠紡杩斿洖銆�	 */
	public static String getUsedPercentValue(Context context) {
		String dir = "/proc/meminfo";
		try {
			FileReader fr = new FileReader(dir);
			BufferedReader br = new BufferedReader(fr, 2048);
			String memoryLine = br.readLine();
			String subMemoryLine = memoryLine.substring(memoryLine
					.indexOf("MemTotal:"));
			br.close();
			long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll(
					"\\D+", ""));
			long availableSize = getAvailableMemory(context) / 1024;
			int percent = (int) ((totalMemorySize - availableSize)
					/ (float) totalMemorySize * 100);
			return percent + "%";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "FloatingWindow";
	}

	/**
	 * 鑾峰彇褰撳墠鍙敤鍐呭瓨锛岃繑鍥炴暟鎹互瀛楄妭涓哄崟浣嶃�
	 * 
	 * @param context
	 *            鍙紶鍏ュ簲鐢ㄧ▼搴忎笂涓嬫枃銆�	 * @return 褰撳墠鍙敤鍐呭瓨銆�	 */
	private static long getAvailableMemory(Context context) {
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		getActivityManager(context).getMemoryInfo(mi);
		return mi.availMem;
	}

}
