# group-purchase

Alice, Bertrand, Clara and Desmond often make group purchases. They keep the list of their purchases in a csv file like this one:

```
item,unitp,qty,amount,buyer
pencils           ,  0.50 , 20  ,  10.00 , Bertrand
paper             ,  1.50 , 25  ,  37.50 , Alice
paper             ,  1.80 , 50  ,  90.00 , Desmond
laundry detergent ,  2.00 , 10  ,  20.00 , Clara
trash bags        ,  4.30 , 100 , 430.00 , Clara
gift cards        ,  8.00 , 1   ,   8.00 , Bertrand
lightbulbs        ,  1.00 , 10  ,  10.00 , Clara
~ shipping        , 40.00 , 1   ,  40.00 ,
~ total           ,       ,     , 645.50 ,
```

The last lines of the file contain the shipping fee and the grand total. Their `item` field begins with a `~`.

The shipping fee is not split into equal parts between buyers: rather it is attributed to each member in proportion to their purchase amount. In the example above, the total _without shipping_ is 605.50. Clara's purchases amount to 460.00 so her part of the shipping fee is 460.00 / 605.50 = 30.38, making her total part of the bill 460.00 + 30.38 = 490.38.

Write a program that reads a CSV file like the one above and outputs a CSV file of each person's total like the one below:

```
buyer,amount
Alice    ,  39.98
Bertrand ,  19.19
Clara    , 490.38
Desmond  ,  95.95
~ total  , 645.50
```
Note that the amounts are rounded and adjusted so that their sum is equal to the grand total.


## 1 Given a list containing 1 order, calculate the total bill for this buyer

    ORDER                                    |     BILL
    item          unit price  qty buyer      |   amount  buyer
    -----------------------------------------+------------------
    pencils             0.50  20  Bertrand   |  10.0    Bertrand

## 2 Given a list containing several orders for a same buyer, calculate the total bill for this buyer

    ORDER                                    |     BILL
    item          unit price  qty buyer      |   amount  buyer
    -----------------------------------------+------------------
    pencils             0.50  20  Bertrand   |  18.0    Bertrand
    gift cards          8.00   1  Bertrand   |   

## 3 Given a list of orders for several buyers and a shipping fee, the shipping is being distributed proportionally to each buyer's amount

SHIPPING FEE = 10.0
ORDER                                    |     BILL w/o shippin  |  with shipping
item          unit price  qty buyer      |   amount  buyer       |  amount
-----------------------------------------+----------------------------------------
pencils             0.50  20  Bertrand   |  18.0    Bertrand     |  23.45
gift cards          8.00   1  Bertrand   |                       |
paper               1.50  10  Clara      |  15.0    Clara        |  19.55

Total w/o shipping = 33.0
Bertrand's part of shipping = 18.0/33.0 * 10.0 = 5.45
Clara's part of shipping =    15.0/33.0 * 10.0 = 4.55
Grand total = 33.0 + 10 = 43.0

## 4 Given a distribution of the shipping fee, the sum of each buyers amount should equal the grand total
SHIPPING FEE = 1.0
ORDER                                    |     BILL w/o shippin  |  with shipping
item          unit price  qty buyer      |   amount  buyer       |  amount
-----------------------------------------+----------------------------------------
some item           33.0   1  Bertrand   |  33.0    Bertrand     |  33.33
some item           33.0   1  Alice      |  33.0    Alice        |  33.34
some item           33.0   1  Clara      |  33.0    Clara        |  33.33

Total w/o shipping = 99.0
each buyer's part of shipping = 33.0/99.0 * 1 = 0.333333333...
grand total = 100

apply cascade rounding to the list of amounts

## 5 On a buyer's birthday the shipping part for this buyer is zero, the shipping fee is offered for this buyer

SHIPPING FEE = 10.0
ORDER                                    |     BILL w/o shippin  |  with shipping
item          unit price  qty buyer      |   amount  buyer       |  amount
-----------------------------------------+----------------------------------------
pencils             0.50  20  Bertrand   |  18.0    Bertrand     |  23.45
gift cards          8.00   1  Bertrand   |                       |
paper               1.50  10  Clara      |  15.0    Clara        |  15.00

Bertrand's birthday is on the 23-Jan-1990
Clara's birthday is on the 19-Jul-1989
it is the 19-Jul

Bertrand's part of the shipping fee = his part, 5.45
Clara's part of the shipping fee = 0.0 offered by the shop
grand total is 23.45 + 15.00

