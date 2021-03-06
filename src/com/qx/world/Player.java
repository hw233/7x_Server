package com.qx.world;

import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.google.protobuf.ByteString;
import com.qx.junzhu.JunZhu;

import qxmobile.protobuf.PlayerData;
import qxmobile.protobuf.PlayerData.State;

public class Player extends Sprite{
	public JunZhu jz;//战斗中如果是null，才会赋值
	public long jzId;
	public int allianceId;
	public IoSession session;
	public int userId;
	public int roleId;//模型Id
	public State pState;
	public String chengHaoId;
	public String lmName;
	public int vip;
	public int jzlevel;//君主等级
	public int zhiWu;
	public int safeArea;//安全区编号1-4 0和负数表示不在安全区
	public int zhanli;//战力
	public int guojia;//国家
	public int worth;//马车价值
	public int horseType;//马车类型
	public int xuePingRemain;
	public Set<Integer> visbileUids;
	public ByteString infoCache;
	public IoBuffer fullCache;
	public Player(){
		pState = PlayerData.State.State_LOADINGSCENE;
	}

	
}
