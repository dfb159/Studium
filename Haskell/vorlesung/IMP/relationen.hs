module Relationen where

type Zahl = Int

type Status = (Umgebung, Zustand)
type Loc = String
type Var = String

type Error = String

--Funktion mit Ort zu Zahl
type Zustand = (Loc -> Zahl)

--Funktion mit Variable zu Ort
type Umgebung = (Var -> Loc)

program :: String -> Status -> Status
program a b = b

status :: Status -> Var -> Zahl
status s = (snd s) . (fst s)

definiere :: Status -> Var -> Loc -> Status
definiere (xs, ys) var loc = (\name -> if name == var then loc else xs name, ys)

setze :: Status -> Loc -> Zahl -> Status
setze (xs, ys) loc x = (xs, \name -> if name == loc then x else ys name)

arithmetikOP :: Status ->  (Zahl -> Zahl -> Zahl) -> Ausdruck -> Ausdruck -> Zahl
arithmetikOP stat func a1 a2 

boolOP :: (Zahl -> Zahl -> Bool) -> Zahl -> Zahl -> Zahl
boolOP func = func

regel :: Status -> String -> Status
regel stat _ = stat

leererZustand :: Zustand
leererZustand _ = -9999

leereUmgebung :: Umgebung
leereUmgebung _ = ""

testStatus :: Status
testStatus = (testUmgebung, testZustand)

testZustand :: Zustand
testZustand loc = if loc == "a1" then 1 else (leererZustand loc)

testUmgebung :: Umgebung
testUmgebung var = if var == "x" then "a1" else (leereUmgebung var)


