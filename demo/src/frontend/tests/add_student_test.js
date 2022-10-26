
describe('Title Assertion', () => {

  before(browser => {
    browser.navigateTo('http://localhost:3000');
  });
  /* The following will perform the actual test/assertions */
  it('tests adding students ', async function (browser) {
    browser.assert.title('Amigoscode')
      .click(".ant-btn")
      .waitForElementVisible(".ant-drawer-content-wrapper")
      .setValue("#name", "John")
      .setValue("#email", "Doe@john.com")
      .sendKeys("#gender", browser.Keys.ARROW_DOWN)
      .sendKeys("#gender", browser.Keys.ENTER)
      .sendKeys("#gender", browser.Keys.NULL)
      .click("body > div:nth-child(6) > div > div.ant-drawer-content-wrapper > div > div > div.ant-drawer-body > form > div:nth-child(3) > div > div > div > div > div > button")
      .pause(3000)
      .assert.textEquals("p.ant-scroll-number-only-unit", "2", "test if The number of students is 2")
      .assert.textEquals("tr.ant-table-row:nth-child(3) > td:nth-child(3)", "John", "test if the name is John")
      .assert.textEquals("tr.ant-table-row:nth-child(3) > td:nth-child(4)", "Doe@john.com", "test if email is correct")
      .assert.textEquals("tr.ant-table-row:nth-child(3) > td:nth-child(5)", "MALE", "test if the gender is correct")
      .pause(3000)
      .click("tr.ant-table-row:nth-child(3) > td:nth-child(6) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2)")
      .pause(3000)
      .click("button.ant-btn:nth-child(2) > span:nth-child(1)")
      .pause(3000)
      .assert.textEquals("p.ant-scroll-number-only-unit", "1", "The number of students is 1")
      ;
    // const addStudentButton = await element('button.ant-btn').findElement();
    // await browser.perform(function () {
    //   const actions = this.actions({ async: true });
    //   return actions
    //     .pause(500)

    // })
    ;
  });


  /* The following will always execute after the test suite */
  after(browser => {
    // This is used to close the browser's session
    browser.end();
  });
});