# 📆 Schedule & Comment Management Project
> Spring Boot + JPA 기반 일정 관리 & 댓글 기능 CRUD 프로젝트

## ✅ 프로젝트 소개
이 프로젝트는 사용자가 일정(Schedule) 을 생성하고, 해당 일정에 댓글(Comment) 을 작성하며 관리할 수 있는 백엔드 REST API 서버입니다.
회원가입/로그인 기능을 기반으로 유저 인증을 적용했고, 일정과 댓글은 User ↔ Schedule(1:N), Schedule ↔ Comment(1:N) 구조로 연관관계를 설계했습니다.

일정과 댓글은 각각 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) 기능을 제공합니다.
또한 일정 목록 조회 시에는 페이지네이션, 댓글 개수, 작성자 이름, 수정일 최신순 정렬 기능을 지원하여 실제 서비스처럼 효율적인 조회가 가능합니다.

비밀번호는 Bcrypt 기반으로 안전하게 암호화하여 저장하였습니다.

> API 명세서
> https://documenter.getpostman.com/view/47562339/2sB3WwqxE8
<img width="1081" height="689" alt="image" src="https://github.com/user-attachments/assets/27a77b8b-1f9f-46af-bd0b-d7ab5f914390" />


## ✨ 주요 기능
|기능|설명|
|------|-----|
|유저 생성|새로운 유저 생성|
|유저 전체 조회|전체 유저 목록 조회|
|유저 개별 조회|특정 유저 조회|
|유저 수정|특정 유저 정보 수정|
|유저 삭제|특정 유저 삭제|
|일정 생성|새로운 일정 생성|
|일정 전체 조회|전체 일정 목록 조회|
|일정 페이징 조회|페이지 번호, 크기를 지정하여 일정 목록 조회|
|일정 개별 조회|특정 일정 조회|
|일정 수정|특정 일정 내용 수정|
|일정 삭제|특정 일정 삭제|
|댓글 생성|새로운 댓글 생성|
|댓글 전체 조회|전체 댓글 목록 조회|
|댓글 개별 조회|특정 댓글 조회|
|댓글 수정|특정 댓글 내용 수정|
|댓글 삭제|특정 댓글 삭제|

## 🛠️ 기술 스택
| 구분 | 기술 |
|------|------|
| Backend | Spring Boot 3.5.7, Spring Web, Spring Validation |
| ORM / JPA | Spring Data JPA |
| DB | MySQL |
| Authentication / Security | HttpSession, BCrypt
| 개발 편의 | Lombok, Postman |
| 빌드/관리 | Gradle |

## 📂 프로젝트 구조
```
src/main/java/com/example/schedule_develop
│
├─ user
│    ├─ domain
│    │    ├─ User
│    │    └─ UserRepository
│    │
│    ├─ controller
│    │    └─ UserController
│    │
│    ├─ service
│    │    └─ UserService
│    │
│    ├─ dto
│    │    ├─ LoginRequest
│    │    ├─ LoginResponse
│    │    ├─ UserRequest
│    │    ├─ UserResponse
│    │    └─ SessionUser
│    │
├─ schedule
│    ├─ domain
│    │    ├─ Schedule
│    │    └─ ScheduleRepository
│    │
│    ├─ controller
│    │    └─ ScheduleController
│    │
│    ├─ service
│    │    └─ ScheduleService
│    │
│    ├─ dto
│    │    ├─ CreateScheduleRequest
│    │    ├─ UpdateScheduleRequest
│    │    ├─ ScheduleResponse
│    │    └─ PageScheduleResponse
│    │
├─ comment
│    ├─ domain
│    │    ├─ Comment
│    │    └─ CommentRepository
│    │
│    ├─ controller
│    │    └─ CommentController
│    │
│    ├─ service
│    │    └─ CommentService
│    │
│    ├─ dto
│    │    ├─ CreateCommentRequest
│    │    ├─ UpdateCommentRequest
│    │    └─ CommentResponse
│    │
├─ common
│    ├─ BaseEntity
│    └─ GlobalExceptionHandler
├─ config
│    └─ PasswordEncoder
│
```
