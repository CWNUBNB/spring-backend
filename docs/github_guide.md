# 📘 GitHub 협업 가이드

## 🧭 Overview

이 문서는 **브랜치 전략**, **PR 생성 규칙**, **Issue 작성 규칙**, **템플릿 구조** 등을 정의합니다.

모든 팀원은 이 문서의 규칙에 따라 작업하고, 협업 시 일관성을 유지합니다.

---

## 🌿 1. Branch Strategy

| 브랜치명 | 용도 | 병합 방향 |
| --- | --- | --- |
| `main` | 운영 배포용 (배포 확정 시 반영) | ← dev |
| `dev` | 개발 통합용 (feature 병합 후 테스트) | ← feature/* |
| `feature/*` | 기능별 개발 브랜치 | → dev |

### 💡 예시

```bash
feature/login-page
feature/add-map-filter
feature/festival-api

```

> ⚙️ 모든 기능 개발은 feature/* 브랜치에서 시작하고,
> 
> 
> 완료 후 `dev`로 Pull Request(PR)를 생성합니다.
> 

---

## 🧩 2. Issue Template Guide

이슈는 작업 단위를 정의하며, 모든 개발은 **Issue 기반으로 관리**됩니다.

이슈를 먼저 생성하고, 해당 번호를 커밋/PR에 연결합니다.

### 📁 템플릿 목록

| 템플릿명 | 파일명 | 설명 |
| --- | --- | --- |
| 🐛 Bug Report | `bug_report.md` | 버그 제보 및 수정 요청 |
| ✨ Feature Request | `feature_request.md` | 신규 기능 제안 / 개발 요청 |
| 🔧 Refactor | `refactor.md` | 코드 구조 개선 / 최적화 |
| ⚙️ Chore | `chore.md` | 설정, 빌드, 배포 등 관리성 작업 |

---

### 🐛 Bug Report

> 파일: .github/ISSUE_TEMPLATE/bug_report.md
> 
- 발생한 문제와 재현 과정을 구체적으로 작성
- 가능한 스크린샷, 로그 첨부
- label: `bug`

---

### ✨ Feature Request

> 파일: .github/ISSUE_TEMPLATE/feature_request.md
> 
- 새로 추가하거나 개선할 기능의 목적과 동작 설명
- 예상 결과 및 관련 문서 링크 첨부
- label: `feature`

---

### 🔧 Refactor

> 파일: .github/ISSUE_TEMPLATE/refactor.md
> 
- 코드 품질 향상 / 구조 개선 / 성능 최적화 목적의 작업
- 영향 범위를 명시하고 체크리스트 포함
- label: `refactor`

---

### ⚙️ Chore

> 파일: .github/ISSUE_TEMPLATE/chore.md
> 
- 환경 설정, CI/CD, 패키지 업데이트 등 관리성 작업
- label: `chore`

---

### 🧾 Issue 생성 시 유의사항

- **하나의 이슈는 하나의 작업만 포함**
- **이슈 제목 규칙**: `[TYPE] 내용`
    
    예) `[FEATURE] 숙소 필터 기능 추가`, `[BUG] 로그인 시 토큰 에러 발생`
    
- **라벨 필수 지정** (`bug`, `feature`, `refactor`, `chore`)

---

## 🔀 3. Pull Request (PR) Guide

### 📁 템플릿 구성

| 브랜치 | 파일 | 설명 |
| --- | --- | --- |
| feature → dev | `.github/PULL_REQUEST_TEMPLATE/dev_pr.md` | 기능 개발용 PR |
| dev → main | `.github/PULL_REQUEST_TEMPLATE/main_pr.md` | 배포용 PR |

---

### 🧩 Dev PR (feature → dev)

> 파일: .github/PULL_REQUEST_TEMPLATE/dev_pr.md
> 

### 주요 항목

- **🚀 Feature Summary**: 구현 기능 요약
- **🧪 Test & Verification**: 테스트 체크리스트
- **📦 Related Issues**: 관련 이슈 번호 (`Close #이슈번호`)
- **📝 Additional Notes**: 참고사항

### 예시

```markdown
## 🚀 Feature Summary
- 숙소 검색 필터 기능 추가
- Axios 인터셉터 에러 핸들링 로직 개선

## 🧪 Test & Verification
- [x] 로컬 테스트 완료
- [x] ESLint 통과

## 📦 Related Issues
- Close #23

## 📝 Additional Notes
- API 응답 구조 변경됨 (프론트 영향 있음)

```

---

### 🏁 Main PR (dev → main)

> 파일: .github/PULL_REQUEST_TEMPLATE/main_pr.md
> 

### 주요 항목

- **🧾 Release Summary**: 배포 내역 요약
- **🔍 Test & QA**: QA 및 배포 전 점검사항
- **🧩 Deployment Notes**: 환경 변수, 마이그레이션 등
- **🗒️ Additional Context**: 기타 참고사항

### 예시

```markdown
## 🧾 Release Summary
- 숙소 추천 API 통합
- 축제 데이터 안정화

## 🔍 Test & QA
- [x] dev 환경 QA 완료
- [x] 주요 버그 수정 내역 확인

## 🧩 Deployment Notes
- .env.prod 최신화 필요

```

---

## ⚙️ 4. 브랜치 자동 템플릿 매핑

> 파일: .github/pull_request_template.yml
> 

```yaml
pull_request_templates:
  - name: Dev Template
    path: .github/PULL_REQUEST_TEMPLATE/dev_pr.md
    branches:
      - dev

  - name: Main Template
    path: .github/PULL_REQUEST_TEMPLATE/main_pr.md
    branches:
      - main

```

💡 이 설정 덕분에,

PR을 생성할 때 브랜치에 따라 **자동으로 해당 템플릿이 로드**됩니다.

---

## 🧾 5. 라벨 관리 (Labels)

| 이름 | 용도 | 색상 추천 |
| --- | --- | --- |
| `bug` | 버그 관련 이슈 | 🔴 빨강 |
| `feature` | 신규 기능 추가 | 🟢 초록 |
| `refactor` | 코드 개선, 최적화 | 🟣 보라 |
| `chore` | 설정/환경 관련 작업 | ⚙️ 회색 |

> 📍 Settings → Labels → New Label 에서 직접 추가할 수 있습니다.
> 

---

## 💬 6. Commit Convention (Optional)

| Prefix | 설명 | 예시 |
| --- | --- | --- |
| ✨ `feat:` | 새로운 기능 추가 | `feat: 숙소 필터 기능 추가` |
| 🐛 `fix:` | 버그 수정 | `fix: 로그인 토큰 갱신 오류 해결` |
| 🔧 `refactor:` | 코드 리팩토링 | `refactor: fetch 로직 공통 함수화` |
| ⚙️ `chore:` | 설정/빌드 변경 | `chore: eslint 규칙 추가` |
| 🧪 `test:` | 테스트 코드 추가 | `test: 숙소 필터 테스트케이스 작성` |

---

## ✅ 최종 디렉토리 구조

```
.github/
 ├── ISSUE_TEMPLATE/
 │   ├── bug_report.md
 │   ├── feature_request.md
 │   ├── refactor.md
 │   ├── chore.md
 │   └── config.yml
 ├── PULL_REQUEST_TEMPLATE/
 │   ├── dev_pr.md
 │   └── main_pr.md
 └── pull_request_template.yml

```

---

## 🏁 7. Workflow Summary

1. **Issue 생성** → `[FEATURE] 숙소 필터 기능 추가`
2. **브랜치 생성** → `feature/filter-feature`
3. **개발 완료 후 PR 생성** → `dev`로 merge
    
    → `dev_pr.md` 자동 적용
    
4. **테스트 완료 후 PR 생성** → `main`으로 merge
    
    → `main_pr.md` 자동 적용
    
5. **배포 후 Close**

---

## 🧠 Tip

> 모든 커밋과 PR 제목에는 반드시 관련 이슈 번호를 포함하세요.
> 

예)

```bash
git commit -m "feat: 숙소 필터 기능 추가 (#23)"

```
