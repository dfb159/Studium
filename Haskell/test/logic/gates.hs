module GateLogic where

land :: Bool -> Bool -> Bool
land = (&&)

lor :: Bool -> Bool -> Bool
lor = (||)

lnot :: Bool -> Bool
lnot = not

lxor :: Bool -> Bool -> Bool
lxor = (/=)

lswitch :: Int -> [Bool] -> Bool
lswitch n l = (n == binToDec l)

binToDec :: [Bool] -> Int
binToDec = binToDecRec 1

binToDecRec :: Int -> [Bool] -> Int
binToDecRec _ [] = 0
binToDecRec k (b:bs) = (if b then k else 0) + (binToDecRec (2*k) bs)

decToBin :: Int -> [Bool]
decToBin n
  | n == 0 = []
  | mod n 2 == 0 = False : decToBin (floor (fromIntegral (n/2)) :: Int)
  | otherwise = True : decToBin (floor (n/2) :: Int)

