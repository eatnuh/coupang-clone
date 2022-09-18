coupang-clone
=============
쿠팡 클론 프로젝트

## 1. 사용기술
* java11
* Spring Boot
* Spring Data Jpa
* Querydsl
* Spring Security

## 2. 모둘별 설명

### 2.1. cp-common
```
- 프로젝트 공통으로 사용하는 모듈
- 커스텀 예외, 에러코드 인터페이스 정의
```

### 2.2. cp-domain-rds
```
- 비즈니스 도메인의 책임을 가지는 모듈
- 하나의 DBMS를 가지며 CRUD 작업을 수행
```

### 2.3. cp-member-api
```
- 일반 유저가 사용할 수 있는 기능을 제공하는 모듈
- 유저 api와 비지니스 로직 담당 
```

### 2.4. cp-seller-api
```
- 판매자가 사용할 수 있는 기능을 제공하는 모듈
- 판매자 api와 비지니스 로직 담당
```

## 3. 모듈간 의존성
```
cp-member-api ┳▶ cp-domain-rds━▶ cp-common
cp-seller-api ┛
```

