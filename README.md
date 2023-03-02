# Text Command Parser
Simple library parsing text to executable commands. 


## Usage

Create DictionaryCommandFactory and add support for your command.

```
DictionaryCommandFactory factory = new DictionaryCommandFactory()
factory.addCommand("list-args", args -> new ListArgsCommand(args));
```

That's it. Now you can do

```
String input = "list-args a b \"c d\""  
Command command = factory.getCommand(input)  
command.invoke();
```

And the output will be

```
1. a  
2. b  
3. c d
```

Simple as that.

Of course you can specify one-word arguments delimiter and multi-word arguments delimiter.

## Add to maven

Add JitPack as repository

```
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```

Then add dependency with latest commit id as a version

```
<dependency>
	<groupId>com.github.michalszulowski</groupId>
	<artifactId>text-command-parser</artifactId>
	<version>CHECK_LATEST_COMMIT_ID</version>
</dependency>
```