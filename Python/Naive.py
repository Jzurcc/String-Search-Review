import time

def naive(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	comparisons = 0

	for i in range(n - m + 1):
		j = 0
		while j < m and text[i + j] == pattern[j]:
			j += 1
			comparisons += 1 
		
		if j == m:
			print(f"Pattern found at index {i}")
			comparisons += 1 

	endTime = time.time()
	runningTime = endTime - startTime
	throughput = n / runningTime if runningTime > 0 else float('inf')

naive("world", "hello world")