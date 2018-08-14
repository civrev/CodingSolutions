# https://open.kattis.com/problems/nastyhacks


cases = int(input())
for i in range(cases):
    line = input().split()
    r,e,c = [int(x) for x in line]
    line = [r,e,c]
    if r==(e-c):
        print("does not matter")
    elif r<(e-c):
        print("advertise")
    else:
    print("do not advertise")
