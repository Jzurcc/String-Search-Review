import time

def RK(pattern, text): 
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	i = j = p = t = 0
	h = 1
	a = 256
	q = 11 # q is any prime number
	shifts = 0
	matches = []

	# preprocessing
	for i in range(m-1):
		h = (h * a) % q

	for i in range(m):
		p = (a * p + ord(pattern[i])) % q
		t = (a * t + ord(text[i])) % q

	# actual searching
	for i in range(n - m + 1):
		if p == t:
			for j in range(m):
				shifts += 1
				if text[i + j] != pattern[j]:
					break
				else:
					j += 1
			if j == m:
				matches.append(i)
				# print(f"Pattern found at index {i}")
		if i < n - m:
			t = (a * (t - ord(text[i]) * h) + ord(text[i + m])) % q
			if t < 0:
				t += q

	endTime = time.time()
	runningTime = endTime - startTime
	throughput = n / runningTime if runningTime > 0 else float('inf')
	return [matches, shifts, runningTime, throughput]
