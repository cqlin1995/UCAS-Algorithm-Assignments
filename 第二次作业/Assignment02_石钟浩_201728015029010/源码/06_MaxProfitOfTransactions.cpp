
// profits[i][j] 表示第i次交易时，0到j天可以获得的最大收益
// profits[0][j] = 0，因为0次交易时必然没有收益
// profits[i][0] = 0，因为只有1天的股票价格时，不能进行一次完整的交易(买和卖)，收益为0
// profits[i][j] = max(profits[i][j - 1], prices[j] - profits[jj] + profits[i - 1][jj])
//               = max(profits[i][j - 1], prices[j] + max(profits[i - 1][jj] - profits[jj])), jj < j and j >= 1

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (prices.size() < 2)
            return 0;
        int maxProf = 0;    // 最大收益
        int K = 2;  // K表示最多可交易的次数

        vector<vector<int>> profits(K + 1, vector<int>(prices.size(), 0));
        for (int i = 1; i <= K; i++) {
            int tmpMax = profits[i - 1][0] - prices[0];
            for (int j = 1; j < prices.size(); j++) {
                profits[i][j] = max(profits[i][j - 1], prices[j] + tmpMax);
                tmpMax = max(tmpMax, profits[i - 1][j] - prices[j]);
                maxProf = max(profits[i][j], maxProf);
            }
        }
        return maxProf;
    }
};