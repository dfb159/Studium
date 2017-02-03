module BaumOperation where

import DC

isSimple :: Baum -> Bool
isSimple (Blatt _) = True
isSimple _ = False

solve :: Baum -> Int
solve _ = 0

partition :: Baum -> [Baum]
partition (Zweig b1 b2) = [b1, b2]

combine :: [Int] -> Int
combine l = (maximum' l) + 1

solveIt :: Baum -> Int
solveIt = dc isSimple solve partition combine

maximum' :: [Int] -> Int
--von positiven Zahlen
maximum' [] = 0
maximum' (x:xs) = max x (maximum' xs)

