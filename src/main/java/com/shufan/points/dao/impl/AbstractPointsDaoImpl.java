package com.shufan.points.dao.impl;

import haiyan.common.CloseUtil;
import haiyan.common.exception.Warning;
import haiyan.common.intf.database.IDBFilter;
import haiyan.common.intf.database.orm.IDBRecord;
import haiyan.common.intf.database.orm.IDBResultSet;
import haiyan.common.intf.session.IContext;
import haiyan.config.castorgen.Table;
import haiyan.config.intf.database.ITableDBManager;
import haiyan.config.intf.session.ITableDBContext;
import haiyan.config.util.ConfigUtil;
import haiyan.orm.database.DBContextFactory;
import haiyan.orm.database.sql.SQLDBFilter;

import java.sql.Date;

import com.shufan.points.dao.PointsDao;

/**
 * 积分数据访问对象抽象类
 * @author 商杰
 *
 */
public abstract class AbstractPointsDaoImpl implements PointsDao {
	protected IContext parentContext;
	protected AbstractPointsDaoImpl() {
	}
	public AbstractPointsDaoImpl(IContext parentContext) {
		this.parentContext = parentContext;
	}
	public IContext getParentContext() {
		return parentContext;
	}
	private static Table pointsDetailTable;
	public Table getPointsDetailTable() {
		if (pointsDetailTable==null)
			synchronized(AbstractPointsDaoImpl.class) {
				if (pointsDetailTable==null)
					pointsDetailTable = ConfigUtil.getTable("T_POINTS_POINTSDETAIL");
			}
		return pointsDetailTable;
	}
	private static Table totalPointsTable;
	public Table getTotalPointsTable() {
		if (totalPointsTable==null)
			synchronized(AbstractPointsDaoImpl.class) {
				if (totalPointsTable==null)
					totalPointsTable = ConfigUtil.getTable("T_POINTS_TOTALPOINTS");
			}
		return totalPointsTable;
	}
	/**
	 * 获取全部积分明细列表
	 */
	@Override
	public IDBResultSet getAllPointsList(String userId,int maxPageSize,int page) {
		ITableDBContext context = null;
		ITableDBManager dbm = null;
		try {
			context = DBContextFactory.createDBContext(parentContext);
			dbm = context.getDBM();
			IDBRecord record = dbm.createRecord();
			record.set("USERID", userId);
			IDBResultSet result = dbm.select(context, getPointsDetailTable(), record, maxPageSize, page);
			return result;
		} catch (Throwable e) {
			throw Warning.wrapException(e);
		}finally{
			CloseUtil.close(dbm);
			CloseUtil.close(context);
		}
	}
	/**
	 * 获取收入积分明细列表
	 */
	@Override
	public IDBResultSet getIncomePointsList(String userId,int maxPageSize,int page) {
		ITableDBContext context = null;
		ITableDBManager dbm = null;
		try {
			context = DBContextFactory.createDBContext(parentContext);
			dbm = context.getDBM();
			IDBFilter filter = new SQLDBFilter(" and USERID = ? and POINTS > ? ",new String[]{userId,"0"});
			IDBResultSet result = dbm.select(context, getPointsDetailTable(), filter, maxPageSize, page);
			return result;
		} catch (Throwable e) {
			throw Warning.wrapException(e);
		}finally{
			CloseUtil.close(dbm);
			CloseUtil.close(context);
		}
	}
	/**
	 * 获取支出积分明细列表
	 */
	@Override
	public IDBResultSet getExpenditurePointsList(String userId,int maxPageSize,int page) {
		ITableDBContext context = null;
		ITableDBManager dbm = null;
		try {
			context = DBContextFactory.createDBContext(parentContext);
			dbm = context.getDBM();
			IDBFilter filter = new SQLDBFilter(" and USERID = ? and POINTS < ? ",new String[]{userId,"0"});
			IDBResultSet result = dbm.select(context, getPointsDetailTable(), filter, maxPageSize, page);
			return result;
		} catch (Throwable e) {
			throw Warning.wrapException(e);
		}finally{
			CloseUtil.close(dbm);
			CloseUtil.close(context);
		}
	}
	/**
	 * 获取当前可用积分
	 */
	@Override
	public int getPoints(String userId) {
		return selectPoints(userId).getInteger("POINTS");
	}
	private IDBRecord selectPoints(String userId) {
		ITableDBContext context = null;
		ITableDBManager dbm = null;
		try {
			context = DBContextFactory.createDBContext(parentContext);
			dbm = context.getDBM();
			IDBRecord record = dbm.select(context, getTotalPointsTable(), userId);
			if(record == null){
				record = dbm.createRecord();
				record.set("ID", userId);
				record.set("POINTS", 0);
				dbm.insert(context, getTotalPointsTable(), record);
			}
			return record;
		} catch (Throwable e) {
			throw Warning.wrapException(e);
		}finally{
			CloseUtil.close(dbm);
			CloseUtil.close(context);
		}
	}
	/**
	 * 消费积分
	 */
	@Override
	public int consumePoints(String userId, IDBRecord orderForm) {
		ITableDBContext context = null;
		ITableDBManager dbm = null;
		try {
			context = DBContextFactory.createDBContext(parentContext);
			dbm = context.getDBM();
			int points = computePoints(userId,orderForm);
			int consumePoints = orderForm.getInteger("CONSUMEPOINTS");
			if(consumePoints != 0){
				IDBRecord record = dbm.createRecord();
				String orderFormImg = orderForm.get("IMAGE").toString();
				String pointsTitle = orderForm.get("NAME").toString();
				String pointsType = orderForm.get("POINTSTYPE").toString(); 
				String orderFormId = orderForm.get("ID").toString();
				record.set("POINTSIMG", orderFormImg);
				record.set("POINTSTITLE", pointsTitle);
				record.set("POINTSTYPE", pointsType);
				record.set("POINTS", consumePoints);
				record.set("ORDERFORMID",orderFormId);
				record.set("USERID", userId);
				record.set("POINTSDATE", new Date(System.currentTimeMillis()));
				dbm.insert(context, getPointsDetailTable(), record);
				
				IDBRecord tpRecord = selectPoints(userId);
				int totalPoints = tpRecord.getInteger("POINTS");
				tpRecord.set("POINTS", totalPoints-consumePoints);
				tpRecord.set("ID", userId);
				dbm.update(context, getTotalPointsTable(), tpRecord);
			}
			return points;
		} catch (Throwable e) {
			throw Warning.wrapException(e);
		}finally{
			CloseUtil.close(dbm);
			CloseUtil.close(context);
		}
	}
	/**
	 * 添加积分
	 */
	@Override
	public int addPoints(String userId, IDBRecord orderForm) {
		ITableDBContext context = null;
		ITableDBManager dbm = null;
		try {
			context = DBContextFactory.createDBContext(parentContext);
			dbm = context.getDBM();
			int points = computePoints(userId,orderForm);
			if(points != 0){
				IDBRecord record = dbm.createRecord();
				String orderFormImg = orderForm.get("IMAGE").toString();
				String pointsTitle = orderForm.get("NAME").toString();
				String pointsType = orderForm.get("POINTSTYPE").toString(); 
				String orderFormId = orderForm.get("ID").toString();
				record.set("POINTSIMG", orderFormImg);
				record.set("POINTSTITLE", pointsTitle);
				record.set("POINTSTYPE", pointsType);
				record.set("POINTS", points);
				record.set("ORDERFORMID",orderFormId);
				record.set("USERID", userId);
				record.set("POINTSDATE", new Date(System.currentTimeMillis()));
				dbm.insert(context, getPointsDetailTable(), record);
				
				IDBRecord tpRecord = selectPoints(userId);
				int totalPoints = tpRecord.getInteger("POINTS");
				tpRecord.set("POINTS", totalPoints+points);
				tpRecord.set("ID", userId);
				dbm.update(context, getTotalPointsTable(), tpRecord);
			}
			return points;
		} catch (Throwable e) {
			throw Warning.wrapException(e);
		}finally{
			CloseUtil.close(dbm);
			CloseUtil.close(context);
		}
	}
	/**
	 * 根据订单计算积分
	 * @param userId
	 * @param orderForm
	 * @return
	 */
	protected abstract int computePoints(String userId, IDBRecord orderForm);

}
