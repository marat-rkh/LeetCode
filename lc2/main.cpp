#include <algorithm>
#include <vector>
#include <iostream>
#include <initializer_list>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *res_head = nullptr;
        ListNode *res_tail = nullptr;
        int extra = 0;
        while (l1 && l2) {
            int sum = l1->val + l2->val + extra;
            if (sum >= 10) {
                sum = sum - 10;
                extra = 1;
            } else {
                extra = 0;
            }
            auto res_node = new ListNode(sum);
            if (!res_head) {
                res_head = res_tail = res_node;
            } else {
                res_tail->next = res_node;
                res_tail = res_node;
            }
            l1 = l1->next;
            l2 = l2->next;
        }
        for (auto l3 = l1 ? l1 : l2; l3; l3 = l3->next) {
            int sum = l3->val + extra;
            if (sum >= 10) {
                sum = sum - 10;
                extra = 1;
            } else {
                extra = 0;
            }
            auto res_node = new ListNode(sum);
            res_tail->next = res_node;
            res_tail = res_node;
        }
        if (extra) {
            auto res_node = new ListNode(1);
            res_tail->next = res_node;
            res_tail = res_node;
        }
        return res_head;
    }
};

static ListNode* buildList(initializer_list<int> vals) {
    ListNode* head = nullptr;
    ListNode* tail = nullptr;
    for (int v : vals) {
        auto* node = new ListNode(v);
        if (!head) {
            head = tail = node;
        } else {
            tail->next = node;
            tail = node;
        }
    }
    return head;
}

static void printList(const ListNode* head) {
    cout << "[";
    for (auto p = head; p; p = p->next) {
        cout << p->val;
        if (p->next) cout << ",";
    }
    cout << "]";
}

void test1() {
    Solution s;
    auto res = s.addTwoNumbers(buildList({2, 4, 3}), buildList({5, 6, 4}));
    printList(res);
    cout << '\n';
}

void test2() {
    Solution s;
    auto res = s.addTwoNumbers(buildList({0}), buildList({0}));
    printList(res);
    cout << '\n';
}

void test3() {
    Solution s;
    auto res = s.addTwoNumbers(buildList({9,9,9,9,9,9,9}), buildList({9,9,9,9}));
    printList(res);
    cout << '\n';
}

int main() {
    test1();
    test2();
    test3();
    return 0;
}
