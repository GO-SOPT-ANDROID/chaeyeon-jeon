# Fragment Lifecycle

## 📍 Fragment Lifecycle이란?

- 프래그먼트 인스턴스마다 자체적 생명주기 有
- 화면에 추가 · 제거 · 진입 · 빠져나감에 따라 다양한 생명주기의 상태로 전환
- `LifecycleOwner` 인터페이스를 상속받아 `Fragment`에서 생명주기 관리
    - `getLifecycle()`을 통해 `Lifecycle` 객체 접근 가능
- fragment의 뷰는 fragment와 독립적인 생명주기 有
    - 프래그먼트는 뷰에서 `getViewLifecycleOwner()`로 참조 가능한 LifecycleOwner 보유
- **FragmentManager**가 프래그먼트의 state를 결정하며, 액티비티에 attach · detach 수행
    - `onAttach()` : 프래그먼트가 FragmentManger에 추가되어 host activity에 attach 되었을 때 호출
    - `onDetach()` : 프래그먼트가 FragmentManger에서 제거되고 host activity로부터 detach 되었을 때 호출

```
⚠️ FragmentManger에서 제거된 후 Fragment 인스턴스를 사용하지 않도록 주의하자!
```
<br>

## 📍 Lifecycle State 종류 (enum value)
![Lifecycle 클래스는 생명주기 상태를 추적하기 위해 State와 Event 활용](https://developer.android.com/static/images/topic/libraries/architecture/lifecycle-states.svg)
```
Lifecycle 클래스는 생명주기 상태를 추적하기 위해 State와 Event 활용
```
① **INITIALIZED** : 초기화 상태
- Activity `onCreate()` 호출 전
- Fragment `FragmentManger` 추가 전

② **CREATED** : 생성 상태
- Activity `onCreate()` 호출 ~ `onStop()` 호출 직후
- Fragment `onCreate()` 호출 ~ `onViewStateRestored()` 호출 직후

③ **STARTED** : 시작 상태
- Activity `onStart()` 호출 ~ `onPause()` 호출 직후
- Fragment `onViewStateRestored()` 호출 ~ `onStart()` 호출 직후

④ **RESUMED** : 재개 상태
- Activity `onResume()` 호출
- Fragment `onResume()` 호출

⑤ **DESTROYED** : 파괴된 상태
- Activity `onDestroy()` 호출
- 이후 다른 이벤트가 생명주기 소유자에게 전달되지 않음
<br>

## 📍 Fragment Lifecycle Callbacks
![https://developer.android.com/static/images/guide/fragments/fragment-view-lifecycle.png](https://developer.android.com/static/images/guide/fragments/fragment-view-lifecycle.png)
### Upward state transitions
- Callback 호출 후 Event 발생

### Downward state transitions
- Event 발생 후 관련 Callback 호출

### ① onCreate()
```kotlin
@MainThread
@CallSuper
public void onCreate(@Nullable Bundle savedInstanceState) {
    mCalled = true;
    restoreChildFragmentState(savedInstanceState);
    if (!mChildFragmentManager.isStateAtLeast(Fragment.CREATED)) {
        mChildFragmentManager.dispatchCreate();
    }
}
```
- 프래그먼트 생성될 때 필요한 초기화 작업 구현
- 부모 액티비티가 생성 중에 호출될 수 있어 주의
- 자식 프래그먼트 생성
- `onAttach()` 이후 호출

### ② onCreateView()
```kotlin
@MainThread
@Nullable
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
    if (mContentLayoutId != 0) {
        return inflater.inflate(mContentLayoutId, container, false);
    }
    return null;
}
```
- 프래그먼트 뷰 생성
- 레이아웃을 inflate하는 로직 포함

### ③ onViewCreated()
```kotlin
@MainThread
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { }
```
- 뷰를 반환하여 동작시키는 로직 포함
- 저장된 상태가 복원되기 전에 호출

### ④ onViewStateRestored()
```kotlin
@MainThread
@CallSuper
public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    mCalled = true;
}
```
- 저장된 상태가 복원된 후 호출
- 저장된 상태를 기반으로 초기화를 수행하는 로직 포함
    EX) 체크박스 선택 여부

### ⑤ onStart()
```kotlin
@MainThread
@CallSuper
public void onStart() {
    mCalled = true;
}
```
- 프래그먼트가 사용자에게 가시화되었을 때 호출
- 부모 액티비티의 `onStart()`와 함께 호출

### ⑥ onResume()
```kotlin
@MainThread
@CallSuper
public void onResume() {
    mCalled = true;
}
```
- 프래그먼트가 가시화되고 사용자와 상호작용할 수 있는 상태
- 부모 액티비티의 `onResume()`과 함께 호출되어 활성화됨

### ⑦ onPause()
```kotlin
@MainThread
@CallSuper
public void onPause() {
    mCalled = true;
}
```
- 프래그먼트가 더 이상 사용자와 상호작용하지 않을 때 호출
- 부모 액티비티의 `onPause()`와 함께 호출

### ⑧ onStop()
```kotlin
@MainThread
@CallSuper
public void onStop() {
    mCalled = true;
}
```
- 프래그먼트가 사용자에게 더 이상 보여지지 않을 때 호출
- 부모 액티비티의 `onStop()`과 함께 호출

### ⑨ onSaveInstanceState()
```kotlin
@MainThread
public void onSaveInstanceState(@NonNull Bundle outState) { }
```
- 추후 재생성을 위해 프래그먼트의 동적 상태를 Bundle에 저장하기 위해 호출
- 액티비티의 `onSaveInstanceState()`와 유사한 역할 수행
    - 프래그먼트는 `onDestroy()` 이후에도 호출 가능
- `onDestroyView()` 이후에도 부모 액티비티가 `onSaveInstanceState()`를 호출하지 않으면 프래그먼트 상태가 저장되지 않기 때문에 사용

### ⑩ onDestroyView()
```kotlin
@MainThread
@CallSuper
public void onDestroyView() {
    mCalled = true;
}
```
- 프래그먼트에서 뷰가 detach 될 때 호출
- 내부적으로는 뷰의 상태가 저장된 후, 부모로부터 제거되지 않았을 때 호출
- 프래그먼트 뷰와 관련된 자원을 해제하여 메모리 누수를 방지하는 로직 포함
- 추후 프래그먼트가 다시 보여지게 되면 새로운 뷰 생성

### ⑪ onDestroy()
```kotlin
@MainThread
@CallSuper
public void onDestroy() {
    mCalled = true;
}
```
- 프래그먼트가 더 이상 사용되지 않고 제거될 때 호출
<br>

# Reference
- [Android Developers > Fragment lifecycle](https://developer.android.com/guide/fragments/lifecycle)
- [Android Developers > Lifecycle.State](https://developer.android.com/reference/androidx/lifecycle/Lifecycle.State#INITIALIZED)
- [Android Developers > Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Android Developers > Fragment](https://developer.android.com/reference/androidx/fragment/app/Fragment#onCreate(android.os.Bundle))
