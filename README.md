# JavaFinalExam_RefrigeratorManagementSystem

# **냉장고 관리 시스템 코드 및 실행 방법**

## **1. 프로젝트 개요**  
본 프로그램은 냉장고의 식품 데이터를 관리하는 시스템입니다.  
**주요 기능**:  
- 식품 추가/삭제  
- 식품 검색 및 정렬  
- 데이터 저장/불러오기  
- 데이터 다른 이름으로 저장

---

## **2. 프로젝트 구조**  

프로그램은 **MVC 패턴**에 따라 설계되었으며, 패키지를 활용해 기능을 분리했습니다.

### **디렉토리 구조**  
```plaintext
RefrigeratorManagementSystem/
│
├── main/                # 프로그램 실행
│   └── Main.java
│
├── model/               # 데이터 모델
│   └── Food.java
│
├── controller/          # 비즈니스 로직
│   └── Refrigerator.java
│
├── view/                # 사용자 인터페이스
│   └── RefrigeratorGUI.java
│
└── save/                # 데이터 내보내기
    └── SaveProcess.java
```

---

## **3. 코드 및 클래스 관계**  

### **3.1. 클래스 간 관계도**  
각 클래스의 역할과 상호작용을 간략히 나타내면 다음과 같습니다:

```plaintext
Main.java
│
└── RefrigeratorGUI (view)  ← 사용자 입력/출력
    │
    ├── Refrigerator (controller)  ← 데이터 관리 및 비즈니스 로직
    │   ├── Food (model)           ← 데이터 객체
    │   └── SaveProcess (save)     ← 데이터 내보내기 기능
```

---

### **3.2. 클래스 설명**  

#### **main 패키지: Main.java**  
- **역할**: 프로그램 실행의 진입점  
- **기능**:  
   - `Refrigerator` 객체와 `RefrigeratorGUI` 객체를 생성하고 프로그램 실행  

---

#### **model 패키지: Food.java**  
- **역할**: 식품 데이터를 저장하는 모델 클래스  
- **필드**:  
   - `String name`: 식품 이름  
   - `int quantity`: 수량  
   - `String expirationDate`: 유통기한  
- **메소드**:  
   - Getter 메소드  
   - `toString()`: 데이터를 문자열로 변환  

---

#### **controller 패키지: Refrigerator.java**  
- **역할**: 냉장고 관리 기능을 담당하는 컨트롤러 클래스  
- **기능**:  
   - 식품 추가/삭제/검색/정렬  
   - 파일 저장 및 불러오기  
- **메소드**:  
   - `addFood(Food food)`: 식품 추가  
   - `removeFood(String name)`: 이름으로 식품 삭제  
   - `searchFood(String name)`: 이름으로 식품 검색  
   - `sortByExpirationDate()`: 유통기한 순 정렬  
   - `saveToFile()`: 데이터를 파일에 저장  
   - `loadFromFile()`: 파일에서 데이터 불러오기  

---

#### **view 패키지: RefrigeratorGUI.java**  
- **역할**: 사용자와의 상호작용을 담당하는 GUI 클래스  
- **구성**:  
   - **버튼**: 추가, 삭제, 검색, 정렬, 저장, 내보내기  
   - **JList**: 식품 목록 표시  
- **메소드**:  
   - 사용자 입력을 받아 `Refrigerator` 메소드 호출  
   - `updateDisplay()`: 화면에 목록 갱신  

---

#### **save 패키지: SaveProcess.java**  
- **역할**: 데이터를 **외부 파일**로 내보내는 클래스  
- **메소드**:  
   - `exportDataToFile(List<Food> foodList, String filename)`:  
     데이터를 파일로 저장  

---

## **4. 실행 방법**  

### **4.1. 프로젝트 컴파일**  
터미널에서 프로젝트 루트 디렉토리로 이동한 후, 다음 명령어를 실행합니다.

```bash
javac main/Main.java controller/Refrigerator.java model/Food.java save/SaveProcess.java view/RefrigeratorGUI.java
```

---

### **4.2. 프로그램 실행**  
컴파일이 완료되면 다음 명령어를 통해 실행합니다.

```bash
java main.Main
```

---