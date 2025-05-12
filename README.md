# TodoPlanner

---
## CRUD

## ERD

##  API 명세서

| 기능             | Method | URL                                          | Request 형식 | Response 형식 | 상태코드 |
|------------------|--------|----------------------------------------------|---------------|----------------|----------|
| 일정 생성         | POST   | `/api/planner`                               | JSON          | JSON           | 201      |
| 일정 전체 조회     | GET    | `/api/planner`                               | -             | JSON           | 200      |
| 일정 단건 조회     | GET    | `/api/planner/{id}`                          | Path Variable | JSON           | 200      |
| 일정 조회(작성자)  | GET    | `/api/planner/request-param?name={name}`     | Query Param   | JSON           | 200      |
| 일정 조회(수정일)  | GET    | `/api/planner/request-param?updateAt={date}` | Query Param   | JSON           | 200      |
| 일정 수정         | PUT    | `/api/planner/{id}`                          | JSON          | JSON           | 200      |
| 일정 삭제         | DELETE | `/api/planner/{id}?password=1234`            | Query Param   | -              | 204      |

---

##  요청/응답
### 일정생성
####  createRequest
```json
{
  "name": "user",
  "password": "1234",
  "title": "회의 일정",
  "content": "2025년 5월 회의 일정입니다."
}
```

####  createResponse
```json
{
  "id": 1,
  "name": "user",
  "createAt": "2025-05-09T15:30:00",
  "updateAt": "2025-05-09T15:30:00",
  "title": "회의 일정",
  "content": "2025년 5월 회의 일정입니다."
}
```
