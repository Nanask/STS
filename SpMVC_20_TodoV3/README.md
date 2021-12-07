# Spring TodoList 프로젝트

##  MySQL, MyBatis를 사용한 TodoList 프로젝트
* mybatis 3.5 버전 사용
* MySQL 8.0.x 버전 사용
* commons-dbcp2 사용

## 이미지(파일) 첨부기능 구현하기
* commons-io, commons-fileupload 사용하기

## fileUpload를 구현하기 위하여 context 설정하기
* multipartResolver를 구현하여 fileUpload 기능 구현
* context.xml에서 multipartResolver를 설정하여 두면 form에서 file tag를 사용하여 file을 업로드하면 background에서 HTTP 프로토콜에 파일을 업로드하는 기능이 첨가되고 특별히 어려운 코드없이 파일 업로드 가능

## 입력 form 설정할 때
* enctype="mulipart/form-data"를 설정해두어야 한다.

## input file tag에 파일 형식 제한하기
* accept를 설정하여 파일 형식을 제안하기
* accept="image/*": (모든) 이미지 파일들만 업로드하기
* accept="video/*": 동영상 파일만 업로드하기
* accept="text/plan": 순수 text file만 올리기(*.txt만 가능)
* accept="text/html": HTML 코드로 된 text 파일만 올리기(*.htmnl)  
   보통 올리지 않는 것이 좋다..
* accept="text/*": (모든) text file 올리기
* accept=".jpg|.gif|.png": jpg, gif, png 파일만 올리기
* accept=".xls|.xlsx": excel file만 올리기
* accept="application/vnd.ms-excel": (모든) excel file 올리기

## 여러개의 파일을 선택, 업로드하기
* input tag에 multiple="multiple" 속성을 추가하면 여러 개의 파일을 선택할 수 있다.

## bean 등에서 물리적 파일에 접근하기 위한 방법
* http://localhost:8080
* https://www.naver.com
* file:///c:/users/my.txt
file:// 물리적 파일에 접속하기 위한 프로토콜
파일열기 프로토콜을 사용하여 로컬디스크 C 드라이버