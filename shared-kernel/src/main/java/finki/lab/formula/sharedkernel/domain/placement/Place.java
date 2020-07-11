package finki.lab.formula.sharedkernel.domain.placement;

import finki.lab.formula.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Embeddable
public class Place implements ValueObject {
    private int place;
    private int points;
    private static Map<Integer, Integer> placePointsMap;
    private static Map<Integer, String> placeToString;
    static{
        placePointsMap = new HashMap<>();
        placePointsMap.put(1, 25);
        placePointsMap.put(2, 18);
        placePointsMap.put(3, 15);
        placePointsMap.put(4, 12);
        placePointsMap.put(5, 10);
        placePointsMap.put(6, 8);
        placePointsMap.put(7, 6);
        placePointsMap.put(8, 4);
        placePointsMap.put(9, 2);
        placePointsMap.put(10, 1);
        placePointsMap.put(11, 0);
        placePointsMap.put(12, 0);
        placePointsMap.put(13, 0);
        placePointsMap.put(14, 0);

        placeToString = new HashMap<>();
        placeToString.put(1, "1st");
        placeToString.put(2, "2nd");
        placeToString.put(3, "3rd");
        placeToString.put(4, "4th");
        placeToString.put(5, "5th");
        placeToString.put(6, "6th");
        placeToString.put(7, "7th");
        placeToString.put(8, "8th");
        placeToString.put(9, "9th");
        placeToString.put(10, "10th");
        placeToString.put(11, "11th");
        placeToString.put(12, "12th");
        placeToString.put(13, "13th");
        placeToString.put(14, "14th");
    }

    public Place(){
    }

    public Place(int place) {
        this.place = place;
        this.points = placePointsMap.get(place);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place1 = (Place) o;
        return place == place1.place &&
                points == place1.points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, points);
    }

    @Override
    public String toString() {
        return placeToString.get(this.place);
    }

    public int getPlace() {
        return place;
    }

    public int getPoints() {
        return points;
    }
}
