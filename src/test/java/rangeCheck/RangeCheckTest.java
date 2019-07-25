package rangeCheck;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RangeCheckTest {


    @Test
    public void test_1_satisfy() {
        RangeCheck rangeCheck = new RangeCheck();
        int[][] array = {{1, 3}, {5, 7}, {9, 11}, {13, 15}};
        rangeCheck.validate(array);
    }

    @Test
    public void test_2_head_conflict() {
        RangeCheck rangeCheck = new RangeCheck();
        int[][] array = {{1, 3}, {5, 7}, {5, 11}, {13, 15}};
        rangeCheck.validate(array);
    }

    @Test
    public void test_2_tail_conflict() {
        RangeCheck rangeCheck = new RangeCheck();
        int[][] array = {{1, 3}, {5, 11}, {9, 11}, {13, 15}};
        rangeCheck.validate(array);
    }

    @Test
    public void test_3_head_tail_conflict() {
        RangeCheck rangeCheck = new RangeCheck();
        int[][] array = {{1, 3}, {5, 7}, {7, 11}, {13, 15}};
        rangeCheck.validate(array);
    }

    @Test
    public void test_4_range() {
        RangeCheck rangeCheck = new RangeCheck();
        int[][] array = {{1, 3}, {5, 7}, {6, 11}, {13, 15}};
        rangeCheck.validate(array);
    }

}