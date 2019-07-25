package rangeCheck;

public class Range {

    private RangeNode currentNode = null;

    public boolean addNode(RangeNode node) {
        if (currentNode == null) {
            currentNode = node;
        } else {
            currentNode.setNextNode(node);
            currentNode = node;
        }
        return true;
    }

}


