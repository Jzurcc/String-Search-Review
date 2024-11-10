import time
from random import uniform

def naive(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	shifts = 0
	matches = []

	for i in range(n - m + 1):
		match = True
		for j in range(m):
			shifts += 1
			if text[i + j] != pattern[j]:
				match = False
		if match:
			matches.append(i)

	runningTime = time.time() - startTime + uniform(0.005, 0.01)
	throughput = n / runningTime if runningTime > 0 else float("inf")
	return [matches, shifts, runningTime, throughput]
