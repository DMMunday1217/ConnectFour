#include "FastLED.h"
#define NUM_LEDS 49
#include <stdio.h>
#include <time.h>
int turn = 0;
const int HEIGHT = 7; 
const int WIDTH = 7;
CRGB leds[NUM_LEDS];
const int button0 = 5;
const int button1 = 6;
const int button2 = 7;

int button0State = 0;
int button1State = 0;
int button2State = 0;
int turnCount = 0;

int arr[][7] = {{-1,-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1}}; 

void display(int arr[HEIGHT][WIDTH]) 
{ 
       for(int i = 0; i < 7; i++){
         for(int j = 0; j < 7; j++){
            if(i % 2 == 0){
              if(arr[i][j] == 0){
                leds[i * 7 + j] = CRGB::Blue; FastLED.show();
              }
              else if(arr[i][j] == 1){
                leds[i * 7 + j] = CRGB::Red; FastLED.show();
              }
            }
            else if(i % 2 == 1){
              if(arr[i][j] == 0){
                leds[i * 7 + 6 - j] = CRGB::Blue; FastLED.show();
              }
              else if(arr[i][j] == 1){
                leds[i * 7 + 6 - j] = CRGB::Red; FastLED.show();
              }
            }
          }  
        }
    delay(500);
} 

void resetTop(int input){
      leds[6] = CRGB::Black;
      leds[7] = CRGB::Black;
      leds[20] = CRGB::Black;
      leds[21] = CRGB::Black;
      leds[34] = CRGB::Black;
      leds[35] = CRGB::Black;
      leds[48] = CRGB::Black; FastLED.show();
   

}

void displayTop(int input){
  resetTop(input);
  switch(input){
    case 0:
      leds[6] = CRGB::Orange; FastLED.show();
    break;
    case 1:
      leds[7] = CRGB::Orange; FastLED.show();
    break;
    case 2:
      leds[20] = CRGB::Orange; FastLED.show();
    break;
    case 3:
      leds[21] = CRGB::Orange; FastLED.show();
    break;
    case 4:
      leds[34] = CRGB::Orange; FastLED.show();
    break;
    case 5:
      leds[35] = CRGB::Orange; FastLED.show();
    break;
    case 6:
      leds[48] = CRGB::Orange; FastLED.show();
    break;
  }
}


int dPad(){
  int output = 3;
  displayTop(output);
  button1State = digitalRead(button1);
  while(button1State == LOW){
    button0State = digitalRead(button0);
    button2State = digitalRead(button2);
    if(button0State == HIGH && output > 0){
      output--;
      delay(250);
      displayTop(output);
    }
    else if(button2State == HIGH && output < 6){
      output++;
      delay(250);
      displayTop(output);
    }
    button1State = digitalRead(button1);
  }
  return output;
}

int checkWin(int b[HEIGHT][WIDTH]){  
  for (int y = 0; y < HEIGHT; y++) { // checks horizontally from top to bottom
        for (int x = 0; x < WIDTH - 3; x++) {
          if (b[x][y] == b[x + 1][y] && b[x][y] == b[x + 2][y] && b[x][y] == b[x + 3][y] && b[x][y] != -1) {
            return b[x][y];
          }
        }
      }
      for (int y = 0; y < HEIGHT - 3; y++) { // checks vertically from left to right
        for (int x = 0; x < WIDTH; x++) {
          if (b[x][y] == b[x][y + 1] && b[x][y] == b[x][y + 2] && b[x][y] == b[x][y + 3] && b[x][y] != -1) {
            return b[x][y];
          }
        }
      }
      for (int y = 0; y < HEIGHT - 3; y++) { // checks diagonally left to right, top to bottom
        for (int x = 0; x < WIDTH - 3; x++) {
          if (b[x][y] == b[x + 1][y + 1] && b[x][y] == b[x + 2][y + 2] && b[x][y] == b[x + 3][y + 3]
              && b[x][y] != -1) {
            return b[x][y];
          }
        }
      }
      for (int y = 0; y < HEIGHT - 3; y++) { // checks diagonally right to left, top to bottom
        for (int x = WIDTH - 1; x > WIDTH - 4; x--) {
          if (b[x][y] == b[x - 1][y + 1] && b[x][y] == b[x - 2][y + 2] && b[x][y] == b[x - 3][y + 3]
              && b[x][y] != -1) {
            return b[x][y];
          }
        }
      }
      for (int y = HEIGHT - 1; y > HEIGHT - 3; y--) { // checks diagonally left to right, bottom to top
        for (int x = 0; x < WIDTH - 3; x++) {
          if (b[x][y] == b[x + 1][y - 1] && b[x][y] == b[x + 2][y - 2] && b[x][y] == b[x + 3][y - 3]
              && b[x][y] != -1) {
            return b[x][y];
          }
        }
      }
      for (int y = HEIGHT - 1; y > HEIGHT - 3; y--) { // checks diagonally right to left, bottom to top
        for (int x = WIDTH - 1; x > WIDTH - 4; x--) {
          if (b[x][y] == b[x - 1][y - 1] && b[x][y] == b[x - 2][y - 2] && b[x][y] == b[x - 3][y - 3]
              && b[x][y] != -1) {
            return b[x][y];
          }
        }
      }
      return -1;
}
  

void setup() { 
  digitalWrite(0,LOW);
  FastLED.addLeds<NEOPIXEL, 0>(leds, NUM_LEDS);
  FastLED.setBrightness(50);
  srand(time(NULL));
  pinMode(button0, INPUT);
  pinMode(button1, INPUT);
  pinMode(button2, INPUT);
}

void loop() {
  int fixedArr[HEIGHT][WIDTH];
  int input;
  while(checkWin(arr) == -1 && turnCount < 48){
      
      display(arr);
      input = dPad();
      delay(500);
      for(int i = 0; i < 6; i++){
        if(arr[input][i] == -1){
          arr[input][i] = turn;
          turn = (turn + 1) % 2;
          break;
        }
        else if(i == 6){
          turnCount--;
        }
      }
      display(arr);
      turnCount++;
  }
  delay(1000);
  button1State = digitalRead(button1);
  while(button1State == LOW){
      fill_solid(leds, 49, CRGB::Green); FastLED.show();
      button1State = digitalRead(button1);
  }
}
