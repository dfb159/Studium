module Uebung20170113 where

or' :: Bool -> Bool -> Bool
or' True _ = True
or' _ x = x

strict_or :: Bool -> Bool -> Bool
strict_or False False = False
strict_or _ _ = True

filter' :: (a -> Bool) -> [a] -> [a]
filter' f l = [x | x <- l, f x]

filterRec :: (a -> Bool) -> [a] -> [a]
filterRec _ [] = []
filterRec f (x:xs)
  | f x = x : filterRec f xs
  | otherwise = filterRec f xs

