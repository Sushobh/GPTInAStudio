# GPTInAStudio (Just for fun). 

GPT Api integration with Android Studio i.e. a plugin.

1) The plugin is capable of generating test case "templates" for Kotlin functions.
2) Analyzing Kotlin function code and suggesting improvements.

https://user-images.githubusercontent.com/20350751/208074849-208f2182-9219-47de-b7bc-4b99f5aa443e.mp4

## Download and usage

[GPTInStudio-1.0-SNAPSHOT.zip](https://github.com/Sushobh/GPTInAStudio/files/10244814/GPTInStudio-1.0-SNAPSHOT.zip)

Download and install this plugin in Android Studio, verified to work with the latest Android Studio versions.

Settings -> Plugins -> Click on settings icon at the top -> Install plugin from disk

### Enter your GPT token
Settings -> Tools -> GPTInStudio Settings

Then right click on function name and use.

## Limitations

1) Currently works only with Kotlin functions only, you also have to make sure they are reasonably sized in 
   terms of size, larger functions may exceed the token limit. 
   [Read more](https://beta.openai.com/docs/guides/completion/inserting-text)
2) Only works with Kotlin, does not work with Java.   


