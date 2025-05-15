## Overview.

기존 Java & Spring Boot 프로젝트를 Kotlin 으로 리팩토링하는 예시

## 목차

<!-- TOC -->

* [Kotlin 플러그인 및 라이브러리 설정](#kotlin-플러그인-및-라이브러리-설정)
* [추가 공부 필요 항목](#추가-공부-필요-항목)
    * [기본 이슈 및 환경 호환](#기본-이슈-및-환경-호환)
    * [ORM 관련](#orm-관련)
    * [비동기 및 코루틴](#비동기-및-코루틴)
    * [테스트 및 기타](#테스트-및-기타)
    * [빌드, 플러그인](#빌드-플러그인)

<!-- TOC -->

<br/>

## Kotlin 플러그인 및 라이브러리 설정

```groovy
plugins {
    // Kotlin 추가
    id 'org.jetbrains.kotlin.jvm' version '1.6.21'
    id 'org.jetbrains.kotlin.plugin.jpa' version '1.6.21'
    id 'org.jetbrains.kotlin.plugin.spring' version '1.6.21'

    // Query DSL 추가시
    id 'org.jetbrains.kotlin.kapt' version '1.6.21'
}

dependencies {
    // Kotlin Standard Library 추가
    implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8'
    implementation 'org.jetbrains.kotlin:kotlin-reflect:1.6.21'

    // Jackson : Json Mapping
    implementation 'com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3'

    // Query DSL : 의존성 추가 후 build 실행 => QClass 생성 확인.
    implementation 'com.querydsl:querydsl-jpa:5.0.0'
    kapt('com.querydsl:querydsl-apt:5.0.0:jpa')
    kapt('org.springframework.boot:spring-boot-configuration-processor')
}

// Kotlin Compile Configuration
compileKotlin {
    kotlinOptions {
        jvmTarget = "11"
    }
}

// Kotlin Test Compile Configuration
compileTestKotlin {
    kotlinOptions {
        jvmTarget = "11"
    }
}
```

<br/>

## 추가 공부 필요 항목

### 기본 이슈 및 환경 호환

1. Java <-> Kotlin 버전 관계
    - Kotlin 컴파일러가 요구하는 최소 Java 버전
    - Kotlin 1.9+와 JDK 21과의 호환성 이슈

2. Spring Boot & Kotlin 호환성
    - Spring Boot 릴리즈에 따른 Kotlin 지원 현황
    - spring-kotlin 관련 공식 가이드 분석
3. Kotlin에서 발생 가능한 Spring 관련 런타임 이슈
    - 프록시 관련 문제 (final class/method 문제)
    - Reflection API 및 의존성 주입 이슈
    - Default 파라미터 + Jackson 직렬화/역직렬화 문제
      Kotlin & QueryDSL 단점 정리

### ORM 관련

1. Kotlin & QueryDSL 단점 정리
    - KClass를 기반으로 한 Q타입 생성 어려움
    - default value와 nullable 필드 매핑 문제
    - KAPT 빌드 속도 문제 및 대체 가능성 (KSP)
2. JOOQ 학습
    - Kotlin-friendly한 타입 세이프 쿼리 빌더
    - Spring과의 통합 방법
    - 장점과 단점 (e.g., DSL은 깔끔하지만 복잡한 설정)
3. Exposed (JetBrains ORM)
    - Kotlin스러운 ORM 대안으로 각광받는 라이브러리
    - JPA와 비교한 성능, 개발 편의성

### 비동기 및 코루틴

1. Kotlin Coroutine & Spring
    - @Async vs Coroutine 기반 비동기 처리
    - WebFlux + Coroutine 조합
    - Structured concurrency와 Dispatchers
2. Coroutine + R2DBC
    - Reactive stack + Kotlin coroutine 기반 DB 접근
    - 예외 처리 및 트랜잭션 처리 방법

### 테스트 및 기타

1. Kotest vs JUnit5
    - Kotlin에서 더 읽기 좋은 BDD 스타일 테스트
    - MockK 사용법 (Mockito와 비교)
2. Kotlin Multiplatform (KMP)
    - 백엔드와 프론트엔드 공통 로직 공유 (e.g., DTO, Validation)
    - 실제 서버 적용은 아직 적지만 미래 대비로 고려 가능
3. Kotlin Scripting & Ktor
    - Ktor: 경량 웹 프레임워크
    - Kotlin Script로 빌드 또는 배치 스크립트 작성

### 빌드, 플러그인

1. Gradle Kotlin DSL 적용
    - 기존 Groovy DSL과 비교
    - 최적화 방법, 빌드 캐시 활용법
2. KSP (Kotlin Symbol Processing)
    - KAPT 대체를 위한 빠른 컴파일 환경
    - Spring과의 연동 가능 여부