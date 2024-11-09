from Naive import naive
from KMP import KMP
from BM_BadChar import BM
from RK import RK
from BNDM_Fixed import BNDM

pattern = "aaba"
text = "aabaacaadaabaaba"

print("naive")
naive(pattern, text)
print("KMP")
KMP(pattern, text)
print("BM")
BM(pattern, text)
print("RK")
RK(pattern, text, 101)
print("BNDM")
BNDM(pattern, text)