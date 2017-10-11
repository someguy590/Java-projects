#include <pololu/3pi.h>
#include "ArmstrongNavigation/ArmstrongNavigation.h"

int main()
{
initialize();

print("(2, 0)->");
lcd_goto_xy(0, 1);
print("(0, 2)");

wait_for_button_press(BUTTON_B);
wait_for_button_release(BUTTON_B);
delay_ms(500);

r3PI.x = 2;
r3PI.y = 0;

gotoPoint(2, 0);
gotoPoint(2, 1);
gotoPoint(2, 2);
gotoPoint(1, 2);
gotoPoint(0, 2);

while(1);
}
