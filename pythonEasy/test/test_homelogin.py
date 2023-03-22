from pages.homelogin_page import homelogin_page

def test_tcl01(driver):
    expect_msg = "Your Login Pin is invalid."

    login = homelogin_page(driver)
    login.fill_fields("paul@gmail.com", "123", "12345")

    result = login.get_error_msg()

    assert result == expect_msg


def test_tcl02(driver):
    expect_msg = "Your Login Password is invalid."
    login = homelogin_page(driver)
    login.fill_fields("paul@gmail.com", "1234", "1234")

    result = login.get_error_msg()

    assert result == expect_msg

def test_tcl03(driver):
    login = homelogin_page(driver)
    login.fill_fields("paul@gmail.com", "123", "1234")

    result = driver.current_url
    if "home.php" in result:
        res = True

    assert res
