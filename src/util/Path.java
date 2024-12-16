package util;

public enum Path {
    CAR("car"),
    LOGIN("login"),
    MAIN("carsMain"),
    ORDER("order"),
    REGISTRATION("registration");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
