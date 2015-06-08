package com.shufan.points.dao;

import haiyan.common.PropUtil;
import haiyan.common.StringUtil;
import haiyan.common.intf.session.IContext;

import java.lang.reflect.Constructor;

import com.shufan.points.dao.impl.HaploidPointsDaoImpl;

/**
 * 积分dao工厂类
 * @author 商杰
 *
 */
public class PointsDaoFactory {
	
	private static String sDao = PropUtil.getInvokeProperty("PointsDao");
	/**
	 * 默认是单倍积分，可通过在AppInvokes.properties中使用PointsDao配置要创建的Dao
	 * @param context
	 * @return
	 */
	public static PointsDao createPointsDao(IContext context) {
		if(StringUtil.isBlankOrNull(sDao))
			return new HaploidPointsDaoImpl(context);
		PointsDao dao = null;
		try {
			Class daoClass = Class.forName(sDao);
			Constructor<PointsDao> constructor = daoClass.getConstructor(IContext.class);
			dao = constructor.newInstance(context);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dao;
	}
}
