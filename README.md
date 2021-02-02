# Tim's Raspberry Pi Experiments

## To Deploy
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

