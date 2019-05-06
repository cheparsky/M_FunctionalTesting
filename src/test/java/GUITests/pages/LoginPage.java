package GUITests.pages;

import GUITests.utils.MyDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//*[@id='login-form']/fieldset/label[1]/input")
    public static WebElement lpLoginInput; //login page - login input

    @FindBy(xpath = "//*[@id='login-form']/fieldset/label[2]/input")
    public static WebElement lpPasswordInput; //login page - login input

    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement lpLoginButton; //login page - login button

    // Function that login user
    public static void login(String login, String pass) {
        System.out.println("Process of login process is started.");
        loginI(login);
        passwordI(pass);
        loginB();
    }

    //Action that enter login
    public static void loginI(String login) {
        MyDriver.inputData(lpLoginInput,
                login,
                "Login page: login input is not editable.",
                "Login page do not contain login input.");
    }

    //Action that enter password
    public static void passwordI(String pass) {
        MyDriver.inputData(lpPasswordInput,
                pass,
                "Login page: password input is not editable.",
                "Login page do not contain password input.");
    }

    //Action that click on Login buttn
    public static void loginB() {
        MyDriver.clickButtonCheckIfElementIsVisible(lpLoginButton,
                EmployeesListPage.elCreateButton,
                "Login page: Login button is clickable.",
                "Login page: Login button is NOT clickable.",
                "Login page do not contain Login button.");
    }

    //Constructor that addPage Object Pattern to this class
    public static void setPageObject() {
        PageFactory.initElements(MyDriver.wd, new LoginPage());
    }
}
