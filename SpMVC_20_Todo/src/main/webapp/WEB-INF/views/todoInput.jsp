<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<form class="form" id="todoInput" method="post" enctype="multipart/form-data">
<!-- enctype="multipart/form-data" 이걸 추가하지않으면 텍스트만 보내짐, 그러니까 이걸 붙여야 파일형태로 전송이 가능하다! -->
	<div class="image_wrapper">
	<img id="to_image_thumnail" 
		src="${rootPath}/static/images/noImage.png" width="30px">
	</div>
	<!-- 문자열 전달 -->
	<input name="to_text" placeholder="TODO"/>
	<div class="insert-button">추가</div>
	<!--accept="image/*" 이미지만 뜨게 설정  -->
	<!-- 파일 전달 -->
	<input accept="image/*" type="file" id="imgTag" name="to_image"/>
</form>
<script>
document.addEventListener("DOMContentLoaded", function(){
	/*image wrapper가 클릭되면*/
	document.querySelector("div.image_wrapper").addEventListener("click", function(){
		document.querySelector("input#imgTag").click()
	})
	/*버튼 뿐만 아이라 무엇이든 click 이벤트를 설정 할 수 있다.*/
	document.querySelector("div.insert-button").addEventListener("click", function(){
		/*입력된 내용을 서버로 전송하기*/
		document.querySelector("form#todoInput").submit()
	})
	
	/* input type=file tag를 클릭하고 fileOpenDialog 가 뜨고 파일을 선택한 후 open 을 누른 후의 이벤트 = change 이벤트*/
	const imgTag = document.querySelector("input#imgTag")
	/*스크립트 오류를 방지하기 위하여 if로 이미지가 있다면 이라는 조건을 달아줌*/
	if(imgTag){
		imgTag.addEventListener("change", function(){
			/*파일을 여러개 넣을 수 있게 하려면 배열로 들어가게 되는데, 지금은 파일 한개씩만 할 것이기 때문에 0번째만 뽑아줌*/
			/* fileOpen Dialog 에서 선택한 파일들 getter*/
			const fileList = imgTag.files
			/* 선택한 파일 중 첫번째 파일만 또 getter */
			const file0 = fileList[0]
			/* 파일 열기(읽기) 클래스 */
			const fileReader = new FileReader()
			/* 선택한 file을 읽기 위해서 오픈*/
			fileReader.readAsDataURL(file0)
			/* 파일을 열고 모두 읽어들였으면*/
			/* 비동기 방식의 callback 함수*/
			fileReader.onload = function() {
				/*파일을 읽어 이미지 src에 부착시켜라*/
				document.querySelector("img#to_image_thumnail").src = fileReader.result
			}
			/* 첫번째 파일이름 alert() */
			/*alert(file0.name)*//*여기까지 진행하면 image 파일 이름이 뜨게 됨*/
			
		})
	}	
	
})
</script>
