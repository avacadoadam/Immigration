package DataTypes;

public enum Priority {

    HIGH(2), MEDIUM(1), LOW(0);

    private int priorityValue;

    Priority(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    public int value() {
        return priorityValue;
    }
}
