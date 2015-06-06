package com.shufan.points.dao;

import haiyan.common.intf.database.orm.IDBRecord;
import haiyan.common.intf.database.orm.IDBResultSet;

/**
 * 积分数据访问对象接口
 * @author 商杰
 *
 */
public interface PointsDao {
	
	public abstract IDBResultSet getAllPointsList(String userId,int maxPageSize,int page);
	public abstract IDBResultSet getIncomePointsList(String userId,int maxPageSize,int page);
	public abstract IDBResultSet getExpenditurePointsList(String userId,int maxPageSize,int page);
	public abstract int getPoints(String userId);
	public abstract int consumePoints(String userId, IDBRecord orderForm);
	public abstract int addPoints(String userId, IDBRecord orderForm);
}
