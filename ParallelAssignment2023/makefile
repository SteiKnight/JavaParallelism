# Makefile

# Directory structure
SRC_DIR = src
BIN_DIR = bin

# Compilation flags
JFLAGS = -d $(BIN_DIR) -sourcepath $(SRC_DIR) -cp $(BIN_DIR)

# Source files
SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SOURCES))

# Default target
all: $(CLASSES)

# Rule to compile .java files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	javac $(JFLAGS) $<

# Clean the generated .class files
clean:
	rm -f $(BIN_DIR)/*.class
