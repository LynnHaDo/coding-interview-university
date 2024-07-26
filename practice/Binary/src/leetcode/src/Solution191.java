package leetcode.src;

public class Solution191 {
    /**
     * Takes the binary representation of a positive integer and returns the number of set bits it has
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int n_copy = n;
        int hammingWeight = 0;

        while (n_copy > 0) {
            hammingWeight += n_copy & 1; 
            n_copy >>= 1; // divide by 2
        }

        return hammingWeight;
    }
}
