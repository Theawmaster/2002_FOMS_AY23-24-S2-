# SC2002 OODP Group Project 
This repo contains the Fast Food Ordering and Management System (FOMS) project in SC2002 Object Oriented Design and Programming.

## The team -- Lab group: FDAC
- Alvin Aw Yong
- Koh Huei Shan, Winnie
- Jedidiah Lee
- Chan Zi Hao
- Siah Yee Long

Project management Google Sheet: https://docs.google.com/spreadsheets/d/1dPtr8qC_U1CEHDBrYfFGHZv_c4DwXO4q4GOTOtAYkY8/edit?usp=sharing

# Project description

# Use

# File Structure
- FOMS: root folder for source code
    - docs: where javadocs files are stored
    - src: where all source codes are stored
        - main
        - test: FOR TESTING ONLY, DELETE BEFORE SUBMITTING
        - utilities: currently only added a Logger to debug stuff instead of using System.out.println()
- UML Diagram: contains all UML Diagrams drawn


# Generating Javadocs
Javadoc comments are special comments used in Java to generate documentation for your code. Here's a simple guide on how to add Javadoc comments:

1. **Class-level comments**: Place Javadoc comments immediately before the class declaration. Describe the purpose and usage of the class.

    ```java
    /**
     * This is a sample class that demonstrates Javadoc comments.
     * It provides an example of how to add Javadoc comments to a class.
     */
    public class MyClass {
        // Class implementation...
    }
    ```

2. **Method-level comments**: Place Javadoc comments immediately before the method declaration. Describe what the method does, its parameters, and return value.

    ```java
    /**
     * Returns the sum of two numbers.
     * 
     * @param a The first number.
     * @param b The second number.
     * @return The sum of a and b.
     */
    public int add(int a, int b) {
        return a + b;
    }
    ```

3. **Attribute-level comments**: Place Javadoc comments immediately before the attribute declaration. Describe the purpose and usage of the attribute.

    ```java
    /**
     * The name of the person.
     */
    private String name;
    ```

4. **Generating Javadocs to see what you just made**: 

    cd into FOMS where src is found

    ```bash
    cd FOMS
    ```

    Generate javadocs

    ```bash
    javadoc -d docs -sourcepath src -subpackages main:utilities:test -private  
    ```

    Navigate to ```FOMS/docs/index.html``` and open it in a browser
