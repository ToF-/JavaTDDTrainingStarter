# Checksum

we need a function to validate account numbers

an account number is a nine digit string : c1, c2, c3, c4, c5, c6, c7, c8, c9.

the account number is said to be valid if its check sum is a multiple of 11.

the check sum is calculated like this
(c1*9 + c2*8 + c3*7 + c4*6 + c5*5 + c6*4 + c7*3 + c8 * 2 + c9).

Here are examples:

    000000000 : 0 modulo 11 = 0
    130000000 : 1 * 9 + 3 * 8 = 33, 33 modulo 11 = 0
    000000051
    123456789
    490867715
    999999990
