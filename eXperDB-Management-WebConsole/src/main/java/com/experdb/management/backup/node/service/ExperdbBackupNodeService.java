package com.experdb.management.backup.node.service;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;
import javax.xml.parsers.*;

import org.json.simple.*;
import org.xml.sax.*;

import com.experdb.management.backup.service.*;


public interface ExperdbBackupNodeService {
	
	List<TargetMachineVO> getNodeList()  throws Exception;

	List<ServerInfoVO> getNodeInfoList();

	List<ServerInfoVO> getUnregNodeList();

	JSONObject nodeInsert(HttpServletRequest request);


	JSONObject getNodeInfo(HttpServletRequest request) throws Exception;

	JSONObject nodeUpdate(HttpServletRequest request);

	JSONObject nodeDelete(HttpServletRequest request) throws Exception;

	JSONObject scheduleInsert(HttpServletRequest request, Map<Object, String> param) throws Exception;

	JSONObject getScheduleInfo(HttpServletRequest request) throws SAXException, IOException, ParseException, ParserConfigurationException;

}
