import time

def BNDM(pattern, text):
    n = len(text)
    m = len(pattern)
    comparisons = 0
    startTime = time.time()

    # preprocessing
    B={-1:0}
    m = len(pattern)
    i=1
    for i in range(m-1,-1,-1):
        B[pattern[i]] = B.get(pattern[i],0) | i

    # actual searching
    i = 0
    while (i <= n-m):
        j = m - 1
        last = m 
        d = ~0
        while (d != 0 and j >= 0):
            comparisons += 1
            if text[i+j] in B: 
                d = d & B[text[i+j]]    
            else:
                d = d & 0
            j = j - 1
            if d != 0:
                if (j >= 0):
                    last = j + 1
                else:
                    print(f"Pattern found at index {i}")
            d = d << 1
        i = i + last
    
    endTime = time.time()
    runningTime = endTime - startTime
    throughput = n / runningTime if runningTime > 0 else float('inf')
    print(startTime)