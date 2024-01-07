package apps.web;

import data.constants.ApplicationIdentifier;
import data.constants.Platform;
import setup.BaseTest;

public class BasicWebScenario extends BaseTest {

    public BasicWebScenario(ApplicationIdentifier application){
        platform = Platform.WEB;
        this.application = application;
    }

}
