from selenium.webdriver.common.by import By

from pages.dashboard_page import dashboard_page


class homelogin_page:

    def __init__(self, driver):
        self.driver = driver

    def signin_link(self):
        return self.driver.find_element(By.XPATH, "/html/body/div[1]/div/div/div[2]/form/div[6]/p/a")

    def field_email(self):
        return self.driver.find_element(By.XPATH, "//input[@name='email']")

    def field_pass(self):
        return self.driver.find_element(By.XPATH, "//input[@name='password']")

    def field_pin(self):
        return self.driver.find_element(By.XPATH, "//input[@name='pin']")

    def submit_btn(self):
        return self.driver.find_element(By.XPATH, "/html/body/div[1]/div/div/div[2]/form/div[5]/span/button")

    def error_msg(self):
        return self.driver.find_element(By.XPATH, "//div[@class='alert alert-danger']")

    def fill_fields(self, email="", password="", pin=""):
        self.field_email().send_keys(email)
        self.field_pass().send_keys(password)
        self.field_pin().send_keys(pin)
        self.submit_btn().click()

    def get_error_msg(self):
        result = self.error_msg().text
        result = result[2:]
        return result

    def get_dashboardpage(self):
        return dashboard_page(self)
