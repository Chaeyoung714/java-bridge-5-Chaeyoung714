package bridge.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    public int readBridgeSize() {
        //TODO : 분리
        System.out.println(System.lineSeparator() + "다리 건너기 게임을 시작합니다.\n"
                + System.lineSeparator()
                + "다리의 길이를 입력해주세요.");
        return Integer.parseInt(Console.readLine());
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println(System.lineSeparator() + "이동할 칸을 선택해주세요. (위: U, 아래: D)");
        return Console.readLine();
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }
}
