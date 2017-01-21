type Point = (Int, Int)
type Picture = Point -> Bool

add :: Point -> Picture -> Picture
add (x1, y1) p (x, y) = p (x, y) || (x == x1 && y == y1)

zoom :: Int -> Picture -> Picture
zoom v p (x, y) = p (round (x/v), round (y/v))

rotate :: Int -> Picture -> Picture
rotate alpha p (x,y) = 
  p (round (cos beta * x - sin beta * y), round (sin beta * x + cos beta * y))
  where beta = 2 * pi - alpha

move :: (Int, Int) -> Picture -> Picture
move (dx, dy) p (x, y) = p (x - dx, y - dy)

overlap :: Picture -> Picture -> Picture
overlap p1 p2 pt = p1 pt || p2 pt

isBlack :: Point -> Picture -> Bool
isBlack pt p = p pt
