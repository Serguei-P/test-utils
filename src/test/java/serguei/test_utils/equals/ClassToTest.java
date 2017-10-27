package serguei.test_utils.equals;


public class ClassToTest {

    private final int errorCode;
    private String stringValue1;
    private String stringValue2;
    private int intValue;
    private Integer integerValue;
    private TestSubclass subclassValue;
    private boolean booleanValue;

    public ClassToTest(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getStringValue1() {
        return stringValue1;
    }

    public void setStringValue1(String stringValue1) {
        this.stringValue1 = stringValue1;
    }

    public String getStringValue2() {
        return stringValue2;
    }

    public void setStringValue2(String stringValue2) {
        this.stringValue2 = stringValue2;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public TestSubclass getSubclassValue() {
        return subclassValue;
    }

    public void setSubclassValue(TestSubclass subclassValue) {
        this.subclassValue = subclassValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + intValue;
        result = prime * result + ((integerValue == null) ? 0 : integerValue.hashCode());
        result = prime * result + ((stringValue1 == null) ? 0 : stringValue1.hashCode());
        result = prime * result + ((stringValue2 == null) ? 0 : stringValue2.hashCode());
        result = prime * result + ((subclassValue == null) ? 0 : subclassValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ClassToTest other = (ClassToTest)obj;
        if (intValue != other.intValue) {
            return false;
        }
        if (integerValue == null) {
            if (other.integerValue != null) {
                return false;
            }
        } else if (!integerValue.equals(other.integerValue)) {
            return false;
        }
        if (errorCode != 1) {
            if (stringValue1 == null) {
                if (other.stringValue1 != null) {
                    return false;
                }
            } else if (!stringValue1.equals(other.stringValue1)) {
                return false;
            }
        }
        if (stringValue2 == null) {
            if (other.stringValue2 != null) {
                return false;
            }
        } else if (!stringValue2.equals(other.stringValue2)) {
            return false;
        }
        if (subclassValue == null) {
            if (other.subclassValue != null) {
                return false;
            }
        } else if (!subclassValue.equals(other.subclassValue)) {
            return false;
        }
        if (booleanValue != other.booleanValue) {
            return false;
        }
        return true;
    }

}
