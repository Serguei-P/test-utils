package serguei.test_utils.equals;

import org.junit.Test;

import serguei.test_utils.equals.EqualsTester;
import serguei.test_utils.equals.ObjectGenerator;
import serguei.test_utils.equals.ObjectValues;

public class EqualsTest {

    @Test
    public void shouldCheckEquals() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(ClassToTest.Error.NONE));
        setValues(tester);

        tester.test();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenFieldMissing() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(ClassToTest.Error.MISSING_FIELD));
        setValues(tester);

        tester.test();
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenNoCheckForNull() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(ClassToTest.Error.NO_NULL));
        setValues(tester);

        tester.test();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenNoWrongHash() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(ClassToTest.Error.WRONG_HASH));
        setValues(tester);

        tester.test();
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenNotEqualsToItself() {
        EqualsTester tester = new EqualsTester(new MyObjectGenerator(ClassToTest.Error.NOT_EQUAL_TO_ITSELF));
        setValues(tester);

        tester.test();
    }

    private void setValues(EqualsTester tester) {
        tester.addStringValues("stringValue1");
        tester.addStringValuesWithNulls("stringValue2");
        tester.addIntegerValues("intValue");
        tester.addIntegerValues("integerValue");
        tester.addValues("subclassValue", new TestSubclass("v1", "v2"), new TestSubclass("v3", "v4"), null);
        tester.addBooleanValues("booleanValue");
        tester.addLongValues("longValue");
    }

    private static class MyObjectGenerator implements ObjectGenerator {

        private final ClassToTest.Error errorCode;

        public MyObjectGenerator(ClassToTest.Error errorCode) {
            this.errorCode = errorCode;
        }

        public Object generate(ObjectValues values) {
            ClassToTest result = new ClassToTest(errorCode);
            result.setStringValue1(values.getString("stringValue1"));
            result.setStringValue2(values.getString("stringValue2"));
            result.setIntValue(values.getInt("intValue"));
            result.setIntegerValue(values.getInteger("integerValue"));
            result.setSubclassValue((TestSubclass)values.getObject("subclassValue"));
            result.setBooleanValue(values.getBool("booleanValue"));
            result.setLongValue(values.getLong("longValue"));
            return result;
        }

    }

}
