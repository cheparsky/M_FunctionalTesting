package GUITests.tests;

import GUITests.pages.AddNewEmployeePage;
import GUITests.pages.EditEmployeePage;
import GUITests.pages.EmployeesListPage;
import GUITests.pages.LoginPage;
import GUITests.utils.MyDriver;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUDTest {

    public static WebDriver wd;
    public static int waitTime = 10;
    public static String mainPage = "http://cafetownsend-angular-rails.herokuapp.com";
    public static String employeePage = "http://cafetownsend-angular-rails.herokuapp.com/employees";

    @BeforeClass
    public static void login() {
        wd = MyDriver.runWDriver("Chrome");
        MyDriver.setWebDriverWait(CRUDTest.waitTime);
        MyDriver.setAllPageObject();
        wd.get(mainPage);
        LoginPage.login("Luke", "Skywalker");
        Assert.assertTrue(wd.getCurrentUrl().equals(employeePage));
    }

    @Test
    public void CRUD_01_createEmployeeTest() {
        Assert.assertTrue("Current page is not a list of employee.", MyDriver.ifWWWEqual(employeePage));
        EmployeesListPage.createB();
        AddNewEmployeePage.createNewEmployee();
        Assert.assertTrue(EmployeesListPage.checkIfListContainUpdatedEmployee());
    }

    @Test
    public void CRUD_02_readAndEditEmployeeTest() {
        Assert.assertTrue("Current page is not a list of employee.", MyDriver.ifWWWEqual(employeePage));
        EmployeesListPage.selectEmployee();
        EmployeesListPage.editB();
        EditEmployeePage.updateEmloyee();
        Assert.assertTrue(EmployeesListPage.checkIfListContainUpdatedEmployee());
    }

    @Test
    public void CRUD_03_deleteEmployeeTest() {
        Assert.assertTrue("Current page is not a list of employee.", MyDriver.ifWWWEqual(employeePage));
        Random ran = new Random();
        int x = ran.nextInt(2) + 1;
        if (x == 1) {
            EmployeesListPage.selectEmployee();
            EmployeesListPage.deleteB();
        }
        else {
            EmployeesListPage.selectEmployeeByDoubleClicking();
            EditEmployeePage.deleteB();
        }
        EmployeesListPage.submitAlertMessage();
        EmployeesListPage.logoutB();
        LoginPage.login("Luke", "Skywalker");
        Assert.assertTrue(EmployeesListPage.checkIfListNotContainUpdatedEmployee());
    }

    @AfterClass
    public static void shutDown() {
        wd.quit();
    }
}
