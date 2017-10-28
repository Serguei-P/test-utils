package serguei.test_utils.equals;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class EqualsTester {
    private final ObjectGenerator generator;
    private final List<Parameter> parameters = new ArrayList<>();
    private int intValue = 1;

    public EqualsTester(ObjectGenerator generator) {
        this.generator = generator;
    }

    public void addValues(String name, Object... values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("There should be at least two values");
        }
        Parameter parameter = new Parameter(parameters.size(), name, values);
        parameters.add(parameter);
    }

    public void addStringValues(String name) {
        Object[] values = new String[3];
        values[0] = name + "_value_1";
        values[1] = name + "_value_2";
        values[2] = name + "_value_3";
        addValues(name, values);
    }

    public void addIntegerValues(String name) {
        Object[] values = new Integer[3];
        Integer value = intValue++;
        values[0] = value;
        value = intValue++;
        values[1] = value;
        values[2] = null;
        addValues(name, values);
    }

    public void addLongValues(String name) {
        Object[] values = new Long[3];
        Long value = (long)intValue++;
        values[0] = value;
        value = (long)intValue++;
        values[1] = value;
        values[2] = null;
        addValues(name, values);
    }

    public void addBooleanValues(String name) {
        Object[] values = new Boolean[3];
        values[0] = Boolean.TRUE;
        values[1] = Boolean.FALSE;
        values[2] = null;
        addValues(name, values);
    }

    public void test() {
        Object baseValue = generator.generate(buildObjectValues(0, 0));
        Object baseValue2 = generator.generate(buildObjectValues(0, 0));
        checkEquality(baseValue, baseValue2, parameters.get(0), 0);
        for (int paramIndex = 0; paramIndex < parameters.size(); paramIndex++) {
            Parameter param = parameters.get(paramIndex);
            for (int valueIndex = 1; valueIndex < param.getObjects().size(); valueIndex++) {
                Object value = generator.generate(buildObjectValues(paramIndex, valueIndex));
                Object value2 = generator.generate(buildObjectValues(paramIndex, valueIndex));
                checkEquality(value, value2, param, valueIndex);
                if (baseValue.equals(value)) {
                    fail("Objects are equal, parameter: \"" + param.getName() + "\", value " + valueIndex);
                }
                if (value.equals(baseValue)) {
                    fail("Objects are equal reverse, parameter: \"" + param.getName() + "\", value " + valueIndex);
                }
            }
        }
    }

    private void checkEquality(Object value1, Object value2, Parameter param, int valueIndex) {
        if (!value1.equals(value2)) {
            fail("Objects are not equals, parameter: \"" + param.getName() + "\", value " + valueIndex);
        }
        if (!value2.equals(value1)) {
            fail("Objects are not equals (reversed), parameter: \"" + param.getName() + "\", value " + valueIndex);
        }
        if (value1.hashCode() != value2.hashCode()) {
            fail("Hash is different for same object, parameter: \"" + param.getName() + "\", value " + valueIndex);
        }
    }

    private ObjectValues buildObjectValues(int diffPos, int valuePos) {
        ObjectValues result = new ObjectValues();
        for (int i = 0; i < parameters.size(); i++) {
            Parameter param = parameters.get(i);
            Object obj;
            if (i != diffPos) {
                obj = param.getObjects().get(0);
            } else if (valuePos < param.getObjects().size()) {
                obj = param.getObjects().get(valuePos);
            } else {
                obj = null;
            }
            OneValue value = new OneValue(param.getPosition(), param.getName(), obj);
            result.addValue(value);
        }
        return result;
    }

    private static class Parameter {

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

}
