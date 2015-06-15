public class Solution {
    public int candy(int[] ratings) {
        int chNum = ratings.length;
        int[] candies = new int[ratings.length];
        for(int i = 0; i < chNum; i++) {
            candies[i] = 1;
        }
        for(int i = 1; i < chNum; i++) {
            if(ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for(int i = chNum - 1; i > 0; i--) {
            if(ratings[i - 1] > ratings[i] && candies[i - 1] <= candies[i]) {
                candies[i - 1] = candies[i] + 1;
            }
        }
        int sum = 0;
        for(int i = 0; i < chNum; i++) {
            sum += candies[i];
        }
        return sum;
    }
}