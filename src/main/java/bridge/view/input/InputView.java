package bridge.view.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readBridgeSize() {
        //TODO : 분리
        System.out.println("다리의 길이를 입력해주세요.");
        return Console.readLine();
    }

    public String readMoving() {
        System.out.println(System.lineSeparator() + "이동할 칸을 선택해주세요. (위: U, 아래: D)");
        return Console.readLine();
    }

    public String readGameCommand() {
        System.out.println(System.lineSeparator() + "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        return Console.readLine();
    }
}