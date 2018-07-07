Program test
   Use arrayMod
   Implicit None
   
   Character(64) :: path
   Type(Element), Allocatable :: array(:)
   
   path = "data.dat"
   Call readToArray(path, array)
   Call printArray(array)
   
End Program
