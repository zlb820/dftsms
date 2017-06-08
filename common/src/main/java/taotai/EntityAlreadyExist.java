package taotai;

/**
 * 实体对象已存在异常
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class EntityAlreadyExist extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExist() {
		super("实体对象已存在异常");
	}
}
