# https://open.kattis.com/problems/tarifa

cap = int(input())
print(sum([cap - int(input()) for x in range(int(input()))] + [cap]))
