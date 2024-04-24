# SC2002 OODP Group Project 
This repo contains the Fast Food Ordering and Management System (FOMS) project in SC2002 Object Oriented Design and Programming.

## The team -- Lab group: FDAC
- Alvin Aw Yong
- Koh Huei Shan, Winnie
- Jedidiah Lee
- Chan Zi Hao
- Siah Yee Long

# Project description

The Fastfood Ordering and Management System (FOMS) is a command-line interface application designed to streamline operations in fast food restaurants. This system facilitates menu browsing, order customisation, payment processing, and order tracking, enhancing the overall customer experience and operational efficiency.

# Objectives
- Apply Object-Oriented programming concepts learned during the course.
- Apply SOLID design principle concepts learned during the course.
- Develop practical skills in Java programming.
- Enhance teamwork capabilities by collaboratively working on a software project.

# Use 

1. To compile and run the project:
    ```bash
    make run
    ```

2. To generate javadocs:
    ```bash
    make doc
    ```
    if on mac:
    ```bash
    open FOMS/docs/index.html
    ```
    if on windows:
    ```bash
    start FOMS/docs/index.html
    ```
3. To clean up the project (remove all .class files):
    ```bash
    make clean
    ```

# File Structure
- **FOMS:** root folder for source code
    - **docs:** where javadocs files are stored
    - **src:** where all source codes are stored
        - **main:** contains the main starting point for the entire FOMS application
        - **constants:** constants used in this project
        - **entities:** all entities present in the project
        - **pages:** all UI pages (front end)
            - customerPages
            - staffPages
                - admin
                - manager
        - **services:** all services used by the pages
            - authenticator
            - payments
        - **utilities:** utility functions handling low-level responsibilities
            - data: contains raw CSV data
            - exceptionHandlers
- **UML Diagram:** contains all UML Diagrams drawn and reference documents


# Generating Javadocs manually

cd into FOMS where src is found

```bash
cd FOMS
```

Generate javadocs

```bash
javadoc -d docs -sourcepath src -subpackages main:utilities:constants:entities:pages:services -private  
```

Navigate to ```FOMS/docs/index.html``` and open it in a browser
