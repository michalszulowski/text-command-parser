# Text Command Parser
Simple library parsing text to executable commands. 


## Usage

Create DictionaryCommandFactory and add support for your command.
> DictionaryCommandFactory factory = new DictionaryCommandFactory()  
> factory.addCommand("list-args", args -> new ListArgsCommand(args));

That's it. Now you can do
> String input = "list-args a b \"c d\""  
> Command command = factory.getCommand(input)  
> command.invoke();

And the output will be
> \* a  
> \* b  
> \* c d

Simple as that.

Of course you can specify one-word arguments delimiter and multi-word commands delimiter.

## Add to maven

