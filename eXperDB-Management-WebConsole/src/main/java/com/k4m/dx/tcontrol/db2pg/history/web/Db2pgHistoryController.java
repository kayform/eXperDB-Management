package com.k4m.dx.tcontrol.db2pg.history.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.k4m.dx.tcontrol.admin.accesshistory.service.AccessHistoryService;
import com.k4m.dx.tcontrol.cmmn.CmmnUtils;
import com.k4m.dx.tcontrol.common.service.HistoryVO;
import com.k4m.dx.tcontrol.db2pg.cmmn.DB2PG_LOG;
import com.k4m.dx.tcontrol.db2pg.history.service.Db2pgHistoryService;
import com.k4m.dx.tcontrol.db2pg.history.service.Db2pgHistoryVO;

@Controller
public class Db2pgHistoryController {
	
	@Autowired
	private Db2pgHistoryService db2pgHistoryService;
	
	@Autowired
	private AccessHistoryService accessHistoryService;
	
	/**
	 * DB2PG 수행이력 화면을 보여준다.
	 * 
	 * @param historyVO
	 * @param request
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pgHistory.do")
	public ModelAndView db2pgHistory(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {			
			// 화면접근이력 이력 남기기
			CmmnUtils.saveHistory(request, historyVO);
			historyVO.setExe_dtl_cd("DX-T0143");
			historyVO.setMnu_id(42);
			accessHistoryService.insertHistory(historyVO);
			
			mv.setViewName("db2pg/history/db2pgHistory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * DDL 수행이력 조회
	 * 
	 * @param request
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pg/selectDb2pgDDLHistory.do")
	public @ResponseBody List<Db2pgHistoryVO> selectDb2pgDDLHistory(@ModelAttribute("historyVO") HistoryVO historyVO, @ModelAttribute("db2pgHistoryVO") Db2pgHistoryVO db2pgHistoryVO, HttpServletRequest request, HttpServletResponse response) {
		List<Db2pgHistoryVO> resultSet = null;
		try {
			// 화면접근이력 이력 남기기
			CmmnUtils.saveHistory(request, historyVO);
			historyVO.setExe_dtl_cd("DX-T0143_02");
			historyVO.setMnu_id(42);
			accessHistoryService.insertHistory(historyVO);

			resultSet = db2pgHistoryService.selectDb2pgDDLHistory(db2pgHistoryVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	/**
	 * Migration 수행이력 조회
	 * 
	 * @param request
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pg/selectDb2pgMigHistory.do")
	public @ResponseBody List<Db2pgHistoryVO> selectDb2pgMigHistory(@ModelAttribute("historyVO") HistoryVO historyVO, @ModelAttribute("db2pgHistoryVO") Db2pgHistoryVO db2pgHistoryVO, HttpServletRequest request, HttpServletResponse response) {
		List<Db2pgHistoryVO> resultSet = null;
		try {
			// 화면접근이력 이력 남기기
			CmmnUtils.saveHistory(request, historyVO);
			historyVO.setExe_dtl_cd("DX-T0143_02");
			historyVO.setMnu_id(42);
			accessHistoryService.insertHistory(historyVO);

			resultSet = db2pgHistoryService.selectDb2pgMigHistory(db2pgHistoryVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	/**
	 * Migration 수행이력 상세보기 화면을 보여준다.
	 * 
	 * @param request
	 * @return resultSet
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pg/selectDb2pgHistory.do")
	public @ResponseBody List<Db2pgHistoryVO> selectDb2pgHistory(@ModelAttribute("historyVO") HistoryVO historyVO, @ModelAttribute("db2pgHistoryVO") Db2pgHistoryVO db2pgHistoryVO, HttpServletRequest request, HttpServletResponse response) {
		List<Db2pgHistoryVO> resultSet = null;
		try {
			// 화면접근이력 이력 남기기
			CmmnUtils.saveHistory(request, historyVO);
			historyVO.setExe_dtl_cd("DX-T0143_02");
			historyVO.setMnu_id(42);
			accessHistoryService.insertHistory(historyVO);

			resultSet = db2pgHistoryService.selectDb2pgHistory(db2pgHistoryVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * DB2PG 수행이력 상세보기 화면을 보여준다.
	 * 
	 * @param
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pg/popup/db2pgHistoryDetail.do")
	public ModelAndView db2pgHistoryDetail(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Db2pgHistoryVO result = null;
		try {
			int imd_exe_sn=Integer.parseInt(request.getParameter("imd_exe_sn"));

			result = (Db2pgHistoryVO) db2pgHistoryService.selectDb2pgHistoryDetail(imd_exe_sn);
			mv.addObject("result",result);
			mv.setViewName("db2pg/popup/db2pgHistoryDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}	
	
	/**
	 * DB2PG 수행 결과 화면을 보여준다.
	 * 
	 * @param
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pg/popup/db2pgResult.do")
	public ModelAndView db2pgResult(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> db2pgResult = null;
		Db2pgHistoryVO result = null;
		try {
			int imd_exe_sn=Integer.parseInt(request.getParameter("imd_exe_sn"));
			String trans_save_pth = request.getParameter("trans_save_pth");
			
			db2pgResult  = DB2PG_LOG.db2pgFile(trans_save_pth);
			
			result = (Db2pgHistoryVO) db2pgHistoryService.selectDb2pgHistoryDetail(imd_exe_sn);
			mv.addObject("result",result);
			mv.addObject("db2pgResult",db2pgResult);
			mv.setViewName("db2pg/popup/db2pgResult");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}	
	
	/**
	 * DDL 수행이력 상세보기 화면을 보여준다.
	 * 
	 * @param
	 * @return ModelAndView mv
	 * @throws Exception
	 */
	@RequestMapping(value = "/db2pg/popup/db2pgResultDDL.do")
	public ModelAndView db2pgResultDDL(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Db2pgHistoryVO result = null;
		try {
			// 화면접근이력 이력 남기기
			CmmnUtils.saveHistory(request, historyVO);
			historyVO.setExe_dtl_cd("DX-T0143_01");
			historyVO.setMnu_id(42);
			accessHistoryService.insertHistory(historyVO);
			
			int imd_exe_sn=Integer.parseInt(request.getParameter("imd_exe_sn"));
			String trans_save_pth = request.getParameter("trans_save_pth");
			result = (Db2pgHistoryVO) db2pgHistoryService.selectDb2pgHistoryDetail(imd_exe_sn);
			mv.addObject("result",result);
			mv.addObject("trans_save_pth",trans_save_pth);
			mv.setViewName("db2pg/popup/db2pgResultDDL");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}	
	
	/**
	 * DDL 수행이력 파일 리스트를 조회한다.
	 * 
	 * @param historyVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/db2pg/selectdb2pgResultDDLFile.do")
	public @ResponseBody List<HashMap<String, String>> selectdb2pgResultDDLFile(@ModelAttribute("historyVO") HistoryVO historyVO, HttpServletRequest request, HttpServletResponse response) {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			String trans_save_pth = request.getParameter("trans_save_pth");	
			//TODO local 테스트!!
			trans_save_pth="C:/test/config";
			
			String pattern = "yyyy-MM-dd HH:mm:ss"; 
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			File dirFile = new File(trans_save_pth);
			File [] fileList = dirFile.listFiles();
			
			if(fileList!=null){
				 for(int i=0; i < fileList.length; i++){
					 HashMap<String, String> hp = new HashMap<String, String>();
					 	hp.put("idx", Integer.toString(i+1));
					    hp.put("name", fileList[i].getName());
					    hp.put("path", trans_save_pth);
					    hp.put("size", Long.toString(fileList[i].length())+" byte");
					    hp.put("date", simpleDateFormat.format(fileList[i].lastModified()));
				        result.add(hp);
				    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * DDL 수행이력 결과를 파일로 다운받는다.
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/db2pg/popup/db2pgFileDownload.do")
	public  void fileDownload(HttpServletRequest request, HttpServletResponse response){
		try {
			//파일경로입력
			String filePath = request.getParameter("path");
			String fileName = request.getParameter("name");
			String viewFileNm = request.getParameter("name");
			DownloadView fileDown = new DownloadView(); //파일다운로드 객체생성
			fileDown.filDown(request, response, filePath, fileName, viewFileNm); //파일다운로드 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
