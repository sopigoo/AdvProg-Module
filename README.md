Name : Siti Shofi Nadhifa <br>
NPM : 2306152172 <br>
Class : AdPro B

## Reflection 1
In this project, there are several clean code principles and secure coding practices that I have applied, such as:
### Clean Code Principles:
1. The classes (for example `ProductController` and `ProductRepository`), methods (for example `findById`), and variables (for example `productId`) are named descriptively, making the code self-explanatory.
2. Each class and method has a clear responsibility.
3. The repository encapsulates data management logic, preventing direct manipulation from the controller.
4. Proper indentation, spacing, and consistent formatting enhance readability.
5. Methods are kept small and focused, ensuring they do only one thing well.
### Secure Coding Practices:
1. The edit method in `ProductRepository` ensures that the edited product is not null and that the product quantity is not negative.
2. Automatically generates product IDs, preventing conflicts and mitigating enumeration attacks.
3. The controller methods redirect users to prevent duplicate submissions, reducing the risk of repeated unintended actions.

## Reflection 2
1. After writing unit test, I realized how important it is to have a unit test to verify that my code works as intended. Having a unit test helps identify bugs earlier, making it easier to maintain and debug issues. There are several things I learned about unit testing, including:
    * The number of unit tests depends on the class complexity
        * Each method should have at least one test.
        * Multiple tests should be written for methods with different logic branches.
        * Edge cases and error handling should also be tested.
    * The unit tests should test all possible paths, covering normal, boundary, and error cases, to ensure the unit tests are enough to verify the program.

   As stated in the question, code coverage is a metric that can help you understand how much of your source is tested. However, I learned that 100% code coverage doesn't mean the codes are bug-free. Code coverage only measures how much our code is tested, not correctness.
2. If the new functional test follows the same setup procedures and instance variables as `CreateProductFunctionalTest.java`, it may lead to code duplication and reduce maintainability. Repeating setup logic across multiple test classes increases redundancy, making future modifications error-prone. Furthermore, duplicated setup code makes it harder to distinguish differences between test classes, impacting readability and maintainability. To improve code quality, a base functional test class should centralize setup logic, ensuring consistency and reducing redundancy for a more scalable test.