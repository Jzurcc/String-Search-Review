from Naive import naive
from KMP import KMP
from BM_BadChar import BM
from RK import RK
from BNDM_Fixed import BNDM
from statistics import fmean
from random import uniform

pattern = "aaba"
text = "aabaacaadaabaaba"

def format(name, func):
    matches = []
    shifts = []
    runningTime = []
    throughput = []
    for i in range(100):
        f = func(pattern, text)
        for j in f[0]:
            if j not in matches:
                matches.append(j)
        
        if f[1] not in shifts:
            shifts.append(f[1])
        runningTime.append(f[2])
        throughput.append(f[3])
    
    print(f'{name} {'-'*(50-len(name))}')
    
    print('Matches at index/es: ', end='')
    print(*matches, sep=', ')
    print(f'No. of shifts: {'' if len(shifts) == 1 else '[has varying shifts!] '}', end='')
    print(*shifts, sep=', ')
    print(f'running time: {fmean(runningTime)}')
    print(f'throughput: {fmean(throughput)}')


format('Naive', naive)
# format('KMP')
# format('BM')
# format('RK')
# format('BNDM')
