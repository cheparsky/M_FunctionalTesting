package GUITests.pages;

import GUITests.utils.MyDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewEmployeePage {

    static String firstName = "Luke";
    static String lastName = "Luke";
    static String startDate = "";
    static String email = "";

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.firstName']")
    public static WebElement aneFirstNameInput; //add new employee page - first name input

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.lastName']")
    public static WebElement aneLastNameInput; //add new employee page - last name input

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.startDate']")
    public static WebElement aneStartDateInput; //add new employee page - start date input

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.email']")
    public static WebElement aneEmailInput; //add new employee page - email input

    @FindBy(xpath = "//button[contains(text(),'Add')]")
    public static WebElement aneAddButton; //add new employee page - add button

    //Function that create new rmployee
    public static void createNewEmployee(){
        firstName = "AC_FN_"+MyDriver.randomString(4);
        lastName = "AC_LN_"+MyDriver.randomString(4);
        startDate = MyDriver.createRandomDate(2000, 2019).toString();
        email = "AC_Email"+MyDriver.randomString(4)+"@"+MyDriver.randomString(4);
        firstNameI(firstName);
        lastNameI(lastName);
        startDateI(startDate);
        emailI(email);
        addB();
    }

    //Action that enter first name
    public static void firstNameI(String firstName) {
        MyDriver.inputData(aneFirstNameInput,
                firstName,
                "Add new employee page: first name input is not editable.",
                "Add new employee page do not contain first name input.");
    }

    //Action that enter last name
    public static void lastNameI(String lastName) {
        MyDriver.inputData(aneLastNameInput,
                lastName,
                "Add new employee page: last name input is not editable.",
                "Add new employee page do not contain last name input.");
    }

    //Action that enter start date
    public static void startDateI(String startDate) {
        MyDriver.inputData(aneStartDateInput,
                startDate,
                "Add new employee page: start date input is not editable.",
                "Add new employee page do not contain start date input.");
    }

    //Action that enter email
    public static void emailI(String email) {
        MyDriver.inputData(aneEmailInput,
                email,
                "Add new employee page: email input is not editable.",
                "Add new employee page do not contain email input.");
    }

    //Action that click on Login button
    public static void addB() {
        MyDriver.clickButtonCheckIfElementIsVisible(aneAddButton,
                EmployeesListPage.elCreateButton,
                "Add new employee page: Add button is clickable.",
                "Add new employee page: Add button is NOT clickable.",
                "Add new employee page do not contain Add button.");
    }

    //Constructor that add Page Object Pattern to this class
    public static void setPageObject () {
        PageFactory.initElements(MyDriver.wd, new AddNewEmployeePage());
    }
}
