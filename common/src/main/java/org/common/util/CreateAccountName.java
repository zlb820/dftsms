package org.common.util;

import java.util.Random;

/**
 * 该类主要用于生成用户名，其主要分为以下几类：<br>
 * 1. 只包含大小写英文字母的名称<br>
 * 2. 包含大小写英文字母及数字的名称<br>
 * 3. 中文名称(不分性别)<br>
 * 4. 中文名称(区分性别)<br>
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 *
 */
public class CreateAccountName {
	public static String baseContainNum = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String baseNotContainNum = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
	private static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
	private static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

	/**
	 * 根据范围生成随机数<br>
	 * 注意：包含mix及max，例如3-5，生成的随机数包含3，4，5。
	 * 
	 * @param mix
	 * @param max
	 * @return
	 */
	public static int getNum(int mix, int max) {
		Random randomLength = new Random();
		return randomLength.nextInt(max - mix + 1) + mix;
	}

	/**
	 * 生成包含英文字母及数字的随机字符串<br>
	 * 默认返回的是5-20位以内的包含大小写字母及数字的字符串
	 * 
	 * @return
	 */
	public static String createENName_containNnum() {
		// 定义生成随机数的字符串序列
		// 生成随机字符串的长度
		// 生成随机字符串
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < getNum(5, 20); i++) {
			int number = random.nextInt(baseContainNum.length());
			sb.append(baseContainNum.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 指定范围来生成包含英文字母及数字的随机字符串<br>
	 * 范围值必须在4-20(包含4和20)<br>
	 * 当范围不合理的时候会返回null<br>
	 * 范围错误情况：<br>
	 * 1. max<min<br>
	 * 2. min<4<br>
	 * 3. max>20<br>
	 * 
	 * @param mixLength
	 * @param maxlength
	 * @return
	 */
	public static String createENName_containNnum(int mixLength, int maxlength) {
		if (maxlength < mixLength || mixLength <= 3 || maxlength > 20) {
			return null;
		}
		int length = 0;
		if (maxlength == mixLength) {
			length = maxlength;
		} else {
			length = getNum(mixLength, maxlength);
		}
		// 生成随机字符串
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseContainNum.length());
			sb.append(baseContainNum.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成包含英文字母的随机字符串<br>
	 * 默认返回的是5-20位以内的包含大小写字母及数字的字符串
	 * 
	 * @return
	 */
	public static String createENName_NotcontainNnum() {
		// 定义生成随机数的字符串序列
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// 生成随机字符串
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < getNum(5, 20); i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 指定范围来生成英文字母及数字的随机字符串<br>
	 * 范围值必须在4-20(包含4和20)<br>
	 * 当范围不合理的时候会返回null<br>
	 * 范围错误情况：<br>
	 * 1. max<min<br>
	 * 2. min<4<br>
	 * 3. max>20<br>
	 * 
	 * @param mixLength
	 * @param maxlength
	 * @return
	 */
	public static String createENName_NotcontainNnum(int mixLength, int maxlength) {
		if (maxlength < mixLength || mixLength <= 3 || maxlength > 20) {
			return null;
		}
		int length = 0;
		if (maxlength == mixLength) {
			length = maxlength;
		} else {
			length = getNum(mixLength, maxlength);
		}
		// 生成随机字符串
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 随机生成中文名称
	 * 
	 * @return
	 */
	public static String createCHNameNoSex() {
		// 获取姓名中的姓
		int index = getNum(0, firstName.length() - 1);
		String first = firstName.substring(index, index + 1);
		// 随机生成性别
		int sex = getNum(0, 1);
		String str = null;
		int length = boy.length();
		if (sex == 0) {
			str = girl;
			length = girl.length();
		} else {
			str = boy;
		}
		// 获取姓名中的第二个子
		index = getNum(0, length - 1);
		String second = str.substring(index, index + 1);

		int hasThird = getNum(0, 1);
		String third = "";
		if (hasThird == 1) {
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;
	}

	/**
	 * 随机生成中文名称
	 * 
	 * @return
	 */
	public static String createCHNameBySex(boolean isGirl) {
		// 获取姓名中的姓
		int index = getNum(0, firstName.length() - 1);
		String first = firstName.substring(index, index + 1);
		String str = null;
		int length = 0;
		if (isGirl) {
			str = girl;
			length = girl.length();
		} else {
			str = boy;
			length = boy.length();
		}
		// 获取姓名中的第二个子
		index = getNum(0, length - 1);
		String second = str.substring(index, index + 1);

		int hasThird = getNum(0, 1);
		String third = "";
		if (hasThird == 1) {
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;
	}
}
