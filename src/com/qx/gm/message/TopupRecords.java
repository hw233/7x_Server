package com.qx.gm.message;

public class TopupRecords {
	private int money;// 充值金额
	private int vcoin;// 获得元宝
	private String top_time;// 充值时间
	private String status;// 充值状态（是否充值成功）

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getVcoin() {
		return vcoin;
	}

	public void setVcoin(int vcoin) {
		this.vcoin = vcoin;
	}

	public String getTop_time() {
		return top_time;
	}

	public void setTop_time(String top_time) {
		this.top_time = top_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
