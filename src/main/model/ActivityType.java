package main.model;

public enum ActivityType {
    LIKE(1), SHARE(2);
    private int value;

    ActivityType(int i) {
        this.value = i;
    }

    public int getId(){
        return value;
    }

}
