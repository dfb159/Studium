module ZahlenFolge where

zahlenFolge :: [Int] -> [Int]
zahlenFolge ml = filter (checkMod ml) [1..]

checkMod :: [Int] -> Int -> Bool
checkMod l x = foldl (||) False (map (\ m -> (x `mod` m) == 0) l)

