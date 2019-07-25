package rangeCheck;

import java.util.Arrays;

/**
 * Model:
 * Description:
 * <p>
 * This class is designed for checking multi range is has common range area.
 * e.g. There is 2-dimension double value array for limiting field range
 * if double[][] range = {{1,2},{3,4}} has no common range
 * but for double[][] range_error {{1,4},{2,3}} there has common range,
 * {1,4} has common range {2,3} for {2,3}
 * <p>
 * Author: 张立新
 * created: 2019/7/25
 */
public class RangeCheck {

    private byte[] axis = new byte[Short.MAX_VALUE];

    private int element = 0;

    public boolean validate(int[][] range) {
        fillAxis(range);
        check();
        return true;
    }

    // TODO 头尾大小检查
    private boolean fillAxis(int[][] range) {
        for (int[] r : range) {
            if (r.length != 2) {
                System.out.println("range length error");
                return false;
            }
            if (axis[r[0]] != 0) {
                System.out.println("head error" + Arrays.toString(r));
                return false;
            }
            axis[r[0]] = 1;

            if (axis[r[1]] != 0) {
                System.out.println("tail error" + Arrays.toString(r));
                return false;
            }
            axis[r[1]] = 2;
            element += 2;
        }
        return true;
    }

    private boolean check() {
        int count = 0;
        int cursor = 0;
        // 上一个游标
        int beforeCursor = -1;

        while (count <= element) {
            cursor++;
            if (axis[cursor] == 0) {
                continue;
            }
            count++;

            if (beforeCursor != -1 && axis[beforeCursor] == axis[cursor]) {
                System.out.println("conflict " + beforeCursor + " " + cursor);
                break;
            }

            beforeCursor = cursor;

        }
        System.out.println("satisfy range");
        return true;
    }
}
