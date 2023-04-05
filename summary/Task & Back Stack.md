# Task & Back Stack
## 📍 Task란?
> 사용자가 앱을 사용하며 상호작용하는 액티비티들을 모아 놓은 것

![https://developer.android.com/static/images/fundamentals/diagram_backstack.png](https://developer.android.com/static/images/fundamentals/diagram_backstack.png)

- 액티비티들은 실행된 순서대로 백 스택(Back Stack)에 저장됨
- 백 스택 안의 액티비티들은 순서가 바뀔 수 없음
- 오직 push와 pop을 통해서 백 스택에 삽입되고 제거
<br>

## 📍 Task 관리 방법
1. manifest의 <activity> 태그 속성을 통해 관리
2.  `startActivity()`에 전달하는 Intent의 flag를 통해 관리

### ① <activity> 속성
- taskAffinity
- launchMode
- allowTaskReparenting
- clearTaskOnLaunch
- alwaysRetainTaskState
- finishOnTaskLaunch

### ② Intent Flag
- FLAG_ACTIVITY_NEW_TASK
- FLAG_ACTIVITY_CLEAR_TOP
- FLAG_ACTIVITY_SINGLE_TOP
<br>

## 📍 manifest 파일을 통해 launchMode 설정
> 액티비티가 task로 어떻게 실행되어야 하는지 설정
- **standard** (default) : 항상 액티비티 스택을 쌓아올림
- **singleTop** : 액티비티의 인스턴스가 이미 태스크의 맨 위에 존재할 경우 새 액티비티 인스턴스를 생성하지 않고 `onNewIntent()` ****메서드를 호출하여 기존의 액티비티 인스턴스를 재활용
- **singleTask** : 새로운 태스크가 생성되고 singleTask로 설정된 액티비티에서 다른 액티비티를 호출할 경우 그 액티비티는 새로 생성된 태스크 위에 쌓임 → 태스크에 이미 생성되었고 singleTask로 설정된 액티비티가 다시 호출될 경우 `onNewIntent()` 메서드를 통해 해당 액티비티를 재활용   
- **singleInstance** : 새로운 태스크가 생성되며 하나의 태스크에 하나의 액티비티만 존재 가능
<br>

## 📍 Intent flags를 활용하여 launch mode 정의
- **FLAG_ACTIVITY_NEW_TASK** : 액티비티를 새로운 태스크에서 시작
- **FLAG_ACTIVITY_CLEAR_TOP** : 스택에 존재하는 모든 액티비티를 제거하고 새 액티비티 추가
- **FLAG_ACTIVITY_SINGLE_TOP** : 이미 생성된 액티비티를 재사용하도록 지정 (`onNewIntent()` 호출)
<br>

# Reference
- [Developers > Tasks and the back stack](https://developer.android.com/guide/components/activities/tasks-and-back-stack)
- [Tistory > [Android] Activity의 LaunchMode](https://choboit.tistory.com/24)
