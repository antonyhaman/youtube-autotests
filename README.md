# youtube-autotests
Test task for an interview in one of the companies

The task was as following:
> Goal: 
determine, does video have preroll ads or not for both cases when adblock on and off
>Requirements: web-driver (chrome), any platform, any required software for sound recording.
>Pseudo scenario:
>adbock : on 
>Find 10 most viewed video for “<input from cli | web >” on youtube.com and start playing one by one 
>record sound <clip_id>_<adbloc-><on | off>.<wav | mp3>
>when clip current time > 1 min, stop record, save file,  start playing another
>when clip == 10, adblock : off, repeat, when adblock : off AND clip == 10; goto next step
>compare equals audio files  - and return was preroll or not
>Output : 
><file_name>, <was preroll> (true, false)

## Technologies used
- Java
- Selenide
- TestNG
- musicg (for audio comparison)

## Platform
Windows only. Could be easily modified to work on Linux.

## How to install and run 
This is Java Maven-based project so you have to have JRE and Maven installed on your pc and then:
1. Clone the project
2. Execute 'mvn test' in a command line, this will launch the tests
3. Check the results in log file at root of the project

### Note:
Currently test is configured to record 20 audio files in total (it comes from the goal) each for 1 minute therefore test runs for about 20 minutes, if you don't want to wait so long, you can modify the following fields at TestSuite class:
```java
private final static int NUMBER_OF_VIDEOS = 10;
private final static int DURATION_MINUTES = 1;
private final static int DURATION_SECONDS = 0;
```

