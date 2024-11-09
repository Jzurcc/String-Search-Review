import time
# BNDM supports patterns of up to 32 characters only
def BNDM(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	a = 256  
	comparisons = 0

	# Preprocessing phase
	B = [0] * a
	s = 1
	for i in range(m - 1, -1, -1):
		B[ord(pattern[i])] |= s
		s <<= 1

	# Searching phase
	i = 0
	matches = 0
	while i <= n - m:
		k = m - 1
		last = m
		d = ~0
		while k >= 0 and d != 0:
			comparisons += 1
			d &= B[ord(text[i + k])]
			k -= 1
			if d != 0:
				if k >= 0:
					last = k + 1
				else:
					matches += 1
					print(f"Pattern found at index {i}")
			d <<= 1
		i += last

	endTime = time.time()
	runningTime = endTime - startTime
	throughput = n / runningTime if runningTime > 0 else float('inf')
BNDM("world", "hello world")