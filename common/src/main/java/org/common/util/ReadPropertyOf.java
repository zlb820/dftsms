package org.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.common.FDBK.domain.DATA.LunBoTu;
import org.common.FDBK.domain.DATA.Shangjia;
import org.common.FDBK.domain.DATA.Android.BaoKuanTuiJian;
import org.common.FDBK.domain.DATA.Android.LBT_data;
import org.common.FDBK.domain.DATA.Android.LunBoTuOfAndroid;
import org.common.FDBK.domain.DATA.Android.TuiJianShangJia;
import org.common.RBAC.domain.SysInfo;

/**
 * 读取当前系统的配置信息
 * 
 * @author fxb
 * @version 1.0
 */
public class ReadPropertyOf {
	// 处理配置信息（键值对的形式）
	private static Properties prop = new Properties();

	/**
	 * 这是一个工具类，所以禁止随意生成实例对象
	 */
	private ReadPropertyOf() {
	}

	/**
	 * 
	 * @return 返回一个包含系统配置信息的对象
	 */
	public static SysInfo loadPropertiesOfSys() {
		SysInfo sysInfo;
		sysInfo = new SysInfo();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("sys.properties");
			prop.load(in);
			sysInfo.setServerAddress(prop.getProperty("serverAddress"));
			sysInfo.setServerPort(prop.getProperty("serverPort", "8080"));
			in.close();
		} catch (IOException e) {
		}

		return sysInfo;
	}

	public static List<LunBoTu> loadPropertiesOfLunBoTu() {
		SysInfo sysInfo = loadPropertiesOfSys();
		String forword = "http://" + sysInfo.getServerAddress() + ":" + sysInfo.getServerPort();
		List<LunBoTu> result = new ArrayList<>();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("indexData.properties");
			prop.load(in);
			for (int i = 1; i < 6; i++) {
				LunBoTu tmp = new LunBoTu();
				tmp.setPicURL(forword + prop.getProperty("lbt" + i + ".picURL"));
				tmp.setStoreID(prop.getProperty("lbt" + i + ".storeID"));
				result.add(tmp);
			}
			in.close();
		} catch (IOException e) {
		}
		return result;
	}

	public static LunBoTuOfAndroid loadPropertiesOfLunBoTu_Android() {
		SysInfo sysInfo = loadPropertiesOfSys();
		// String forword = "http://" + sysInfo.getServerAddress() + ":" +
		// sysInfo.getServerPort();
		String forword = "http://" + sysInfo.getServerAddress();
		LunBoTuOfAndroid result = new LunBoTuOfAndroid();
		List<LBT_data> list = new ArrayList<>();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("indexData.properties");
			prop.load(in);
			for (int i = 1; i < 6; i++) {
				LBT_data tmp = new LBT_data();
				tmp.setPicURL(forword + prop.getProperty("lbt" + i + ".picURL"));
				tmp.setStoreID(prop.getProperty("lbt" + i + ".storeID"));
				list.add(tmp);
			}
			result.setLbt(list);
			in.close();
		} catch (IOException e) {
		}
		return result;
	}

	public static List<Shangjia> loadPropertiesOfBaoKuan() {
		SysInfo sysInfo = loadPropertiesOfSys();
		String forword = "http://" + sysInfo.getServerAddress() + ":" + sysInfo.getServerPort();
		List<Shangjia> result = new ArrayList<>();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("indexData.properties");
			prop.load(in);
			for (int i = 1; i < 6; i++) {
				Shangjia tmp = new Shangjia();
				tmp.setPicURL(forword + prop.getProperty("bktj" + i + ".picURL"));
				tmp.setStoreID(prop.getProperty("bktj" + i + ".storeID"));
				tmp.setStoreName(prop.getProperty("bktj" + i + ".storeName"));
				result.add(tmp);
			}
			in.close();
		} catch (IOException e) {
		}
		return result;
	}

	public static BaoKuanTuiJian loadPropertiesOfBaoKuan_Android() {
		SysInfo sysInfo = loadPropertiesOfSys();
		// String forword = "http://" + sysInfo.getServerAddress() + ":" +
		// sysInfo.getServerPort();
		String forword = "http://" + sysInfo.getServerAddress();
		BaoKuanTuiJian result = new BaoKuanTuiJian();
		List<Shangjia> list = new ArrayList<>();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("indexData.properties");
			prop.load(in);
			for (int i = 1; i < 6; i++) {
				Shangjia tmp = new Shangjia();
				tmp.setPicURL(forword + prop.getProperty("bktj" + i + ".picURL"));
				tmp.setStoreID(prop.getProperty("bktj" + i + ".storeID"));
				tmp.setStoreName(prop.getProperty("bktj" + i + ".storeName"));
				list.add(tmp);
			}
			result.setBktj(list);
			in.close();
		} catch (IOException e) {
		}
		return result;
	}

	public static List<Shangjia> loadPropertiesOfJingPin() {
		SysInfo sysInfo = loadPropertiesOfSys();
		String forword = "http://" + sysInfo.getServerAddress() + ":" + sysInfo.getServerPort();
		List<Shangjia> result = new ArrayList<>();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("indexData.properties");
			prop.load(in);
			for (int i = 1; i < 4; i++) {
				Shangjia tmp = new Shangjia();
				tmp.setPicURL(forword + prop.getProperty("jpsj" + i + ".picURL"));
				tmp.setStoreID(prop.getProperty("jpsj" + i + ".storeID"));
				tmp.setStoreName(prop.getProperty("jpsj" + i + ".storeName"));
				result.add(tmp);
			}
			in.close();
		} catch (IOException e) {
		}
		return result;
	}

	public static TuiJianShangJia loadPropertiesOfJingPin_Android() {
		SysInfo sysInfo = loadPropertiesOfSys();
		// String forword = "http://" + sysInfo.getServerAddress() + ":" +
		// sysInfo.getServerPort();
		String forword = "http://" + sysInfo.getServerAddress();
		TuiJianShangJia result = new TuiJianShangJia();
		List<Shangjia> list = new ArrayList<>();
		try {
			InputStream in = ReadPropertyOf.class.getClassLoader().getResourceAsStream("indexData.properties");
			prop.load(in);
			for (int i = 1; i < 4; i++) {
				Shangjia tmp = new Shangjia();
				tmp.setPicURL(forword + prop.getProperty("jpsj" + i + ".picURL"));
				tmp.setStoreID(prop.getProperty("jpsj" + i + ".storeID"));
				tmp.setStoreName(prop.getProperty("jpsj" + i + ".storeName"));
				list.add(tmp);
			}
			result.setTjsj(list);
			in.close();
		} catch (IOException e) {
		}
		return result;
	}
}
