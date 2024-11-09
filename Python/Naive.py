import time

def naive(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	comparisons = 0

	for i in range(n - m + 1):
		match = True
		for j in range(m):
			comparisons += 1
			if text[i + j] != pattern[j]:
				match = False
		if match:
			print(f"Pattern found at index {i}")

	endTime = time.time()
	runningTime = endTime - startTime
	throughput = n / runningTime if runningTime > 0 else float('inf')
	print((endTime - startTime) * 1000)

naive("aaba", "aabaacaadaabaaba")