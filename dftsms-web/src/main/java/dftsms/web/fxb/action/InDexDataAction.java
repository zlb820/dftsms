package dftsms.web.fxb.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.common.FDBK.domain.Comment;
import org.common.FDBK.domain.DATA.IndexData;
import org.common.FDBK.domain.DATA.ReturnData;
import org.common.FDBK.domain.DATA.ShopSimpleMSG;
import org.common.FDBK.domain.DATA.Android.AndroidIndexData;
import org.common.FDBK.domain.DATA.Android.ContainAllIndex;
import org.common.FDBK.domain.DATA.Android.DataToReturn;
import org.common.FDBK.domain.simple.CollectionSimpleMSG;
import org.common.FDBK.domain.simple.CommentSimple;
import org.common.RBAC.domain.simple.AccountSimple;
import org.common.util.ReadPropertyOf;
import org.common.util.ResultSimple;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import dftsms.web.fxb.action.base.InDexDataBaseAction;
import dftsms.web.fxb.util.UtilOfFxb;

public class InDexDataAction extends InDexDataBaseAction {
	private static final long serialVersionUID = 1L;
	ReturnData<ShopSimpleMSG> resultData;// 返回数据格式
	IndexData<ShopSimpleMSG> resultIndex;// 带上半部分的数据
	AndroidIndexData<List<ShopSimpleMSG>> resultAndroidData;// 返回Android数据
	AndroidIndexData<ContainAllIndex> resultAndroidDataAll;// 返回Android数据
	ResultSimple writeComment;
	ResultSimple collection;
	ResultSimple collectionIsSave;
	DataToReturn<List<CommentSimple>> resultComment_x;
	List<CommentSimple> resultComment;
	DataToReturn<List<CollectionSimpleMSG>> resultCollection_x;
	List<CollectionSimpleMSG> resultCollection;

	private String areacountyCode;// 所筛选的店铺所在县 Code
	private int pagestamp;// 请求页数
	private int pageCount;// 总页数
	private int numOfShow;// 单页显示数量
	private int rule;// 排序规则
	private double x;// x坐标
	private double y;// Y坐标
	private String shopNodeStyleId;
	private String storeID;
	private String accountID;
	private Float level;
	private String comment;
	private String sort;
	private String goodName;
	private String collectionId;

	public String checkCollectionIsSave() {
		collectionIsSave = UtilOfFxb.checkParameterIsNull(accountID, storeID);
		if ("xxxx01".equals(collectionIsSave.getCode())) {
			collectionIsSave = infoManage.checkCollectionIsSave(accountID, storeID);
		}
		return Action.SUCCESS;
	}

	/**
	 * 收藏店铺
	 */
	public String saveCollection() {
		collection = UtilOfFxb.checkParameterIsNull(accountID, storeID);
		System.out.println(collection);
		System.out.println(collection.getCode()+collection.getMessage());
		if ("xxxx01".equals(collection.getCode())) {
			collection = infoManage.saveCollection(accountID, storeID);
		}
		return Action.SUCCESS;
	}

	public String deleteCollection() {
		collection = UtilOfFxb.checkParameterIsNull(collectionId);
		if ("xxxx01".equals(collection.getCode())) {
			collection = infoManage.delCollection(collectionId);
		}
		return Action.SUCCESS;
	}

	public String showAllCollection() {
		resultCollection = infoManage.getAllCollection(accountID);
		resultCollection_x = new DataToReturn<>();
		resultCollection_x.setPageItems(resultCollection);
		return Action.SUCCESS;
	}

	/**
	 * 保存用户评论
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String saveComment() {
		UtilOfFxb<AccountSimple> utilOfCheck = new UtilOfFxb<>();
		writeComment = utilOfCheck.checkParameterIsNull(storeID, accountID, comment);
		if ("xxxx01".equals(writeComment.getCode())) {
			if (level >= 0 && level <= 5) {
				writeComment = infoManage.writeComment(storeID, accountID, comment, level);
			}
		}
		return Action.SUCCESS;
	}

	/**
	 * 获取商家的所有评论
	 * 
	 * @return
	 */
	public String getStoreAllComment() {
		List<Comment> tmp = infoManage.getStoreAllComment(storeID);
		Iterator<Comment> it = tmp.iterator();
		resultComment = new ArrayList<>();
		while (it.hasNext()) {
			Comment temp = it.next();
			CommentSimple simple = new CommentSimple();
			simple.setAccountID(temp.getAccount().getId());
			simple.setAccountName(temp.getAccount().getName());
			simple.setAccountPic(temp.getAccount().getPicture().getPic_url());
			simple.setComment(temp.getContent());
			simple.setLevel(temp.getLevel());
			simple.setStoreID(temp.getStore().getId());
			simple.setStoreName(temp.getStore().getName());
			simple.setStyleId("52");
			simple.setTime(temp.getTime());
			simple.setStorePic(temp.getStore().getPicture().getPic_url());
			resultComment.add(simple);
		}
		resultComment_x = new DataToReturn<>();
		resultComment_x.setPageItems(resultComment);
		return Action.SUCCESS;
	}

	/**
	 * 获取用户的所有评论
	 * 
	 * @return
	 */
	public String getAccountAllComment() {
		List<Comment> tmp = infoManage.getCustomerAllComment(accountID);
		Iterator<Comment> it = tmp.iterator();
		resultComment = new ArrayList<>();
		while (it.hasNext()) {
			Comment temp = it.next();
			CommentSimple simple = new CommentSimple();
			simple.setAccountID(temp.getAccount().getId());
			simple.setAccountName(temp.getAccount().getName());
			simple.setAccountPic(temp.getAccount().getPicture().getPic_url());
			simple.setComment(temp.getContent());
			simple.setLevel(temp.getLevel());
			simple.setStoreID(temp.getStore().getId());
			simple.setStoreName(temp.getStore().getName());
			simple.setStyleId("53");
			simple.setTime(temp.getTime());
			resultComment.add(simple);
		}
		resultComment_x = new DataToReturn<>();
		resultComment_x.setPageItems(resultComment);
		return Action.SUCCESS;
	}

	/**
	 * 根据分类获取数据
	 * 
	 * @return
	 */
	public String AndroidDataBySort() {
		List<ShopSimpleMSG> listIndex = infoManage.getIndexDataBySort(x, y, areacountyCode, shopNodeStyleId, sort);
		resultAndroidData = new AndroidIndexData<>();
		resultAndroidData.setPageItems(listIndex);
		return Action.SUCCESS;
	}

	/**
	 * 根据商品名称获取数据
	 * 
	 * @return
	 */
	public String AndroidDataByGoodName() {
		List<ShopSimpleMSG> listIndex = infoManage.getIndexDataByGoodName(x, y, areacountyCode, shopNodeStyleId,
				goodName);
		resultAndroidData = new AndroidIndexData<>();
		resultAndroidData.setPageItems(listIndex);
		return Action.SUCCESS;
	}

	public String AndroidData() {
		ActionContext atx = ActionContext.getContext();
		Map<String, Object> session = atx.getSession();
		String saveAccountID = (String) session.get("fxb_AccountID");
		boolean flag = false;
		if (null != saveAccountID && saveAccountID.length() != 0) {
			flag = true;
		}
		if (0 == pagestamp) {// 如果是首页
			resultAndroidDataAll = new AndroidIndexData<>();
			ContainAllIndex index = new ContainAllIndex();
			index.setA_lbt(ReadPropertyOf.loadPropertiesOfLunBoTu_Android());
			index.setB_bktj(ReadPropertyOf.loadPropertiesOfBaoKuan_Android());
			index.setC_tjsj(ReadPropertyOf.loadPropertiesOfJingPin_Android());
			// index.setD_shopNode(listIndex);
			resultAndroidDataAll.setPageItems(index);
			resultAndroidDataAll.setPagestamp(pagestamp + 1);
			resultAndroidDataAll.setLogin(flag);
			return Action.SUCCESS;
		} else {
			List<ShopSimpleMSG> listIndex = infoManage.getIndexData(x, y, areacountyCode, pagestamp, numOfShow, rule,
					shopNodeStyleId);
			List<ShopSimpleMSG> listIndex_temp= infoManage.getIndexData(x, y, areacountyCode, pagestamp*numOfShow + 1, 1, rule, shopNodeStyleId);
			boolean flagIsNotNull =false;;
			if(listIndex_temp.isEmpty()){
				flagIsNotNull=true;
			}else{
				flagIsNotNull=false;
			}
			
			resultAndroidData = new AndroidIndexData<>();
			// OnlyStoreData index = new OnlyStoreData();
			// index.setShopNode(listIndex);
			// resultAndroidData.setPageItems(index);
			resultAndroidData.setPageItems(listIndex);
			if (!flagIsNotNull) {
				resultAndroidData.setPagestamp(pagestamp + 1);
			} else {
				resultAndroidData.setPagestamp(0);
			}
			resultAndroidData.setLogin(flag);
			return "NotAll";
		}
	}

	public String execute() {
		resultData = new ReturnData<>();
		if (pagestamp < 1 || numOfShow < 1) {
			resultData.setCode("xxxx00");
			resultData.setMsg("请求参数异常");
			return Action.SUCCESS;
		}
		if (1 == pagestamp) {
			// TODO 验证县Code是否存在

			// 获取商家的总数量
			Long numOfAll = infoManage.getStoreByArea(areacountyCode);
			// 分页
			pageCount = numOfAll.intValue() / numOfShow + 1;
			resultData.setPageCount(pageCount);// 保存总页数
			if (rule < 1 || rule > 6) {// 保存数据排序规则
				resultData.setOrderingRule(0);
			} else {
				resultData.setOrderingRule(rule);
			}
			resultData.setOnePageNum(numOfShow);// 保存单页显示数量(是分页时显示的数量)
			resultData.setCurrentPage(1);// 保存当前页数
			resultData.setData(infoManage.getIndexData(x, y, areacountyCode, 1, numOfShow, rule, shopNodeStyleId));
			resultData.setCode("000800");
			resultData.setMsg("数据反馈成功!");
			if (0 == pagestamp) {// 是否是首页
				resultIndex = new IndexData<>();
				resultIndex.setPageCount(resultData.getPageCount());
				resultIndex.setOrderingRule(resultData.getOrderingRule());
				resultIndex.setOnePageNum(resultData.getOnePageNum());
				resultIndex.setCurrentPage(resultData.getCurrentPage());
				resultIndex.setData(resultData.getData());
				resultIndex.setCode("00080022222");
				resultIndex.setMsg("数据反馈成功!");
				resultIndex.setLunBoTu(ReadPropertyOf.loadPropertiesOfLunBoTu());
				resultIndex.setBaokuan(ReadPropertyOf.loadPropertiesOfBaoKuan());
				resultIndex.setPinpai(ReadPropertyOf.loadPropertiesOfJingPin());
				return "indexresult";
			}
		} else {
			if (pagestamp > pageCount) {
				resultData.setCode("xxxx00");
				resultData.setMsg("请求参数异常");
				return Action.SUCCESS;
			}
			resultData.setPageCount(pageCount);// 保存总页数
			if (rule < 1 || rule > 6) {// 保存数据排序规则
				resultData.setOrderingRule(0);
			} else {
				resultData.setOrderingRule(rule);
			}
			resultData.setOnePageNum(numOfShow);// 保存单页显示数量(是分页时显示的数量)
			resultData.setCurrentPage(pagestamp);// 保存当前页数
			resultData.setData(infoManage.getIndexData(x, y, areacountyCode, numOfShow * (pagestamp - 1) + 1, numOfShow,
					rule, shopNodeStyleId));
			resultData.setCode("000800");
			resultData.setMsg("数据反馈成功!");
		}

		return Action.SUCCESS;
	}

	public IndexData<ShopSimpleMSG> getResultIndex() {
		return resultIndex;
	}

	public void setResultIndex(IndexData<ShopSimpleMSG> resultIndex) {
		this.resultIndex = resultIndex;
	}

	public ReturnData<ShopSimpleMSG> getResultData() {
		return resultData;
	}

	public void setResultData(ReturnData<ShopSimpleMSG> resultData) {
		this.resultData = resultData;
	}

	public String getAreacountyCode() {
		return areacountyCode;
	}

	public void setAreacountyCode(String areacountyCode) {
		this.areacountyCode = areacountyCode;
	}

	public int getPagestamp() {
		return pagestamp;
	}

	public void setPagestamp(int pagestamp) {
		this.pagestamp = pagestamp;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getNumOfShow() {
		return numOfShow;
	}

	public void setNumOfShow(int numOfShow) {
		this.numOfShow = numOfShow;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	@JSON(serialize = false)
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	@JSON(serialize = false)
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public AndroidIndexData<List<ShopSimpleMSG>> getResultAndroidData() {
		return resultAndroidData;
	}

	public void setResultAndroidData(AndroidIndexData<List<ShopSimpleMSG>> resultAndroidData) {
		this.resultAndroidData = resultAndroidData;
	}

	public AndroidIndexData<ContainAllIndex> getResultAndroidDataAll() {
		return resultAndroidDataAll;
	}

	public void setResultAndroidDataAll(AndroidIndexData<ContainAllIndex> resultAndroidDataAll) {
		this.resultAndroidDataAll = resultAndroidDataAll;
	}

	public String getShopNodeStyleId() {
		return shopNodeStyleId;
	}

	public void setShopNodeStyleId(String shopNodeStyleId) {
		this.shopNodeStyleId = shopNodeStyleId;
	}

	public ResultSimple getWriteComment() {
		return writeComment;
	}

	public void setWriteComment(ResultSimple writeComment) {
		this.writeComment = writeComment;
	}

	public List<CommentSimple> getResultComment() {
		return resultComment;
	}

	public void setResultComment(List<CommentSimple> resultComment) {
		this.resultComment = resultComment;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public Float getLevel() {
		return level;
	}

	public void setLevel(Float level) {
		this.level = level;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public ResultSimple getCollection() {
		return collection;
	}

	public void setCollection(ResultSimple collection) {
		this.collection = collection;
	}

	public List<CollectionSimpleMSG> getResultCollection() {
		return resultCollection;
	}

	public DataToReturn<List<CommentSimple>> getResultComment_x() {
		return resultComment_x;
	}

	public void setResultComment_x(DataToReturn<List<CommentSimple>> resultComment_x) {
		this.resultComment_x = resultComment_x;
	}

	public DataToReturn<List<CollectionSimpleMSG>> getResultCollection_x() {
		return resultCollection_x;
	}

	public void setResultCollection_x(DataToReturn<List<CollectionSimpleMSG>> resultCollection_x) {
		this.resultCollection_x = resultCollection_x;
	}

	public void setResultCollection(List<CollectionSimpleMSG> resultCollection) {
		this.resultCollection = resultCollection;
	}

	public ResultSimple getCollectionIsSave() {
		return collectionIsSave;
	}

	public void setCollectionIsSave(ResultSimple collectionIsSave) {
		this.collectionIsSave = collectionIsSave;
	}

}
