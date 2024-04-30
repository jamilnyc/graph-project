public class Vertex {
    private final String name;
    private final double x;
    private final double y;

    public Vertex(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        // Objects are equal to themselves
        if (this == obj) {
            return true;
        }

        // Objects can't be equal to null
        if (obj == null) {
            return false;
        }

        // Can't compare to objects of a different class
        if (getClass() != obj.getClass()) {
            return false;
        }

        // Vertices are equal if they have the same name and coordinates
        Vertex other = (Vertex) obj;
        return this.getName().equals(other.getName()) && this.getX() == other.getX() && this.getY() == other.getY();
    }

    public boolean notEquals(Vertex other) {
        return !this.equals(other);
    }

    @Override
    public String toString() {
        return "Vertex [" + name + ", " + x + ", " + y + "]";
    }
}
