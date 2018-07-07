Module arrayMod
   Use myType ! im jeweiligen problem implementieren

   public :: readToArray

Contains

   Subroutine readToArray(path, array) ! liest eine Datei mit Datentyp
      Implicit None
      
      Integer :: ioError, k
      Character(64), Intent(in) :: path
      Type(Element), Allocatable :: array(:)
      Type(Element) :: elem
      
      Open(Unit = 10, File = path, Status = "Old", Action = "Read", IOStat = ioError)
      If(ioError /= 0) Stop ! stoppe bei einem Error
      
      k = 0
      Do ! Anzahl Zeilen bestimmen
         Read(10, *, IOStat = ioError) elem
         If(ioError > 0) Cycle
         If(ioError < 0) Exit
         k = k + 1
      End Do
      
      Allocate(array(k))
      Rewind(10)
      
      k = 1
      Do ! Daten einlesen
         Read(10, *, IOStat = ioError) array(k)
         If(ioError > 0) Cycle
         If(ioError < 0) Exit
         k = k + 1
      End Do
      
      Close(10)
   End Subroutine readToArray
      
   Subroutine printArray(array) ! gibt ein array mit Laendern der Reihe nach aus
   Implicit None
   
   Type(Element), Intent(in) :: array(:)
   Integer :: i
   
   Do i = 1, size(array)
      Write (*,*) array(i)
   End Do
End Subroutine
   
End Module arrayMod
