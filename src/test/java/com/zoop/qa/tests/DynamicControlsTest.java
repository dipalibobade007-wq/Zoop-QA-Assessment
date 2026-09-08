package com.zoop.qa.tests;

import com.zoop.qa.base.BaseTest;
import com.zoop.qa.pages.DynamicControlsPage;
import com.zoop.qa.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class DynamicControlsTest extends BaseTest {

    @Test(description = "Remove and add a dynamically controlled checkbox")
    public void shouldRemoveAndAddCheckbox() {
        DynamicControlsPage page = new DynamicControlsPage(driver());
        page.open(baseUrl());

        Assert.assertTrue(page.isCheckboxVisible(), "Checkbox should initially be visible");
        page.removeCheckbox();
        Assert.assertFalse(page.isCheckboxVisible(), "Checkbox should disappear after Remove");
        Assert.assertEquals(page.getMessage(), "It's gone!", "Unexpected remove message");

        page.addCheckbox();
        Assert.assertTrue(page.isCheckboxVisible(), "Checkbox should return after Add");
        Assert.assertEquals(page.getMessage(), "It's back!", "Unexpected add message");
    }

    @Test(description = "Enable and disable a text input without fixed waits")
    public void shouldEnableAndDisableInput() {
        DynamicControlsPage page = new DynamicControlsPage(driver());
        page.open(baseUrl());

        Assert.assertFalse(page.isInputEnabled(), "Input should initially be disabled");
        page.enableInput();
        Assert.assertTrue(page.isInputEnabled(), "Input should become enabled");
        Assert.assertEquals(page.getMessage(), "It's enabled!", "Unexpected enable message");

        page.disableInput();
        Assert.assertFalse(page.isInputEnabled(), "Input should become disabled");
        Assert.assertEquals(page.getMessage(), "It's disabled!", "Unexpected disable message");
    }
}
