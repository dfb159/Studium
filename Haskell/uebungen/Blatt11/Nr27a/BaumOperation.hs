module BaumOperation where

import DC

isSimple :: Baum -> Bool
isSimple (Blatt _) = True
isSimple _ = False

solve :: Baum -> Int
solve (Blatt n) = n

partition :: Baum -> [Baum]
partition (Zweig b1 b2) = [b1, b2]

combine :: [Int] -> Int
combine = maximum'

solveIt :: Baum -> Int
solveIt = dc isSimple solve partition combine

maximum' :: [Int] -> Int
--von positiven Zahlen
maximum' [] = 0
maximum' (x:xs) = max x (maximum' xs)
