package test.interview;

import com.sun.tools.javac.util.Assert;

import java.util.Objects;

/**
 * @author chenkechao
 * @date 2020/2/29 9:58 下午
 */
public class Solution125 {

    public static void main(String[] args) {
        boolean result = new Solution125().isPalindrome("race a car");
        System.out.println(result);
    }

    public boolean isPalindrome(String s) {
        Assert.checkNonNull(Objects.nonNull(s));
        if (s.length() == 0) {
            return true;
        }

        int head = 0;
        int tail = s.length() - 1;
        // [48...57] 数字
        // [65...90] 大写字母
        // [97...122] 小写字母
        while (head < tail) {
            char i = s.charAt(head);
            char j = s.charAt(tail);
            if (!(isNumberChar(i) || isLowerChar(i) || isUpperChar(i))) {
                head++;
                continue;
            }
            if (!(isNumberChar(j) || isLowerChar(j) || isUpperChar(j))) {
                tail--;
                continue;
            }

            if (!isNumberChar(i) && !isNumberChar(j)) {
                //字母
                if (isUpperChar(i)) {
                    if (isUpperChar(j)) {
                        //同时为小写
                        if (i == j) {
                            head++;
                            tail--;
                        } else {
                            return false;
                        }
                    } else {
                        // j 为 大写
                        if ((i + 32) == j) {
                            head++;
                            tail--;
                        } else {
                            return false;
                        }
                    }
                }
                else {
                    if (isLowerChar(j)) {
                        //同时为小写
                        if (i == j) {
                            head++;
                            tail--;
                        } else {
                            return false;
                        }
                    } else {
                        // j 为 大写
                        if (i == (j + 32)) {
                            head++;
                            tail--;
                        } else {
                            return false;
                        }
                    }
                }

            } else {
                if (i == j) {
                    head++;
                    tail--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumberChar(char c) {
        return c >= 48 && c <= 57;
    }

    private boolean isUpperChar(char c) {
        return c >= 65 && c <= 90;
    }

    private boolean isLowerChar(char c) {
        return c >= 97 && c <= 122;
    }
}
