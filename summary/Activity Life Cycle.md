# Android Activity Life Cycle

## 📍 생명 주기(Life Cycle)·수명 주기란?
> 프로세스의 생성 및 소멸을 포함한 일련의 과정
- 안드로이드는 앱이 실행된 후 상태 변화가 발생할 때마다 화면에 보여지는 액티비티의 **생명 주기 메서드**를 호출하여 상태 변화를 알림
- 모바일 컴퓨팅의 자원은 한정되었으나 필수(ex. 전화, 동영상 재생)로 수행해야 하는 작업 존재
  - 안드로이드는 시스템 자원이 부족하면 사용자 동의 없이 프로세스 강제 종료
  - 생명주기 메서드를 통해 배터리, 네트워크 트래픽 등의 자원 절약 가능
<br>

## 📍 생명 주기 메서드
```
⚠️ 생명 주기 메서드는 상태 변화에 따라 안드로이드가 호출하기 때문에 직접 호출 불가능
```
### ① onCreate()
```
override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  // 액티비티 생성
}
```
- **`Bundle` 타입의 `savedInstanceState` 객체** : 액티비티가 중지 · 정지되면 임시적으로 액티비티 상태를 저장하고, 액티비티가 복귀 · 재실행될 때 저장된 데이터를 통해 원래 상태로 복귀
    - 액티비티를 처음 시작하는 경우에는 null 값 포함
- 필수적으로 구현해야 함
- `finish()` 호출 시 `onDestroy()` 호출

### ② onStart()
```
override fun onStart() {
  super.onStart()
  // 액티비티 보이기 시작
}
```
- `finish()` 호출 시 `onStop()` 호출

### ③ onResume()
```
override fun onResume() {
  super.onResume()
  // 액티비티가 보이며 사용자와 상호작용이 가능한 상태
}
```
- `finish()` 호출 시 `onPause()` 호출

### ④ onPause()
```
override fun onPause() {
  super.onPause()
  // 액티비티 일부 가려짐
}
```
- 액티비티가 foreground에 존재하지 않고 background에 해당
- 짧게 진행되어야 하는 함수이기 때문에 부하가 큰 작업 지양

### ⑤ onStop()
```
override fun onStop() {
  super.onStop()
  // 액티비티 완전히 가려짐
}
```
- 투명한 서브 액티비티가 호출된 경우에는 메인 액티비티가 보이기 때문에 호출되지 않음
- 시작 시에는 `onRestart()`, 종료 시에는 `onDestroy()` 호출
- 강제 종료 시 호출되지 않기 때문에, 강제 종료 전에 수행되어야 하는 함수는 전에 수행

### ⑥ onDestroy()
```
override fun onDestroy() {
  super.onDestroy()
  // 액티비티 종료
}
```

### ⑦ onRestart()
```
override fun onRestart() {
  super.onRestart()
  // 완전히 가려졌던 액티비티 다시 실행
}
```
- 액티비티 생성 X
<br>

## 📍 액티비티의 상태 천이
![activity_lifecycle](https://user-images.githubusercontent.com/70993562/194457882-cb7d16f3-b6dc-425d-b381-c58c3ddb7a6d.png)
- Activity Running 기준 상하 대칭 구조를 이루고 있음!
<br>

## 📍 액티비티의 강제 종료와 상태 복원

### 💡 생명주기 메서드에 의한 상태 복원의 문제점
- 자원 부족, 화면 방향 전환 등의 이유로 시스템이 액티비티를 파기할 수 있음
    - 액티비티 복원이 불가능
    - 액티비티 실행 시 시스템이 액티비티 인스턴스 재생성
- 사용자는 시스템에 의한 액티비티 파기 · 복원 여부를 알 수 없음
- 강제 종료의 경우에도 액티비티 상태를 저장할 수 있도록 아래 메서드 제공
    - 사용자가 직접 재정의하여 사용

### ① onSaveInstanceState()
> 액티비티가 보이는 마지막 시점에 호출되어 상태 저장
```
override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
  super.onSaveInstanceState(outState, outPersistentState)
}
```
- onStart() 이후에 호출되어 onCreate() 내부에서 Activity를 복원하는 경우 Bundle 객체가 비어있을 수 있음
    - onCreate() 내부에서 상태 복원 시에는 Bundle 객체가 null 값인지 확인
    - onRestoreInstanceState() 재정의

### ② onRestoreInstanceState()
> 액티비티가 다시 시작되는 시점에 호출되어 상태 복원
```
override fun onRestoreInstanceState(savedInstanceState: Bundle) {
  super.onRestoreInstanceState(savedInstanceState)
}
```
![restore_instance](https://user-images.githubusercontent.com/70993562/194458850-6b067f7a-7bd6-48db-be43-26a0c547157d.png)

<img src="https://user-images.githubusercontent.com/70993562/194458906-a5da6c26-d7d2-4332-acae-1058a8bca32b.png" width=50% height=50%/> <br>
▲ onSavedInstanceState(), onRestoreInstanceState()의 호출 위치를 나타낸 그림

<img src="https://user-images.githubusercontent.com/70993562/194459649-31fd5f50-db24-4b89-a908-4f8cbb302793.png" width=50% height=50%/> <br>
▲ API 28+ 부터는 onSaveInstanceState()가 onStop() 다음에 호출된다고 함

<br>

## 📍 액티비티 생명 주기 응용

### 프로젝트 실행
- onCreate() → onStart() → onResume()

### 화면 방향 전환
- onPause() → onStop() → **onDestroy()** → onCreate() → onStart() → onResume()

### 홈 버튼 클릭
- onPause() → onStop()

### Back 버튼으로 복귀
- onRestart() → onStart() → onResume()

### 프로젝트 종료
- onPause() → onStop() → onDestroy()

### 다른 액티비티 이동 (A → B)
- A.onPause() → B.onCreate() → B.onStart() → B.onResume() → A.onStop()

### 다른 액티비티에서 복귀 (B → A)
- B.onPause() → A.onRestart() → A.Start() → A.onResume() → B.onStop() → B.onDestroy()

### 투명 액티비티로 이동 (A → B)
- A.onPause() → B.onCreate() → B.onStart() → B.onResume()

### 투명 액티비티에서 복귀 (B → A)
- B.onPause() → A.onResume() → B.onStop() → A.onDestroy()

### Back 버튼으로 액티비티 종료
- onPause() → onStop() → onDestroy()
<br>

# Reference
- [Android Developers > The activity lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [Android Developers > Fragment lifecycle](https://developer.android.com/guide/fragments/lifecycle)
- [Android Developers > Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
