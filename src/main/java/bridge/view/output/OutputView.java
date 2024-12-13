package bridge.view.output;

import bridge.dto.GameResult;
import bridge.dto.MapDto;
import bridge.model.game.Line;
import bridge.model.user.Movement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OutputView {
    private static String JOINING_DELIMITER = " | ";

    public void printStartLine() {
        System.out.println("다리 건너기 게임을 시작합니다." + System.lineSeparator());
    }

    public void printMap(List<Movement> movements) {
        printMapStatus(movements);
    }

    public void printResult(GameResult gameResult) {
        System.out.println(System.lineSeparator() + "최종 게임 결과");
        printMapStatus(gameResult.getMovements());
        System.out.println();
        System.out.println(String.format("게임 성공 여부: %s", gameResult.getGameStatus().getExpression()));
        System.out.println(String.format("총 시도한 횟수: %d", gameResult.getTryCount()));
    }

    private void printMapStatus(List<Movement> movements) {
        MapDto mapDto = getMapPhrasesOf(movements);
        System.out.println(String.format("[ %s ]", String.join(JOINING_DELIMITER, mapDto.getUpperMap())));
        System.out.println(String.format("[ %s ]", String.join(JOINING_DELIMITER, mapDto.getLowerMap())));
    }

    private MapDto getMapPhrasesOf(List<Movement> movements) {
        List<String> upperMap = new ArrayList<>();
        List<String> lowerMap = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement.getLine().equals(Line.UP)) {
                upperMap.add(MovementStatusSign.findByStatusOrEmpty(Optional.of(movement.getStatus())));
                lowerMap.add(MovementStatusSign.findByStatusOrEmpty(Optional.empty()));
                continue;
            }
            lowerMap.add(MovementStatusSign.findByStatusOrEmpty(Optional.of(movement.getStatus())));
            upperMap.add(MovementStatusSign.findByStatusOrEmpty(Optional.empty()));
        }
        return new MapDto(upperMap, lowerMap);
    }
}
