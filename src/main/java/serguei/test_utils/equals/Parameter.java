package serguei.test_utils.equals;

import java.util.ArrayList;
import java.util.List;

class Parameter {
    private final int position;
    private final String name;
    private final List<Object> objects;

    public Parameter(int position, String name, Object[] objects) {
        this.position = position;
        this.name = name;
        this.objects = new ArrayList<>(objects.length);
        for (Object object : objects) {
            this.objects.add(object);
        }
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public List<Object> getObjects() {
        return objects;
    }
}
