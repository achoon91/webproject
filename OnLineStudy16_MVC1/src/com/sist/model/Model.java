package com.sist.model;

import javax.servlet.http.HttpServletRequest;

/*
 * 	서로 다른 여러개의 클래스를 묶어서 한개의 이름(인터페이스)으로 관리(제어)
 * 	상속
 * 	extends, implements
 * 
 * 	class A ==> A 데이터형
 * 	class B ==> B 데이터형
 * 	interface I
 * 	class C implements I ==> 데이터형 (I,C)
 * 	
 * 	interface 동물
 * 	class 개 implements 동물 (개,동물)
 * 
 * 	동물 ani=new 개()
 * 	
 * 	==> 인터페이스에 존재하는 메소드에 한정 => 인터페이스에 메소드를 증가하면 => 구현하고 있는 모든 클래스가 에러발생
 * 	==> POJO (인터페이스 구현(x), 상속(x)) ==> 구분 (어노테이션) => Spring
 */

public interface Model {
	// 기능(요청) 처리하는 메소드 => Model의 모든 클래스가 요청을 처리하기 위한 메소드
	public String handlerRequest(HttpServletRequest request);
	// 주소(메모리) ==> 값을 주소에 채워주는 방식 (클래스,배열=> 메모리 주소를 이용하는 방식)
	/*
	 * 	매개변수 전송 방식 : Call by Value : 다른 메모리에 값을 복사(일반 데이터)
	 * 				  Call by Reference : 같은 메모리 주소 원본이 넘어간다
	 * 
	 * 	MVC 동작 순서
	 * 		브라우저에서 요청을 받는 경우(JSP/Servlet)
	 * 			request		(request를 받아서) request 		데이터베이스 연결
	 * 		사용자 ======== 컨트롤러 ======== Model클래스======== 데이터베이스(DAO)
	 * 				결과값을 받는다		request에 값을 담아서 	(setAttribute())
	 * 		
	 * 		======= Model 클래스 ============= 컨트롤러 ============ JSP로 전송 ====== 사용자
	 * 		
	 * 		사용자가 보내준 request를 이용해서  ===> 결과값을 담아 준다 ==> (request는 전체 동일 하다)
	 * 													========================= 
	 * 													Call By Reference
	 * 		1) 컨트롤러 : 서블릿
	 * 		2) 모델 : 요청 처리 ==> 확장(재사용) 일반 자바
	 *		3) 뷰 : 요청 처리된 결과값을 출력	
	 *
	 *		컨트롤러 ==> 연결해야하는 모델과 뷰를 알고 있다
	 *			1) 요청 받는다
	 *				String cmd=request.getRequestURL()
	 *				=> (list).do
	 *			2) 모델을 찾는다	==> ListModel
	 *			3) 모델에서 넘겨준 결과값을 request,session에 담아서
	 *					request.setAttribute()
	 *			4) JSP를 찾고 => request,seesion을 넘겨준다
	 *					forward(request,response)
	 *					
	 *					list.do ==> ListModel ===> list.jsp
	 *					detail.do ==> DetailModel ==> detail.jsp ==> Map을 이용해서 (요청:키, 해당 자바파일)
	 *				
	 *					***** Controller가 수정하기 위해서 Controller를 소스수정  ==> 전체 사이트를 구동할 수 없다
	 *					*
	 *					* 파일을 이용한다 (.properties,.xml)
	 *					*						======= Spring,struts
	 *
	 */
}
