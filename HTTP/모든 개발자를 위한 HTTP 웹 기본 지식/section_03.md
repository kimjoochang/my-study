# 모든 개발자를 위한 HTTP 웹 기본 지식

## HTTP 프로토콜 - HTTP 기본

1. 모든 것이 HTTP

    - HTTP에 모든 것을 전송
        - HTML, TEXT
        - IMAGE, 음성, 영상, 파일
        - JSON, XML(API)
        - 거의 모든 형태의 데이터 전송 가능
        - 서버 간에 데이터를 주고 받을 때도 대부분 HTTP 사용

    - HTTP/1.1 : 가장 많이 사용, 우리에게 가장 중요한 버전

    - 기반 프로토콜
        - TCP : HTTP/1.1, HTTP/2
        - UDP : HTTP/3
        - 현재 HTTP/1.1 주로 사용
            - HTTP/2, HTTP/3도 점점 증가


2. 클라이언트 서버 구조

    - Request Response 구조
    - 클라이언트는 서버에 요청을 보내고, 응답을 대기
    - 서버가 요청에 대한 결과를 만들어서 응답


3. Stateful, Stateless

    - 무상태 프로토콜 (스테이트리스(Stateless))
        - 서버가 클라이언트의 상태를 보존X
        - 장점 : 서버 확장성 높음 (스케일아웃 ; 수평 확장 유리)
        - 단점 : 클라이언트가 추가 데이터 전송

    - 상태 유지 (Stateful)
        - 중간에 다른 점원(서버)으로 바뀌면 안된다 (바뀔 시, 상태 정보를 다른 점원에게 미리 알려줘야 한다)
        - 서버가 장애가 나면 다른 서버로 대체 불가

    - 무상태 (Stateless)
        - 중간에 다른 점원으로 바뀌어도 된다
        - 갑자기 고객(클라이언트 요청)이 증가해도 점원(서버)을 대거 투입할 수 있다
        - 응답 서버를 쉽게 바꿀 수 있다 → 무한한 서버 증설 가능

    - Stateless의 실무 한계
        - 모든 것을 무상태로 설계할 수 있는 경우도 있고 없는 경우도 있음
            
            ex) 로그인(상태유지)
            
        - 로그인한 사용자의 경우 로그인 했다는 상태를 서버에 유지
        - 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서 상태 유지
        - 상태 유지는 최소한만 사용


4. 비연결성

    - HTTP는 기본적으로 연결을 유지하지 않는 모델

    - 일반적으로 초 단위 이하의 빠른 속도로 응답

    - 1시간 동안 수천명이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 매우 작음

    - 서버 자원을 매우 효율적으로 사용 가능!

    - 한계와 극복
        - TCP/IP 연결을 새로 맺어야 함 - 3 way handshake 시간 추가
        - 웹 브라우저로 사이트를 요청하면 HTML 뿐만 아니라 자바스크립트, css, 추가 이미지 등 수 많은 자원이 함께 다운로드
        - 지금은 HTTP 지속 연결(Persistent Connections)로 문제 해결
        - HTTP/2, HTTP/3에서 더 많은 최적화

5. HTTP 메시지

    - HTTP 요청 메시지, HTTP 응답 메시지로 나뉨

    - HTTP 메시지 구조

        - start-line, header, empty line, message body로 나눠져 있음
 
    - start-line (시작라인)

        - request-line(요청) / status-line(응답) 으로 나뉨

        - request-line(요청 메시지) => method SP(공백) request-target(=path) SP HTTP-version CRLF
            ex) Get /search?q=hello&hi=ko HTTP/1.1

            - HTTP 메서드 (GET : 조회)
                - 종류 : GET, POST, PUT, DELETE
                - 서버가 수행해야 할 동작 지정

            - 요청대상 (/search?q=hello&hi=ko)
                - 절대경로 = / 로 시작하는 경로

            HTTP-Version => HTTP/1.1


        - status-line(응답 메시지) => HTTP-version SP status-code SP reason-phrase CRLF
            ex) HTTP/1.1 200 OK

            - HTTP 버전 (HTTP/1.1)
            
            - HTTP 상태 코드 (200)
                - 요청 성공, 실패를 나타냄
                - 200 : 성공
                - 400 : 클라이언트 요청 오류
                - 500 : 서버 내부 오류

            - 이유 문구 (OK)
                - 사람이 이해할 수 있는 짧은 상태 코드 설명 글

    - HTTP 헤더

        - header-field = field-name: OWS field-value OWS (OWS : 띄어쓰기 허용)
            - ex) Host: www.google.com
            - ex) Content-Type: text/html;charset=UTF-8
                  Content-Length: 3423

        - HTTP 전송에 필요한 모든 부가정보
            - ex) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보 ..

        - 표준 헤더가 너무 많음

        - 필요시 임의의 헤더 추가 가능

    - HTTP 메시지 바디

        - 실제 전송할 데이터

        - HTML 문서, 이미지, 영상, JSON 등등 byte로 표현할 수 있는 모든 데이터 전송 가능

        