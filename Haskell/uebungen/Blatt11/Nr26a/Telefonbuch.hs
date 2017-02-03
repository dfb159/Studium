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
sucheMuster num tel = [x | x <- tel, num == take (length num) (snd x)]
