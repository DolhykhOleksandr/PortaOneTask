Current instruction provides simple way for users to run java applications without installing Java environment like setting system environments and so on.

1. Prepare execution environment:
   1.1. Download data-explorer.jar package from this repository
   1.2. Create empty folder in user Desktop dir with name "data_explorer"
   1.3. Choose your current Operation system, OS architecture and download JRE 17 (https://adoptium.net/temurin/releases/) as zip archive
   1.4. Unzip downloaded archive to the created "data_explorer" folder and rename it to the 'jre'
   1.5. Put data-explorer.jar to the "data_explorer" folder
   1.6. Also you can put data file with numbers to the same location

2. Application execution:
   2.1. Open your local command prompt (e.g. PowerShell) and locate to the "data_explorer" folder, for example:
        cd C:\Users\<user_name>\Desktop\data_explorer
   2.2. Execute following command to run DataExplorer application:
        jre\bin\java.exe -jar data-explorer.jar <data_file_name>.txt
   * <data_file_name>.txt - a path to the data txt file with numbers
