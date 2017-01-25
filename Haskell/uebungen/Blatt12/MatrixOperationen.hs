module MatrixOperationen where
import Data.Array
import Matrix

transpose :: Matrix -> Matrix
transpose m = array ((1,1),(a,b)) [((x,y),(m ! (y,x)))|x<-[1..a],y<-[1..b]]
  where a = snd (snd (bounds m)); b = fst (snd (bounds m))

mult :: Matrix -> Matrix -> Matrix
mult m1 m2 = array ((1,1),(l2,h1)) [((x,y),(multZelle x y m1 m2 h2))|x<-[1..l2],y<-[1..h1]]
  where h1= snd (snd (bounds m1)); l2 = fst (snd (bounds m2)); h2= snd (snd (bounds m2))

multZelle :: Int -> Int -> Matrix -> Matrix -> Int -> Int
multZelle x y m1 m2 h2= sum [(m1 ! (i,y))*(m2 ! (x,i)) | i<-[1..h2]]
