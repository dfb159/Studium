scalarproduct :: (Num a) => (a,a,a) -> (a,a,a) -> a
scalarproduct u v = (first u * first v) + (second u * second v) + (third u * third v)

first :: (a, b, c) -> a
first (x, _, _) = x

second :: (a, b, c) -> b
second (_, y, _) = y

third :: (a, b, c) -> c
third (_, _, z) = z

addVec :: (Num a) => (a, a, a) -> (a, a, a) -> (a, a, a)
addVec u v = (first u + first v, second u + second v, third u + third v)

vectorproduct :: (Num a) => (a, a, a) -> (a, a, a) -> (a, a, a)
vectorproduct u v = ((second u * third v) - (third u * second v), (third u * first v) - (first u * third v), (first u * second v) - (second u * first v))

vectorlength u = sqrt ((first u)^2 + (second u)^2 + (third u)^2)

normalize u = ((first u)/vectorlength u, (second u)/vectorlength u, (third u)/vectorlength u)

circumference :: Double -> Double
circumference r = 2 * pi * r

cylinder :: (RealFloat a) => a -> a -> a
cylinder r h =
  let sideArea = circumference r * h
      topArea = pi * r^2
  in  sideArea + 2 * topArea
