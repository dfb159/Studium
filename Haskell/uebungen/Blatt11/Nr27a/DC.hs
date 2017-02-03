module DC where

data Baum = Zweig (Baum) (Baum) | Blatt Int deriving Show

dc :: (a -> Bool) -> (a -> b) -> (a -> [a]) -> ([b] -> b) -> a -> b
dc isSimple solve partition combine problem = if  isSimple problem  then  solve problem
                                              else combine (map (dc isSimple solve partition combine) (partition problem))

