import time

def BM(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	shifts = 0
	a = 256 # ASCII alphabet size

	# preprocessing - Bad Character Heuristic
	badChar = [-1]*a
	for i in range(m):
		badChar[ord(pattern[i])] = i

	# actual searching
	i = 0
	while(i <= n-m):
		j = m-1
		while j >= 0 and pattern[j] == text[i+j]:
			j -= 1
			shifts += 1
			
		if j < 0:
			print(f"Pattern found at index {i}")
			i += (m-badChar[ord(text[i+m])] if i+m < n else 1)
		else:
			i += max(1, j-badChar[ord(text[i+j])])
		shifts += 1

	endTime = time.time()
	runningTime = endTime - startTime
	throughput = n / runningTime if runningTime > 0 else float('inf')
	print(shifts)

BM("aaba", "aabaacaadaabaaba")