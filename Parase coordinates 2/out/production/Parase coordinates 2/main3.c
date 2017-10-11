#include <pololu/3pi.h>
#include "ArmstrongNavigation/ArmstrongNavigation.h"

int main()
{
initialize();

print("(0, 1)->");
lcd_goto_xy(0, 1);
print("(2, 1)");

wait_for_button_press(BUTTON_B);
wait_for_button_release(BUTTON_B);
delay_ms(500);

r3PI.x = 0;
r3PI.y = 1;

gotoPoint(0, 1);
gotoPoint(1, 1);
gotoPoint(2, 1);

while(1);
}
