module PrimeGen where

prime :: Int -> Int -> [Int]
prime x y = filter (isPrime) [x..y]

isPrime :: Int -> Bool
isPrime x = 
