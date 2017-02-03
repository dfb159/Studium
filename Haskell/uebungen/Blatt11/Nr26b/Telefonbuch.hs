module Telefonbuch where

import Data

leeresTelefonbuch :: Telefonbuch
leeresTelefonbuch _ = "nicht gefunden"

eintragen :: String -> String -> Telefonbuch -> Telefonbuch
eintragen name nummer tel z = if name == z then nummer
                              else tel z

suche :: String -> Telefonbuch -> String
suche name tel = tel name

sucheMuster :: String -> Telefonbuch -> Telefonbuch
sucheMuster num tel name = if subStr num (tel name) then tel name else "nicht gefunden"

subStr :: String -> String -> Bool
subStr [] _ = True
subStr (s:ss) []  = False
subStr (s:ss) (t:tt) = s == t && subStr ss tt
