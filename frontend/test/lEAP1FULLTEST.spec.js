// Generated by Selenium IDE
const { Builder, By, Key, until } = require('selenium-webdriver')
const assert = require('assert')

describe('LEAP 1 FULL TEST', function() {
  this.timeout(30000)
  let driver
  let vars
  beforeEach(async function() {
    driver = await new Builder().forBrowser('chrome').build()
    vars = {}
  })
  afterEach(async function() {
    await driver.quit();
  })
  it('LEAP 1 FULL TEST', async function() {
    await driver.get("http://localhost:3000/")
    await driver.manage().window().setRect(1920, 1053)
    await driver.findElement(By.id("inputEmail")).click()
    await driver.findElement(By.id("inputEmail")).sendKeys("super_admin")
    await driver.findElement(By.id("inputPassword")).sendKeys("super_admin")
    await driver.findElement(By.id("inputPassword")).sendKeys(Key.ENTER)
    await driver.findElement(By.css(".card:nth-child(2) .input-button")).click()
    {
      const element = await driver.findElement(By.css(".card:nth-child(2) .input-button"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.name("environmentName")).click()
    await driver.findElement(By.name("environmentName")).sendKeys("env1")
    await driver.findElement(By.name("environmentName")).sendKeys(Key.ENTER)
    await driver.findElement(By.css(".card-deck:nth-child(1) > .card:nth-child(1) .card-title")).click()
    {
      const element = await driver.findElement(By.css(".card-deck:nth-child(1) > .card:nth-child(1) .card-title"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".btn")).click()
    await driver.findElement(By.id("capabilityName")).click()
    await driver.findElement(By.id("capabilityName")).sendKeys("capability 1 TEST")
    {
      const dropdown = await driver.findElement(By.id("paceOfChange"))
      await dropdown.findElement(By.xpath("//option[. = 'True']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("informationQuality"))
      await dropdown.findElement(By.xpath("//option[. = '1']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("TOM"))
      await dropdown.findElement(By.xpath("//option[. = 'TOM']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("applicationFit"))
      await dropdown.findElement(By.xpath("//option[. = '1']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("resourcesQuality"))
      await dropdown.findElement(By.xpath("//option[. = '1']")).click()
    }
    await driver.findElement(By.css(".btn-secondary")).click()
    {
      const element = await driver.findElement(By.css(".btn-secondary"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    {
      const element = await driver.findElement(By.CSS_SELECTOR, "body")
      await driver.actions({ bridge: true }).moveToElement(element, 0, 0).perform()
    }
    await driver.findElement(By.id("validityPeriod")).click()
    await driver.findElement(By.id("validityPeriod")).sendKeys("2021-05-11")
    await driver.findElement(By.css(".btn:nth-child(4)")).click()
    {
      const element = await driver.findElement(By.css(".btn:nth-child(4)"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    {
      const element = await driver.findElement(By.CSS_SELECTOR, "body")
      await driver.actions({ bridge: true }).moveToElement(element, 0, 0).perform()
    }
    await driver.findElement(By.css(".btn:nth-child(1)")).click()
    {
      const dropdown = await driver.findElement(By.id("statusId"))
      await dropdown.findElement(By.xpath("//option[. = '2021-05-11']")).click()
    }
    {
      const element = await driver.findElement(By.id("statusId"))
      await driver.actions({ bridge: true }).moveToElement(element).clickAndHold().perform()
    }
    {
      const element = await driver.findElement(By.id("statusId"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    {
      const element = await driver.findElement(By.id("statusId"))
      await driver.actions({ bridge: true }).moveToElement(element).release().perform()
    }
    await driver.findElement(By.id("statusId")).click()
    await driver.findElement(By.css(".btn-primary")).click()
    {
      const element = await driver.findElement(By.css(".btn-primary"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".btn-primary")).click()
    await driver.findElement(By.id("capabilityName")).click()
    await driver.findElement(By.id("capabilityName")).sendKeys("capability 2")
    {
      const dropdown = await driver.findElement(By.id("parentCapability"))
      await dropdown.findElement(By.xpath("//option[. = 'capability 1 TEST']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("level"))
      await dropdown.findElement(By.xpath("//option[. = 'TWO']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("paceOfChange"))
      await dropdown.findElement(By.xpath("//option[. = 'True']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("informationQuality"))
      await dropdown.findElement(By.xpath("//option[. = '1']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("TOM"))
      await dropdown.findElement(By.xpath("//option[. = 'TOM']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("applicationFit"))
      await dropdown.findElement(By.xpath("//option[. = '1']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("resourcesQuality"))
      await dropdown.findElement(By.xpath("//option[. = '1']")).click()
    }
    {
      const dropdown = await driver.findElement(By.id("statusId"))
      await dropdown.findElement(By.xpath("//option[. = '2021-05-11']")).click()
    }
    await driver.findElement(By.css(".btn-primary")).click()
    {
      const element = await driver.findElement(By.css(".btn-primary"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    {
      const element = await driver.findElement(By.css(".MuiTableCell-body > .MuiButtonBase-root"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".MuiTableCell-body > .MuiButtonBase-root")).click()
    {
      const element = await driver.findElement(By.CSS_SELECTOR, "body")
      await driver.actions({ bridge: true }).moveToElement(element, 0, 0).perform()
    }
    await driver.findElement(By.linkText("env1")).click()
    await driver.findElement(By.css(".card-deck:nth-child(3) > .card:nth-child(3) img")).click()
    await driver.findElement(By.id("validityPeriod")).click()
    await driver.findElement(By.id("validityPeriod")).sendKeys("2021-07-02")
    await driver.findElement(By.css(".btn")).click()
    {
      const element = await driver.findElement(By.css(".btn"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".MuiTableRow-root:nth-child(1) .bi-pencil")).click()
    await driver.findElement(By.id("validityPeriod")).click()
    await driver.findElement(By.id("validityPeriod")).sendKeys("2021-02-08")
    await driver.findElement(By.css(".btn")).click()
    {
      const element = await driver.findElement(By.css(".btn"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.linkText("env1")).click()
    await driver.findElement(By.css(".card-deck:nth-child(1) > .card:nth-child(1) .card-title")).click()
    {
      const element = await driver.findElement(By.css(".MuiTableCell-root > .MuiButtonBase-root > .MuiIconButton-label > .material-icons"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".MuiTableCell-root > .MuiButtonBase-root > .MuiIconButton-label > .material-icons")).click()
    {
      const element = await driver.findElement(By.CSS_SELECTOR, "body")
      await driver.actions({ bridge: true }).moveToElement(element, 0, 0).perform()
    }
    await driver.findElement(By.css(".MuiTableRow-root:nth-child(2) .bi-pencil")).click()
    await driver.executeScript("window.scrollTo(0,0)")
    await driver.findElement(By.id("capabilityName")).click()
    await driver.findElement(By.id("capabilityName")).sendKeys("capability 2 PART 2")
    await driver.findElement(By.css(".btn")).click()
    {
      const element = await driver.findElement(By.css(".MuiTableCell-root > .MuiButtonBase-root > .MuiIconButton-label > .material-icons"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".MuiTableCell-root > .MuiButtonBase-root > .MuiIconButton-label > .material-icons")).click()
    {
      const element = await driver.findElement(By.CSS_SELECTOR, "body")
      await driver.actions({ bridge: true }).moveToElement(element, 0, 0).perform()
    }
    await driver.findElement(By.css(".MuiTableRow-root:nth-child(1) .bi-trash")).click()
    await driver.findElement(By.css(".btn-sm:nth-child(1)")).click()
    await driver.findElement(By.linkText("env1")).click()
    await driver.findElement(By.linkText("Home")).click()
    await driver.findElement(By.css(".card:nth-child(1) .input-button")).click()
    {
      const element = await driver.findElement(By.css(".card:nth-child(1) .input-button"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.css(".btn-primary")).click()
    await driver.findElement(By.id("username")).click()
    await driver.findElement(By.id("username")).sendKeys("wannes")
    await driver.findElement(By.id("email")).sendKeys("wannes")
    await driver.findElement(By.css(".btn")).click()
    {
      const element = await driver.findElement(By.css(".btn"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.linkText("Home")).click()
    await driver.findElement(By.css(".card:nth-child(3) .input-button")).click()
    {
      const element = await driver.findElement(By.css(".card:nth-child(3) .input-button"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
    await driver.findElement(By.linkText("env1")).click()
    {
      const element = await driver.findElement(By.linkText("env1"))
      await driver.actions({ bridge: true }).moveToElement(element).perform()
    }
  })
})
