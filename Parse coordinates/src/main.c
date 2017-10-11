#include <pololu/3pi.h>
#include "ArmstrongNavigation/ArmstrongNavigation.h"

int main()
{
initialize();

r3PI.x = 1;
r3PI.y = 0;

gotoPoint(2, 0);
gotoPoint(3, 0);
gotoPoint(3, 1);
gotoPoint(3, 2);
gotoPoint(2, 2);
r3PI.x = 2;
r3PI.y = 0;

gotoPoint(2, 1);
gotoPoint(2, 2);
gotoPoint(1, 2);
gotoPoint(0, 2);
r3PI.x = 0;
r3PI.y = 1;

gotoPoint(1, 1);
gotoPoint(2, 1);

while(1);
}
