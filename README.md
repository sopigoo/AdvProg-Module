Name : Siti Shofi Nadhifa <br>
NPM : 2306152172 <br>
Class : AdPro B

<details>
<summary>Module 1 - Coding Standards</summary>

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

</details>

<details>
<summary>Module 2 - CI/CD & DevOps</summary>

### Deployment Link
https://inherent-kellyann-sopigoo-a69023dd.koyeb.app

## Reflection
### Code Quality Issue(s) fixed
1. Remove unnecessary public modifiers in interface of `ProductService.java`
   ```java
   public interface ProductService { 
       Product create(Product product);
       List<Product> findAll();
       Product findById(String productId);
       void delete(String productId);
       Product edit(Product product, String productId);
   }
   ```
   I removed the unnecessary public modifiers in the `ProductService` interface because, in `Java`, all interface methods are implicitly `public` and `abstract`, making the explicit `public` keyword redundant.
   By keeping the code concise, I improve readability and follow `Java` conventions, ensuring that there is no misunderstanding about access modifiers in interfaces.
2. Change import to a specific one
   From a general import `import org.springframework.web.bind.annotation.*` to a specific import
   ```java
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.ModelAttribute;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   ```
   I also changed the wildcard import (`import org.springframework.web.bind.annotation.*`) to specific imports to prevent unnecessary class loading, which helps reduce memory usage and avoids potential conflicts when multiple packages have similarly named classes.
   Additionally, by explicitly listing the imported classes, I make the code easier to maintain since it clearly shows which annotations and classes are being used.
   This approach makes the project more efficient and readable.

### CI/CD Implementation
I think, I have already implemented `CI/CD` well and met the definitions of `Continuous Integration (CI)` and `Continuous Development (CD)`.
In terms of CI, I have implemented `ci.yml` which ensures continuous integration by running unit tests automatically on every push or pull-request.
Additionally, I have also implemented `pmd.yml` which performs static code analysis using PMD to detect bugs and bad practices and `scorecard.yml` which analyzes the repository's security and supply chain health using OSSF scorecard.
For CD, I integrated my app with `Koyeb` to handle automatic deployments, ensuring that every validated change is deployed without manual intervention.
This setup streamlines development, reduces the risk of introducing critical bugs into production, and maintains high software reliability and security.

</details>