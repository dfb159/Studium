module ZahlenFolge where

zahlenFolge :: [Int]
zahlenFolge = filter (checkMod [5,7,9]) [1..]

checkMod :: [Int] -> Int -> Bool
checkMod l x = foldl (||) False (map (\ m -> (x `mod` m) == 0) l)


