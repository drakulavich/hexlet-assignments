package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public double getArea() {
        return this.area;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public int compareTo(Home other) {
        if (this.getArea() == other.getArea()) {
            return 0;
        }
        return this.getArea() > other.getArea() ? 1 : -1;
    }

    @Override
    public String toString() {
        return getFloorCount() + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
