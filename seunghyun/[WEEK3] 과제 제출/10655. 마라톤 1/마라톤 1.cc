#include <stdio.h>
#include <math.h>

struct p {
    int x;
    int y;
}Point[100000];

int main() {
    int n, x, y, min;
    int len[100000];
    int sum = 0;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d %d", &x, &y);
        Point[i].x = x;
        Point[i].y = y;
    }
    for (int i = 0; i < n - 1; i++) {
        len[i] = abs(Point[i].x - Point[i + 1].x) + abs(Point[i].y - Point[i + 1].y);
        sum += len[i];
    }
    min = sum;
    for (int i = 1; i < n - 1; i++) {
        int temp = sum;
        temp -= len[i-1];
        temp -= len[i];
        temp += abs(Point[i - 1].x - Point[i + 1].x) + abs(Point[i - 1].y - Point[i + 1].y);
        if (temp < min)
            min = temp;
    }
    printf("%d", min);
    return 0;
}