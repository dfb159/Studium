first10 x = [ x*n | n <- [1..10]]
first10Smaller x y = [ x*n | n <- [1..10], x*n < y]
remainder x y l = [ z | z <- l, z `mod` x == y]
boomBang l = [ if x `mod` 5 == 3 then "BOOM" else ( if x `mod` 3 == 1 then "BANG" else "ZZZZ" ) | x <- l, odd x]
removeNonUppercase str = [c | c <- str, c `elem` ['A'..'Z'] || c == ' ']
removeOdd l = [ [ x | x <- xs, even x] | xs <- l]

sum' :: (Num a) => [a] -> a
sum' [] = 0
sum' (x:xs) = x + sum' xs

length' :: (Num b) => [a] -> b
length' [] = 0
length' (_:xs) = 1 + length' xs

max' :: (Ord a) => a -> a -> a
max' a b
  | a > b  = a
  | otherwise = b

compateTo :: (Ord a) => a -> a -> Ordering
a `compareTo` b
  | a > b = GT
  | a == b = EQ
  | otherwise = LT
