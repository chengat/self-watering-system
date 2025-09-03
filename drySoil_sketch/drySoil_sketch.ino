/******************************************
 * Name: Nikhil Nambiar Chengat
 * ID: 217783606
 * Course: EECS 1021
 * Minor Project: Self Watering System
 ******************************************/
 
#include <Arduino.h>
#include <U8x8lib.h>

#define MOSFET 2
#define MOISTURE A0
#define WET_THRESH 550

void setup() {
  Serial.begin(9600);

  pinMode(MOSFET, OUTPUT);
  detectDrySoil();
}

void detectDrySoil() {

  int moistureValue = 0;
  moistureValue = analogRead(MOISTURE);
  Serial.print("Moisture in soil at: "); Serial.println(moistureValue);

  if (moistureValue >= WET_THRESH) {
    Serial.println("Insufficient water, watering now!");
    Serial.println();
  }
  
  while (moistureValue >= WET_THRESH) {
    digitalWrite(MOSFET, HIGH); delay(3500); digitalWrite(MOSFET, LOW); delay(2000);
    moistureValue = analogRead(MOISTURE);

    if (moistureValue > WET_THRESH) {
      Serial.print("Current moisture in soil at: "); Serial.println(moistureValue);
      Serial.println("More water needed!");
    }
    else {
      Serial.println();
      Serial.print("Current moisture in soil at: "); Serial.println(moistureValue);
    }
  }
  
  Serial.println();
  Serial.println("Enough moisture in the soil! No more water needed!");

}

void loop() {
  // put your main code here, to run repeatedly:

}
