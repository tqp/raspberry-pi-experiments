# Tim's Raspberry Pi Experiments

## To Deploy
git clone https://github.com/tqp/raspberry-pi-experiments.git
git pull  
mvn package  
sudo java -jar <target>.jar  
open on http://<ip-address>:8080

## Pi4J Pin Issues with Raspberry Pi 4
Raspberry Pi 4 uses a different pin scheme than prior versions.  
Ref: https://stackoverflow.com/questions/64584365/raspberry-pi4-with-pi4j-java  
Fix that with this line...  
```text
GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
```

## Reference Pages for Projects

LEDs on Raspberry Pi GPIO with Java Pi4J - Start to Finish  
https://www.youtube.com/watch?v=29va8L2LMfI
