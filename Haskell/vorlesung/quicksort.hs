dc :: (a -> Bool) -> (a -> b) -> (a -> [a]) -> ([b] -> b) -> a -> b
dc isSimple solve partition combine problem = 
  if isSimple problem then solve problem
  else combine (map (dc isSimple solve partition combine) (partition problem))

quicksort :: (Ord a) => [a] -> [a]
quicksort l = dc test id split flatten l

test :: [a] -> Bool
test [] = True
test [x] = True
test l = False

split :: (Ord a) => [a] -> [[a]]
split (x:l) = [filter (<x) l, [x], filter (>x) l]

flatten :: [[a]] -> [a]
flatten [l1, lx, l2] = append (append l1 lx) l2

append :: [a] -> [a] -> [a]
append [] l = l
append (x:l1) l2 = x : append l1 l2

