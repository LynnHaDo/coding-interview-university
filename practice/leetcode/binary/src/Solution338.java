package leetcode.src;

public class Solution338 {

    /**
     * Slow way
     * 
     * Given an integer n, return an array ans of length n + 1 
     * such that for each i (0 <= i <= n), 
     * ans[i] is the number of 1's in the binary representation of i.
     * 
     * @param n integer for which the function generates the list of 1 bits for
     * @return array ans of length n + 1 such that for each i (0 <= i <= n), 
     * ans[i] is the number of 1's in the binary representation of i.
     */
    public int[] countBits(int n) {
        int[] numOneBits = new int[n+1];
        numOneBits[0] = 0;
        
        if (n == 0) {
            return numOneBits;
        }

        numOneBits[1] = 1;

        for (int i = 2; i <= n; i++) {
            // If i is even, i is just i/2 with an extra 0 at the end => they have equal number of 1s
            if (i % 2 == 0) {
                numOneBits[i] = numOneBits[i / 2];
            }
            // If i is odd, i = (i-1)+1 where i-1 is an even number (case 1), plus an additional 1 at the end
            else {
                numOneBits[i] = numOneBits[Math.floorDiv(i, 2)] + 1;
            }
        }

        return numOneBits;
    }
}