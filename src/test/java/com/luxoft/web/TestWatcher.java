package com.luxoft.web;

import com.luxoft.web.page.HomePage;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestWatcher implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext extensioncontext) {
        Method method = extensioncontext.getRequiredTestMethod();
        if (extensioncontext.getExecutionException().isPresent()) {
            makeScreenshotOnFailure(method.getName());
        }
    }

    @Attachment(value = "{testName} - screenshot", type = "image/png")
    private byte[] makeScreenshotOnFailure(String testName) {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy");
        String sDate = ft.format(new Date());
        byte[] screenshotBytes = new byte[0];
        try {
            File screenshotl = new File("D:\\DISTR\\LUX\\UpSProject\\screenshots\\"
                    + testName + sDate + ".png ");
            screenshotl.delete();
            screenshotBytes = ((TakesScreenshot) HomePage.wd).getScreenshotAs(OutputType.BYTES);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(screenshotBytes));
            ImageIO.write(image, "png", screenshotl);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return screenshotBytes;
    }
}
