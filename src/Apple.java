public class Apple extends Food {
    private Position position;
    private String icon = "./assets/apple.png";

    public Apple(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public String getIcon() {
        return this.icon;
    }
}