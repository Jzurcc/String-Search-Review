import time
		
def KMP(pattern, text):
	startTime = time.time()
	m = len(pattern)
	n = len(text)
	shifts = 0

	# preprocessing - Calculating LPS
	length = 0
	LPS = [0] * m
	i = 1
	while i < m:
		if pattern[i]== pattern[length]:
			length += 1
			LPS[i] = length
			i += 1
		else:
			if length != 0:
				length = LPS[length-1]
			else:
				LPS[i] = 0
				i += 1

	# actual searching
	i = j = 0 
	while i < n:
		shifts += 1
		if pattern[j] == text[i]:
			i += 1
			j += 1

		if j == m:
			print(f"Pattern found at index {i-j}")
			j = LPS[j-1]
		elif i < n and pattern[j] != text[i]:
			if j != 0:
				j = LPS[j-1]
			else:
				i += 1
				
	endTime = time.time()
	runningTime = endTime - startTime
	throughput = n / runningTime if runningTime > 0 else float('inf')
	print(runningTime)

KMP("aaba", "aabaacaadaabaaba")