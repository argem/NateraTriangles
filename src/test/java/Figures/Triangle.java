package Figures;

public class Triangle {
    String id;
    double firstSide;
    double secondSide;
    double thirdSide;
    double perimeter;
    double area;

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstSide(double firstSide) {
        this.firstSide = firstSide;
    }

    public void setSecondSide(double secondSide) {
        this.secondSide = secondSide;
    }

    public void setThirdSide(double thirdSide) {
        this.thirdSide = thirdSide;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public double getFirstSide() {
        return firstSide;
    }

    public double getSecondSide() {
        return secondSide;
    }

    public double getThirdSide() {
        return thirdSide;
    }

    public double getPerimeter() {
        perimeter = firstSide + secondSide + thirdSide;
        return perimeter;
    }

    public double getArea() {
        double halfPerimeter = getPerimeter()/2;
        area = Math.sqrt(halfPerimeter*(halfPerimeter-firstSide)*(halfPerimeter-secondSide)*(halfPerimeter-thirdSide));
        return area;
    }
}
