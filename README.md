
다음의 내용을 구현하고 있는 간단한 방명록 웹 어플리케이션 입니다. 


개발환경 :  Tomcat 7, JDK 7, Oracle 10g XE, Maven

처음 실행할때는 /src/main/resources 아래의 dbpool-context.xml 파일의 내용중  

<prop key="hibernate.hbm2ddl.auto">update</prop>

위와 같은 내용을 

<prop key="hibernate.hbm2ddl.auto">create</prop>

로 실행함. 

oracle의 경우 127.0.0.1 에서 id: hr password : hr 계정을 사용함. 본인에 맞게 수정함

한번 실행한 이후에는 create를 다시 update로 수정함.

git에서 import를 한 후에 config_backup 폴더안의 .setttings 폴더 .gitignore .project .classpath 를 프로젝트 폴더로 복사한다.


mvn clean install

mvn eclipse:clean eclipse:eclipse

maven update

를 수행한다.


간단한 방명록.

사용된 기술.

spring mvc 

spring security 

spring data jpa + query dsl + hibernate ( jpa 사용) 

OpenEntityManagerInViewFilter 적용 

file upload 

file download 

api 

ejs.js 템플릿 엔진 적용

summernote 에디터 적용.


oracle 10g xe 에서 테스트. 


감사한 분들 : 박재성님(자바지기님), 김영한님, 기타 수많은 분들.