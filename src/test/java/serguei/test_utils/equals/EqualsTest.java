package serguei.test_utils.equals;

import org.junit.Test;

import serguei.test_utils.equals.EqualsTester;
import serguei.test_utils.equals.ObjectGenerator;
import serguei.test_utils.equals.ObjectValues;

public class EqualsTest {

    @Test
    public void shouldCheckEquals() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(0));
        setValues(tester);

        tester.test();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenFieldMissing() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(1));
        setValues(tester);

        tester.test();
    }

    private void setValues(EqualsTester tester) {
        tester.addValues(0, "stringValue1", "value11", "value12", null);
        tester.addValues(1, "stringValue2", "value21", "value22", null);
        tester.addValues(2, "intValue", 1, 2);
        tester.addValues(3, "integerValue", 3, 4, null);
        tester.addValues(4, "subclassValue", new TestSubclass("v1", "v2"), new TestSubclass("v3", "v4"), null);
        tester.addValues(5, "booleanValue", true, false);
    }

    private static class MyObjectGenerator implements ObjectGenerator {

        private final int errorCode;

        public MyObjectGenerator(int errorCode) {
            this.errorCode = errorCode;
        }

        public Object generate(ObjectValues values) {
            ClassToTest result = new ClassToTest(errorCode);
            result.setStringValue1(values.getString("stringValue1"));
            result.setStringValue2(values.getString("stringValue2"));
            result.setIntValue(values.getInteger("intValue"));
            result.setIntegerValue(values.getInteger("integerValue"));
            result.setSubclassValue((TestSubclass)values.getObject("subclassValue"));
            result.setBooleanValue(values.getBoolean("booleanValue"));
            return result;
        }

    }

}
