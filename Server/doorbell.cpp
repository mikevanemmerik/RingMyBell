#include <wiringPi.h>
#include <stdio.h>
#include <time.h> 
#include <iostream>
#include <fstream>

using namespace std;


void pressed();
void log_doorbell();

int pin_LED = 0 ;    // 0  = pin 17 
int pin_switch = 15; // 15 = pin 14




// Use GPIO Pin 17, which is Pin 0 for wiringPi library
#define BUTTON_PIN 0

// the event counter 
volatile int eventCounter = 0;
void myInterrupt(void) 
{
   eventCounter++;
}


int main (void)
{
		// sets up the wiringPi library
	  if (wiringPiSetup () < 0) {
		  printf ("Unable to setup wiringPi, kut voor je");
		  return 1;
	  }
	  pinMode(pin_LED, OUTPUT);
	 
	  // set Pin 17/0 generate an interrupt on high-to-low transitions
	  // and attach myInterrupt() to the interrupt
	  if ( wiringPiISR (pin_LED, INT_EDGE_FALLING, &myInterrupt) < 0 ) {
		   printf ("Unable to setup wiringPi, kut voor je2");
		  return 1;
	  }
	 
	  // display counter value every second.
	  while ( 1 ) {
		printf( "%d\n", eventCounter );
		eventCounter = 0;
		delay( 1000 ); // wait 1 second
	  }
	 
	  return 0;
	
	
	/*wiringPiSetup () ;
	pinMode(pin_LED, OUTPUT);
	pinMode(pin_switch, INPUT);
  
	for (;;)
	{
		if (  digitalRead (pin_switch) == HIGH){ 
		
			log_doorbell();
			// 
			digitalWrite(pin_LED, 1);
			printf("Button pressed\n");
			pressed();
			delay(5*1000);
			pressed();
			 digitalWrite(pin_LED, 0);
		}
		delay(100);
	}
	return 0 ;*/
}

// 
void pressed()
{
	while(digitalRead (pin_switch) == 1)
	{
		// Do nothing	
		delay(100);		
	}
}

void log_doorbell()
{
	time_t current_time;
	current_time = time(NULL) ;
	char* c_time_string = ctime(&current_time);
	ofstream myfile ("doorinfo.txt", std::ios_base::app);
	// 
	if (myfile.is_open())
	{
		myfile << c_time_string << " " << "doorbell\n";
		myfile.close();
	}
}
