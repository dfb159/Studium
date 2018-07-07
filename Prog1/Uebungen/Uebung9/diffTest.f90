Program diffTest 
   Use diff
   Implicit None
   
   Real :: x, dy, h
   h = 0.0
   x = 2.5
   dy = 0
   dy = eDIFF(func, x, h)
   Write(*,*) x, func(x), dy
   
Contains
   Real Function func(x)
      Implicit None
      Real, Intent(In) :: x
      func = 2*x**2
   End Function
End Program
