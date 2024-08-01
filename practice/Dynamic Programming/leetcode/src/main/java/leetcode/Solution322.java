package src;

public class Solution322 {
    /**
     * Idea 1: Greedy -> Does not work 
     * Counterexample: coins = {1, 3, 4, 5}, amount = 7)
     * Optimal: 3 + 4 (2 coins). Greedy would go from 5 to 2 1's => 3 coins 
    */

    /**
     * Idea 2: Recursion (DFS) -> Not optimal (overlapping subproblems)
     * Example: coins = {1, 3, 4, 5}, amount = 7. Go thru each coin choice, calculate the remaining amount and add it as the child node. 
     * Each child node has 4 children. Continue until the remaining amount is negative.
    */

    /**
     * Idea 3: Dynamic programming
     * Store the min number of coins needed 
    */
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 1 && amount % coins[0] == 0) {
            return amount/coins[0];
        } 

        if (coins.length == 1 && amount % coins[0] != 0) {
            return -1;
        } 

        int[] minCoins = new int[amount+1];
        minCoins[0] = 0; // need 0 coins for 0 amount

        // Initialize
        for (int i = 1; i < amount + 1; i++) {
            minCoins[i] = amount + 1; // invalid amount
        }

        // Compute the min coin 
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                // Still being able to add
                if (i - coin >= 0) {
                    minCoins[i] = Math.min( minCoins[i], // not adding the coin
                                            minCoins[i-coin] + 1); // adding the coin
                }
            }
        }

        return minCoins[amount];
    }

    public static void main(String[] args) {
        Solution322 solution = new Solution322();
        int[] coins = {186,419,83,408};
        int[] coins2 = {2};
        int[] coins3 = {1};

        System.out.println(solution.coinChange(coins, 6249));
        System.out.println(solution.coinChange(coins2, 3));
        System.out.println(solution.coinChange(coins3, 0));
    }
}