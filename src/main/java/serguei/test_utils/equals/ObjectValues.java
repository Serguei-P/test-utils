package serguei.test_utils.equals;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

public class ObjectValues {

    private List<OneValue> values = new ArrayList<>();
    
    ObjectValues() {
        
    }
    
    public Object getObject(int index) {
        return getObject(index, Class.class, false);
    }

    public String getString(int index) {
        return (String)getObject(index, String.class, true);
    }

    public Integer getInteger(int index) {
        return (Integer)getObject(index, Integer.class, true);
    }

    public Boolean getBoolean(int index) {
        return (Boolean)getObject(index, Boolean.class, true);
    }

    public Object getObject(String name) {
        return getObject(findByName(name));
    }

    public String getString(String name) {
        return getString(findByName(name));
    }

    public Integer getInteger(String name) {
        return getInteger(findByName(name));
    }

    public Boolean getBoolean(String name) {
        return getBoolean(findByName(name));
    }

    void addValue(OneValue oneValue) {
        values.add(oneValue);
    }

    private int findByName(String name) {
        for (OneValue value : values) {
            if (value.getName().equals(name)) {
                return value.getPosition();
            }
        }
        fail("Parameter with name \"" + name + "\" not found");
        return 0;
    }

    private Object getObject(int index, Class<?> type, boolean checkType) {
        Object object = values.get(index).getObject();
        if (object != null && checkType) {
            checkClass(object, type);
        }
        return object;
    }

    private void checkClass(Object object, Class<?> type) {
        if (!(object.getClass().getName().equals(type.getName()))) {
            throw new IllegalArgumentException(
                    "Wrong class type, expected " + type.getName() + " but was " + object.getClass().getName());
        }
    }
}
