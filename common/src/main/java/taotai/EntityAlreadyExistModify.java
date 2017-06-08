package taotai;

/**
 * 实体对象已存在异常(修改)
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class EntityAlreadyExistModify extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistModify() {
		super("实体对象已存在异常(修改)");
	}
}
