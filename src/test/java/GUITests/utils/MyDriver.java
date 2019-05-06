package GUITests.utils;

import GUITests.pages.AddNewEmployeePage;
import GUITests.pages.EditEmployeePage;
import GUITests.pages.EmployeesListPage;
import GUITests.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyDriver {

    //add all the necessary variables
    public static WebDriver wd;
    private static int timeToWait;
    public static List<String> errList = new ArrayList<String>();
    public static Date currentDate;
    public static String date;

    // We define the function that is responsible for connecting the appropriate driver for our tests
    public static WebDriver runWDriver(String browserName) {

        if (browserName.equals("Firefox")) {
            WebDriverManager.chromedriver().setup();
            wd = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            wd = new ChromeDriver();
            wd.manage().window().maximize();
        }
        return wd;
    }

    // We define functions that are responsible for setting the waiting time for an element
    public static void setWebDriverWait(int timeSec) {
        timeToWait = timeSec;
    }

    // We define functions that is setting Page Object Pattern for our pages
    public static void setAllPageObject(){
        LoginPage.setPageObject();
        EmployeesListPage.setPageObject();
        AddNewEmployeePage.setPageObject();
        EditEmployeePage.setPageObject();
    }

    // We define functions which shows if element is present on page
    public static boolean isElementVisible(WebElement e) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                new WebDriverWait(wd, 20)
                        .until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState")
                                .equals("complete"));
                new WebDriverWait(wd, timeToWait).until(ExpectedConditions.visibilityOf(e));
                boolean result = false;
                result = e.isDisplayed();
                System.out.println("element " + e + " czy widoczny: " + result);
                return result;
            } catch (TimeoutException exx) {
                throw new RuntimeException("Element is not visible. RuntimeException occured on " + e);
            } catch (StaleElementReferenceException es) {
                attempts++;
            }
        }
        if (attempts > 2)
            throw new RuntimeException("Element is not visible. StaleElementReferenceException occured on " + e);
        return false;
    }

    // We define functions which checks if button is present and if is clickable
    public static void clickButton(WebElement element,  //webelement
                                   String successMsg, //Message when button is clickable
                                   String errMsg1,   //Message when button is non-clickable
                                   String errMsg2) {     //Message when is no button

        if (MyDriver.isElementVisible(element)) {
            try {
                element.click();
                System.out.println(successMsg);
            } catch (Exception e) {
                MyDriver.errMessage(errMsg1, e);
            }
        } else {
            MyDriver.errMessage(errMsg2);
        }
    }

    // We define a function that checks if the button is, whether it is clickable and whether it redirects to the appropriate page
    public static void clickButtonCheckIfPageLoaded(WebElement element, //Webelement
                                                    String pageTo,  // The page to which driver must go after pressing the button
                                                    String successMsg, // The message when going to the correct page
                                                    String errMsg1, // Message if you do not go to the correct page, i.e. the button is not clickable
                                                    String errMsg2) { // Message if there is no button on the page

        int urlLenght = pageTo.length();
        if (MyDriver.isElementVisible(element)) {
            try {
                element.click();
                if (wd.getCurrentUrl().substring(0, urlLenght).equals(pageTo)) {
                    System.out.println(successMsg + " Jesteś na stronie: " + wd.getCurrentUrl());
                } else {
                    MyDriver.errMessage(errMsg1 + " Nadal jesteś na stronie: " + wd.getCurrentUrl());
                }
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
            catch (Exception e) {
                MyDriver.errMessage(e + errMsg1 + " Nadal jesteś na stronie: " + wd.getCurrentUrl(), e);
            }
        } else {
            MyDriver.errMessage(errMsg2);
        }
    }

    // Define a function that checks if the button is, is it clickable and whether the target element on the page is shown after clicking
    public static void clickButtonCheckIfElementIsVisible(WebElement element, //Webelement
                                                          WebElement elementCheck,  // Web element with which we check whether the button works
                                                          String successMsg, // Message if elementCheck is on the page
                                                          String errMsg1,// Message if the elementCheck is not on the page, i.e. maybe the button is not clickable
                                                          String errMsg2) { // Message if there is no button on the page

        if (MyDriver.isElementVisible(element)) {
            try {
                element.click();
                if (MyDriver.isElementVisible(elementCheck)) {
                    System.out.println(successMsg);
                } else {
                    MyDriver.errMessage(errMsg1 + " Nadal jesteś na stronie: " + wd.getCurrentUrl());
                }
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
            catch (Exception e) {
                MyDriver.errMessage(errMsg1 + " Nadal jesteś na stronie: " + wd.getCurrentUrl(), e);
            }
        } else {
            MyDriver.errMessage(errMsg2);
        }
    }

    // Define a function that checks if the button is, is it clickable and whether the target element on the page is not shown after clicking
    public static void clickButtonCheckIfElementIsNotVisible(WebElement element, //Webelement
                                                             WebElement elementCheck,  // Web element with which we check whether the button works
                                                             String successMsg, // Message if elementCheck is on the page
                                                             String errMsg1,// Message if the elementCheck is not on the page, i.e. maybe the button is not clickable
                                                             String errMsg2) { // Message if there is no button on the page

        if (MyDriver.isElementVisible(element)) {
            try {
                element.click();
                if (!MyDriver.isElementVisible(elementCheck)) {
                    System.out.println(successMsg);
                } else {
                    MyDriver.errMessage(errMsg1);
                }
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
            catch (Exception e) {
                MyDriver.errMessage(errMsg1, e);
            }
        } else {
            MyDriver.errMessage(errMsg2);
        }
    }

    /// We define a function that checks whether there is input, whether you can enter data in the input and enter data
    public static void inputData(WebElement element, //Webelement
                                 String data, // Text entered to input
                                 String errMsg1,  // Message in case of non-editable input
                                 String errMsg2){	// Message if there is no input

        if (MyDriver.isElementVisible(element)) {
            try {
                element.clear();
                element.sendKeys(data);
                System.out.println("dane: " + data + ", zostały wpisane");
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
            catch (Exception e) {
                MyDriver.errMessage(errMsg1, e);
            }
        } else {
            MyDriver.errMessage(errMsg2);
        }
    }

    // We define a function that checks if there is an input, if you can delete data in the paste and delete data
    public static void clearData(WebElement element, //Webelement
                                 String errMsg1,  // Message in case of non-editable input
                                 String errMsg2){	// Message if there is no input

        if (MyDriver.isElementVisible(element)) {
            try {
                element.clear();
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
            catch (Exception e) {
                MyDriver.errMessage(errMsg1, e);
            }
        } else {
            MyDriver.errMessage(errMsg2);
        }
    }

    // We define functions, whether is a checkbox exist, whether it is clickable and selects it
    public static void checkboxClick(WebElement element,  //Webelement
                                     String errMSg1, // Message in case of correct checkbox state
                                     String errMSg2,  // Message in case of non-clickable checkbox
                                     String errMSg3){  // Message in case of no checkbox

        if (MyDriver.isElementVisible(element)) {
            try {
                if (!element.isSelected()) {
                    element.click();
                } // if checked to = true | if no = false
                else {
                    MyDriver.errMessage(errMSg1);
                }
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd);}
            catch (Exception e) {
                MyDriver.errMessage(errMSg2, e);
            }
        } else {
            MyDriver.errMessage(errMSg3);
        }
    }

    // We define functions, whether is a checkbox exist, whether it is clickable and unchecked
    public static void checkboxUnClick(WebElement element,  //Webelement
                                       String errMSg1, // Message in case of correct checkbox state
                                       String errMSg2,  // Message in case of non-clickable checkbox
                                       String errMSg3){  // Message in case of no checkbox

        if (MyDriver.isElementVisible(element)) {
            try {
                if (!element.isSelected()) {
                    element.click();
                } // if checked to = true | if no = false
                else {
                    MyDriver.errMessage(errMSg1);
                }
            }
            //catch(WebDriverException wex){ WriteCookiesToFile.ReadCookies(wd); }
            catch (Exception e) {
                MyDriver.errMessage(errMSg2, e);
            }
        } else {
            MyDriver.errMessage(errMSg3);
        }
    }

    // Define a function that checks if the given and current pages are the same, if the same is OK
    public static boolean ifWWWEqual(String page) {
        int pageLength = page.length();
        try {
            if (wd.getCurrentUrl().substring(0, pageLength).equals(page)) {
                //System.out.println("Obecna strona nie różni się od żądanej strony - OK. Jesteś na stronie: " + wd.getCurrentUrl());
                return true;
            } else {
                //System.out.println("Obecna strona różni się od żądanej strony. Jesteś na stronie: " + wd.getCurrentUrl());
                return false;
            }
        } catch (Exception e) {
            MyDriver.errMessage("Obecna strona '" + wd.getCurrentUrl() + "' różni się ot żądanej strony '" + page + "'.");
            return false;
        }
    }

    // Define a function that checks if the given and current pages are not the same, if the same is OK
    public static boolean ifWWWNotEqual(String page) {
        int pageLength = page.length();
        try {
            if (wd.getCurrentUrl().substring(0, pageLength).equals(page)) {
                //System.out.println("Obecna strona nie różni się ot żądanej strony. Jesteś na stronie: " + wd.getCurrentUrl());
                return false;
            } else {
                //System.out.println("Obecna strona różni się od żądanej strony - OK. Jesteś na stronie: " + wd.getCurrentUrl());
                return true;
            }
        } catch (Exception e) {
            MyDriver.errMessage("Obecna strona '" + wd.getCurrentUrl() + "' różni się od żądanej strony '" + page + "' (OK), ale jest błąd przy sprawdzeniu URL.");
            return true;
        }
    }

    // We define the function that adds to the bug report tests errors information
    public static void errMessage(String errMesage, Exception ex) {
        System.err.println(errMesage + "\n" + ex);
        currentDate = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
        errList.add(date + " - " + errMesage + "\n");
        String expartone = ex.fillInStackTrace().toString();
        errList.add(date + " - " + expartone + "\n");
        String exparttwo = Arrays.toString(ex.getStackTrace());
        errList.add(date + " - " + exparttwo + "\n");
    }

    // We define the function that adds to the bug report tests errors information
    public static void errMessage(String errMesage) {
        currentDate = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
        errList.add(date + " - " + errMesage + "\n");
        System.err.println(errMesage);
    }

    //Function that highlight element
    public static void highLighterMethod(WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        //Thread.sleep(500);
    }

    // Function that create random string

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String randomString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    // Function that create random date

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
