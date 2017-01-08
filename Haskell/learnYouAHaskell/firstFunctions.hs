doubleMe x = x + x
doubleUs x y = doubleMe x + doubleMe y
doubleSmallNumber x = 
  if x > 100
    then x
    else doubleMe x
doubleSmallNumber' x = doubleSmallNumber x + 1

bmiTell :: (RealFloat a) => a -> a -> String
bmiTell weight height
  | bmi <= skinny = "You're underweight, you emo, you!"
  | bmi <= normal = "You're just perfectly fine, just ugly as hell I guess"
  | bmi <= fat = "You're fat! Just rediculus. Lose some fcking weight mate"
  | otherwise = "Congratulations, you won! You're a whale!" 
  where
    bmi = weight / height^2
    skinny = 18.5
    normal = 25.0
    fat = 30.0

calcBmis :: (RealFloat a) => [(a, a)] -> [a]
calcBmis xs = [bmi | (w, h) <- xs, let bmi = w / h^2]

maximum' :: (Ord a) => [a] -> a
maximum'[] = error "maximum of empty list"
maximum' [x] = x
maximum' (x:xs) = max x (maximum' xs)

replicate' :: (Num i, Ord i) => i -> a -> [a]
replicate' n x
  | n <= 0 = []
  | otherwise = x:replicate' (n-1) x

take' :: (Num i, Ord i) => i-> [a] -> [a]
take' n _
  | n <= 0 = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

repeat' :: a -> [a]
repeat' x = x:repeat' x

zip' ::  [a] -> [b] -> [(a,b)]
zip' _ [] = []
zip' [] _ = []
zip' (x:xs) (y:ys) = (x,y):zip' xs ys

elem' :: (Eq a) => a -> [a] -> Bool
elem' a [] = False
elem' a (x:xs)
  | a == x = True
  | otherwise = a `elem'` xs

quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) =
  let smallerSorted = quicksort (filter ( <= x) xs)
      biggerSorted = quicksort (filter ( > x) xs)
  in  smallerSorted ++ [x] ++ biggerSorted

divideByThree :: (Floating a) => a -> a
divideByThree = (/3)

isUpperAlpha :: Char -> Bool
isUpperAlpha = (`elem` ['A'..'Z'])

applyTwice :: (a -> a) -> a -> a
applyTwice f x = f (f x)

zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (x:xs) (y:ys) = f x y : zipWith' f xs ys

flip' :: (a -> b -> c) -> (b -> a -> c)
flip' f x y = f y x

map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = f x : map f xs

filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' f (x:xs)
  | f x = x : filter' f xs
  | otherwise = filter' f xs

largestDivisible :: (Integral a) => a -> a -> a
largestDivisible n m = head (filter p [n, (n-1)..])
  where p x = x `mod` m == 0

oddSquares :: (Integral a, Ord a) => a -> a
oddSquares n = sum (takeWhile (<n) (filter odd (map (^2) [1..])))

collatzSeq :: (Integral a) => a -> [a]
collatzSeq 1 = [1]
collatzSeq n
  | even n = n : collatzSeq (n `div` 2)
  | odd n  = n : collatzSeq (n * 3 + 1)

numLongCollatz :: Int -> Int -> Int
numLongCollatz n m = length (filter (\xs -> length xs > m) (map collatzSeq [1..n]))

sumFold :: (Num a) => [a] -> a
sumFold = foldl (+) 0

elemFold :: (Eq a) => a -> [a] -> Bool
elemFold y ys = foldl (\acc x -> if x == y then True else acc) False ys

mapFold :: (a -> b) -> [a] -> [b]
mapFold f xs = foldr (\x acc -> f x:acc) [] xs


