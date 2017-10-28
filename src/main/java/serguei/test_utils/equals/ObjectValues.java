package serguei.test_utils.equals;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

public class ObjectValues {

    private List<OneValue> values = new ArrayList<>();

    ObjectValues() {

    }

    public Object getObject(String name) {
        return getObjectByName(name);
    }

    public String getString(String name) {
        return (String)getObjectByName(name, String.class);
    }

    public Integer getInteger(String name) {
        return (Integer)getObjectByName(name, Integer.class);
    }

    public int getInt(String name) {
        Integer result = (Integer)getObjectByName(name, Integer.class);
        if (result != null) {
            return result;
        } else {
            return 0;
        }
    }

    public Long getLong(String name) {
        Object obj = getObjectByName(name, Long.class, Integer.class);
        if (obj != null && obj instanceof Integer) {
            return ((Integer)obj).longValue();
        } else {
            return (Long)obj;
        }
    }

    public Boolean getBoolean(String name) {
        return (Boolean)getObjectByName(name, Boolean.class);
    }

    public boolean getBool(String name) {
        Boolean result = (Boolean)getObjectByName(name, Boolean.class);
        if (result != null) {
            return result;
        } else {
            return false;
        }
    }

    void addValue(OneValue oneValue) {
        values.add(oneValue);
    }

    private Object getObjectByName(String name, Class<?>... types) {
        Object object = findByName(name).getObject();
        if (types.length > 0) {
            checkClass(object, types);
        }
        return object;
    }

    private OneValue findByName(String name) {
        for (OneValue value : values) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        fail("Parameter with name \"" + name + "\" not found");
        return null;
    }

    private void checkClass(Object object, Class<?>[] types) {
        for (Class<?> type : types) {
            if (object == null || object.getClass().getName().equals(type.getName())) {
                return;
            }
        }
        fail("Wrong class type, expected " + types[0].getName() + " but was " + object.getClass().getName());
    }
}
