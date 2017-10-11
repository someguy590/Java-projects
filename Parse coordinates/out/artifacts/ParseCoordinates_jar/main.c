#include <pololu/3pi.h>
#include "ArmstrongNavigation/ArmstrongNavigation.h"

int main()
{
initialize();

r3PI.x = 0;
r3PI.y = 2;

gotoPoint(0, 1);
gotoPoint(0, 2);
gotoPoint(0, 3);
gotoPoint(0, 4);
gotoPoint(0, 5);
gotoPoint(1, 5);
gotoPoint(2, 5);
gotoPoint(3, 5);
gotoPoint(4, 5);
gotoPoint(5, 5);

while(1);
}
