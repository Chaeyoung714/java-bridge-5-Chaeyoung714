package bridge.view;

import bridge.model.Line;
import bridge.model.Movement;
import bridge.model.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<Movement> movements) {
        List<String> upperMap = new ArrayList<>();
        List<String> lowerMap = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement.getLine().equals(Line.UP)) {
                upperMap.add(movement.getStatus().getExpression());
                lowerMap.add(" ");
                continue;
                //TODO : Status에 none도 넣어야하나.
            }
            lowerMap.add(movement.getStatus().getExpression());
            upperMap.add(" ");
        }

        System.out.println(String.format("[ %s ]", String.join(" | ", upperMap)));
        System.out.println(String.format("[ %s ]", String.join(" | ", lowerMap)));
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }
}
