# VendingMachineKata

# Requirements

- Maven

### Using Homebrew:
```
If you do not have homebrew, run this command in the terminal to install homebrew which can be found on Homebrew's front page:
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

### Setup Maven
```
type "brew install maven" in the terminal
```


# How to Setup Project / How to run tests

#Option 1 Terminal: Recommended Way
## Running Tests
```
    a) Clone the repo
    b) Navigate to the solution through the terminal
    c) Once you're in the project directory type "mvn clean install"
    d) Once the build is successful scroll up until you see "T E S T S" section
    e) You should see "Tests run: 33, Failures: 0, Errors: 0, Skipped: 0"
    f) To run the tests again type "mvn clean test"
```

#Option 2 IDE: Not Recommended
``` 
1) Ideally, download IntelliJ Idea - it has a free 30-day evaluation phase. All default install settings are fine.
2) Clone Repo
3) Configure Your JDK if you haven't for IntelliJ, in this project I used JDK 9 but I think any recent JDK should be fine.
4) Import the project in IntelliJ as Maven Project
    - Once the project is opened, IntelliJ should do all the work.
    - If that is not the case, there should be a message that pops up at the bottom of the screen. saying Import Changes or it gives you the option Enable Auto Import, either will work.
    - There could also be a button in bottom right of IDE that will  say "Add As Maven Prject" that will do the trick as well.
    - Give IntelliJ some time to load/index and pull in dependencies, it really depends on how fast your pc setup is.
5) Navigate to "VendingMachineTest" class, right click on it and click Run 'VendingMachineTest'
    - You should see the success message saying "Tests Passed: 33 passed".
```

# Optional Plugin

```
The plugin below is helpful for reading the tests, it converts your tests to a sentance wich you can view.
Enso - https://plugins.jetbrains.com/plugin/6403-enso
```
