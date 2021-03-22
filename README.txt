To run these automated tests yoy will need to have installed:
1. The latest version of Intellij;
2. Java
3. Maven
4. Chrome

To run them:
1. download the project from git
2. Open the project in Intellij
3. Navigate to:
TCAutomation -> src -> test -> java -> AllegroTestst
4. Click the green cricle with a small trangle on the line counter (there are 3 of them) and choose "Run" option

The first test (createNewAccount) uses three variables, first two of which can be modified:
String email = "spoof@email.com"; - email address used in the test;
String password = "Password123"; - password used in test
String confirmationURL = "https://allegro.pl/rejestracja/potwierdzenie"; - address of a page to which user is redirected after successful registration of an account

After 2 or 3 runs of autotests, Allegro notices that some kind of automated tool is being used. When this happens:
* Captcha needs to be solved manually before test can continue, some time has been allowed for this before tests proceed;
* An error "Something went wrong" is displayed and new account is not being registered upon providind all required information correctly;
* All this goes away after an hour and tests can be re-run;
 

