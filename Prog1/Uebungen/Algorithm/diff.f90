Module diff
   
   public :: eDIFF
   public :: zDIFF
   public :: vDIFF
   
Contains
   Real Function eDIFF(f, x, h) ! extrapoliertedifferenzmethode
      Implicit None
      Interface
         Real Function f(x)
            Real, Intent(In) :: x
         End Function
      End Interface
      Real, intent(In) :: x
      Real :: h
           
      If (h == 0.0) Then
         h = 4.5e4**0.2/50 ! = 0.17048
      End If
           
      eDIFF = (4.0 * zDIFF(f, x, h/2) - zDIFF(f, x, h)) / 3
   End Function
       
   Real Function zDIFF(f, x, h) ! zentraledifferenzmethode
      Implicit None
      Interface
         Real Function f(x)
            Real, Intent(In) :: x
         End Function
      End Interface
      Real, intent(In) :: x
      Real :: h
           
      If (h == 0.0) Then
               h = (12e-7)**(1.0/3)
      End If
      
      zDIFF = (f(x + h/2) - f(x - h/2)) / h
   End Function
       
   Real Function vDIFF(f, x, h) ! vorwaertsdifferenzmethode
      Implicit None
      Interface
         Real Function f(x)
            Real, Intent(In) :: x
         End Function
      End Interface
      Real, Intent(In) :: x
      Real :: h
           
      If (h == 0.0) Then
         h = sqrt(2e-7)
      End If
      
      vDIFF = (f(x + h) - f(x)) / h
   End Function

End Module
