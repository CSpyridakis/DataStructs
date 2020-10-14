JC = javac
JVM= java
JFLAGS = -g
OPT = -O3
WARN = 

SRC = src/
BIN = bin/

MAIN = Main

# Help menu
USAGE = Usage: make [OPTION]
DESCRIPTION = 
HELPMENU = $(USAGE)\n$(DESCRIPTION)\nOPTIONS:

JAVA_FILES = $(SRC)Dictionary.java $(SRC)FilesOpen.java $(SRC)Index.java $(SRC)Main.java $(SRC)WordOfDictionary.java

# -----------------------------------------------------

all: classes ## Compile all

classes:
	mkdir -p $(BIN)
	$(JC) $(JFLAGS) -d $(BIN) $(JAVA_FILES)

run: 
	$(JVM) -cp $(BIN) $(MAIN)

clean:	## Clean binaries
	$(RM) -r $(BIN)
	$(RM) Dictionary.txt Index.txt

help:	## Print this help message and exit
	@echo "$(HELPMENU)" ; grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "   %-30s %s\n", $$1, $$2}'

count:	## Count number of lines
	wc -l $(JAVA_FILES)

