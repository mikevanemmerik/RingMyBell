
#include <stdio.h>
#include <string.h>
#include <wiringPi.h>

// 0 = pin 14
// args: open / closed
// 0: name
// 1: open/close 
void setStatus(int state);

int main(int argc, char *argv[]) 
{
	wiringPiSetup () ;
	pinMode (0, OUTPUT) ;
	
	if(argc != 2)
	{
		// miss-use of program
		setStatus(LOW);
		printf("usage: ./door [open/close]\n");
		return 0;
	}
	
	if(strncmp("open", argv[1], 4) == 0)
	{
		// open
		setStatus(HIGH);
		printf("Opening door\n");
	} 
	else 
	{
		// close
		setStatus(LOW);
		printf("Closing door\n");
	}
}

void setStatus(int state){
	digitalWrite (0,  state) ;
}

