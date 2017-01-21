module Telefonbuch where

import Data

leeresTelefonbuch :: Telefonbuch
leeresTelefonbuch = []

eintragen :: String -> String -> Telefonbuch -> Telefonbuch
eintragen name nummer tel = (name, nummer) : tel

suche :: String -> Telefonbuch -> String
suche _ [] = "nicht gefunden"
suche x (tel:tels)
  | x == (fst tel) = snd tel
  | otherwise = suche x tels

sucheMuster :: String -> Telefonbuch -> Telefonbuch
sucheMuster num tel = filter (\(x,y) -> beginsWith num y) tel

beginsWith :: String -> String -> Bool
beginsWith [] _ = True
beginsWith _ [] = False
beginsWith (x:xs) (y:ys)
  | x == y = beginsWith xs ys
  | otherwise = False

testTel :: Telefonbuch
testTel = (eintragen "RandomDude" "3234325" (eintragen "Noice" "1337" (eintragen "Hallo" "1369" leeresTelefonbuch)))

