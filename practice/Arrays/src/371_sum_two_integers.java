class Solution371 {
    public int getSum(int a, int b) {
        
        while (b != 0) {
            int tmp = (a & b) << 1; // left shift the carry to the next digit addition
            a = a ^ b; // 0 ^/+ 0 = 0; 1 ^/+ 0 = 1; 1 ^/+ 1 = 0
            b = tmp; 
        }

        return a;
    }

    public static void main(String[] args){
        Solution371 solution = new Solution371();
        int a = 2;
        int b = 3;
        System.out.println(solution.getSum(a, b));
    }
}
