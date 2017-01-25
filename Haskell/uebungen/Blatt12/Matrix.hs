module Matrix(Matrix, create, view) where
import Data.Array

type Matrix = Array (Int,Int) Int

create :: Int -> Int -> (Int -> Int -> Int) -> Matrix
create a b f = array ((1,1),(a,b)) [((x,y),(f x y))|x<-[1..a],y<-[1..b]]

view :: Matrix -> IO ()
view m = mapM_ (\y -> putStrLn (show [m!(x,y)|x<-[1..mx]])) [1..my]
         where ((_,_),(mx,my)) = bounds m

