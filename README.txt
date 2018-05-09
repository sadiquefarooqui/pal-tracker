using below command line to push the jar into the PCF

cf push pal-tracker --random-route -b  java_buildpack_offline -p build/libs/pal-tracker.jar

and then use the generated PCF routes to hit the app
https://seattle-2018-05.pal.pivotal.io