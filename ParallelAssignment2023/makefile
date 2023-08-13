# Makefile for MonteCarloMini Java project

# Compiler and compiler flags
JAVAC = javac
JFLAGS = -g

# List of Java source files
SOURCES = \
	MonteCarloMini/MonteCarloMinimization.java \
	MonteCarloMini/MonteCarloMinimizationParallel.java \
	MonteCarloMini/Search.java \
	MonteCarloMini/TerrainArea.java

# List of class files (automatically derived from source files)
CLASSES = $(SOURCES:.java=.class)

# Rule to build all classes
all: $(CLASSES)

# Rule to compile .java files to .class files
%.class: %.java
	$(JAVAC) $(JFLAGS) $<

# Clean up generated files
clean:
	rm -f $(CLASSES)

# Run the MonteCarloMinimization program
run:
	java MonteCarloMini.MonteCarloMinimization

# Run the MonteCarloMinimizationParallel program
run-parallel:
	java MonteCarloMini.MonteCarloMinimizationParallel

.PHONY: all clean run run-parallel
