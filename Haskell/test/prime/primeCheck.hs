module PrimeCheck where

primePart :: Int -> [Int]
primePart 1 = []
primePart x = prime : primePart (round (x/prime))
  where prime = getLowestPrime x 2

getLowestPrime a k
  | k > a = 0
  | a `mod` k == 0 = k
  | otherwise = getLowestPrime a (k+1)
