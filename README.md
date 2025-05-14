# TodoPlanner

---
## CRUD
<img src="https://github.com/user-attachments/assets/e0efbcf8-4eec-43be-a44f-31895365824f" width="400"/>

## ERD
![image](https://github.com/user-attachments/assets/56b857aa-5dd1-4feb-bd43-89cb44dbad2c)



##  API 명세서

| 기능             | Method | URL                                        | Request     | Response    | 상태코드 |
|----------------|--------|------------------------------------------|---------------|--------------|------|
| 일정 생성          | POST   | `/api/planner`                           | JSON          | JSON         | 201  |
| 일정 전체 조회      | GET    | `/api/planner?page=1&size=10`            | Query Param   | Page Response | 200  |
| 일정 단건 조회      | GET    | `/api/planner/{id}`                      | Path Variable | JSON         | 200  |
| 일정 검색          | GET    | `/api/planner/search?userId={userId}&page=1&size=10` 또는 `/api/planner/search?updateAt={date}&page=1&size=10` | Query Param | Page Response | 200 |
| 일정 내용 수정      | PUT    | `/api/planner/{id}`                      | JSON          | JSON         | 200  |
| 일정 삭제          | DELETE | `/api/planner/{id}?password={password}`   | Path Variable, Query Param | - | 200  |
| 유저 생성          | POST   | `/api/user`                              | JSON          | JSON         | 201  |
| 유저 전체 조회      | GET    | `/api/user`                              | -             | JSON List    | 200  |
| 유저 단건 조회      | GET    | `/api/user/{id}`                         | Path Variable | JSON         | 200  |
| 유저 이름 수정      | PUT    | `/api/user/{id}`                         | JSON          | JSON         | 200  |
| 유저 삭제          | DELETE | `/api/user/{id}`                         | Path Variable | -            | 200  |

---

##  요청/응답

### 1. 일정 API
#### 1.1 일정생성
- 생성
  - Request : PlanRequestDto
  - Response : PlanResponseDto
#### 1.2 일정조회
- 단건조회
  - Request : Path Variable Long
  - Response : PlanResponseDto
- 복수조회(전체, 수정일, 작성자아이디)
  - Request : PageRequestDto
  - Response : PageResponseDto 
#### 1.3 일정수정
- 수정
  - Request : PlanUpdateRequestDto
  - Response : PlanResponseDto
#### 1.4 일정삭제
- 삭제
    - Request : PathVariable Long id, RequestParam String password
    - Response : -
### 2. 유저 API
#### 2.1 유저생성
- 생성
    - Request : UserCreateRequestDto
    - Response : UserResponseDto
#### 2.2 유저조회
- 조회
    - Request : UserResponseDto
    - Response : UserResponseDto
#### 2.3 유저수정
- 수정
    - Request : UserUpdateRequestDto
    - Response : UserResponseDto
#### 2.4 유저삭제
- 삭제
    - Request : PathVariable Long id
    - Response : -
