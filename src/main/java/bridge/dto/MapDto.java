package bridge.dto;

import java.util.Collections;
import java.util.List;

public class MapDto {
    private final List<String> upperMap;
    private final List<String> lowerMap;

    public MapDto(List<String> upperMap, List<String> lowerMap) {
        this.upperMap = upperMap;
        this.lowerMap = lowerMap;
    }

    public List<String> getUpperMap() {
        return Collections.unmodifiableList(upperMap);
    }

    public List<String> getLowerMap() {
        return Collections.unmodifiableList(lowerMap);
    }
}
