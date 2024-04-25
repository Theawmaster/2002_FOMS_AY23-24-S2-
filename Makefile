# Makefile for Java project

JAVAC = javac
JAVA = java
JAVADOC = javadoc
SRC_DIR = FOMS/src
MAIN_DIR = $(SRC_DIR)/main
MAIN_CLASS = main.mainFOMSApp  # Ensure this is correctly named
FLAGS = -g
DOC_DIR = FOMS/docs

# Find all Java files in the src directory and its subdirectories
SOURCES = $(shell find $(SRC_DIR) -name '*.java')

# Default make target
all: $(SOURCES)
	$(JAVAC) $(FLAGS) -d $(SRC_DIR) $(SOURCES)

# Rule to run the main class
run: all
	$(JAVA) -cp $(SRC_DIR) $(MAIN_CLASS)

# Clean rule to delete all compiled files
clean:
	find $(SRC_DIR) -type f -name '*.class' -delete

# Rule to generate JavaDoc
doc:
	$(JAVADOC) -d $(DOC_DIR) -sourcepath $(SRC_DIR) -subpackages main:utilities:constants:entities:pages:services -private -noqualifier all -author

# Prevent make from doing something with a file named 'run' or 'clean' or 'doc'
.PHONY: all clean run doc
