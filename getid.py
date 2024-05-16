with open('testdata', 'r') as file:
    a = [line.strip().split('\"')[1] for line in file]
    b = [int(x) for x in a]
c = []
for i in b:
    if i not in c:
        c.append(i)
    else:
        print(i)

