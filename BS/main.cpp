#include <wiringPi.h>
int main (void)
{
  wiringPiSetup () ;
  pinMode (0, OUTPUT) ;
  pinMode (2, OUTPUT) ;
  for (;;)
  {
    digitalWrite (2, HIGH) ; 
    digitalWrite (0, LOW) ; delay (50) ;
    digitalWrite (2,  LOW) ;
    digitalWrite (0,  HIGH) ; delay (50) ;
  }
  
  return 0 ;
}

/*
1. Code reageren op input van knop (print)  (Ik word gebeld)
2. knop op telefoon, brand lamp op rasberry (Openen deur)


1.2. Telefoon laten reageren op knop. (later de deurbel)


ADVANCED:
- Geluid van deur naar rasberry

*/
