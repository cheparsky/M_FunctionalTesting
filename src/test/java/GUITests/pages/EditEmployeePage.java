package GUITests.pages;

import GUITests.utils.MyDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditEmployeePage {

    @FindBy(xpath = "//button[contains(text(),'Update')]")
    public static WebElement eeUpdateButton; //edit employee page - edit button

    @FindBy(xpath = "//p[contains(text(),'Delete')]")
    public static WebElement eeDeleteButton; //edit employee page - delete button

    //Function that updates rmployee
    public static void updateEmloyee(){
        AddNewEmployeePage.firstName ="AC_FN_"+MyDriver.randomString(4);
        AddNewEmployeePage.firstNameI(AddNewEmployeePage.firstName);
        AddNewEmployeePage.lastName = "AC_LN_"+MyDriver.randomString(4);
        AddNewEmployeePage.lastNameI(AddNewEmployeePage.lastName);
        AddNewEmployeePage.startDate = MyDriver.createRandomDate(2000, 2019).toString();
        AddNewEmployeePage.startDateI(AddNewEmployeePage.startDate);
        AddNewEmployeePage.email = "AC_Email"+MyDriver.randomString(4)+"@"+MyDriver.randomString(4);
        AddNewEmployeePage.emailI(AddNewEmployeePage.email);
        updateB();
    }

    //Action that clickss on Update button
    public static void updateB() {
        MyDriver.clickButtonCheckIfElementIsVisible(eeUpdateButton,
                EmployeesListPage.elCreateButton,
                "Edit employee page: Update button is clickable.",
                "Edit employee page: Update button is NOT clickable.",
                "Edit employee page do not contain Update button.");
    }

    //Action that clickss on Login button
    public static void deleteB() {
        MyDriver.clickButton(eeDeleteButton,
                "Edit employee page: Add button is clickable.",
                "Edit employee page: Add button is NOT clickable.",
                "Edit employee page do not contain Add button.");
    }

    //Constructor that adds Page Object Pattern to this class
    public static void setPageObject () {
        PageFactory.initElements(MyDriver.wd, new EditEmployeePage());
    }
}
