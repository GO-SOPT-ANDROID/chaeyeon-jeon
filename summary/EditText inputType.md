# EditText inputType
## 📍 EditText의 inputType 속성이란?
> EditText가 입력받을 데이터의 유형을 미리 지정하여 사용자의 입력 형식을 제한하거나 검증할 때 사용되는 속성
- ‘|’로 구분하여 여러 유형 결합 가능
    
    EX) `android:inputType="text|textMultiLine"`
    
- 지정하지 않으면 줄바꿈 입력 가능
<br>

## 📍 inputType 유형

| Type | Description |
| --- | --- |
| text | 전형적인 텍스트 입력으로 줄바꿈 불가능 |
| textCapCharacters | 영문 입력 시 대문자로 입력 (Shift를 눌러 순간적으로 소문자 입력도 가능하니 주의) |
| textCapWords | 모든 단어의 첫 번째 영문이 대문자로 입력 |
| textCapSentences | 모든 문장의 첫 번째 영문이 대문자로 입력 |
| textAutoCorrect | 입력에 대해 자동 교정 가능 |
| textAutoComplete | 입력에 대해 자동 완성 가능 |
| textMultiLine | 줄바꿈 가능 (default: single line, max length: 5000) |
| textNoSuggestions | 입력 시 사전에 등록된 단어를 추천 단어로 표시하지 않음 |
| textEnableTextConversionSuggestions | 추가 정보가 있는 경우 제공 (EX. 일본어 글자 변환) |
| textUri | URI 입력 |
| textEmailAddress | 이메일 주소 입력 |
| textEmailSubject | 이메일 제목 입력 |
| textShortMessage | 짧은 메시지 입력 |
| textLongMessage | 긴 메시지 입력 |
| textPersonName | 사람 이름 입력 |
| textPostalAddress | 주소 우편번호 입력 |
| textPassword | 입력된 문자가 가려지도록 비밀번호 입력 |
| textVisiblePassword | 입력된 문자가 보이도록 비밀번호 입력 |
| textWebEditText | 웹 양식으로 제공되는 텍스트 입력 |
| textFilter | 필터링 하기 위한 텍스트 입력 |
| textPhonetic | 발음 문자 입력 |
| textWebEmailAddress | 웹 양식으로 제공되는 이메일 주소 입력 |
| textWebPassword | 웹 양식으로 제공되는 비밀번호 입력 |
| number | 숫자 입력 |
| numberSigned | 부호가 있는 숫자 입력 |
| numberDecimal | 소수점이 잇는 숫자 입력 |
| numberPassword | 숫자로 된 비밀번호 입력 |
| phone | ‘-’ 활용하여 전화번호 입력 |
| datetime | 날짜와 시간 입력 (’-’로 날짜, ‘:’로 시간 구분) |
| date | ‘-’ 활용하여 날짜 입력 |
| time | ‘:’ 활용하여 시간 입력 |
<br>

# Reference
- [Android Developers > TextView > android:inputType](https://developer.android.com/reference/android/widget/TextView#attr_android:inputType)
- [Tistory > [Android Studio] EditText - inputType 종류](https://128june.tistory.com/65)
