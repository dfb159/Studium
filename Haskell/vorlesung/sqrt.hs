sqrt :: (Num a) => a -> a
sqrt r = sqrtCalc r r

sqrtCalc :: (Num a) => a -> a -> a
sqrtCalc r a = b
  where b = (a + (r/a))/2
