<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="../../cmmn/cs.jsp"%>

<%
	/**
	* @Class Name : securitySet.jsp
	* @Description : securitySet 화면
	* @Modification Information
	*
	*   수정일         수정자                   수정내용
	*  ------------    -----------    ---------------------------
	*  2018.01.04     최초 생성
	*
	* author 변승우 대리
	* since 2018.01.04
	*
	*/
%>
<script>

/* ********************************************************
 * 페이지 시작시 함수
 ******************************************************** */
$(window.document).ready(function() {
	fn_makeSelect01();
	fn_makeSelect02();
	fn_makeSelect03();
	
	fn_selectEncriptSet();
});

/* ********************************************************
 * 관리서버 모니터링 주기
 ******************************************************** */
 function fn_makeSelect01(){
	var sec = "";
	var secHtml ="";
	
	secHtml += '<select class="select t6" name="MONITOR_POLLING_SERVER" id="MONITOR_POLLING_SERVER">';	
	for(var i=10; i<=599; i++){
		if(i >= 0 && i<10){
			sec = i;
		}else{
			sec = i;
		}
		secHtml += '<option value="'+sec+'">'+sec+'</option>';
	}
	secHtml += '</select>';	
	$( "#period01" ).append(secHtml);
} 

 /* ********************************************************
  * 에이전트와 관리서버 통신 주기
  ******************************************************** */
  function fn_makeSelect02(){
 	var sec = "";
 	var secHtml ="";
 	
 	secHtml += '<select class="select t6" name="MONITOR_POLLING_AGENT" id="MONITOR_POLLING_AGENT">';	
 	for(var i=5; i<=399; i++){
 		if(i >= 0 && i<10){
 			sec = i;
 		}else{
 			sec = i;
 		}
 		secHtml += '<option value="'+sec+'">'+sec+'</option>';
 	}
 	secHtml += '</select> ';	
 	$( "#period02" ).append(secHtml);
 } 
 
  /* ********************************************************
   * 암호화 키의 유효기간이 다음 날짜 이하로 남으면 경고
   ******************************************************** */
   function fn_makeSelect03(){
  	var sec = "";
  	var secHtml ="";
  	
  	secHtml += '<select class="select t6" name="MONITOR_EXPIRE_CRYPTO_KEY" id="MONITOR_EXPIRE_CRYPTO_KEY">';	
  	for(var i=10; i<=599; i++){
  		if(i >= 0 && i<10){
  			sec = i;
  		}else{
  			sec = i;
  		}
  		secHtml += '<option value="'+sec+'">'+sec+'</option>';
  	}
  	secHtml += '</select>';	
  	$( "#period03" ).append(secHtml);
  } 

  
  function fn_selectEncriptSet(){
	  $.ajax({
			url : "/selectEncryptSet.do", 
		  	data : {
		  	},
			dataType : "json",
			type : "post",
			beforeSend: function(xhr) {
		        xhr.setRequestHeader("AJAX", true);
		     },
			error : function(xhr, status, error) {
				if(xhr.status == 401) {
					alert("<spring:message code='message.msg02' />");
					 location.href = "/";
				} else if(xhr.status == 403) {
					alert("<spring:message code='message.msg03' />");
		             location.href = "/";
				} else {
					alert("ERROR CODE : "+ xhr.status+ "\n\n"+ "ERROR Message : "+ error+ "\n\n"+ "Error Detail : "+ xhr.responseText.replace(/(<([^>]+)>)/gi, ""));
				}
			},
			success : function(data) {
				if(data.resultCode == "0000000000"){
					if(data.list[0].ValueTrueFalse == true){
						$("#ValueTrueFalse").attr('checked', true);
					}else{
						$("#ValueTrueFalse").attr('checked', false);
					}
				
					if(data.list[0].MONITOR_AGENT_AUDIT_LOG_HMAC == true){
						$("#MONITOR_AGENT_AUDIT_LOG_HMAC").attr('checked', true);
					}else{
						$("#MONITOR_AGENT_AUDIT_LOG_HMAC").attr('checked', false);
					}
					
					$("#MONITOR_POLLING_AGENT").val(data.list[0].MONITOR_POLLING_AGENT);
					$("#MONITOR_EXPIRE_CRYPTO_KEY").val(data.list[0].MONITOR_EXPIRE_CRYPTO_KEY);				
					$("#MONITOR_POLLING_SERVER").val(data.list[0].MONITOR_POLLING_SERVER);
				}else if(data.resultCode == "8000000003"){
					alert(data.resultMessage);
					location.href = "/securityKeySet.do";
				}else{
					alert("resultCode : " + data.resultCode + " resultMessage : " + data.resultMessage);			
				}	
			}
		});	
  }
  
  function fn_save(){
	  var arrmaps = [];
	  var tmpmap = new Object();
	  
	  tmpmap["ValueTrueFalse"] =$(ValueTrueFalse).prop("checked");
	  tmpmap["MONITOR_AGENT_AUDIT_LOG_HMAC"] = $(MONITOR_AGENT_AUDIT_LOG_HMAC).prop("checked");
	  tmpmap["MONITOR_POLLING_AGENT"] = $("#MONITOR_POLLING_AGENT").val();
	  tmpmap["MONITOR_EXPIRE_CRYPTO_KEY"] = $("#MONITOR_EXPIRE_CRYPTO_KEY").val();
	  tmpmap["MONITOR_POLLING_SERVER"] = $("#MONITOR_POLLING_SERVER").val();
	  arrmaps.push(tmpmap);	
		
		$.ajax({
			url : "/saveEncryptSet.do", 
		  	data : {
		  		arrmaps : JSON.stringify(arrmaps),
		  	},
			dataType : "json",
			type : "post",
			beforeSend: function(xhr) {
		        xhr.setRequestHeader("AJAX", true);
		     },
			error : function(xhr, status, error) {
				if(xhr.status == 401) {
					alert("<spring:message code='message.msg02' />");
					 location.href = "/";
				} else if(xhr.status == 403) {
					alert("<spring:message code='message.msg03' />");
		             location.href = "/";
				} else {
					alert("ERROR CODE : "+ xhr.status+ "\n\n"+ "ERROR Message : "+ error+ "\n\n"+ "Error Detail : "+ xhr.responseText.replace(/(<([^>]+)>)/gi, ""));
				}
			},
			success : function(data) {
				if(data.resultCode == "0000000000"){
					alert("등록되었습니다.")
					location.reload();
				}else if(data.resultCode == "8000000003"){
					alert(data.resultMessage);
					location.href = "/securityKeySet.do";
				}else{
					alert("resultCode : " + data.resultCode + " resultMessage : " + data.resultMessage);			
				}	
			}
		});	
  }
</script>
<style>
.cmm_bd .sub_tit>p {
	padding: 0 8px 0 33px;
	line-height: 24px;
	background: url(../images/popup/ico_p_2.png) 8px 48% no-repeat;
}

select.t6{
	height: 25px !important;
}
</style>


<div id="contents">
	<div class="contents_wrap">
		<div class="contents_tit">
			<h4>암호화 설정<a href="#n"><img src="../images/ico_tit.png" class="btn_info"/></a></h4>
			<div class="infobox"> 
				<ul>
					<li>암호화 설정</li>
				</ul>
			</div>
			<div class="location">
				<ul>
					<li>Encrypt</li>
					<li>설정</li>
					<li class="on">암호화 설정</li>
				</ul>
			</div>
		</div>

		<div class="contents">
			<div class="cmm_grp">
				<div class="btn_type_01">
					<a href="#n" class="btn" onClick="fn_save()"><span>저장</span></a> 
				</div>
				<div class="cmm_bd">
					<div class="sub_tit">
						<p>암호화설정</p>
					</div>
					<div class="overflows_areas">
						<table class="write">
							<colgroup>
								<col style="width: 300px;" />
								<col style="width: 55px;" />
								<col />
							</colgroup>
							<tbody>
								<tr>
									<td colspan="2">
										<div class="inp_chk">
											<span> <input type="checkbox" id="ValueTrueFalse" name="ValueTrueFalse" /> 
												<label for="ValueTrueFalse">정책전송 중지</label>
											</span>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row" class="ico_t2">관리서버 모니터링 주기</th>
									<td><div id="period01"></div></td>
									<td>초</td>
								</tr>
								<tr>
									<th scope="row" class="ico_t2">에이전트와 관리서버 통신 주기</th>
									<td><div id="period02"></div></td>
									<td>초(5 ~ 86400초)</td>
								</tr>
								<tr>
									<th scope="row" class="ico_t2">암호화키의 유효기간이 다음 날짜 이하로 남으면 경고</th>
									<td><div id="period03"></div></td>
									<td>일(10 ~ 600초)</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="inp_chk">
											<span> <input type="checkbox" id="MONITOR_AGENT_AUDIT_LOG_HMAC" name="MONITOR_AGENT_AUDIT_LOG_HMAC" /> 
												<label for="MONITOR_AGENT_AUDIT_LOG_HMAC">에이전트가 기록하는 로그에 위변조 방지를 적용</label>
											</span>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="loading"><img src="/images/spin.gif" alt="" /></div>