package serguei.test_utils.equals;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class EqualsTester {
    private final ObjectGenerator generator;
    private final List<Parameter> parameters = new ArrayList<>();
    private int maxPosition = -1;
    private List<Parameter> sortedParameters;

    public EqualsTester(ObjectGenerator generator) {
        this.generator = generator;
    }

    public void addValues(int pos, String name, Object... values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("There should be at least two values");
        }
        Parameter parameter = new Parameter(pos, name, values);
        parameters.add(parameter);
        if (pos > maxPosition) {
            maxPosition = pos;
        }
    }

    public void test() {
        orderParameters();
        Object baseValue = generator.generate(buildObjectValues(0, 0));
        Object baseValue2 = generator.generate(buildObjectValues(0, 0));
        checkEquality(baseValue, baseValue2, sortedParameters.get(0), 0);
        for (int paramIndex = 0; paramIndex <= maxPosition; paramIndex++) {
            Parameter param = sortedParameters.get(paramIndex);
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

    private void orderParameters() {
        sortedParameters = new ArrayList<>(maxPosition + 1);
        for (int pos = 0; pos <= maxPosition; pos++) {
            sortedParameters.add(find(pos));
        }
    }

    private Parameter find(int pos) {
        for (int i = 0; i <= maxPosition; i++) {
            Parameter param = parameters.get(i);
            if (param.getPosition() == pos) {
                return param;
            }
        }
        throw new IllegalArgumentException("Parameter " + pos + " is missing");
    }

    private ObjectValues buildObjectValues(int diffPos, int valuePos) {
        ObjectValues result = new ObjectValues();
        for (int i = 0; i <= maxPosition; i++) {
            Parameter param = sortedParameters.get(i);
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

}
