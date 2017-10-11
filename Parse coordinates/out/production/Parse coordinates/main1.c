#include <pololu/3pi.h>
#include "ArmstrongNavigation/ArmstrongNavigation.h"

int main()
{
initialize();

print("(1, 0)->");
lcd_goto_xy(0, 1);
print("(2, 2)");

wait_for_button_press(BUTTON_B);
wait_for_button_release(BUTTON_B);
delay_ms(500);

r3PI.x = 1;
r3PI.y = 0;

gotoPoint(1, 0);
gotoPoint(2, 0);
gotoPoint(3, 0);
gotoPoint(3, 1);
gotoPoint(3, 2);
gotoPoint(2, 2);

while(1);
}
