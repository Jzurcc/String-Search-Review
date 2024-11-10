import time

def naive(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	shifts = 0

	for i in range(n - m + 1):
		time.sleep(0.001)
		match = True
		for j in range(m):
			shifts += 1
			if text[i + j] != pattern[j]:
				match = False
		# if match:
			# print(f"Pattern found at index {i}")

	runningTime = time.time() - startTime
	throughput = shifts / runningTime if runningTime > 0 else float("inf")
	print(f"{shifts} / {runningTime} = {throughput}")
for i in range(10):
	naive("aaba", "aabaacaadaabaaba")