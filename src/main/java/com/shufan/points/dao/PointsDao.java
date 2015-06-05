package com.shufan.points.dao;

import haiyan.common.intf.database.orm.IDBRecord;
import haiyan.common.intf.database.orm.IDBResultSet;

/**
 * 积分数据访问对象接口
 * @author 商杰
 *
 */
public interface PointsDao {
	
	public abstract IDBResultSet getAllPointsList(String userId);
	public abstract IDBResultSet getIncomePointsList(String userId);
	public abstract IDBResultSet getExpenditurePointsList(String userId);
	public abstract int getPoints(String userId);
	public abstract boolean consumePoints(String userId,int points);
	public abstract int computePoints(String userId,IDBRecord orderForm);
	public abstract int addPoints(String userId,int points);
}
