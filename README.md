# Reference
똥왕의 분노 ( https://play.google.com/store/apps/details?id=com.nflygame.ddongpi&hl=ko&gl=US&pli=1 )

## 지원 Operating Systems
|OS| 지원 여부 |
|-----|--------|
|windows | :o:  |
| Linux  | :o: |
|MacOS  | :o:  |

## 실행 방법
1. 자바 개발 키트(Java Development Kit, JDK)를 설치한다
```
https://www.oracle.com/java/technologies/downloads/#jdk22-windows
에서 본인의 OS에 해당하는 파일 다운로드
```
2. 환경변수 등록
```
1. 명령 프롬프트를 관리자 권한으로 실행
2. setx SystemPath "{JDK 설치 경로}\jdk-22\bin" -m 명령어 실행
```
3. 게임 실행
```
명령 프롬프트로 다운로드 받은 디렉토리에 접근한 후
java -classpath ./bin;./image Main 명령어 실행
```

# 실행 예시
![escapeveg-ezgif com-video-to-gif-converter](https://github.com/yubinnii/oss_personal_project_phase1/assets/157782716/3ac0c3fa-a441-4296-9c5c-fbdddeecd393)


# 코드 설명
## Main.java
### class Main
- Description : 게임을 시작하는 메인 클래스
  1. public static void main(String[] args) : 메인 게임 창을 초기화하고 메인 화면을 설정

## MainScreen.java
### class MainScreen
-  Description : 게임 제목과 설명, 게임 시작 및 랭킹 보기 버튼을 제공하는 메인 화면 클래스
  1. public MainScreen(JFrame frame) : 게임 제목, 설명 및 버튼을 포함한 메인 화면 초기화
  2. private void startGame() : 게임 패널로 전환하여 게임 시작
  3. private void viewRanking() : 랭킹 화면으로 전환

## MainGame.java
### class MainGame
-  Description : 플레이어 움직임, 야채 생성 및 충돌 감지를 포함한 게임 플레이를 처리하는 메인 게임 클래스
  1. public MainGame(JFrame frame) : 플레이어, 야채 및 게임 메커니즘을 초기화
  2. public void startGame() : 게임 루프 타이머 시작
  3. private void gameLoop() : 게임 상태를 업데이트하고 충돌을 확인하는 메인 게임 루프
  4. private void CreateVegetable() : 상단에서 떨어지는 야채 무작위 생성
  5. private void moveVegetables() : 야채 이동
  6. private void onCollision() : 플레이어와 야채 간의 충돌을 확인
  7. private void updateScore() : 화면을 벗어난 야채를 기준으로 점수 업데이트
  8. private void showGameOverScreen() : 게임이 종료되면 게임 오버 화면으로 전환
  9. protected void paintComponent(Graphics g) : 플레이어, 야채 및 경고 메시지를 포함한 게임 요소를 화면에 렌더링

## GameOverScreen.java
### class GameOverScreen
-  Description : 최종 점수를 표시하고 게임을 재시작하거나 랭킹을 등록하거나 메인 화면으로 돌아갈 수 있는 옵션을 제공하는 게임 오버 화면 클래스
  1. public GameOverScreen(JFrame frame, int score) : 최종 점수와 동작 버튼을 포함한 게임 오버 화면 초기화
  2. private void startGame() : 게임 패널로 전환하여 게임을 다시 시작
  3. private void goToMainScreen() : 메인 화면으로 전환
  4. private void showRankingInput() : 점수를 등록할 랭킹 입력 화면으로 전환

## RankingInputScreen.java 
### class RankingInputScreen
-  Description : 플레이어가 이름을 입력하고 점수를 등록할 수 있는 랭킹 입력 화면 클래스
  1. public RankingInputScreen(JFrame frame, int score) : 이름 입력을 위한 텍스트 필드와 제출 버튼을 포함한 랭킹 입력 화면 초기화
  2. private void saveRanking(String name, int score) : 입력된 이름과 점수를 랭킹 파일에 저장
  3. private void showRankingScreen() : 모든 등록된 점수를 표시하는 랭킹 화면으로 전환

## RankingScreen.java
### class RankingScreen
-  Description : 등록된 점수를 점수순으로 표시하는 랭킹 화면 클래스
  1. public RankingScreen(JFrame frame) : 랭킹 화면을 초기화하고 파일에서 랭킹 로드
  2. private List<RankingEntry> loadRankings() : 파일에서 랭킹을 로드하고 점수별로 정렬
  3. private void displayRankings(List<RankingEntry> rankings) : 랭킹을 화면에 표시
  4. private void goToMainScreen() : 메인 화면으로 전환

### class RankingEntry
-  Description : 랭킹 항목을 저장하고 관리하기 위한 헬퍼 클래스
  1. RankingEntry(String name, int score) : 플레이어의 이름과 점수로 랭킹 항목 초기화


# TODO List
* 디저트 먹으면 목숨 늘어나는 기능 추가하기
* 배경 꾸미기
