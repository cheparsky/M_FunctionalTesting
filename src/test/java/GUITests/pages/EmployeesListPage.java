package GUITests.pages;

import GUITests.utils.MyDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeesListPage {

    @FindBy(xpath = "//*[@id='employee-list']/li")
    public static List<WebElement> elListOfEmployee; //employees list - list of employee

    @FindBy(xpath = "//*[@id='employee-list']/li[1]")
    public static WebElement elFirstElementInListOfEmployee; //employees list - first element of list of employee

    @FindBy(id = "bAdd")
    public static WebElement elCreateButton; //employees list - create new employee button

    @FindBy(id = "bEdit")
    public static WebElement elEditButton; //employees list - edit employee button

    @FindBy(id = "bDelete")
    public static WebElement elDeleteButton; //employees list - delete employee button

    @FindBy(xpath = "//p[contains(text(),'Logout')]")
    public static WebElement elLogoutButton; //employees list - logout button

    //Action that clickss on Create button
    public static void createB() {
        MyDriver.clickButtonCheckIfElementIsVisible(EmployeesListPage.elCreateButton,
                AddNewEmployeePage.aneAddButton,
                "Employees list page: Create button is clickable.",
                "Employees list page: Create button is NOT clickable.",
                "Employees list page do not contain Create button."
        );
    }

    //Action that clicks on Edit button
    public static void editB() {
        MyDriver.clickButtonCheckIfElementIsVisible(EmployeesListPage.elEditButton,
                EditEmployeePage.eeUpdateButton,
                "Employees list page: Edit button is clickable.",
                "Employees list page: Edit button is NOT clickable.",
                "Employees list page do not contain Edit button."
        );
    }

    //Action that clicks on Delete button
    public static void deleteB() {
        MyDriver.clickButton(EmployeesListPage.elDeleteButton,
                "Employees list page: Delete button is clickable.",
                "Employees list page: Delete button is NOT clickable.",
                "Employees list page do not contain Delete button."
        );
    }

    //Action that clicks on first element on list of employees
    public static void selectFirstElementInListOfEmployee() {
        MyDriver.clickButton(elFirstElementInListOfEmployee,
                "Employees list page: first element in list is clickable.",
                "Employees list page: first element in list is NOT clickable.",
                "Employees list page do not contain first element in list."
        );
    }

    //Action that returns created and/or updated employee
    public static WebElement getCreatedAndUpdatedEmployee() {
        for (int i = 0; i <= 10; i++) {
            if (elListOfEmployee.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        if (AddNewEmployeePage.firstName.isEmpty()) {
            return elFirstElementInListOfEmployee;
        } else {
            for (WebElement employee : elListOfEmployee) {
                if (employee.getAttribute("innerHTML").trim().equals(AddNewEmployeePage.firstName + " " + AddNewEmployeePage.lastName)) {
                    return employee;
                }
            }
        }
        return null;
    }

    //Action that selects created and/or updated employee on list of employees
    public static void selectEmployee() {
        WebElement employee = EmployeesListPage.getCreatedAndUpdatedEmployee();
        MyDriver.clickButton(employee,
                "Employees list page: element in list is clickable.",
                "Employees list page: element in list is NOT clickable.",
                "Employees list page do not contain element in list."
        );

    }

    //Action that selects created and/or updated employee on list of employees
    public static void selectEmployeeByDoubleClicking() {
        WebElement employee = EmployeesListPage.getCreatedAndUpdatedEmployee();
        if (MyDriver.isElementVisible(employee)){
            Actions actions = new Actions(MyDriver.wd);
            actions.doubleClick(employee).perform();
        }

    }


    //Action that checks if list of employees contain updated employee
    public static boolean checkIfListContainUpdatedEmployee() {
        WebElement employee = EmployeesListPage.getCreatedAndUpdatedEmployee();
        if (employee == null) return false;
        else if (MyDriver.isElementVisible(employee))
            return true;
        else {
            System.out.println(employee);
            return false;
        }
    }

    //Action that checks if list of employees not contain updated employee
    public static boolean checkIfListNotContainUpdatedEmployee() {
        boolean state = false;
        for (int i = 0; i <= 20; i++) {
            WebElement employee = EmployeesListPage.getCreatedAndUpdatedEmployee();
            System.out.println("checkIfListNotContainUpdatedEmployee is: " + employee);
            if (employee == null) {
                state = true;
                break;
            } else if (MyDriver.isElementVisible(employee)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("checkIfListNotContainUpdatedEmployee error beacause: " + employee.getText());
                state = false;
            }
        }
        return state;
    }

    public static void submitAlertMessage() {
        MyDriver.wd.switchTo().alert().accept();
    }

    //Action that clicks on Logout button
    public static void logoutB() {
        MyDriver.clickButtonCheckIfPageLoaded(elLogoutButton,
                "http://cafetownsend-angular-rails.herokuapp.com/login",
                "Employees list page: Logout button is clickable.",
                "Employees list page: Logout button is NOT clickable.",
                "Employees list page do not contain Logout button."
        );
    }

    //Constructor that adds Page Object Pattern to this class
    public static void setPageObject() {
        PageFactory.initElements(MyDriver.wd, new EmployeesListPage());
    }
}
