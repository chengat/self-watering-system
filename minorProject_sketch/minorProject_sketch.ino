/******************************************
 * Name: Nikhil Nambiar Chengat
 * ID: 217783606
 * Course: EECS 1021
 * Minor Project: Self Watering System
 ******************************************/
 
#include <Arduino.h>
#include <U8x8lib.h>

#define MOSFET 2
#define REDLED 4
#define MOISTURE A0
#define WET_THRESH 550

auto display = U8X8_SSD1306_128X64_NONAME_HW_I2C(U8X8_PIN_NONE);

void setup() {
  Serial.begin(9600);

  display.begin();
  display.setFlipMode(0);
  display.clearDisplay();

  pinMode(MOSFET, OUTPUT); // Sets the D2 pin (MOSFET + Pump) to output
  pinMode(REDLED, OUTPUT); // Sets the D4 pin (LED) to output
  sendMoistureSensorData();
}

void sendMoistureSensorData() {
  const auto value = analogRead(A0);
  const byte data[] = {0, 0, highByte(value), lowByte(value)};

  Serial.write(data, 4); // send 0,0, "high byte", "low byte"
  Serial.println(); // send "newline"
  delay(1000);
}

void loop() {
  //sendMoistureSensorData(); //to be added

  display.setFont(u8x8_font_profont29_2x3_r);
  display.setCursor(0, 0);
  if (!Serial.available()) {
    return;
  }

  const auto receivedData = Serial.read();

  char buf[16];
  sprintf(buf, "%03d", receivedData);

  if (receivedData < 100) {
    display.print("LED OFF!");
    digitalWrite(MOSFET, LOW);
    digitalWrite(REDLED, LOW);
  }
  else if (receivedData == 255) {
    digitalWrite(MOSFET, HIGH);

  }
  else {
    display.print("LED ON!");
    digitalWrite(REDLED, HIGH);
    digitalWrite(MOSFET, LOW);
  }

}
