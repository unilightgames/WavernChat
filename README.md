# WavernChat (Known as WavernTCP & TCPChat in alpha & beta stage.)
If you launch the client without making any modifications, it will not work.
The reason for this is because by default, the "Client.java" file will have a server already set.
This was the server used during development, and is not on anymore.

# GUIDE TO CONNECTING TO A SERVER
Edit the "Client.java file"
Locate the line at the bottom of the file that says "Socket socket = new Socket("localhost", 1234);"
Change the text that says "localhost" to the server IP of the chat server you wanna join, aswell as the port.
Don't forget to save using CTRL + S (or file and then save)

# One more thing..
# You need java JDK and JRE to launch this, so make sure to install those things if you havent!
JDK - https://download.oracle.com/java/22/latest/jdk-22_windows-x64_bin.msi
JRE - https://www.java.com/en/download/manual.jsp

Now your done!
just open the "launchclient.bat" file to automatically run the Client.java file and have fun chatting! 

# For server hosters..
https://github.com/jzzjackz/WavernServer
