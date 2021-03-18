package com.experdb.proxy.server;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/**
* @author 박태혁
* @see
* 
*      <pre>
* == 개정이력(Modification Information) ==
*
*   수정일       수정자           수정내용
*  -------     --------    ---------------------------
*  2018.04.23   박태혁 최초 생성
*      </pre>
*/

public interface SocketApplication {
	public static Logger log = Logger.getLogger(SocketApplication.class);
	
	public JSONObject perform(String tran_cd, JSONObject dbInfoObj) throws Exception;
}
