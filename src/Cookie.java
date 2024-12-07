public class Cookie extends Food {
    private Position position;
    private String icon = "./assets/cookie.png";

    public Cookie(Position position) {
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