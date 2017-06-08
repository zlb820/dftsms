package dftsms.web.fxb.util;

import java.util.Date;

import org.common.util.CreateAccountName;
import org.common.util.Result;
import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

public class UtilOfFxb<T> {
	/**
	 * 获取当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String getTimestamp() {
		return String.valueOf(new Date().getTime() / 1000);
	}

	public static Long getTimestampOfLong() {
		return new Date().getTime() / 1000;
	}

//	@Test
//	public void test() {
//		System.out.println("当前时间的时间戳：" + getTimestamp());
//	}

	/**
	 * 检查必要参数是否包含
	 * 
	 * @param arg
	 * @return
	 */
	public static ResultSimple checkNeededElementIsNull(String... arg) {
		ResultSimple result = new ResultSimple();
		for (String tmp : arg) {
			if (null == tmp || tmp.length() == 0) {
				result.setCode("xxxx03");
				result.setMessage("请求顺序错误，导致此次请求无法执行!");
				return result;
			}
		}
		result.setCode("xxxx04");
		result.setMessage("请求所需必要元素已包含，可以继续执行!");
		return result;
	}

	/**
	 * 将简单类型的返回值转换成通用类型
	 * 
	 * @param simpleResult
	 * @return
	 */
	public Result<T> checkNeededElementIsNull_returnResult(String... arg) {
		Result<T> result = new Result<T>();
		for (String tmp : arg) {
			if (null == tmp || tmp.length() == 0) {
				result.setCode("xxxx03");
				result.setMessage("请求顺序错误，导致此次请求无法执行!");
				return result;
			}
		}
		result.setCode("xxxx04");
		result.setMessage("请求所需必要元素已包含，可以继续执行!");
		return result;
	}

	/**
	 * 检查必要参数是否包含
	 * 
	 * @param arg
	 * @return
	 */
	public static ResultSimple checkParameterIsNull(String... arg) {
		ResultSimple result = new ResultSimple();
		for (String tmp : arg) {
			if (null == tmp || tmp.length() == 0) {
				result.setCode("xxxx00");
				result.setMessage("请求参数异常");
				return result;
			}
		}
		result.setCode("xxxx01");
		result.setMessage("请求必要参数审查通过");
		return result;
	}

	/**
	 * 将简单类型的返回值转换成通用类型
	 * 
	 * @param simpleResult
	 * @return
	 */
	public Result<T> checkParameterIsNull_returnResult(String... arg) {
		Result<T> result = new Result<T>();
		for (String tmp : arg) {
			if (null == tmp || tmp.length() == 0) {
				result.setCode("xxxx00");
				result.setMessage("请求参数异常");
				return result;
			}
		}
		result.setCode("xxxx01");
		result.setMessage("请求必要参数审查通过");
		return result;
	}

	// 生成姓名前参数检查
	public static ResultSimple checkAccountNameParameter(int nameGenerationStrategy, int nameMixLenth, int nameMaxLenth,
			boolean nameIsGirl) {
		if (nameGenerationStrategy < 0 || nameGenerationStrategy > 6) {
			return ResultUtil.saveResult("xxxx00", "请求参数异常!");
		}
		if (2 == nameGenerationStrategy || 4 == nameGenerationStrategy) {
			if (nameMaxLenth < nameMixLenth || nameMixLenth < 3 || nameMaxLenth > 20) {
				return ResultUtil.saveResult("xxxx00", "请求参数异常!");
			}
		}
		return ResultUtil.saveResult("xxxx01", "请求必要参数审查通过!");
	}

	/**
	 * 根据生成策略生成账户名<br>
	 * 注意：只是生成账户名，但并未检查是否重复
	 * 
	 * @param nameGenerationStrategy
	 * @param nameMixLenth
	 * @param nameMaxLenth
	 * @param nameIsGirl
	 * @return
	 */
	public static String createAccountName(int nameGenerationStrategy, int nameMixLenth, int nameMaxLenth,
			boolean nameIsGirl) {
		// 生成姓名
		String nameOfSystemGeneration;
		// 根据生成策略生成账户名
		switch (nameGenerationStrategy) {
		case 1:// 纯英文字母不包含数字（长度默认）
			nameOfSystemGeneration = CreateAccountName.createENName_NotcontainNnum();
			break;
		case 2:// 纯英文字母不包含数字（指定长度范围）
			nameOfSystemGeneration = CreateAccountName.createENName_NotcontainNnum(nameMixLenth, nameMaxLenth);
			break;
		case 3:// 纯英文字母包含数字（长度默认）
			nameOfSystemGeneration = CreateAccountName.createENName_containNnum();
			break;
		case 4:// 纯英文字母包含数字（指定长度范围）
			nameOfSystemGeneration = CreateAccountName.createENName_containNnum(nameMixLenth, nameMaxLenth);
			break;
		case 5:// 生成中文名称
			nameOfSystemGeneration = CreateAccountName.createCHNameNoSex();
			break;
		case 6:// 根据性别生成中文名称
			nameOfSystemGeneration = CreateAccountName.createCHNameBySex(nameIsGirl);
			break;
		default:
			nameOfSystemGeneration = CreateAccountName.createENName_containNnum();
			break;
		}
		return nameOfSystemGeneration;
	}
}
