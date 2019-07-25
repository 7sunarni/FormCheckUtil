package rangeCheck;

public class RangeNode {
    public static final byte HEAD = 1;
    public static final byte TAIL = 2;
    private double value;
    private byte type;
    private RangeNode nextNode;

    public RangeNode(double value, byte type) {
        this.value = value;
        this.type = type;
    }

    public RangeNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(RangeNode nextNode) {
        this.nextNode = nextNode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}


