#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <algorithm>
using namespace std;

int N;
vector<int> p, q;

int main() {
    cin >> N;
    vector<int> p(N), q(N);
    for (int i = 0; i < N; ++i) cin >> p[i], --p[i];
    for (int i = 0; i < N; ++i) cin >> q[i], --q[i];

    // 各順列が何番目かを求める
    map<vector<int>, int> ord;
    int iter = 0;

    // スタートとなる順列
    vector<int> v(N);
    for (int i = 0; i < N; ++i) v[i] = i;

    // 順番に
    do {
        ord[v] = iter++;
    } while (next_permutation(v.begin(), v.end()));

    cout << abs(ord[p] - ord[q]) << endl;
}