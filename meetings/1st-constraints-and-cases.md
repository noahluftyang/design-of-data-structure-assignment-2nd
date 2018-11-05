# 1st meeting - constraints and cases

> 문제 해결을 간소화하기 위한 제약조건 및 케이스 분리

## Contents

- [Define MVP](#define-mvp)
- [Constraints](#constraints)
- [Cases](#cases)
- [Data analysis](#data-analysis)

## Define MVP

- 사용자는 현재 위치(start floor)와 도착 강의실(target classroom)을 지정한다.
- 사용자는 [이동 수단](#cases)을 결정할 수 있다.
- 사용자가 선택한 수단에 따라 각 경로의 `worst case`들을 비교하여 최적 경로를 반환한다.

| Name               | Contents                                                                   |
| ------------------ | -------------------------------------------------------------------------- |
| Must solve         | - 각 수업시작 20분 전부터 10분 간격의 유동인구 계산                                           |
| Things might solve | - 중간 층에서 이동을 시작하는 경우 <br/> - 엘리베이터가 만원인 경우는 제외                             |
| Not in scope       | - 주로 사용하지 않는 경로는 제외 (ex. 비상 엘리베이터) <br/> - 특정 경로를 중간 지점으로 경유하여 이동하는 경우는 제외 |

## Cases

- case1) 엘리베이터만 이용
- case2) 엘리베이터와 계산을 같이 이용
- case3) 계단만 이용

## Constraints

- 엘리베이터의 worst case는 `엘리베이터가 모든 층에 멈추고 이동하는 경우`이다.
- 계단은 worst case와 best case가 동일하다.

## Data analysis

- 홀수층/짝수층 엘리베이터의 각 층 이동 시간은 `20sec`
- 전층 운행 엘리베이터의 각 층 이동 시간은 `16sec`
- 엘리베이터의 각 층간 대기 시간은 `???sec`
- 계단의 각 층 이동 시간은 `18sec`