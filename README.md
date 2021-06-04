# woowahan-board-service
* [참고사항] woowahan-board-service -> woowahan-admin-service -> woowahan-frontend
  -> (테스트 후 데이터 생성) -> woowahan-batch-service 순으로 각 서비스의 README.md 읽고 실행 시
  번거로움 없이 진행 가능합니다.

본 프로젝트는 게시판 서비스를 제공합니다.
<br/><br/>

<프로젝트 실행 방법><br/>
해당 프로젝트는 MySQL 기반 프로젝트입니다. 로컬 PC에 MySQL 환경설정 셋팅이 필요합니다.

1. 본 서비스를 실행 시키기 전 /resources/TABLE_DDL.sql 스크립트를 실행하여 필요한 table 들을 생성해야 합니다.
2. /resources/application.yml 파일의 datasource.url, datasource.username, datasource.password
   값을 로컬 환경의 MySQL 설정 값으로 수정하여야 합니다.
3. 댓글 등록 시 메일 발송을 위하여 개인 SMTP 인증이 필요합니다. 
   /resources/application.xml 에서 {USER_GMAIL_ADDRESS}, {USER_GMAIL_PASSWORD}에 
   개인 GMAIL 계정 정보를 기입하고 실행시켜 주시길 바랍니다.   
4. ./gradlew build 을 실행합니다.
5. java -jar build/libs/woowahan-board-service-0.0.1-SNAPSHOT.jar

* 본 서비스는 Swagger UI 를 제공합니다. http://localhost:8002/swagger-ui/#/