package serguei.test_utils.equals;


class OneValue {
    private final int position;
    private final String name;
    private final Object object;

    OneValue(int position, String name, Object object) {
        this.position = position;
        this.name = name;
        this.object = object;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

}
