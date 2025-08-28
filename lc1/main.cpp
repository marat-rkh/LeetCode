#include <algorithm>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    static vector<int> twoSum(vector<int>& nums, int target) {
        vector<pair<int,int>> nums2;
        nums2.reserve(nums.size());
        for (int i = 0; i < static_cast<int>(nums.size()); ++i) {
            nums2.emplace_back(nums[i], i);
        }

        sort(nums2.begin(), nums2.end(), [](const pair<int,int>& x, const pair<int,int>& y) {
            return x.first < y.first;
        });
        for (int i = 0; i < static_cast<int>(nums2.size()); i++) {
            int diff = target - nums2[i].first;
            auto it = lower_bound(nums2.begin() + i + 1, nums2.end(), diff, [](const pair<int,int>& p, int v) { 
                return p.first < v; 
            });
            if (it != nums2.end() && it->first == diff) {
                return vector<int>{nums2[i].second, it->second};
            }
        }
        return vector<int>{};
    }
};

int main() {
    vector<int> nums{3,3};
    int target = 6;
    auto res = Solution::twoSum(nums, target);
    if (res.size() == 2) {
        cout << "[" << res[0] << "," << res[1] << "]" << endl;
    } else {
        cout << "no solution (unexpected)" << endl;
    }
    return 0;
}
