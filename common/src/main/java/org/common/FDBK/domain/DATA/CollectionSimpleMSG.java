package org.common.FDBK.domain.DATA;

public class CollectionSimpleMSG {
	private String styleId = "36";
	private String collectionID;
	private String stoID;
	private String stoName;
	private int score;
	private String URL;
	private String addConcrete;

	public String getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(String collectionID) {
		this.collectionID = collectionID;
	}

	public String getStoID() {
		return stoID;
	}

	public void setStoID(String stoID) {
		this.stoID = stoID;
	}

	public String getStoName() {
		return stoName;
	}

	public void setStoName(String stoName) {
		this.stoName = stoName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getAddConcrete() {
		return addConcrete;
	}

	public void setAddConcrete(String addConcrete) {
		this.addConcrete = addConcrete;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
}
