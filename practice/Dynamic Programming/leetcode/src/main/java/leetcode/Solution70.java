package src;

public class Solution70 {
    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] numWays = new int[n+1];
        numWays[0] = 0;
        numWays[1] = 1;
        numWays[2] = 2;

        for (int i = 3; i < n+1; i++) {
            numWays[i] = 0;
        }
        
        for (int i = 3; i < n+1; i++) {
            numWays[i] = numWays[i-1] + numWays[i-2];
        }

        return numWays[n];
    }
}
