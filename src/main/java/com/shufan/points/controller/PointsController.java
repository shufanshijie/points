package com.shufan.points.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PointsController {
	/**
	 * 根据用户Id获取用户全部积分明细列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public ModelAndView allPointsList(HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}
	/**
	 * 根据用户Id获取用户收入积分明细列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public ModelAndView incomePointsList(HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}
	/**
	 * 根据用户Id获取用户支出积分明细列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public ModelAndView expenditurePointsList(HttpServletRequest req, HttpServletResponse res){
		
		return null;
	}
	/**
	 * 根据用户Id查看当前可用积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public Model points(HttpServletRequest req, HttpServletResponse res){ 
		
		return null;
	}
	/**
	 * 根据用户Id增加积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public Model addPoints(HttpServletRequest req, HttpServletResponse res){ 
		
		return null;
	}
	/**
	 * 根据用户Id消费积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public Model consumePoints(HttpServletRequest req, HttpServletResponse res){ 
		
		return null;
	}
	/**
	 * 根据用户订单计算积分
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping
	public Model computePoints(HttpServletRequest req, HttpServletResponse res){ 
		
		return null;
	}
}
