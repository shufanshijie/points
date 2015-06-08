package com.shufan.points.controller;

import haiyan.common.intf.database.orm.IDBRecord;
import haiyan.common.intf.database.orm.IDBResultSet;
import haiyan.common.intf.web.IWebContext;
import haiyan.web.session.WebContextFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shufan.points.dao.PointsDao;
import com.shufan.points.dao.PointsDaoFactory;

@Controller
public class PointsController {
	/**
	 * 根据用户Id获取用户全部积分明细列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "allPoints/{userID}/{pageIndex}", method = RequestMethod.GET)
	public ModelAndView allPointsList(HttpServletRequest req, HttpServletResponse res
			,@PathVariable("userID")String userId,@PathVariable("userID")int pageIndex){
		IWebContext context = WebContextFactory.createDBContext(req, res);
		String sPageSize = req.getParameter("maxPageSize");
		int maxPageSize = sPageSize == null ? 20 : Integer.parseInt(sPageSize);
		PointsDao dao = PointsDaoFactory.createPointsDao(context);
		IDBResultSet list = dao.getAllPointsList(userId, maxPageSize, pageIndex);
		ModelMap model = new ModelMap();
		model.put("list", list.getRecords());
		return new ModelAndView("pointsList.vm",model);
	}
	/**
	 * 根据用户Id获取用户收入积分明细列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "incomePoints/{userID}/{pageIndex}", method = RequestMethod.GET)
	public ModelAndView incomePointsList(HttpServletRequest req, HttpServletResponse res
			,@PathVariable("userID")String userId,@PathVariable("userID")int pageIndex){
		IWebContext context = WebContextFactory.createDBContext(req, res);
		String sPageSize = req.getParameter("maxPageSize");
		int maxPageSize = sPageSize == null ? 20 : Integer.parseInt(sPageSize);
		PointsDao dao = PointsDaoFactory.createPointsDao(context);
		IDBResultSet list = dao.getIncomePointsList(userId, maxPageSize, pageIndex);
		ModelMap model = new ModelMap();
		model.put("list", list.getRecords());
		return new ModelAndView("pointsList.vm",model);
	}
	/**
	 * 根据用户Id获取用户支出积分明细列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "expenditurePoints/{userID}/{pageIndex}", method = RequestMethod.GET)
	public ModelAndView expenditurePointsList(HttpServletRequest req, HttpServletResponse res
			,@PathVariable("userID")String userId,@PathVariable("userID")int pageIndex){
		IWebContext context = WebContextFactory.createDBContext(req, res);
		String sPageSize = req.getParameter("maxPageSize");
		int maxPageSize = sPageSize == null ? 20 : Integer.parseInt(sPageSize);
		PointsDao dao = PointsDaoFactory.createPointsDao(context);
		IDBResultSet list = dao.getExpenditurePointsList(userId, maxPageSize, pageIndex);
		ModelMap model = new ModelMap();
		model.put("list", list.getRecords());
		return new ModelAndView("pointsList.vm",model);
	}
	/**
	 * 根据用户Id查看当前可用积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "points/{userID}" , method = RequestMethod.GET)
	public int points(HttpServletRequest req, HttpServletResponse res,@PathVariable("userID")String userId){ 
		IWebContext context = WebContextFactory.createDBContext(req, res);
		PointsDao dao = PointsDaoFactory.createPointsDao(context);
		int points = dao.getPoints(userId);
		return points;
	}
	/**
	 * 根据用户Id增加积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public int addPoints(HttpServletRequest req, HttpServletResponse res,@PathVariable("userID")String userId){ 
		IWebContext context = WebContextFactory.createDBContext(req, res);
		String sOrderForm = req.getParameter("orderForm");
		JSONObject json = JSONObject.fromObject(sOrderForm);
		IDBRecord orderForm = json2Record(json);
		PointsDao dao = PointsDaoFactory.createPointsDao(context);
		int points = dao.addPoints(userId, orderForm);
		return points;
	}
	private IDBRecord json2Record(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据用户Id消费积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "addPoints/{userID}" , method = RequestMethod.GET)
	public int consumePoints(HttpServletRequest req, HttpServletResponse res,@PathVariable("userID")String userId){ 
		IWebContext context = WebContextFactory.createDBContext(req, res);
		String sOrderForm = req.getParameter("orderForm");
		JSONObject json = JSONObject.fromObject(sOrderForm);
		IDBRecord orderForm = json2Record(json);
		PointsDao dao = PointsDaoFactory.createPointsDao(context);
		int points = dao.consumePoints(userId, orderForm);
		return points;
	}
}
